package com.example.tugas.ui.home.tagihan

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugas.data.model.RiwayatTagihanModel
import com.example.tugas.databinding.FragmentTagihanBinding
import com.example.tugas.di.component.FragmentComponent
import com.example.tugas.ui.base.BaseFragment
import com.example.tugas.ui.home.tagihan.detail.DetailTagihanActivity
import com.example.tugas.ui.home.tagihan.list.ListTagihanActivity
import com.example.tugas.ui.notifikasi.NotificationActivity
import com.example.tugas.utils.extensions.formatRibuan

class TagihanFragment : BaseFragment<TagihanViewModel, FragmentTagihanBinding>() {
    companion object {
        const val TAG = "RiwayatFragment"
        fun newInstance(): TagihanFragment {
            val args = Bundle()
            val fragment = TagihanFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val adapterTagihan by lazy {
        val adp = TagihanAdapter()
        adp.listener = object : TagihanAdapter.InterfaceRiwayatTagihan {
            override fun onRiwayatTagihanClick(item: RiwayatTagihanModel, position: Int) {
                val i = Intent(requireContext(), DetailTagihanActivity::class.java).apply {
                    putExtra(DetailTagihanActivity.TAGIHAN_ID, item.id)
                }
                startActivity(i)
            }
        }
        adp
    }

    private val adapterRiwayatTagihan by lazy {
        val adp = TagihanAdapter()
        adp.listener = object : TagihanAdapter.InterfaceRiwayatTagihan {
            override fun onRiwayatTagihanClick(item: RiwayatTagihanModel, position: Int) {
                val i = Intent(requireContext(), DetailTagihanActivity::class.java).apply {
                    putExtra(DetailTagihanActivity.TAGIHAN_ID, item.id)
                }
                startActivity(i)
            }
        }
        adp
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTagihanBinding = FragmentTagihanBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {
        binding.ivNotifikasi.setOnClickListener {
            startActivity(Intent(context, NotificationActivity::class.java))
        }

        binding.tvListDaftarTagihan.setOnClickListener {
            val i = Intent(requireContext(), ListTagihanActivity::class.java).apply {
                putExtra(ListTagihanActivity.IS_LUNAS, false)
            }
            startActivity(i)
        }

        binding.tvListRiwayatTagihan.setOnClickListener {
            val i = Intent(requireContext(), ListTagihanActivity::class.java).apply {
                putExtra(ListTagihanActivity.IS_LUNAS, true)
            }
            startActivity(i)
        }

        binding.rcDaftarTagihan.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            isNestedScrollingEnabled = false
            setHasFixedSize(false)
            adapter = adapterTagihan
        }

        binding.rcRiwayatTagihan.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            isNestedScrollingEnabled = false
            setHasFixedSize(false)
            adapter = adapterRiwayatTagihan
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setupObservers() {
        super.setupObservers()

        viewModel.tagihan.observe(this) {
            adapterTagihan.submitList(it.tagihan)

            val jumlah = it.tagihan.size
            binding.tvTotalTagihan.text = "Rp" + it.total.formatRibuan()
            binding.tvTitleDaftarTagihan.text = "Daftar Tagihan ($jumlah)"

            if (it.tagihan.isNotEmpty()) {
                binding.rcDaftarTagihan.visibility = View.VISIBLE
                binding.tvEmptyDaftarTagihan.visibility = View.GONE
            } else {
                binding.rcDaftarTagihan.visibility = View.GONE
                binding.tvEmptyDaftarTagihan.visibility = View.VISIBLE
            }
        }

        viewModel.riwayat.observe(this) {
            adapterRiwayatTagihan.submitList(it)

            if (it.isNotEmpty()) {
                binding.rcRiwayatTagihan.visibility = View.VISIBLE
                binding.tvEmptyRiwayatTagihan.visibility = View.GONE
            } else {
                binding.rcRiwayatTagihan.visibility = View.GONE
                binding.tvEmptyRiwayatTagihan.visibility = View.VISIBLE
            }
        }
    }
}