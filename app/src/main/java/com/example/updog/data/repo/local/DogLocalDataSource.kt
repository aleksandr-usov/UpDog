package com.example.updog.data.repo.local

import com.example.updog.data.repo.local.db.DogDatabase
import com.example.updog.data.repo.model.DogRepoModel
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogLocalDataSource @Inject constructor(
    private val database: DogDatabase
) {
    fun insertDogs(dogs: List<DogRepoModel>) = database.dogDao().insertDogs(dogs)

    fun getAllDogs(): Flowable<List<DogRepoModel>> =
        database.dogDao().getAllDogs()
}