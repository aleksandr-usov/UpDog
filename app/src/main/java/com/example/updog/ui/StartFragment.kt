package com.example.updog.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.updog.R
import com.example.updog.data.repo.model.DogImageRepoModel
import com.example.updog.data.repo.model.DogRepoModel

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Breeds"
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    interface OnChooseBreedClickListener {
        fun onItemClick(newlySelected: DogRepoModel)
    }
}