package com.example.tugas.ui.home.tagihan

import androidx.lifecycle.MutableLiveData
import com.example.tugas.data.model.RiwayatTagihanModel
import com.example.tugas.data.model.TagihanModel
import com.example.tugas.data.repository.TagihanRepository
import com.example.tugas.ui.base.BaseViewModel
import com.example.tugas.utils.common.Resource
import com.example.tugas.utils.network.NetworkHelper
import com.example.tugas.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class TagihanViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val tagihanRepository: TagihanRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    val tagihan: MutableLiveData<TagihanModel> = MutableLiveData()
    val riwayat: MutableLiveData<List<RiwayatTagihanModel>> = MutableLiveData()
    override fun onCreate() {
        onGetTagihan()
        onGetRiwayat()
    }

    fun onGetTagihan() {
        if (checkInternetConnectionWithMessage()) {
            compositeDisposable.add(
                tagihanRepository.doGetTagihanAktif()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            if (it.status == "200") {
                                tagihan.postValue(it.data!!)
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

    fun onGetRiwayat() {
        if (checkInternetConnectionWithMessage()) {
            compositeDisposable.add(
                tagihanRepository.doGetRiwayatTagihan()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            if (it.status == "200") {
                                riwayat.postValue(it.data!!)
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