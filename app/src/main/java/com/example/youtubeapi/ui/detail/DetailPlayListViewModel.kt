package com.example.youtubeapi.ui.detail

import androidx.lifecycle.*
import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.data.models.PlayListDetail
import com.example.youtubeapi.data.network.Resource
import com.example.youtubeapi.data.repository.YouTubeRepository

class DetailPlayListViewModel(var repository: YouTubeRepository) : BaseViewModel() {
    fun fetchPlayListsItems(
        playlistId: String,
        pageToken: String?
    ): LiveData<Resource<PlayListDetail?>> {
        return repository.fetchPlayListsItems(playlistId, pageToken)
    }

    fun loadDataVideo() {
        detailPlaylists.postValue(repository.loadDetailPlayList()?.items)
    }
}


