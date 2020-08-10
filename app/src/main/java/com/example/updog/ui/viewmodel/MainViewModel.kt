package com.example.updog.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.updog.data.api.model.DogImages
import com.example.updog.data.repo.UpDogRepository
import com.example.updog.data.repo.model.DogModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel constructor(
    private val upDogRepository: UpDogRepository
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _allBreeds = MutableLiveData<List<DogModel>>()
    val allBreeds: LiveData<List<DogModel>> = _allBreeds

    private var _allSubbreeds = MutableLiveData<List<DogModel>>()
    var allSubbreeds: LiveData<List<DogModel>> = _allSubbreeds

    private val _allImages = MutableLiveData<DogImages>()
    val allImages: LiveData<DogImages> = _allImages

    private val _selectedBreed = MutableLiveData<DogModel>()
    val selectedBreed: LiveData<DogModel> = _selectedBreed

    init {
        getAllDogs()
    }

    private fun getAllDogs() {
        disposables.add(
            upDogRepository.getAllDogs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _allBreeds.value = it
                }, {
                    it.printStackTrace()
                })
        )
    }

    fun loadImages(dogModel: DogModel) {
        if (dogModel.parentName.isEmpty()) {
            disposables.add(
                upDogRepository.getAllImages(dogModel.name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _allImages.value = it
                    }, {
                        it.printStackTrace()
                    })
            )
        } else {
            disposables.add(
                upDogRepository.getAllImagesBySubbreed(dogModel.parentName, dogModel.name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _allImages.value = it
                    }, {
                        it.printStackTrace()
                    })
            )
        }
    }

    fun onBreedClicked(selectedBreed: DogModel) {
        _selectedBreed.value = selectedBreed
    }

    fun onBreedWithSubbreedsClicked(selectedBreed: DogModel) {
        _allSubbreeds.value = selectedBreed.subbreeds
    }

    fun onSubbreedClicked(selectedBreed: DogModel) {
        _selectedBreed.value = selectedBreed
    }
}