package com.example.updog.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.updog.R
import com.example.updog.data.repo.model.DogModel
import com.example.updog.ui.SubbreedFragment
import kotlinx.android.synthetic.main.breed_list_element.view.*
import kotlinx.android.synthetic.main.subbreed_list_element.view.*
import java.lang.ref.WeakReference

class SubbreedsAdapter(
    val listener: SubbreedFragment.OnChooseSubbreedClickListener
) : RecyclerView.Adapter<SubbreedsAdapter.SubbreedViewHolder>() {

    private val allSubbreeds = mutableListOf<DogModel>()

    fun setItems(newItems: List<DogModel>) {
        allSubbreeds.clear()
        allSubbreeds.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubbreedViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.subbreed_list_element, parent, false)
        return SubbreedViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubbreedViewHolder, position: Int) {
        val item = allSubbreeds[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return allSubbreeds.size
    }

    inner class SubbreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var item: WeakReference<DogModel>
        private val textViewSubbreed = itemView.tv_subbreed_name

        fun bind(item: DogModel) {
            this.item = WeakReference(item)

            textViewSubbreed.text = item.name

            itemView.setOnClickListener { listener.onItemClick(item) }
        }
    }
}