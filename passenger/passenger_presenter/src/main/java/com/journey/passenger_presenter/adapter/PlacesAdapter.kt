package com.journey.passenger_presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.journey.common_utils.other.type.sendOneParametreBlock
import com.journey.passenger_domen.model.PlaceData
import com.journey.passenger_presenter.databinding.ItemPlaceBinding

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 16/11/2022.
 */
class PlacesAdapter() : RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {
    var differ = AsyncListDiffer(this, ITEM_DIFF)
        private set
    private var itemSelect: sendOneParametreBlock<Int>? = null

    fun setItemClick(block: sendOneParametreBlock<Int>) {
        itemSelect = block
    }


    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<PlaceData>() {
            override fun areItemsTheSame(oldItem: PlaceData, newItem: PlaceData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PlaceData, newItem: PlaceData): Boolean =
                oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(differ.currentList[position])

    override fun getItemCount(): Int = differ.currentList.size


    inner class ViewHolder(private var binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.item.setOnClickListener {
                itemSelect?.invoke(differ.currentList[absoluteAdapterPosition].id)
            }
        }

        fun bind(item: PlaceData) {
            binding.image.setImageResource(item.picture)
            binding.name.text = item.name
        }
    }
}