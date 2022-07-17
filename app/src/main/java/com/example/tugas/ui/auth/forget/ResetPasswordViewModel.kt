package com.example.tugas.ui.auth.forget

import androidx.lifecycle.MutableLiveData
import com.example.tugas.data.repository.UserRepository
import com.example.tugas.ui.base.BaseViewModel
import com.example.tugas.utils.network.NetworkHelper
import com.example.tugas.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ResetPasswordViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    val isSuccessResetPassword: MutableLiveData<Boolean> = MutableLiveData()
    override fun onCreate() {}

    fun onResetPassword(email: String, password: String, confirmPassword: String, kode: String) {
        if (checkInternetConnectionWithMessage()) {
            compositeDisposable.add(
                userRepository.doUserUpdatePassword(email, password, confirmPassword,kode)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            if (it.status == "200") {
                                isSuccessResetPassword.postValue(true)
                            } else {
                                isSuccessResetPassword.postValue(false)
                            }
                        }, {
                            handleNetworkError(it)
                        }
                    )
            )
        }
    }
}