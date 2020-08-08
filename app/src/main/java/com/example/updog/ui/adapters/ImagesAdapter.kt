package com.example.updog.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.updog.R
import com.example.updog.data.repo.model.DogImageRepoModel
import java.lang.ref.WeakReference

class ImagesAdapter() : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    private val images = mutableListOf<DogImageRepoModel>()

    fun setItems(newItems: List<DogImageRepoModel>) {
        images.clear()
        images.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.image_list_element, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var item: WeakReference<DogImageRepoModel>

        fun bind(item: DogImageRepoModel) {
            this.item = WeakReference(item)

            //TODO: How do we get images here

        }
    }
}