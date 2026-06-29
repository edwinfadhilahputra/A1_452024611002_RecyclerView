package com.example.advancedrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.advancedrecyclerview.databinding.ItemHeaderBinding
import com.example.advancedrecyclerview.databinding.ItemNumberBinding

private const val VIEW_TYPE_HEADER = 0
private const val VIEW_TYPE_ITEM = 1

class AdvancedAdapter : ListAdapter<DataItem, RecyclerView.ViewHolder>(DataItemDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> VIEW_TYPE_HEADER
            is DataItem.NumberItem -> VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            VIEW_TYPE_ITEM -> NumberViewHolder.from(parent)
            else -> throw IllegalArgumentException("View type tidak dikenal")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                val item = getItem(position) as DataItem.Header
                holder.bind(item)
            }

            is NumberViewHolder -> {
                val item = getItem(position) as DataItem.NumberItem
                holder.bind(item)
            }
        }
    }

    // --- ViewHolder untuk Header ---
    class HeaderViewHolder private constructor(private val binding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataItem.Header) {
            binding.header = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemHeaderBinding.inflate(inflater, parent, false)
                return HeaderViewHolder(binding)
            }
        }
    }

    // --- ViewHolder untuk Item Angka ---
    class NumberViewHolder private constructor(private val binding: ItemNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DataItem.NumberItem) {
            binding.item = item

            // Logika pewarnaan dinamis dipindahkan ke sini secara aman
            val number = item.numberData
            binding.textNumber.text = number.toString()

            if (number % 2 == 0) {
                binding.textNumber.setTextColor(android.graphics.Color.parseColor("#4CAF50")) // Hijau
            } else {
                binding.textNumber.setTextColor(android.graphics.Color.parseColor("#F44336")) // Merah
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NumberViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemNumberBinding.inflate(inflater, parent, false)
                return NumberViewHolder(binding)
            }
        }
    }

    // --- DiffUtil Callback ---
    class DataItemDiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }
}