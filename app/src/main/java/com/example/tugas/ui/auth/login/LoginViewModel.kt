package com.example.tugas.ui.auth.login

import androidx.lifecycle.MutableLiveData
import com.example.tugas.data.repository.UserRepository
import com.example.tugas.ui.base.BaseViewModel
import com.example.tugas.utils.common.Event
import com.example.tugas.utils.common.Resource
import com.example.tugas.utils.network.NetworkHelper
import com.example.tugas.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()
    val loggingIn: MutableLiveData<Boolean> = MutableLiveData()
    val isLogin = userRepository.getCurrentUser() != null

    override fun onCreate() {}

    fun onLogin(email: String, password: String) {
        if (checkInternetConnectionWithMessage()) {
            loggingIn.postValue(true)

            compositeDisposable.add(
                userRepository.doUserLogin(email, password)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            userRepository.removeCurrentUser()

                            loggingIn.postValue(false)
                            if (it.status == "200") {

                                it.data?.let { user ->
                                    userRepository.saveCurrentUser(user)
                                }
                                launchMain.postValue(Event(emptyMap()))
                            } else {
                                messageString.postValue(Resource.error(it.msg))
                            }
                        },
                        {
                            handleNetworkError(it)
                            loggingIn.postValue(false)
                        }
                    )
            )
        }
    }

    fun onForget(email: String) {
        if (checkInternetConnectionWithMessage()) {
            compositeDisposable.add(
                userRepository.doUserForgetPassword(email)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            loggingIn.postValue(false)
                            if (it.status == "200") {

                            } else {
                                messageString.postValue(Resource.error(it.msg))
                            }
                        },
                        {
                            handleNetworkError(it)
                        }
                    )
            )
        }
    }
    fun onmessage(title: String) {
        messageString.postValue(Resource.error(title))
    }
}