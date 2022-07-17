package com.example.tugas.di.component

import com.example.tugas.di.FragmentScope
import com.example.tugas.di.module.FragmentModule
import com.example.tugas.ui.home.dashboard.DashboardFragment
import com.example.tugas.ui.home.pengaturan.PengaturanFragment
import com.example.tugas.ui.home.tagihan.TagihanFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {
    fun inject(fragment: DashboardFragment)
    fun inject(fragment: TagihanFragment)
    fun inject(fragment: PengaturanFragment)
}