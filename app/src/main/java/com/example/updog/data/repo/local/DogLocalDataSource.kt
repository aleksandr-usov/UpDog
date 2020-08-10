package com.example.updog.data.repo.local

import com.example.updog.data.repo.local.db.DogDatabase
import com.example.updog.data.repo.model.DogModel
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
}