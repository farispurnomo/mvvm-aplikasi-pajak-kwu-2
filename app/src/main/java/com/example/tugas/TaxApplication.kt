package com.example.tugas

import android.app.Application
import com.example.tugas.di.component.ApplicationComponent
import com.example.tugas.di.component.DaggerApplicationComponent
import com.example.tugas.di.module.ApplicationModule
import com.example.tugas.utils.log.Logger
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class TaxApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()

//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Logger.w(
//                    "TanganSantriApplication" + task.exception,
//                    "Fetching FCM registration token failed"
//                )
//                return@OnCompleteListener
//            }
//
//            // Get new FCM registration token
//            val token = task.result
//
//            // Log and toast
//            Logger.d("TanganSantriApplication", "TOKEN :: $token")
//            fcmToken = token
////            Toast.makeText(baseContext, "token:: $token", Toast.LENGTH_SHORT).show()
//        })
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    // Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }

    companion object {
        var fcmToken = ""
    }
}