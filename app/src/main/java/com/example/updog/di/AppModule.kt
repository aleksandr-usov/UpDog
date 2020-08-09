package com.example.updog.di

import android.content.Context
import androidx.room.Room
import com.example.updog.UpDogApplication
import com.example.updog.data.repo.local.db.DogDatabase
import com.example.updog.data.repo.remote.UpDogService
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds
    abstract fun provideDaggerApplication(application: UpDogApplication): Context

    @Module
    companion object {
        @Provides
        @Singleton
        @JvmStatic
        fun provideDatabase(context: Context): DogDatabase {
            val builder = Room.databaseBuilder(
                context,
                DogDatabase::class.java,
                "dog_database"
            )
                .fallbackToDestructiveMigration()
            return builder.build()
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideService(): UpDogService {
            val client = OkHttpClient.Builder()
            client.addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client.build())
                .baseUrl(UpDogService.BASE_URL)
                .build()

            return retrofit.create(UpDogService::class.java)
        }
    }
}