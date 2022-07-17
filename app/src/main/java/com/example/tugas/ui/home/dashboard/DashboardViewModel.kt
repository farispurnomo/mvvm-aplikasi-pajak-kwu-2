package com.example.tugas.ui.home.dashboard

import androidx.lifecycle.MutableLiveData
import com.example.tugas.data.model.BannerModel
import com.example.tugas.data.model.MenuPembayaranModel
import com.example.tugas.data.repository.CommonRepository
import com.example.tugas.ui.base.BaseViewModel
import com.example.tugas.utils.common.Resource
import com.example.tugas.utils.network.NetworkHelper
import com.example.tugas.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class DashboardViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val commonRepository: CommonRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val listBanner: MutableLiveData<List<BannerModel>> = MutableLiveData()

    val isLoadingTotalSaldo: MutableLiveData<Boolean> = MutableLiveData()
    val totalSaldo: MutableLiveData<Int> = MutableLiveData()

    val menuPembayaranModel: MutableLiveData<List<MenuPembayaranModel>> = MutableLiveData()

    override fun onCreate() {
        onGetTotalSaldo()
        onGetListMenuPembayaran()
        onGetBanner()
    }

    fun onGetBanner() {
        if (checkInternetConnectionWithMessage()) {
            compositeDisposable.add(
                commonRepository.doGetBanner()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            if (it.status == "200") {

                                listBanner.postValue(it.data!!)
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

    fun onGetTotalSaldo() {
        if (checkInternetConnectionWithMessage()) {
            isLoadingTotalSaldo.postValue(true)
            compositeDisposable.add(
                commonRepository.doGetTotalSaldo()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            isLoadingTotalSaldo.postValue(false)
                            if (it.status == "200") {

                                totalSaldo.postValue(it.data ?: 0)
                            } else {
                                messageString.postValue(Resource.error(it.msg))
                            }
                        },
                        {
                            handleNetworkError(it)

                            isLoadingTotalSaldo.postValue(false)
                            totalSaldo.postValue(0)
                        }
                    )
            )
        }
    }

    fun onGetListMenuPembayaran() {
        if (checkInternetConnectionWithMessage()) {
            compositeDisposable.add(
                commonRepository.doGetMenuPembayaran()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            isLoadingTotalSaldo.postValue(false)
                            if (it.status == "200") {

                                menuPembayaranModel.postValue(it.data!!)
                            } else {
                                messageString.postValue(Resource.error(it.msg))
                            }
                        },
                        {
                            handleNetworkError(it)

                            isLoadingTotalSaldo.postValue(false)
                            totalSaldo.postValue(0)
                        }
                    )
            )
        }
    }
}