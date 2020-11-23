package com.example.youtubeapi.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.youtubeapi.conventer.ClassTypeConverter
import com.example.youtubeapi.data.models.*

@Database(entities = [PlayList::class],version = 1)
@TypeConverters(ClassTypeConverter::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun historyDao(): HistoryDao

}