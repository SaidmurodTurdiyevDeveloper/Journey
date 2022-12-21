package com.example.myjourney.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.text.toUpperCase
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myjourney.databinding.ItemPlaceSearchBinding
import com.example.myjourney.domen.model.PlaceData
import com.example.myjourney.other.type.sendOneParametreBlock
import java.util.*

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 16/11/2022.
 */
class PlaceSearchAdapter() : RecyclerView.Adapter<PlaceSearchAdapter.ViewHolder>() {
    private var differ = AsyncListDiffer(this, ITEM_DIFF)
    private var defaultList: List<PlaceData> = emptyList()
    private var itemSelect: sendOneParametreBlock<Int>? = null
    fun submitList(ls: List<PlaceData>) {
        differ.submitList(ls.toMutableList())
        defaultList = differ.currentList
    }

    fun setItemClick(block: sendOneParametreBlock<Int>) {
        itemSelect = block
    }

    fun filter(text: String) {
        val newList = defaultList.filter {
            it.name.uppercase().startsWith(text.uppercase())
        }
        differ.submitList(newList.toMutableList())
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
        val binding = ItemPlaceSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(differ.currentList[position])

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private var binding: ItemPlaceSearchBinding) : RecyclerView.ViewHolder(binding.root) {
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