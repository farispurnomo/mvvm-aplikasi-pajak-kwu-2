package com.example.tugas.ui.auth.forget

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.example.tugas.R
import com.example.tugas.databinding.ActivityResetPasswordBinding
import com.example.tugas.di.component.ActivityComponent
import com.example.tugas.ui.base.BaseActivity

class ResetPasswordActivity : BaseActivity<ResetPasswordViewModel, ActivityResetPasswordBinding>() {

    override fun getViewBinding(): ActivityResetPasswordBinding =
        ActivityResetPasswordBinding.inflate(layoutInflater)

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    private var email: String = ""

    override fun setupView(savedInstanceState: Bundle?) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val extras = intent.extras
        if (extras != null) {
            email = extras.getString(EMAIL, "")
            countDownTimer(millis = (5 * 1000))
        }

        binding.tvEmail.text = email

        binding.ivSendEmail.setOnClickListener {
            countDownTimer(millis = (5 * 1000))
        }

        binding.btReset.setOnClickListener {
            viewModel.onResetPassword(
                email = email,
                password = binding.etPassword.text.toString(),
                confirmPassword = binding.etPasswordKonfirmasi.text.toString(),
                kode = binding.etOtp.text.toString()
            )
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.isSuccessResetPassword.observe(this) {
            if (it) {
                val builder = AlertDialog.Builder(this).create()
                val view = layoutInflater.inflate(R.layout.dialog_register, null)
                val title = view.findViewById<AppCompatTextView>(R.id.tvTitle)
                val btOk = view.findViewById<AppCompatButton>(R.id.btYa)

                builder.setView(view)
                builder.create()

                title.text = "Password berhasil direset"
                btOk.setOnClickListener {
                    builder.dismiss()
//                    finish()
                }

                builder.setCanceledOnTouchOutside(false)
                builder.show()
            }
        }
    }

    private fun countDownTimer(millis: Long, interval: Long = 1000) {
        binding.tvCountdown.visibility = View.VISIBLE
        binding.ivSendEmail.visibility = View.GONE

        val timer = object : CountDownTimer(millis, interval) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                var diff = millisUntilFinished
                val secondsInMilli: Long = 1000
                val minutesInMilli = secondsInMilli * 60
                val hoursInMilli = minutesInMilli * 60
                val daysInMilli = hoursInMilli * 24

//                val emapsedDays = diff / daysInMilli
                diff %= daysInMilli

//                val elapsedHours = diff / hoursInMilli
                diff %= hoursInMilli

                val elapsedMinutes = diff / minutesInMilli
                diff %= minutesInMilli

                val elapsedSeconds = diff / secondsInMilli

                binding.tvCountdown.text =
                    elapsedMinutes.toString().padStart(2, '0') +
                            ":" + elapsedSeconds.toString().padStart(2, '0')
            }

            override fun onFinish() {
                binding.tvCountdown.text = "00:00"
                binding.tvCountdown.visibility = View.GONE
                binding.ivSendEmail.visibility = View.VISIBLE
            }
        }
        timer.start()
    }

    companion object {
        const val TAG = "ResetPasswordActivity"
        const val EMAIL = "email"
    }
}