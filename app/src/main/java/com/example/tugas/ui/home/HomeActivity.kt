package com.example.tugas.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tugas.R
import com.example.tugas.databinding.ActivityHomeBinding
import com.example.tugas.di.component.ActivityComponent
import com.example.tugas.ui.auth.login.LoginActivity
import com.example.tugas.ui.base.BaseActivity
import com.example.tugas.ui.home.dashboard.DashboardFragment
import com.example.tugas.ui.home.pengaturan.PengaturanFragment
import com.example.tugas.ui.home.tagihan.TagihanFragment
import com.google.android.material.snackbar.Snackbar

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
//            .setCustomAnimations(
//                R.anim.design_bottom_sheet_slide_in,
//                R.anim.design_bottom_sheet_slide_out
//            )
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
    }

//    @Inject
//    lateinit var loadingDialog: LoadingDialog

    override fun getViewBinding(): ActivityHomeBinding =
        ActivityHomeBinding.inflate(layoutInflater)

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationDashboard -> {
                    val fragment = DashboardFragment()
                    addFragment(fragment)
                    return@setOnItemSelectedListener true
                }
                R.id.navigationTagihan -> {
                    val fragment = TagihanFragment()
                    addFragment(fragment)
                    return@setOnItemSelectedListener true
                }
                R.id.navigationPengaturan -> {
                    val fragment = PengaturanFragment()
                    addFragment(fragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

        binding.bottomNavigationView.selectedItemId = R.id.navigationDashboard
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.isLogout.observe(this) {
            if (it) {
                showMessage("Berhasil Logout")
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
            }
        }

        viewModel.tagihanNavigation.observe(this) {
            binding.bottomNavigationView.selectedItemId = R.id.navigationTagihan
        }
    }

    fun showSnackbarMessage(msg: String) {
        Snackbar.make(binding.bottomNavigationView, msg, Snackbar.LENGTH_SHORT)
            .apply {
                anchorView = binding.bottomNavigationView
            }.show()
    }

    companion object {
        const val TAG = "HomeActivity"
    }
}