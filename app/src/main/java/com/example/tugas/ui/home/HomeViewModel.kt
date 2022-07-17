package com.example.tugas.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.tugas.data.model.UserModel
import com.example.tugas.data.repository.UserRepository
import com.example.tugas.ui.base.BaseViewModel
import com.example.tugas.utils.common.Event
import com.example.tugas.utils.network.NetworkHelper
import com.example.tugas.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    val tagihanNavigation = MutableLiveData<Event<Boolean>>()

    val user: MutableLiveData<UserModel> = MutableLiveData()
    val isLogout = MutableLiveData<Boolean>()

    override fun onCreate() {
        onGetDetailUser()
    }

    fun onLogout() {
        userRepository.removeCurrentUser()
        isLogout.postValue(true)
    }

    fun onTagihanNavigation() {
        tagihanNavigation.postValue(Event(true))
    }

    fun onGetDetailUser() {
        user.postValue(userRepository.getCurrentUser())
    }
}