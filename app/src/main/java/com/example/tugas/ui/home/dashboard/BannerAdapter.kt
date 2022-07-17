package com.example.tugas.ui.home.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tugas.data.model.BannerModel
import com.example.tugas.databinding.BannerItemLayoutBinding

//class BannerAdapter(private val bannerListItem: List<BannerModel>) :
//    RecyclerView.Adapter<BannerViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.banner_item_layout, parent, false)
//
//        return BannerViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return bannerListItem.size
//    }
//
//    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
//        val bannerItem = bannerListItem[position]
//        holder.bind(bannerItem)
//    }
//
//}
//
//class BannerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
//    private val bannerImageView = view.findViewById<ImageView>(R.id.banner_image_view)
//
//    fun bind(bannerItem: BannerModel) {
//        Glide.with(view.context)
//            .load(bannerItem.imageUrl)
//            .into(bannerImageView)
//    }
//}

class BannerAdapter : ListAdapter<BannerModel, BannerAdapter.MyViewHolder>(
    BannerDiffCallback
) {
    lateinit var binding: BannerItemLayoutBinding

    companion object {
        val BannerDiffCallback = object : DiffUtil.ItemCallback<BannerModel>() {
            override fun areItemsTheSame(oldItem: BannerModel, newItem: BannerModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: BannerModel, newItem: BannerModel): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding =
            BannerItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: BannerModel) = with(itemView) {
            Glide.with(context)
                .load(item.imageUrl)
                .into(binding.bannerImageView)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}