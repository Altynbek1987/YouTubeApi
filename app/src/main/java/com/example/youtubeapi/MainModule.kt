package com.example.youtubeapi

import android.app.Application
import androidx.room.Room
import com.example.youtubeapi.data.local.room.AppDatabase
import com.example.youtubeapi.data.local.room.DataBaseClient
import com.example.youtubeapi.data.network.RetrofitClient
import com.example.youtubeapi.repository.YouTubeRepository
import com.example.youtubeapi.ui.detail.DetailViewModel
import com.example.youtubeapi.ui.home.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

var mainModule: Module = module {
single { RetrofitClient() }
    single { RetrofitClient().instanceRetrofit() }

    factory { YouTubeRepository(get(),get()) }

//    factory { Room.databaseBuilder(
//        androidContext(),
//        AppDatabase::class.java,"database-name"
//    ).build() }

}
val databaseModule = module {
    factory { DataBaseClient().providerDatabase(androidContext()) }
    factory { DataBaseClient().provideHistoryDao(get()) }

}
var viewModelModule = module {
    viewModel{MainViewModel(get())}
    viewModel { DetailViewModel(get()) }


}
//fun provideDatabase(application: Application): AppDatabase {
//    return Room.databaseBuilder(application, AppDatabase::class.java, "countries")
//        .fallbackToDestructiveMigration()
//        .build()
//}
//factory { provideDatabase(application = Application()) }