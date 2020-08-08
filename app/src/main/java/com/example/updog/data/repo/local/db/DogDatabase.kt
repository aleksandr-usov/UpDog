package com.example.updog.data.repo.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.updog.data.repo.model.DogImageRepoModel
import com.example.updog.data.repo.model.DogRepoModel

@Database(
    entities = [DogRepoModel::class, DogImageRepoModel::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(DogTypeConverters::class)
abstract class DogDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao
    abstract fun dogImageDao(): DogImageDao
}