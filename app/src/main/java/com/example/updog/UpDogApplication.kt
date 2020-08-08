package com.example.updog

import android.app.Application
import com.example.updog.di.AppComponent
import com.example.updog.di.DaggerAppComponent
import com.facebook.stetho.Stetho

class UpDogApplication : Application() {
    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        component = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}