package com.example.updog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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
                mainViewModel.onBreedClicked(newlySelected)
                findNavController().navigate(R.id.action_breedFragment_to_imageFragment)
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
        initLiveData()
        initViews(view)
    }

    private fun initViews(view: View) {
        with(view) {
            dogsRecyclerView = findViewById(R.id.rv_dogs_list)
        }
        dogsRecyclerView.adapter = allDogsAdapter

        val dividerItemDecoration = DividerItemDecoration(
            dogsRecyclerView.context,
            LinearLayoutManager.VERTICAL
        )
        dogsRecyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun initLiveData() {
        mainViewModel.allBreeds.observe(viewLifecycleOwner, Observer {
            allDogsAdapter.setItems(it)
        })
    }

    interface OnChooseBreedClickListener {
        fun onItemClick(newlySelected: DogModel)
    }
}