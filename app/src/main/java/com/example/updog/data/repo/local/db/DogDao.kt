package com.example.updog.data.repo.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.updog.data.repo.model.DogRepoModel
import io.reactivex.Flowable

@Dao
interface DogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDogs(dogs: List<DogRepoModel>)

    @Query("SELECT * FROM dogs")
    fun getAllDogs(): Flowable<List<DogRepoModel>>
}