package com.example.updog.data.repo.remote

import com.example.updog.data.api.model.DogImageResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UpDogService {
    companion object {
        const val BASE_URL = "https://dog.ceo/api/"
    }

    @GET("breeds/list/all")
    fun getAllBreeds(): Single<Response<ResponseBody>>

    @GET("breed/{breed}/images")
    fun getAllImagesByBreed(@Path(value = "breed") breed: String): Single<List<DogImageResponse>>
}