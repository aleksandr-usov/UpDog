package com.example.updog.data.repo.local

import com.example.updog.data.repo.local.db.DogDatabase
import com.example.updog.data.repo.model.DogImageModel
import com.example.updog.data.repo.model.DogModel
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogLocalDataSource @Inject constructor(
    private val database: DogDatabase
) {
    fun insertDogs(dogs: List<DogModel>) = database.dogDao().insertDogs(dogs)

    fun getAllDogs(): Single<List<DogModel>> =
        database.dogDao().getAllDogs()

    fun insertDogImages(dogImages: List<DogImageModel>) =
        database.dogImageDao().insertDogImages(dogImages)

    fun getAllDogImages(): Single<List<DogImageModel>> =
        database.dogImageDao().getAllDogImages()
}