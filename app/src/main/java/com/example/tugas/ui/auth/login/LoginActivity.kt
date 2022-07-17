package com.example.tugas.ui.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.example.tugas.R
import com.example.tugas.databinding.ActivityLoginBinding
import com.example.tugas.di.component.ActivityComponent
import com.example.tugas.ui.auth.register.RegisterActivity
import com.example.tugas.ui.auth.forget.ResetPasswordActivity
import com.example.tugas.ui.base.BaseActivity
import com.example.tugas.ui.home.HomeActivity
import com.example.tugas.utils.common.Resource

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun getViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        if (viewModel.isLogin) {
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            finish()
        }

        binding.btLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.onLogin(email, password)
        }

//        binding.etEmail.setText("test")
//        binding.etPassword.setText("1234")

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }

        binding.tvForget.setOnClickListener {
            val dialog = MaterialDialog(this, BottomSheet(LayoutMode.WRAP_CONTENT))
                .customView(R.layout.dialog_forget)
                .cancelable(true)
            val customView = dialog.getCustomView()
            val btReset = customView.findViewById<AppCompatButton>(R.id.btReset)
            val etEmail = customView.findViewById<AppCompatEditText>(R.id.etEmail)

            btReset.setOnClickListener {
                if (etEmail.text.toString().isNotEmpty()) {
                    dialog.dismiss()
                    viewModel.onForget(etEmail.text.toString())
                    val intent = Intent(this, ResetPasswordActivity::class.java).apply {
                        putExtra(ResetPasswordActivity.EMAIL, etEmail.text.toString())
                    }
                    startActivity(intent)
                }else{
                    viewModel.onmessage("Email harus Diisi");
                }

            }

            dialog.show{
                cornerRadius(16f)
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.launchMain.observe(this) {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, HomeActivity::class.java))
                finish()
            }
        }
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}