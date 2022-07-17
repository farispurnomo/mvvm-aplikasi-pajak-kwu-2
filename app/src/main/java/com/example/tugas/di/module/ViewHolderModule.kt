package com.example.tugas.di.module

import androidx.lifecycle.LifecycleRegistry
import com.example.tugas.di.ViewModelScope
import com.example.tugas.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}