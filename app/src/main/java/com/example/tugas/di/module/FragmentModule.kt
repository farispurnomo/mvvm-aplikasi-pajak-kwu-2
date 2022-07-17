package com.example.tugas.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugas.data.repository.CommonRepository
import com.example.tugas.data.repository.TagihanRepository
import com.example.tugas.ui.base.BaseFragment
import com.example.tugas.ui.home.dashboard.DashboardViewModel
import com.example.tugas.ui.home.pengaturan.PengaturanViewModel
import com.example.tugas.ui.home.tagihan.TagihanViewModel
import com.example.tugas.utils.ViewModelProviderFactory
import com.example.tugas.utils.network.NetworkHelper
import com.example.tugas.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: BaseFragment<*, *>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun provideDashboardViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        commonRepository: CommonRepository
    ): DashboardViewModel =
        ViewModelProvider(fragment,
            ViewModelProviderFactory(DashboardViewModel::class) {
                DashboardViewModel(
                    schedulerProvider, compositeDisposable, networkHelper, commonRepository
                )
            }
        )[DashboardViewModel::class.java]

    @Provides
    fun provideRiwayatViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        tagihanRepository: TagihanRepository
    ): TagihanViewModel =
        ViewModelProvider(fragment,
            ViewModelProviderFactory(TagihanViewModel::class) {
                TagihanViewModel(
                    schedulerProvider,
                    compositeDisposable,
                    networkHelper,
                    tagihanRepository
                )
            }
        )[TagihanViewModel::class.java]

    @Provides
    fun providePengaturanViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
    ): PengaturanViewModel =
        ViewModelProvider(fragment,
            ViewModelProviderFactory(PengaturanViewModel::class) {
                PengaturanViewModel(schedulerProvider, compositeDisposable, networkHelper)
            }
        )[PengaturanViewModel::class.java]
}