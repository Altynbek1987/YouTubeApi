package com.example.youtubeapi.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.youtubeapi.data.models.PlayList

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    open fun insert(playlistItems: PlayList): Long


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(playlistItems: PlayList)


    @Query("SELECT*FROM playList")
    fun getAll(): LiveData<List<PlayList>?>?
}