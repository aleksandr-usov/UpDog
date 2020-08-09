package com.example.updog.di

import com.example.updog.UpDogApplication
import com.example.updog.ui.BreedFragment
import com.example.updog.ui.SubbreedFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class
    ]
)

@Singleton
interface AppComponent {
    fun inject(breedFragment: BreedFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: UpDogApplication): Builder
        fun build(): AppComponent
    }
}