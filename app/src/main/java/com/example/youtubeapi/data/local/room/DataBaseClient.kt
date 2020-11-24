package com.example.youtubeapi.data.local.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

class DataBaseClient {

    internal fun providerDatabase(context: Context):AppDatabase?{
        return  Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "playlist.database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideHistoryDao(appDatabase: AppDatabase):HistoryDao?=appDatabase.historyDao()
}