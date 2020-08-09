package com.example.updog.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.updog.data.api.model.DogImageResponse
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

    private val _allPictures = MutableLiveData<List<DogImageResponse>>()
    val allPictures: LiveData<List<DogImageResponse>> = _allPictures


    init {
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

        disposables.add(
            upDogRepository.getAllImages("akita")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _allPictures.value = it
                }, {
                    it.printStackTrace()
                })
        )
    }

    fun onBreedWithSubbreedsClicked(selectedBreed: DogModel) {
        _allSubbreeds.value = selectedBreed.subbreeds
    }
}