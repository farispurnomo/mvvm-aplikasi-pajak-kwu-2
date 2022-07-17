package com.example.tugas.ui.notifikasi

import androidx.lifecycle.MutableLiveData
import com.example.tugas.data.model.NotificationModel
import com.example.tugas.data.repository.UserRepository
import com.example.tugas.ui.base.BaseViewModel
import com.example.tugas.utils.common.Resource
import com.example.tugas.utils.network.NetworkHelper
import com.example.tugas.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class NotificationViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    val notification: MutableLiveData<List<NotificationModel>> = MutableLiveData()
    override fun onCreate() {
        onGetListNotifikasi()
    }

    fun onGetListNotifikasi() {
        if (checkInternetConnectionWithMessage()) {
            compositeDisposable.add(
                userRepository.doGetListNotifikasi()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            if (it.status == "200") {
//                                notification.postValue(it.data!!)
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
}