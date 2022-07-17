package com.example.tugas.ui.home.pengaturan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.tugas.R
import com.example.tugas.databinding.FragmentPengaturanBinding
import com.example.tugas.di.component.FragmentComponent
import com.example.tugas.ui.base.BaseFragment
import com.example.tugas.ui.home.HomeActivity
import com.example.tugas.ui.webview.WebviewActivity

class PengaturanFragment : BaseFragment<PengaturanViewModel, FragmentPengaturanBinding>() {
    companion object {
        const val TAG = "PengaturanFragment"
        fun newInstance(): PengaturanFragment {
            val args = Bundle()
            val fragment = PengaturanFragment()
            fragment.arguments = args
            return fragment
        }
    }

//    @Inject
//    lateinit var loadingDialog: LoadingDialog

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPengaturanBinding = FragmentPengaturanBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {
//        loadingDialog.apply {
//            isCancelable = false
//            arguments = Bundle().apply {
//                putInt(LoadingDialog.MESSAGE_KEY, R.string.logging_in)
//            }
//        }

        binding.tvTentang.setOnClickListener { showDialogAbout() }
        binding.tvLogout.setOnClickListener {
            (activity as HomeActivity).viewModel.onLogout()
        }

        binding.tvTos.setOnClickListener {
            val i = Intent(requireContext(), WebviewActivity::class.java)
            i.putExtra(WebviewActivity.EXTRA_TITLE, "Syarat dan Ketentuan")
            i.putExtra(WebviewActivity.EXTRA_URL, "https://policies.google.com/terms?hl=en-US")
            startActivity(i)
        }

        binding.tvPolicy.setOnClickListener {
            val i = Intent(requireContext(), WebviewActivity::class.java)
            i.putExtra(WebviewActivity.EXTRA_TITLE, "Kebijakan Privasi")
            i.putExtra(WebviewActivity.EXTRA_URL, "https://policies.google.com/privacy?hl=en-US")
            startActivity(i)
        }
        binding.tvFaq.setOnClickListener {
            val i = Intent(requireContext(), WebviewActivity::class.java)
            i.putExtra(WebviewActivity.EXTRA_TITLE, "FAQ")
            i.putExtra(WebviewActivity.EXTRA_URL, "https://policies.google.com/faq?hl=en-US")
            startActivity(i)
        }

    }

    override fun setupObservers() {
        super.setupObservers()

        (activity as HomeActivity).viewModel.user.observe(this) {
            binding.tvNamaUser.text = it.user_name
            binding.tvIdUser.text = it.user_id
        }

//        viewModel.totalUang.observe(this) {
//            it.getIfNotHandled()?.run {
//                val uang = this.formatRibuan()
//                binding.tvTotal.text = "Rp. $uang"
//            }
//        }
//
//        viewModel.isLoading.observe(this) {
//            if (it) {
////                loadingDialog.show(parentFragmentManager, LoadingDialog.TAG)
//                binding.refresh.isRefreshing = true
//            } else try {
////                loadingDialog.dismiss()
//                binding.refresh.isRefreshing = false
//            } catch (e: NullPointerException) {
//                // Sometime this happens
//            }
//        }

    }

    private fun showDialogAbout() {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.dialog_about, null)
        val button = view.findViewById<Button>(R.id.dialogDismiss_button)
        builder.setView(view)
        button.setOnClickListener {
            builder.dismiss()
        }
        builder.setCanceledOnTouchOutside(false)
        builder.show()
    }
}