package com.example.youtubeapi.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.youtubeapi.data.models.PlayList
import com.example.youtubeapi.data.models.PlaylistItems

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(playlist: PlayList): Long


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(playlistItems: PlayList)


    @Query("SELECT*FROM playlist")
    fun getAll(): MutableList<PlayList>?
}