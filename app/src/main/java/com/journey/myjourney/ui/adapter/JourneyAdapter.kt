package com.journey.myjourney.ui.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.journey.myjourney.R
import com.journey.myjourney.databinding.ItemJourneyBinding
import com.journey.myjourney.domen.model.PlaceJourneyData
import com.journey.myjourney.other.Constants
import com.journey.myjourney.other.type.sendOneParametreBlock

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 16/11/2022.
 */
class JourneyAdapter(private var resources:Resources) : RecyclerView.Adapter<JourneyAdapter.ViewHolder>() {
    private var differ = AsyncListDiffer(this, ITEM_DIFF)
    private var defaultList: List<PlaceJourneyData> = emptyList()
    private var itemSelect: sendOneParametreBlock<Int>? = null
    fun submitList(ls: ArrayList<PlaceJourneyData>) {
        differ.submitList(ls.toMutableList())
        defaultList = differ.currentList
    }

    fun setItemClick(block: sendOneParametreBlock<Int>) {
        itemSelect = block
    }


    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<PlaceJourneyData>() {
            override fun areItemsTheSame(oldItem: PlaceJourneyData, newItem: PlaceJourneyData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PlaceJourneyData, newItem: PlaceJourneyData): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemJourneyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(differ.currentList[position])

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private var binding: ItemJourneyBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.item.setOnClickListener {
                itemSelect?.invoke(differ.currentList[absoluteAdapterPosition].id)
            }
            binding.description.setOnClickListener {
                itemSelect?.invoke(differ.currentList[absoluteAdapterPosition].id)
            }
        }

        fun bind(item: PlaceJourneyData) {
            when (item.isGone){
                Constants.GO->{
                    binding.ivIsGone.setImageResource(R.drawable.background_is_gone_green)
                    binding.textBkg.background.setTint(resources.getColor(R.color.green))
                    binding.expansionLayout.setBackgroundResource(R.color.green)
                }
                Constants.CURRENT_GO->{
                    binding.ivIsGone.setImageResource(R.drawable.background_is_gone_orange)
                    binding.textBkg.background.setTint(resources.getColor(R.color.orange))
                    binding.expansionLayout.setBackgroundResource(R.color.orange)
                }
                Constants.GONE->{
                    binding.ivIsGone.setImageResource(R.drawable.background_is_gone_red)
                    binding.textBkg.background.setTint(resources.getColor(R.color.red))
                    binding.expansionLayout.setBackgroundResource(R.color.red)
                }
            }
            binding.image.setImageResource(item.picture)
            binding.name.text = item.name
            binding.description.text = item.info
            if (binding.expansionLayout.isExpanded) {
                binding.expansionLayout.collapse(false)
            }
        }
    }
}