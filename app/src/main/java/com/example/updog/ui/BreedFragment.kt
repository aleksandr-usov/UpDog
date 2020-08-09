package com.example.updog.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.example.updog.R
import com.example.updog.UpDogApplication
import com.example.updog.data.repo.model.DogModel
import com.example.updog.ui.adapters.BreedsAdapter
import com.example.updog.ui.viewmodel.MainViewModel
import com.example.updog.ui.viewmodel.MainViewModelFactory
import javax.inject.Inject

class BreedFragment : Fragment() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val mainViewModel by lazy {
        ViewModelProvider(requireActivity(), mainViewModelFactory).get(MainViewModel::class.java)
    }

    private lateinit var dogsRecyclerView: RecyclerView

    private val listener: OnChooseBreedClickListener = object :
        OnChooseBreedClickListener {
        override fun onItemClick(newlySelected: DogModel) {
            if (newlySelected.subbreeds.isNotEmpty()) {
                mainViewModel.onBreedWithSubbreedsClicked(newlySelected)
                findNavController().navigate(R.id.action_breedFragment_to_subbreedFragment)
            } else {
                //TODO: go to pictures
            }
        }
    }

    private val allDogsAdapter = BreedsAdapter(listener)

    override fun onCreate(savedInstanceState: Bundle?) {
        UpDogApplication.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_breed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initLiveData()
    }

    private fun initViews(view: View) {
        with(view) {
            dogsRecyclerView = findViewById(R.id.rv_dogs_list)
        }
        dogsRecyclerView.adapter = allDogsAdapter
    }

    private fun initLiveData() {
        mainViewModel.allBreeds.observe(viewLifecycleOwner, Observer {
            allDogsAdapter.setItems(it)
        })

        mainViewModel.allPictures.observe(viewLifecycleOwner, Observer {
            Log.d("TAG111", it.toString())
        })
    }

    interface OnChooseBreedClickListener {
        fun onItemClick(newlySelected: DogModel)
    }
}