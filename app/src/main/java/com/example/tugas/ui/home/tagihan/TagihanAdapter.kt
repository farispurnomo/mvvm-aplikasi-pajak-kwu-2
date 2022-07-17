package com.example.tugas.ui.home.tagihan

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas.data.model.RiwayatTagihanModel
import com.example.tugas.databinding.ItemRiwayatTagihanBinding
import com.example.tugas.utils.extensions.formatRibuan

class TagihanAdapter : ListAdapter<RiwayatTagihanModel, TagihanAdapter.MyViewHolder>(
    RiwayatTagihanDiffCallback
) {

    lateinit var binding: ItemRiwayatTagihanBinding
    var listener: InterfaceRiwayatTagihan? = null

    companion object {
        val RiwayatTagihanDiffCallback = object : DiffUtil.ItemCallback<RiwayatTagihanModel>() {
            override fun areItemsTheSame(
                oldItem: RiwayatTagihanModel,
                newItem: RiwayatTagihanModel
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: RiwayatTagihanModel,
                newItem: RiwayatTagihanModel
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding =
            ItemRiwayatTagihanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                val position = layoutPosition
                listener?.apply {
                    onRiwayatTagihanClick(getItem(position), position)
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: RiwayatTagihanModel) = with(itemView) {
            binding.tvTitle.text = item.title
            binding.tvSubTitle.text = item.subtitle
            binding.tvTanggal.text = item.tanggal
            binding.tvTotal.text = "Rp. " + item.total.formatRibuan()
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface InterfaceRiwayatTagihan {
        fun onRiwayatTagihanClick(item: RiwayatTagihanModel, position: Int)
    }
}