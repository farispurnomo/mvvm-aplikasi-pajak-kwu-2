package com.example.tugas.ui.home.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tugas.data.model.MenuPembayaranModel
import com.example.tugas.databinding.FragmentDashboardBinding
import com.example.tugas.di.component.FragmentComponent
import com.example.tugas.ui.base.BaseFragment
import com.example.tugas.ui.home.HomeActivity
import com.example.tugas.ui.notifikasi.NotificationActivity
import com.example.tugas.ui.pembayaran.pbb.PbbCariNopActivity
import com.example.tugas.utils.extensions.formatRibuan

class DashboardFragment : BaseFragment<DashboardViewModel, FragmentDashboardBinding>(),
    MenuPembayaranAdapter.InterfaceMenuPembayaran {
//    @Inject
//    lateinit var loadingDialog: LoadingDialog

    private var isVisibleTotalSaldo = true

    private val adapterMenuPembayaran by lazy {
        val adp = MenuPembayaranAdapter()
        adp.listener = this
        adp
    }

    private val adapterBanner by lazy {
        val adp = BannerAdapter()
        adp
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {
        binding.vpBanner.adapter = adapterBanner
        binding.vpBanner.offscreenPageLimit = 1
        binding.diIndicator.setViewPager2(binding.vpBanner)

        binding.ivNotifikasi.setOnClickListener {
            startActivity(Intent(context, NotificationActivity::class.java))
        }

        binding.rcMenu.apply {
            layoutManager = GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            isNestedScrollingEnabled = false
            setHasFixedSize(false)
            adapter = adapterMenuPembayaran
        }

        binding.llTagihan.setOnClickListener {
            (activity as HomeActivity).viewModel.onTagihanNavigation()
        }

        binding.llTopup.setOnClickListener { (activity as HomeActivity).showSnackbarMessage("Fitur belum tersedia") }
        binding.llTransfer.setOnClickListener { (activity as HomeActivity).showSnackbarMessage("Fitur belum tersedia") }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.menuPembayaranModel.observe(this) {
            adapterMenuPembayaran.submitList(it)
        }

        viewModel.totalSaldo.observe(this) {
            binding.tvTotalSaldo.text = "Rp." + it.formatRibuan()
        }

        viewModel.listBanner.observe(this) {
            adapterBanner.submitList(it)
        }
    }

    companion object {
        const val TAG = "DashboardFragment"
        fun newInstance(): DashboardFragment {
            val args = Bundle()
            val fragment = DashboardFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onMenuPembayaranListener(item: MenuPembayaranModel, position: Int) {
        when (item.aksi) {
            "pbb" -> {
                startActivity(Intent(context, PbbCariNopActivity::class.java))
            }
            else -> (activity as HomeActivity).showSnackbarMessage("Fitur Belum Tersedia")
        }
    }
}