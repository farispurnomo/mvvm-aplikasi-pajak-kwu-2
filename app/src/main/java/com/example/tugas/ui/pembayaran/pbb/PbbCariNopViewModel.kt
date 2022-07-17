package com.example.tugas.ui.pembayaran.pbb

import androidx.lifecycle.MutableLiveData
import com.example.tugas.data.model.DetailNopModel
import com.example.tugas.data.repository.PembayaranRepository
import com.example.tugas.ui.base.BaseViewModel
import com.example.tugas.utils.common.Resource
import com.example.tugas.utils.network.NetworkHelper
import com.example.tugas.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class PbbCariNopViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val pembayaranRepository: PembayaranRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    val tahunTagihan: MutableLiveData<List<String>> = MutableLiveData()
    val detailTagihan: MutableLiveData<DetailNopModel> = MutableLiveData()

    override fun onCreate() {}

    fun onGetTahunTagihan(nop: String) {
        if (checkInternetConnectionWithMessage()) {
            compositeDisposable.add(
                pembayaranRepository.doGetPBBTahunTagihanByNop(nop)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            if (it.status == "200") {
                                tahunTagihan.postValue(it.data!!)
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

    fun onGetDetailNop(nop: String, tahun: String) {
        if (checkInternetConnectionWithMessage()) {
            compositeDisposable.add(
                pembayaranRepository.doGetPBBDetailPembayaran(nop, tahun)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            if (it.status == "200") {
                                detailTagihan.postValue(it.data!!)
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

    fun onBayartagihan(id: String) {
        if (checkInternetConnectionWithMessage()) {
            compositeDisposable.add(
                pembayaranRepository.doGetPBBBayarTagihan(id)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            if (it.status == "200") {
//                                tahunTagihan.postValue(it.data!!)
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