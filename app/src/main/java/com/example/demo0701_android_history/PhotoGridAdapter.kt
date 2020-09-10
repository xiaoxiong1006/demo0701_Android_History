package com.example.demo0701_android_history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demo0701_android_history.databinding.GridViewItemBinding

class PhotoGridAdapter:ListAdapter<AndroidHistoryData,
        PhotoGridAdapter.AndroidPropertyViewHolder>(DiffCallBack) {

    class AndroidPropertyViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(androidProperty: AndroidHistoryData) {
            binding.property = androidProperty
            binding.executePendingBindings()
        }

    }

    companion object DiffCallBack : DiffUtil.ItemCallback<AndroidHistoryData>() {
        override fun areItemsTheSame(
            oldItem: AndroidHistoryData,
            newItem: AndroidHistoryData
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: AndroidHistoryData,
            newItem: AndroidHistoryData
        ): Boolean {
            return oldItem.version_id == newItem.version_id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AndroidPropertyViewHolder {
        return AndroidPropertyViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AndroidPropertyViewHolder, position: Int) {
        val androidProperty = getItem(position)
        holder.bind(androidProperty)
    }
}