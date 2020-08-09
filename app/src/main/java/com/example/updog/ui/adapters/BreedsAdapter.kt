package com.example.updog.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.updog.R
import com.example.updog.data.repo.model.DogModel
import com.example.updog.ui.BreedFragment
import kotlinx.android.synthetic.main.breed_list_element.view.*
import java.lang.ref.WeakReference

class BreedsAdapter(
    val listener: BreedFragment.OnChooseBreedClickListener
) : RecyclerView.Adapter<BreedsAdapter.BreedViewHolder>() {

    private val allBreeds = mutableListOf<DogModel>()

    fun setItems(newItems: List<DogModel>) {
        allBreeds.clear()
        allBreeds.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BreedViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.breed_list_element, parent, false)
        return BreedViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val item = allBreeds[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return allBreeds.size
    }

    inner class BreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var item: WeakReference<DogModel>
        private val textViewBreed = itemView.tv_breed_name
        private val textViewSubbreeds = itemView.tv_subbreed_count

        fun bind(item: DogModel) {
            this.item = WeakReference(item)

            textViewBreed.text = item.name
            when {
                item.subbreeds.size > 1 -> {
                    textViewSubbreeds.text = "(${item.subbreeds.size} subbreeds)"
                }
                item.subbreeds.size == 1 -> {
                    textViewSubbreeds.text = "(1 subbreed)"
                }
                else -> {
                    textViewSubbreeds.text =  ""
                }
            }

            itemView.setOnClickListener { listener.onItemClick(item) }
        }
    }
}