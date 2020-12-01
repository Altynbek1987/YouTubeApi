package com.example.youtubeapi.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.firstapp.extensions.showToast
import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.data.local.room.AppDatabase
import com.example.youtubeapi.data.models.PlayList
import com.example.youtubeapi.data.models.PlayListDetail
import com.example.youtubeapi.data.models.PlaylistItems
import com.example.youtubeapi.data.network.Resource
import com.example.youtubeapi.data.network.Status
import com.example.youtubeapi.repository.YouTubeRepository

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
        Log.e("ppp","MainViewModel fun loadData()"+repository.loadPlaylist()?.items)
    }

}