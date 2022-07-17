package com.example.tugas.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.example.tugas.R
import com.example.tugas.databinding.ActivityRegisterBinding
import com.example.tugas.di.component.ActivityComponent
import com.example.tugas.ui.auth.login.LoginActivity
import com.example.tugas.ui.base.BaseActivity

class RegisterActivity : BaseActivity<RegisterViewModel, ActivityRegisterBinding>() {

    override fun getViewBinding(): ActivityRegisterBinding =
        ActivityRegisterBinding.inflate(layoutInflater)

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            finish()
        }

        binding.btRegister.setOnClickListener {
//            viewModel.onRegister(email = binding.etEmail.text.toString(), password = binding.etPassword.text.toString(), confirmPassword = binding.etPasswordKonfirmasi.text.toString())

            viewModel.onRegister(email = binding.etEmail.text.toString(), password = binding.etPassword.text.toString(), confirmPassword = binding.etPasswordKonfirmasi.text.toString())

        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.isSuccessRegister.observe(this) {
            if (it) {
                val builder = AlertDialog.Builder(this).create()
                val view = layoutInflater.inflate(R.layout.dialog_register, null)
                val btOk = view.findViewById<AppCompatButton>(R.id.btYa)

                builder.setView(view)
                builder.create()
                btOk.setOnClickListener {
                    builder.dismiss()
                    startActivity(Intent(this, LoginActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                    finish()
                }

                builder.setCanceledOnTouchOutside(false)
                builder.show()
            }
        }
    }

    companion object {
        const val TAG = "RegisterActivity"
    }
}