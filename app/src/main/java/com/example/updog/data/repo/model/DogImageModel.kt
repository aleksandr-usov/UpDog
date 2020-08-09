package com.example.updog.data.repo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "dog_images",
    foreignKeys = [
        ForeignKey(
            entity = DogModel::class,
            parentColumns = ["name"],
            childColumns = ["parent_dog"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DogImageModel(
    @ColumnInfo(name = "parent_dog")
    val parentDogName: String,
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val isLiked: Boolean = false
)