package com.journey.passenger_presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.journey.common_utils.other.type.sendOneParametreBlock
import com.journey.passenger_domen.model.Service
import com.journey.passenger_presenter.databinding.ItemServiceBinding

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 16/11/2022.
 */
class ServiceAdapter() : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {
    var differ = AsyncListDiffer(this, ITEM_DIFF)
        private set
    private var defaultList: List<Service> = emptyList()
    private var itemSelect: sendOneParametreBlock<Int>? = null

    fun setItemClick(block: sendOneParametreBlock<Int>) {
        itemSelect = block
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<Service>() {
            override fun areItemsTheSame(oldItem: Service, newItem: Service): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Service, newItem: Service): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(differ.currentList[position])

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private var binding: ItemServiceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

        }

        fun bind(item: Service) {
            binding.tvName.text = item.name
        }
    }
}