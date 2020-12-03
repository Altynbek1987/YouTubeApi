package com.example.youtubeapi.ui.detail

import android.util.Log
import androidx.lifecycle.*
import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.data.models.PlayListDetail
import com.example.youtubeapi.data.network.Resource
import com.example.youtubeapi.data.repository.YouTubeRepository

class DetailPlayListViewModel (var repository: YouTubeRepository): BaseViewModel()  {

        fun fetchPlayListsItems(playlistId:String,pageToken:String?): LiveData<Resource<PlayListDetail?>> {
        return repository.fetchPlayListsItems(playlistId,pageToken)
    }
    fun loadDataVideo() {
        detailPlaylists.postValue(repository.loadDetailPlayList()?.items)
        Log.e("ppp", "class DetailViewModel fun loadDataVideo() ${repository.loadDetailPlayList()?.items?.size}")
    }
//    fun fetchPlayListsItems(playlistId:String,pageToken:String?): LiveData<Resource<PlayListDetail?>> {
//                fun fetchPlayListsItems(playlistId:String,pageToken:String?): LiveData<Resource<PlayListDetail?>> {
//        if (pageToken != null) {
//            repository.fetchPlayListsItems(playlistId, pageToken).observeForever{
//                when(it.status){
//                    Status.SUCCESS->detailPlaylists.postValue(it.data?.items)
//                    Status.ERROR -> {
//                        errorMessage.postValue(it.message.toString())
//                        Log.e("ddd","DetailViewModel fetchPlayListsItems ${it.data?.items?.size}")
//                        loadDataVideo()
//                    }
//                }
//            }
//        }
//        return repository.fetchPlayListsItems(playlistId,pageToken)
//    }
//    }
}


