package com.example.youtubeapi.ui.home

import androidx.lifecycle.LiveData
import com.example.youtubeapi.data.network.connection.NetworkConnection
import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.data.network.Status
import com.example.youtubeapi.data.repository.YouTubeRepository

class MainViewModel(var repository: YouTubeRepository,private val networkConnection: NetworkConnection) : BaseViewModel() {
    fun fetchPlaylists() {
        repository.fetchPlayLists().observeForever {
            when (it.status) {
                Status.SUCCESS -> playlistItems.postValue(it.data?.items)
                Status.ERROR -> {
                    errorMessage.postValue(it.message.toString())
                    loadData()
                }
            }
        }
    }

    fun networkLiveData() : LiveData<Boolean> {
        return networkConnection
    }

    fun loadData() {
        playlistItems.postValue(repository.loadPlaylist()?.items)
    }
}