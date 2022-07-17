package com.example.tugas.di.component

import com.example.tugas.di.ActivityScope
import com.example.tugas.di.module.ActivityModule
import com.example.tugas.ui.auth.login.LoginActivity
import com.example.tugas.ui.auth.register.RegisterActivity
import com.example.tugas.ui.auth.forget.ResetPasswordActivity
import com.example.tugas.ui.home.HomeActivity
import com.example.tugas.ui.home.tagihan.detail.DetailTagihanActivity
import com.example.tugas.ui.home.tagihan.list.ListTagihanActivity
import com.example.tugas.ui.notifikasi.NotificationActivity
import com.example.tugas.ui.pembayaran.pbb.PbbCariNopActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: LoginActivity)
    fun inject(activity: ResetPasswordActivity)
    fun inject(activity: RegisterActivity)
    fun inject(activity: HomeActivity)
    fun inject(activity: NotificationActivity)
    fun inject(activity: PbbCariNopActivity)
    fun inject(activity: DetailTagihanActivity)
    fun inject(activity: ListTagihanActivity)

}