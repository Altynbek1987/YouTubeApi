package com.example.youtubeapi.repository

import androidx.lifecycle.liveData
import com.example.youtubeapi.data.network.Resource
import com.example.youtubeapi.data.network.YoutubeApi
import kotlinx.coroutines.Dispatchers

class YouTubeRepository(private var api: YoutubeApi) {
    val part = "snippet,contentDetails"
    val key = "AIzaSyAHFfInYFsMdLO5h8GDO2wm3m84I5_7lyo"
    val channelId = "UCPz3xmUpIbo8jooCtV_vMNw"
//UCbSFAcyBtP3hp--0PmKF95Q   JOLDO.KG
   // private var api = RetrofitClient().instanceRetrofit()

    fun fetchPlayLists() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.fetchPlayLists(part, key, channelId)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }

    fun fetchPlayListsItems(playlistId: String,pageToken: String?) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data =  api.fetchPlayListsItems(part,key, playlistId,pageToken  )
                )
            )
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }
}