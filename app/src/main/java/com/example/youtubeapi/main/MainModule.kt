package com.example.youtubeapi.main

import com.example.youtubeapi.data.local.pref.Preferences
import com.example.youtubeapi.data.local.room.DataBaseClient
import com.example.youtubeapi.data.network.RetrofitClient
import com.example.youtubeapi.data.repository.YouTubeRepository
import com.example.youtubeapi.ui.detail.DetailPlayListViewModel
import com.example.youtubeapi.ui.home.MainViewModel
import com.example.youtubeapi.ui.video.DetailVideoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

var mainModule: Module = module {
single { RetrofitClient() }
    single { RetrofitClient().instanceRetrofit() }
    single { YouTubeRepository(get(),get()) }
    single { Preferences(get()) }

}
val databaseModule = module {
    single { DataBaseClient().providerDatabase(androidContext()) }
    single { DataBaseClient().provideHistoryDao(get()) }

}
var viewModelModule = module {
    viewModel{MainViewModel(get())}
    viewModel { DetailPlayListViewModel(get()) }
    viewModel { DetailVideoViewModel (get())}

}

