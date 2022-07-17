package com.example.tugas.ui.home.tagihan.list

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugas.data.model.RiwayatTagihanModel
import com.example.tugas.databinding.ActivityListTagihanBinding
import com.example.tugas.di.component.ActivityComponent
import com.example.tugas.ui.base.BaseActivity
import com.example.tugas.ui.home.tagihan.detail.DetailTagihanActivity
import com.example.tugas.ui.home.tagihan.TagihanAdapter

class ListTagihanActivity : BaseActivity<ListTagihanViewModel, ActivityListTagihanBinding>() {

    private val adapterTagihan by lazy {
        val adp = TagihanAdapter()
        adp.listener = object : TagihanAdapter.InterfaceRiwayatTagihan {
            override fun onRiwayatTagihanClick(item: RiwayatTagihanModel, position: Int) {
                val i = Intent(this@ListTagihanActivity, DetailTagihanActivity::class.java).apply {
                    putExtra(DetailTagihanActivity.TAGIHAN_ID, item.id)
                }
                startActivity(i)
            }
        }
        adp
    }

    override fun getViewBinding(): ActivityListTagihanBinding =
        ActivityListTagihanBinding.inflate(layoutInflater)

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        var isLunas = true

        val extras = intent.extras
        if (extras != null) {
            isLunas = extras.getBoolean(IS_LUNAS, true)
        }

        if (isLunas) {
            binding.toolbar.title = "Riwayat Tagihan"
        } else {
            binding.toolbar.title = "List Tagihan"
        }

        viewModel.onGetListTagihan()
        binding.swTagihan.setOnRefreshListener {
            viewModel.onGetListTagihan()
        }

        binding.rcTagihan.apply {
            layoutManager = LinearLayoutManager(this@ListTagihanActivity)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = adapterTagihan
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.tagihan.observe(this) {
            adapterTagihan.submitList(it)
        }

        viewModel.isLoading.observe(this) {
            binding.swTagihan.isRefreshing = it
        }
    }

    companion object {
        const val IS_LUNAS = "is_lunas"
    }
}