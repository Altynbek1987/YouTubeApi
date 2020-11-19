package com.example.youtubeapi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapi.data.models.PlayList
import com.example.youtubeapi.data.models.PlayListDetail
import com.example.youtubeapi.data.network.Resource
import com.example.youtubeapi.repository.YouTubeRepository

class MainViewModel : ViewModel() {

    fun fetchPlaylists(): LiveData<Resource<PlayList>> {
        return YouTubeRepository().fetchPlayLists()
    }

}