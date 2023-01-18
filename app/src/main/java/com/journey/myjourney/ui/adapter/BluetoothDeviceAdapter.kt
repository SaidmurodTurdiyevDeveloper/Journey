package com.journey.myjourney.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.journey.myjourney.R
import com.journey.myjourney.databinding.ItemCutListBinding
import com.journey.myjourney.databinding.ItemDeviceBinding
import com.journey.myjourney.other.type.sendOneParametreBlock
import com.journey.myjourney.ui.model.DataBluetoothList
import com.journey.myjourney.ui.model.DataCut
import com.journey.myjourney.ui.model.DataDevice

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/11/2022.
 */
class BluetoothDeviceAdapter(private var context: Context) : RecyclerView.Adapter<BluetoothDeviceAdapter.ViewHolder>() {
    var differ = AsyncListDiffer(this, ITEM_DIFF)
        private set
    private var itemClick: sendOneParametreBlock<DataDevice>? = null


    fun setItemClick(block: sendOneParametreBlock<DataDevice>) {
        itemClick = block
    }


    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<DataBluetoothList>() {
            override fun areItemsTheSame(oldItem: DataBluetoothList, newItem: DataBluetoothList): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: DataBluetoothList, newItem: DataBluetoothList): Boolean =
                oldItem.equals(newItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (differ.currentList[position] is DataDevice) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == 0) ViewHolderDevice(ItemDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        else ViewHolderCut(ItemCutListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    abstract inner class ViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: DataBluetoothList)
    }

    inner class ViewHolderCut(private var binding: ItemCutListBinding) : ViewHolder(binding) {

        override fun bind(item: DataBluetoothList) {
            if (item is DataCut) {
                binding.tvTitle.text = item.title
            }
        }
    }

    inner class ViewHolderDevice(private var binding: ItemDeviceBinding) : ViewHolder(binding) {
        init {
            binding.item.setOnClickListener {
                val data = differ.currentList[absoluteAdapterPosition]
                if (data is DataDevice) {
                    itemClick?.invoke(data)
                }
            }
        }

        override fun bind(item: DataBluetoothList) {
            if (item is DataDevice) {
                binding.tvName.text = item.name
                if (item.isConnected) binding.tvName.setTextColor(ContextCompat.getColor(context, R.color.blue))
            }
        }
    }
}