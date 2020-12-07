package com.example.youtubeapi.ui.home

import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.data.network.Status
import com.example.youtubeapi.data.repository.YouTubeRepository

class MainViewModel(var repository: YouTubeRepository) : BaseViewModel() {
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

    fun loadData() {
        playlistItems.postValue(repository.loadPlaylist()?.items)
    }
}