package com.journey.passenger_presenter.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.journey.common_utils.other.type.sendOneParametreBlock
import com.journey.passenger_domen.model.JourneyData
import com.journey.passenger_domen.model.TYPE_GONE
import com.journey.passenger_presenter.R
import com.journey.passenger_presenter.databinding.ItemJourneyBinding

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 16/11/2022.
 */
class JourneyAdapter(private var resources:Resources) : RecyclerView.Adapter<JourneyAdapter.ViewHolder>() {
    private var differ = AsyncListDiffer(this, ITEM_DIFF)
    private var defaultList: List<JourneyData> = emptyList()
    private var itemSelect: sendOneParametreBlock<Int>? = null
    fun submitList(ls: ArrayList<JourneyData>) {
        differ.submitList(ls.toMutableList())
        defaultList = differ.currentList
    }

    fun setItemClick(block: sendOneParametreBlock<Int>) {
        itemSelect = block
    }


    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<JourneyData>() {
            override fun areItemsTheSame(oldItem: JourneyData, newItem: JourneyData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: JourneyData, newItem: JourneyData): Boolean =
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

        fun bind(item: JourneyData) {
            when (item.isGone){
                TYPE_GONE.GO->{
                    binding.ivIsGone.setImageResource(R.drawable.background_is_gone_green)
                    binding.textBkg.background.setTint(resources.getColor(com.journey.common_utils.R.color.green))
                    binding.expansionLayout.setBackgroundResource(com.journey.common_utils.R.color.green)
                }
                TYPE_GONE.Current->{
                    binding.ivIsGone.setImageResource(R.drawable.background_is_gone_orange)
                    binding.textBkg.background.setTint(resources.getColor(com.journey.common_utils.R.color.orange))
                    binding.expansionLayout.setBackgroundResource(com.journey.common_utils.R.color.orange)
                }
                TYPE_GONE.Gone->{
                    binding.ivIsGone.setImageResource(R.drawable.background_is_gone_red)
                    binding.textBkg.background.setTint(resources.getColor(com.journey.common_utils.R.color.red))
                    binding.expansionLayout.setBackgroundResource(com.journey.common_utils.R.color.red)
                }
            }
            binding.image.setImageResource(item.place.picture)
            binding.name.text = item.place.name
            binding.description.text = item.place.info
            if (binding.expansionLayout.isExpanded) {
                binding.expansionLayout.collapse(false)
            }
        }
    }
}