package com.example.youtubeapi.ui.video

import androidx.lifecycle.LiveData
import com.example.youtubeapi.data.network.connection.NetworkConnection
import com.example.youtubeapi.base.BaseViewModel

class DetailVideoViewModel(private val networkConnection: NetworkConnection): BaseViewModel() {

    fun networkLiveData() : LiveData<Boolean> {
        return networkConnection
    }
}