package com.example.youtubeapi

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application(){

    override fun onCreate() {
        super.onCreate()
//        val db = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java,"database-name"
//        ).build()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(mainModule,viewModelModule,databaseModule))
        }
    }


}