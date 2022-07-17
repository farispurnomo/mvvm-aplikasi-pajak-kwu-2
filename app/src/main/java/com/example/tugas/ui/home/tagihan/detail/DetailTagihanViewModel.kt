package com.example.tugas.ui.home.tagihan.detail

import androidx.lifecycle.MutableLiveData
import com.example.tugas.data.model.RiwayatTagihanModel
import com.example.tugas.data.repository.PembayaranRepository
import com.example.tugas.data.repository.TagihanRepository
import com.example.tugas.ui.base.BaseViewModel
import com.example.tugas.utils.network.NetworkHelper
import com.example.tugas.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class DetailTagihanViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val tagihanRepository: TagihanRepository,
    private val pembayaranRepository: PembayaranRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    val tagihan: MutableLiveData<RiwayatTagihanModel> = MutableLiveData()

    override fun onCreate() {}

    fun onGetDetailTagihan(tagihanId: String) {

    }

    fun onBayarTagihan(tagihanId: String) {

    }
}