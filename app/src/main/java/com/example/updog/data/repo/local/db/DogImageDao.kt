package com.example.updog.data.repo.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.updog.data.repo.model.DogImageModel
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface DogImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDogImages(task: List<DogImageModel>)

    @Query("SELECT * FROM dog_images")
    fun getAllDogImages(): Single<List<DogImageModel>>
}