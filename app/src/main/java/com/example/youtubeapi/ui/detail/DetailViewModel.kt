package com.example.youtubeapi.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapi.data.models.PlayListDetail
import com.example.youtubeapi.data.network.Resource
import com.example.youtubeapi.repository.YouTubeRepository

class DetailViewModel : ViewModel()  {

    fun fetchPlayListsItems(pageToken:String?,playlistId:String): LiveData<Resource<PlayListDetail>> {
        return YouTubeRepository().fetchPlayListsItems(pageToken,playlistId)
    }
}