package com.example.updog.data.repo.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.updog.data.repo.model.DogModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface DogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDogs(dogs: List<DogModel>): Completable

    @Query("SELECT * FROM dogs")
    fun getAllDogs(): Single<List<DogModel>>
}