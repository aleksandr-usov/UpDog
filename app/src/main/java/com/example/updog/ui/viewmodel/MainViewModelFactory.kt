package com.example.updog.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.updog.data.repo.UpDogRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val upDogRepository: UpDogRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(upDogRepository) as T
    }
}