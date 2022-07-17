package com.example.tugas.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugas.data.repository.PembayaranRepository
import com.example.tugas.data.repository.TagihanRepository
import com.example.tugas.data.repository.UserRepository
import com.example.tugas.ui.auth.forget.ResetPasswordViewModel
import com.example.tugas.ui.auth.login.LoginViewModel
import com.example.tugas.ui.auth.register.RegisterViewModel
import com.example.tugas.ui.base.BaseActivity
import com.example.tugas.ui.common.dialog.LoadingDialog
import com.example.tugas.ui.home.HomeViewModel
import com.example.tugas.ui.home.tagihan.detail.DetailTagihanViewModel
import com.example.tugas.ui.home.tagihan.list.ListTagihanViewModel
import com.example.tugas.ui.notifikasi.NotificationViewModel
import com.example.tugas.ui.pembayaran.pbb.PbbCariNopViewModel
import com.example.tugas.utils.ViewModelProviderFactory
import com.example.tugas.utils.network.NetworkHelper
import com.example.tugas.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


/**
 * Kotlin Generics Reference: https://kotlinlang.org/docs/reference/generics.html
 * Basically it means that we can pass any class that extends BaseActivity which take
 * BaseViewModel subclass as parameter
 */
@Module
class ActivityModule(private val activity: BaseActivity<*, *>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideLoadingDialog() = LoadingDialog()

    @Provides
    fun provideLoginViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): LoginViewModel =
        ViewModelProvider(activity, ViewModelProviderFactory(LoginViewModel::class) {
            LoginViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
        })[LoginViewModel::class.java]

    @Provides
    fun provideResetPasswordViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): ResetPasswordViewModel =
        ViewModelProvider(activity, ViewModelProviderFactory(ResetPasswordViewModel::class) {
            ResetPasswordViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper,
                userRepository
            )
        })[ResetPasswordViewModel::class.java]

    @Provides
    fun provideRegisterViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): RegisterViewModel =
        ViewModelProvider(activity, ViewModelProviderFactory(RegisterViewModel::class) {
            RegisterViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
        })[RegisterViewModel::class.java]

    @Provides
    fun provideHomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): HomeViewModel =
        ViewModelProvider(activity, ViewModelProviderFactory(HomeViewModel::class) {
            HomeViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
        })[HomeViewModel::class.java]

    @Provides
    fun provideNotificationViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): NotificationViewModel =
        ViewModelProvider(activity, ViewModelProviderFactory(NotificationViewModel::class) {
            NotificationViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper,
                userRepository
            )
        })[NotificationViewModel::class.java]

    @Provides
    fun providePbbCariNopViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        pembayaranRepository: PembayaranRepository
    ): PbbCariNopViewModel =
        ViewModelProvider(activity, ViewModelProviderFactory(PbbCariNopViewModel::class) {
            PbbCariNopViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper,
                pembayaranRepository
            )
        })[PbbCariNopViewModel::class.java]

    @Provides
    fun provideListTagihanViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        tagihanRepository: TagihanRepository,
    ): ListTagihanViewModel =
        ViewModelProvider(activity, ViewModelProviderFactory(ListTagihanViewModel::class) {
            ListTagihanViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper,
                tagihanRepository
            )
        })[ListTagihanViewModel::class.java]

    @Provides
    fun provideDetailTagihanViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        tagihanRepository: TagihanRepository,
        pembayaranRepository: PembayaranRepository
    ): DetailTagihanViewModel =
        ViewModelProvider(activity, ViewModelProviderFactory(DetailTagihanViewModel::class) {
            DetailTagihanViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper,
                tagihanRepository,
                pembayaranRepository
            )
        })[DetailTagihanViewModel::class.java]
}