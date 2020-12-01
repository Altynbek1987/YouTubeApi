package com.example.youtubeapi.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.data.models.DetailVideo
import com.example.youtubeapi.data.models.PlayListDetail
import com.example.youtubeapi.data.network.Resource
import com.example.youtubeapi.repository.YouTubeRepository

class DetailVideoViewModel(var repository: YouTubeRepository): BaseViewModel() {

//    fun fetchVideoDetail(playlistId:String,pageToken:String?): LiveData<Resource<PlayListDetail?>> {
//        return repository.fetchVideoDetail(playlistId,pageToken)
//    }
}