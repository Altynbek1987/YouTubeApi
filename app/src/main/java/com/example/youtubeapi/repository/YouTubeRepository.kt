package com.example.youtubeapi.repository

import androidx.lifecycle.liveData
import com.example.youtubeapi.data.network.Resource
import com.example.youtubeapi.data.network.RetrofitClient
import kotlinx.coroutines.Dispatchers

class YouTubeRepository (){
    val part = "snippet,contentDetails"
    val key = "AIzaSyAHFfInYFsMdLO5h8GDO2wm3m84I5_7lyo"
    val channelId = "UCbSFAcyBtP3hp--0PmKF95Q"

    private var api  = RetrofitClient().instanceRetrofit()

    fun fetchPlayLists()= liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = api.fetchPlayLists(part,key,channelId)))
            } catch (e: Exception) {
                emit(Resource.error(data = null, message =  e.message ?: "Error"))
            }
    }
    fun fetchPlayListsItems(pageToken:String?,playlistId:String)= liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {//////
            emit(Resource.success(data = api.fetchPlayListsItems(part,pageToken,playlistId,key)))
        }catch (e: Exception){
            emit(Resource.error(data = null, message =  e.message ?: "Error"))
        }
    }
}