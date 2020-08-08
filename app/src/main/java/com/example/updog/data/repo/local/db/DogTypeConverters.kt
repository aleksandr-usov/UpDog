package com.example.updog.data.repo.local.db

import androidx.room.TypeConverter
import com.example.updog.data.repo.model.DogImageRepoModel
import com.example.updog.data.repo.model.DogRepoModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DogTypeConverters {
    private var gson = Gson()

    @TypeConverter
    fun stringToSubbreeds(data: String?): List<DogRepoModel?>? {
        if (data == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<DogRepoModel?>?>() {}.type
        return gson.fromJson<List<DogRepoModel?>>(data, listType)
    }

    @TypeConverter
    fun subbreedsToString(subbreeds: List<DogRepoModel?>?): String? {
        return gson.toJson(subbreeds)
    }

    @TypeConverter
    fun stringToDogImages(data: String?): List<DogImageRepoModel?>? {
        if (data == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<DogImageRepoModel?>?>() {}.type
        return gson.fromJson<List<DogImageRepoModel?>>(data, listType)
    }

    @TypeConverter
    fun dogImagesToString(dogImages: List<DogImageRepoModel?>?): String? {
        return gson.toJson(dogImages)
    }
}
