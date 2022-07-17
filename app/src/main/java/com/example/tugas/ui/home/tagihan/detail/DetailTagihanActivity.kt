package com.example.tugas.ui.home.tagihan.detail

import android.os.Bundle
import com.example.tugas.databinding.ActivityDetailTagihanBinding
import com.example.tugas.di.component.ActivityComponent
import com.example.tugas.ui.base.BaseActivity

class DetailTagihanActivity : BaseActivity<DetailTagihanViewModel, ActivityDetailTagihanBinding>() {
    override fun getViewBinding(): ActivityDetailTagihanBinding =
        ActivityDetailTagihanBinding.inflate(layoutInflater)

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val extras = intent.extras
        if (extras != null) {
            val tagihanId = extras.getString(TAGIHAN_ID, "")
            viewModel.onGetDetailTagihan(tagihanId)
        }

        binding.btSubmit.setOnClickListener {
        showMessage("Berhasil dibayarkan")
            finish()
//    viewModel.onBayarTagihan()
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.tagihan.observe(this) {

        }
    }

    companion object {
        const val TAGIHAN_ID = "tagihan_id"
    }

}