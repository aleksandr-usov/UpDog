package com.example.updog.di

import android.content.Context
import androidx.room.Room
import com.example.updog.UpDogApplication
import com.example.updog.data.repo.local.db.DogDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
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

            return builder.build()
        }
    }
}