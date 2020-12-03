package com.example.youtubeapi.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import com.example.youtubeapi.data.local.room.HistoryDao
import com.example.youtubeapi.data.models.*
import com.example.youtubeapi.data.network.Resource
import com.example.youtubeapi.data.network.YoutubeApi
import kotlinx.coroutines.Dispatchers

class YouTubeRepository(private var api: YoutubeApi, var dao: HistoryDao) {
    val part = "snippet,contentDetails"
    val key = "AIzaSyAHFfInYFsMdLO5h8GDO2wm3m84I5_7lyo"
    val channelId = "UCPz3xmUpIbo8jooCtV_vMNw"
//UCbSFAcyBtP3hp--0PmKF95Q   JOLDO.KG

    fun fetchPlayLists() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val result = api.fetchPlayLists(part, key, channelId)
            emit(Resource.success(data = result))
            Log.e("vvv","${result.items?.size}")
            dao.insertPlayList(result)
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }

//    suspend fun fetch(total: PlayList?):PlayList?{
//        val newTotal = api.fetchPlayLists(part, key, channelId)
//        if (total!=null)total.items?.addAll(mutableListOf())
//        return fetch(total)
//    }
    fun fetchPlayListsItems(playlistId: String, pageToken: String?) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val dataPlayL = fetchDetail(playlistId, pageToken, null)
            emit(Resource.success(data = dataPlayL))
                dao.insertDetail(dataPlayL)
            Log.e("ddd","YouTubeRepository fetchPlayListsItems ${dataPlayL?.items?.size}")
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }
    suspend fun fetchDetail(
        playlistId: String,
        pageToken: String?,
        data: PlayListDetail?
    ): PlayListDetail? {
        val newData = api.fetchPlayListsItems(part, key, playlistId, pageToken)
        if (data != null) data.items?.let { newData.items?.addAll(it) }
        Log.e("rrr"," ${data?.items?.size}")
        return if (newData.nextPageToken != null) fetchDetail(playlistId, newData.nextPageToken, data = newData)
        else newData
    }

    fun loadPlaylist(): PlayList? {
        return dao.getAll()
    }

    fun loadDetailPlayList(): PlayListDetail? {
        return dao.getDetail()
    }

    fun fetchVideoDetail(playlistId: String, pageToken: String?) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            val dataPlayL = fetchDetail(playlistId, pageToken, null)
            emit(Resource.success(data = dataPlayL))
            dao.insertDetail(dataPlayL)
            Log.e("ddd","YouTubeRepository fetchPlayListsItems ${dataPlayL?.items?.size}")
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }

//    fun fetchVideoDetailVideo(id : String) = liveData(Dispatchers.IO){
//        emit(Resource.loading(data = null))
//        try {
//            val dataPlayL = fetchDetailVideo(id,null)
//            emit(Resource.success(data = dataPlayL))
//            Log.e("ddd","YouTubeRepository fetchPlayListsItems ${dataPlayL?.id}")
//        } catch (e: Exception) {
//            emit(Resource.error(data = null, message = e.message ?: "Error"))
//        }
//    }
//    suspend fun fetchDetailVideo(
//        id: String,
//        data: DetailVideo?
//    ): DetailVideo? {
//        val newData = api.fetchPlayListsVideo(part,key,id)
//        if (data != null) data.id?.let { newData.id }
//        Log.e("rrr"," ${data?.id}")
//        return if (newData.id != null) fetchDetailVideo(id, data = newData)
//        else newData
//    }

}