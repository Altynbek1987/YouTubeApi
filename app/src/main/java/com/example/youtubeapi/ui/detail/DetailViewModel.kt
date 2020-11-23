package com.example.youtubeapi.ui.detail

import androidx.lifecycle.*
import com.example.youtubeapi.data.models.DetailVideo
import com.example.youtubeapi.data.models.PlayListDetail
import com.example.youtubeapi.data.network.Resource
import com.example.youtubeapi.data.network.Status
import com.example.youtubeapi.repository.YouTubeRepository

class DetailViewModel (var repository: YouTubeRepository): ViewModel()  {
    var errorMessage = MutableLiveData<String>()
    var detailPlaylists = MutableLiveData<MutableList<DetailVideo>>()
    var detail: MutableList<DetailVideo>? = mutableListOf()
    var playlistId: String? = null

    fun fetchPlayListsItems(playlistId:String,nextPageToken:String?): LiveData<Resource<PlayListDetail>> {
        if (nextPageToken != null){
            repository.fetchPlayListsItems(playlistId,nextPageToken).observeForever {
                when(it.status){
                    Status.SUCCESS->getAllVideo(it?.data)
                    Status.ERROR -> errorMessage.value = it.message.toString()
                }
            }
        }
        return repository.fetchPlayListsItems(playlistId,nextPageToken)
    }
        private fun getAllVideo(data: PlayListDetail?) {
        data?.items?.let { detail?.addAll(it) }
        if (!data?.nextPageToken.isNullOrEmpty()) playlistId?.let { fetchPlayListsItems(it, data?.nextPageToken) }
        else detailPlaylists.value = detail
    }

    //
//    fun fetchPlaylistVideo(playlistId: String?, nextPageToken: String? = null) {
//        this.playlistId = playlistId
//        if (nextPageToken != null) {
//            repository.fetchPlayListsItems(playlistId,nextPageToken).observeForever {
//                when (it.status) {
//                    Status.SUCCESS -> getAllVideo(it?.data)
//                    Status.ERROR -> errorMessage.value = it.message.toString()
//                }
//            }
//            Log.e("ooo","fetchPlaylistVideo"+ this.playlistId)
//        }
//    }
//    private fun getAllVideo(data: PlayList?) {
//        data?.items?.let { detail?.addAll(it) }
//        if (!data?.nextPageToken.isNullOrEmpty()) fetchPlaylistVideo(playlistId, data?.nextPageToken)
//        else detailPlaylists.value = detail
//    }

}


