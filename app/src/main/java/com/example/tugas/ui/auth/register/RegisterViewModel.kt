package com.example.tugas.ui.auth.register

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.tugas.data.repository.UserRepository
import com.example.tugas.ui.base.BaseViewModel
import com.example.tugas.utils.common.Resource
import com.example.tugas.utils.network.NetworkHelper
import com.example.tugas.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class RegisterViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    val isSuccessRegister: MutableLiveData<Boolean> = MutableLiveData()
    override fun onCreate() {}

    fun onRegister(email: String, password: String, confirmPassword: String) {
        if (checkInternetConnectionWithMessage()) {
            if (email !="" && password !="" && confirmPassword != "") {
                if (password == confirmPassword) {
                    compositeDisposable.add(
                        userRepository.doUserRegister(email, password, confirmPassword)
                            .subscribeOn(schedulerProvider.io())
                            .subscribe(
                                {
                                    if (it.status == "200") {
//                                        messageString.postValue(Resource.error(it.msg))
                                        isSuccessRegister.postValue(true)
                                    } else {
                                        isSuccessRegister.postValue(false)
//                                        messageString.postValue(Resource.error(it.msg))
                                    }
                                }, {
                                    handleNetworkError(it)
                                }
                            )
                    )
                } else {
                    messageString.postValue(Resource.error("Kata Sandi Tidak Sama"))
                }
            }else{
                messageString.postValue(Resource.error("Tidak Boleh Kosong"))
            }
        }
    }
}