package com.example.updog.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.updog.R
import com.example.updog.UpDogApplication
import com.example.updog.data.repo.UpDogRepository
import com.example.updog.data.repo.model.DogModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StartFragment : Fragment() {

    @Inject
    lateinit var upDogRepository: UpDogRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        UpDogApplication.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Breeds"
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result = upDogRepository.getAllDogs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("TAG", it.toString())
                }, {
                    it.printStackTrace()
                })
    }

    interface OnChooseBreedClickListener {
        fun onItemClick(newlySelected: DogModel)
    }
}