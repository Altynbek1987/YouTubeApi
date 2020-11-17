package com.example.youtubeapi.repository

import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi.models.PlayList
import com.example.youtubeapi.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YouTubeRepository (){
    val part = "snippet"
    val key = "AIzaSyAHFfInYFsMdLO5h8GDO2wm3m84I5_7lyo"
    val channelId = "UCbSFAcyBtP3hp--0PmKF95Q"

    private var api  = RetrofitClient().instanceRetrofit()

    fun fetchPlayListsFromNetwork(): MutableLiveData<PlayList?> {
        val data = MutableLiveData<PlayList?>()
        api.fetchPlayLists(part,key,channelId).enqueue(object : Callback<PlayList?> {
            override fun onFailure(call: Call<PlayList?>, t: Throwable) {
                data.value = null
            }
            override fun onResponse(call: Call<PlayList?>, response: Response<PlayList?>) {
                data.value = response.body()
            }
        })
        return data
    }
}