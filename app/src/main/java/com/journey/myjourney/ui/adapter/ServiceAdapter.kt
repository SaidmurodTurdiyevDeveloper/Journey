package com.journey.myjourney.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.journey.myjourney.data.model.ServiceData_Full
import com.journey.myjourney.databinding.ItemServiceBinding
import com.journey.myjourney.other.type.sendOneParametreBlock

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 16/11/2022.
 */
class ServiceAdapter() : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {
    var differ = AsyncListDiffer(this, ITEM_DIFF)
        private set
    private var defaultList: List<ServiceData_Full> = emptyList()
    private var itemSelect: sendOneParametreBlock<Int>? = null

    fun setItemClick(block: sendOneParametreBlock<Int>) {
        itemSelect = block
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<ServiceData_Full>() {
            override fun areItemsTheSame(oldItem: ServiceData_Full, newItem: ServiceData_Full): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ServiceData_Full, newItem: ServiceData_Full): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(differ.currentList[position])

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private var binding: ItemServiceBinding) : RecyclerView.ViewHolder(binding.root) {
        init {

        }

        fun bind(item: ServiceData_Full) {
            binding.tvName.text = item.name
        }
    }
}