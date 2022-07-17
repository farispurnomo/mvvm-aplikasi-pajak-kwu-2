package com.example.tugas.ui.notifikasi

import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugas.databinding.ActivityNotificationBinding
import com.example.tugas.di.component.ActivityComponent
import com.example.tugas.ui.base.BaseActivity

class NotificationActivity : BaseActivity<NotificationViewModel, ActivityNotificationBinding>() {
    private val adapterNotifikasi by lazy {
        val adp = NotificationAdapter()
        adp
    }

    override fun getViewBinding(): ActivityNotificationBinding =
        ActivityNotificationBinding.inflate(layoutInflater)

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        binding.rcNotifikasi.apply {
            layoutManager = LinearLayoutManager(this@NotificationActivity)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = adapterNotifikasi
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.notification.observe(this) {
            adapterNotifikasi.submitList(it)
        }
    }
}