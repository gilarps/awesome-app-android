package com.gilar.awesomeapp.di

import com.gilar.awesomeapp.view.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(
        )
    }

}
