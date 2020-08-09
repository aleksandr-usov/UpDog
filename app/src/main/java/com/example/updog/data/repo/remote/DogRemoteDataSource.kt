package com.example.updog.data.repo.remote

import com.example.updog.data.api.model.DogImageResponse
import com.example.updog.data.repo.model.DogModel
import io.reactivex.Single
import org.json.JSONObject
import javax.inject.Inject

class DogRemoteDataSource @Inject constructor(
    private val upDogService: UpDogService
) {

    fun getAllDogs(): Single<List<DogModel>> = upDogService.getAllBreeds().map {
        val jsonString = it.body()?.string() ?: return@map listOf<DogModel>()
        val jsonObject = JSONObject(jsonString)
        val breedsObject = jsonObject["message"] as JSONObject
        val breedNamesArray = breedsObject.names() ?: throw RuntimeException()
        val breedsList = mutableListOf<DogModel>()
        for(i in 0 until breedNamesArray.length()) {
            val breedName = breedNamesArray[i] as String
            val subbreedsArray = breedsObject.getJSONArray(breedName)
            val subbreedsObjects = mutableListOf<DogModel>()
            for (j in 0 until subbreedsArray.length()) {
                val subbreedName = subbreedsArray.get(j) as String
                subbreedsObjects += DogModel(
                    name = subbreedName,
                    subbreeds = emptyList(),
                    parentName = breedName
                )
            }
            breedsList += DogModel(
                breedName,
                subbreedsObjects
            )
        }
        return@map breedsList
    }

    fun getAllDogImages(
        breed: String
    ): Single<List<DogImageResponse>> = upDogService.getAllImagesByBreed(breed)
}