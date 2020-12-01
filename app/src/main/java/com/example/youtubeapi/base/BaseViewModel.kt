package com.example.youtubeapi.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapi.data.models.DetailVideo
import com.example.youtubeapi.data.models.PlaylistItems

open class BaseViewModel : ViewModel() {
    var errorMessage = MutableLiveData<String>()
    var playlistItems = MutableLiveData<MutableList<PlaylistItems>>()
    var detailPlaylists = MutableLiveData<MutableList<DetailVideo>>()
}