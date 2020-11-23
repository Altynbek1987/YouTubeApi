package com.example.youtubeapi.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.youtubeapi.data.local.room.AppDatabase
import com.example.youtubeapi.data.models.PlayList
import com.example.youtubeapi.data.models.PlayListDetail
import com.example.youtubeapi.data.models.PlaylistItems
import com.example.youtubeapi.data.network.Resource
import com.example.youtubeapi.data.network.Status
import com.example.youtubeapi.repository.YouTubeRepository

class MainViewModel (var repository: YouTubeRepository,var roomDatabase: AppDatabase): ViewModel() {
    var errorMessage = MutableLiveData<String>()
    var playlists = MutableLiveData<MutableList<PlaylistItems>>()

fun fetchPlaylists(){
    repository.fetchPlayLists().observeForever {
        when(it.status){
            Status.SUCCESS->playlists.postValue(it.data?.items)
            Status.ERROR -> errorMessage.postValue(it.message.toString())
        }

    }
}
    fun save(playList:PlayList){
        roomDatabase.historyDao().insert(playList)
//        roomDatabase.historyDao().getAll()
//        Log.e("ooo"," MainViewModel RESULT" +roomDatabase.historyDao().getAll())
    }
//    fun fetchPlaylists(): LiveData<Resource<PlayList>> {
//        //return YouTubeRepository().fetchPlayLists()
//
//    }

}