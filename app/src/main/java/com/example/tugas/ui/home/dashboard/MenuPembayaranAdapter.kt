package com.example.tugas.ui.home.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas.R
import com.example.tugas.data.model.MenuPembayaranModel
import com.example.tugas.databinding.ItemMenuPembayaranBinding

class MenuPembayaranAdapter : ListAdapter<MenuPembayaranModel, MenuPembayaranAdapter.MyViewHolder>(
    MenuPembayaranDiffCallback
) {

    lateinit var binding: ItemMenuPembayaranBinding
    var listener: InterfaceMenuPembayaran? = null

    companion object {
        val MenuPembayaranDiffCallback = object : DiffUtil.ItemCallback<MenuPembayaranModel>() {
            override fun areItemsTheSame(
                oldItem: MenuPembayaranModel,
                newItem: MenuPembayaranModel
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MenuPembayaranModel,
                newItem: MenuPembayaranModel
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding =
            ItemMenuPembayaranBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                val position = layoutPosition
                listener?.apply {
                    onMenuPembayaranListener(getItem(position), position)
                }
            }
        }

        fun bind(item: MenuPembayaranModel) = with(itemView) {
            binding.tvMenu.text = item.name
            binding.ivMenu.setImageDrawable(getDrawable(context, item.imageTemp?: R.drawable.ic_baseline_help_outline_24))
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface InterfaceMenuPembayaran {
        fun onMenuPembayaranListener(item: MenuPembayaranModel, position: Int)
    }
}