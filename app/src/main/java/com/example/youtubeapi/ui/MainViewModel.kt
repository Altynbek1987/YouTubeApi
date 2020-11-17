package com.example.youtubeapi.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapi.models.PlayList
import com.example.youtubeapi.repository.YouTubeRepository

class MainViewModel : ViewModel() {

    fun fetchMain(): MutableLiveData<PlayList?> {
        return YouTubeRepository().fetchPlayListsFromNetwork()
    }
}