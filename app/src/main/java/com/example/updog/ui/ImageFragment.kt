package com.example.updog.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.updog.R
import com.example.updog.data.repo.model.DogModel
import com.example.updog.ui.adapters.ImagesAdapter
import com.example.updog.ui.adapters.SubbreedsAdapter
import com.example.updog.ui.viewmodel.MainViewModel

class ImageFragment : Fragment() {

    private val mainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    private lateinit var imagesRecyclerView: RecyclerView

    private val imagesAdapter = ImagesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initLiveData()
    }

    private fun initViews(view: View) {
        with(view) {
            imagesRecyclerView = findViewById(R.id.rv_images_list)
        }
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(imagesRecyclerView)
        imagesRecyclerView.adapter = imagesAdapter
    }

    private fun initLiveData() {
        mainViewModel.allImages.observe(viewLifecycleOwner, Observer {
            imagesAdapter.setItems(it.imageUrls)
        })

        mainViewModel.selectedBreed.observe(viewLifecycleOwner, Observer {
            mainViewModel.loadImages(it)
        })
    }
}