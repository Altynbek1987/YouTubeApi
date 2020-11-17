package com.example.youtubeapi.interfa

import com.example.youtubeapi.models.PlayList
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {
    @GET("youtube/v3/playlists")
    fun fetchPlayLists(
        @Query("part") part: String,
        @Query("key") key: String,
        @Query("channelId") channelId: String
    ): retrofit2.Call<PlayList>
}