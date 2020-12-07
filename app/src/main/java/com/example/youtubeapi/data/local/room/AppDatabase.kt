package com.example.youtubeapi.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.youtubeapi.conventer.ClassTypeConverter
import com.example.youtubeapi.conventer.TypeConverterVideo
import com.example.youtubeapi.data.models.*

@Database(entities = [PlayList::class, PlayListDetail::class], version = 1)
@TypeConverters(ClassTypeConverter::class, TypeConverterVideo::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}