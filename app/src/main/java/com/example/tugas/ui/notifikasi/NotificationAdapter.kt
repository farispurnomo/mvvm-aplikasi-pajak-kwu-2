package com.example.tugas.ui.notifikasi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas.data.model.NotificationModel
import com.example.tugas.databinding.ItemListNotifkasiBinding

class NotificationAdapter : ListAdapter<NotificationModel, NotificationAdapter.MyViewHolder>(
    NotificationDiffCallback
) {
    lateinit var binding: ItemListNotifkasiBinding

    companion object {
        val NotificationDiffCallback = object : DiffUtil.ItemCallback<NotificationModel>() {
            override fun areItemsTheSame(
                oldItem: NotificationModel,
                newItem: NotificationModel
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: NotificationModel,
                newItem: NotificationModel
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding =
            ItemListNotifkasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: NotificationModel) = with(itemView) {
            binding.tvTitle.text = item.title
            binding.tvSubTitle.text = item.subtitle
            binding.tvTimestamp.text = item.timestamp
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}