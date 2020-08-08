package com.example.updog.data.repo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "dogs"
)
data class DogRepoModel(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    @ColumnInfo(name = "sub_breeds")
    val subbreeds: List<DogRepoModel>,
    val images: List<DogImageRepoModel>,
    val parentName: String = ""
)