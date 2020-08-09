package com.example.updog.data.repo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "dogs"
)
data class DogModel(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    @ColumnInfo(name = "sub_breeds")
    val subbreeds: List<DogModel>,
    val parentName: String = ""
)