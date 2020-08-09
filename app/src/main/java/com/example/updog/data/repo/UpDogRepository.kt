package com.example.updog.data.repo

import com.example.updog.data.repo.local.DogLocalDataSource
import com.example.updog.data.repo.model.DogImageModel
import com.example.updog.data.repo.model.DogModel
import com.example.updog.data.repo.remote.DogRemoteDataSource
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpDogRepository @Inject constructor(
    private val dogLocalDataSource: DogLocalDataSource,
    private val dogRemoteDataSource: DogRemoteDataSource
) {
    fun getAllDogs(): Single<List<DogModel>> {
        return dogLocalDataSource.getAllDogs()
            .flatMap {
                if (it.isEmpty()) {
                    dogRemoteDataSource.getAllDogs()
                        .flatMap { dogs ->
                            dogLocalDataSource
                                .insertDogs(dogs)
                                .toSingle<List<DogModel>> { dogs }
                        }
                } else {
                    Single.just(it)
                }
            }
    }

    fun getAllImages(): Single<List<DogImageModel>> {
        return dogLocalDataSource.getAllDogImages()
    }
}
