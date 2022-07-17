package com.example.tugas.ui.home.tagihan.list

import androidx.lifecycle.MutableLiveData
import com.example.tugas.data.model.RiwayatTagihanModel
import com.example.tugas.data.repository.TagihanRepository
import com.example.tugas.ui.base.BaseViewModel
import com.example.tugas.utils.common.Resource
import com.example.tugas.utils.network.NetworkHelper
import com.example.tugas.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ListTagihanViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val tagihanRepository: TagihanRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    val tagihan: MutableLiveData<List<RiwayatTagihanModel>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCreate() {
        onGetListTagihan()
    }

    fun onGetListTagihan() {
        if (checkInternetConnectionWithMessage()) {
            isLoading.postValue(true)

            compositeDisposable.add(
                tagihanRepository.doGetTagihan()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            isLoading.postValue(false)
                            if (it.status == "200") {

                                tagihan.postValue(it.data!!)
                            } else {
                                messageString.postValue(Resource.error(it.msg))
                            }
                        },
                        {
                            handleNetworkError(it)
                            isLoading.postValue(false)
                        }
                    )
            )
        }
    }

}