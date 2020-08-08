package com.example.updog.data.repo.local

import com.example.updog.data.repo.local.db.DogDatabase
import com.example.updog.data.repo.model.DogImageRepoModel
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogImageLocalDataSource @Inject constructor(
    private val database: DogDatabase
) {
    fun insertDogImages(dogImages: List<DogImageRepoModel>) =
        database.dogImageDao().insertDogImages(dogImages)

    fun getAllDogImages(): Flowable<List<DogImageRepoModel>> =
        database.dogImageDao().getAllDogImages()
}