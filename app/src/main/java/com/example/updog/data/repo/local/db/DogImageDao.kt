package com.example.updog.data.repo.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.updog.data.repo.model.DogImageRepoModel
import io.reactivex.Flowable

@Dao
interface DogImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDogImages(task: List<DogImageRepoModel>)

    @Query("SELECT * FROM dog_images")
    fun getAllDogImages(): Flowable<List<DogImageRepoModel>>
}