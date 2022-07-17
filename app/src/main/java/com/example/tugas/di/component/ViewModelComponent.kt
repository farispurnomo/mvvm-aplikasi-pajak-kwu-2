package com.example.tugas.di.component

import com.example.tugas.di.ViewModelScope
import com.example.tugas.di.module.ViewHolderModule
//import com.example.tangansantri.ui.pos.posKategori.PosKategoriItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

//    fun inject(viewHolder: PosKategoriItemViewHolder)
}