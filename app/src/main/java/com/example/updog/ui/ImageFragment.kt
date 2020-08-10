package com.example.updog.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.updog.R
import com.example.updog.ui.adapters.ImagesAdapter
import com.example.updog.ui.viewmodel.MainViewModel


class ImageFragment : Fragment() {

    private val mainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    private lateinit var imagesRecyclerView: RecyclerView

    private val toolbar by lazy { requireView().findViewById<androidx.appcompat.widget.Toolbar>(R.id.tb_images_toolbar) }

    private val progressBar by lazy { requireView().findViewById<ProgressBar>(R.id.pb_images) }

    private val imagesAdapter = ImagesAdapter()

    var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val sendIntent = Intent()

        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Check out this dog: $url"
        )
        sendIntent.type = "text/plain"
        this.startActivity(sendIntent)
        return true
    }

    private fun initViews(view: View) {
        with(view) {
            imagesRecyclerView = findViewById(R.id.rv_images_list)
        }
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(imagesRecyclerView)
        imagesRecyclerView.adapter = imagesAdapter

        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        val messageReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                url = intent?.getStringExtra("url").toString()
            }
        }

        LocalBroadcastManager.getInstance(requireActivity()).registerReceiver(
            messageReceiver,
            IntentFilter("custom-message")
        )
    }

    private fun initLiveData() {
        mainViewModel.allImages.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                progressBar.visibility = View.GONE
            }
            imagesAdapter.setItems(it.imageUrls)
        })

        mainViewModel.selectedBreed.observe(viewLifecycleOwner, Observer {
            mainViewModel.loadImages(it)
            toolbar.title = it.name.capitalize()
        })
    }
}