package com.example.updog.data.repo.local.db

import androidx.room.TypeConverter
import com.example.updog.data.repo.model.DogModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DogTypeConverters {
    private var gson = Gson()

    @TypeConverter
    fun stringToSubbreeds(data: String?): List<DogModel?>? {
        if (data == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<DogModel?>?>() {}.type
        return gson.fromJson<List<DogModel?>>(data, listType)
    }

    @TypeConverter
    fun subbreedsToString(subbreeds: List<DogModel?>?): String? {
        return gson.toJson(subbreeds)
    }
}
