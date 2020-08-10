package com.example.updog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.updog.R
import com.example.updog.data.repo.model.DogModel
import com.example.updog.ui.adapters.SubbreedsAdapter
import com.example.updog.ui.viewmodel.MainViewModel

class SubbreedFragment : Fragment() {

    private val mainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    private lateinit var subbreedsRecyclerView: RecyclerView

    private val listener: OnChooseSubbreedClickListener = object :
        OnChooseSubbreedClickListener {
        override fun onItemClick(newlySelected: DogModel) {
            mainViewModel.onSubbreedClicked(newlySelected)
            findNavController().navigate(R.id.action_subbreedFragment_to_imageFragment)
        }
    }

    private val allSubbreedsAdapter = SubbreedsAdapter(listener)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subbreed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initLiveData()
    }

    private fun initViews(view: View) {
        with(view) {
            subbreedsRecyclerView = findViewById(R.id.rv_subbreeds_list)
        }
        subbreedsRecyclerView.adapter = allSubbreedsAdapter
    }

    private fun initLiveData() {
        mainViewModel.allSubbreeds.observe(viewLifecycleOwner, Observer {
            allSubbreedsAdapter.setItems(it)
        })
    }

    interface OnChooseSubbreedClickListener {
        fun onItemClick(newlySelected: DogModel)
    }
}