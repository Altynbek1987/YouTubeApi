package com.example.youtubeapi.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.youtubeapi.data.models.PlayList
import com.example.youtubeapi.data.models.PlayListDetail

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayList(playlist: PlayList)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(playListDetail: PlayListDetail?)


    @Query("SELECT*FROM playlist")
    fun getAll(): PlayList?

    @Query("SELECT*FROM playlistdetail")
    fun getDetail(): PlayListDetail?




    @Query("SELECT*FROM playlistdetail WHERE id=:id")
    fun getId(id: String): MutableList<PlayListDetail>?

    @Query("SELECT*FROM playlistdetail WHERE id=:id")
    fun getById(id: Int): MutableList<PlayListDetail>?
}