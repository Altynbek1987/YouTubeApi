package com.example.youtubeapi.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class PlayListDetail(

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Long? = null,

    @SerializedName("nextPageToken")
    var nextPageToken: String? = null,

    @SerializedName("items")
    var items: MutableList<DetailVideo>? = null

) : Serializable

data class DetailVideo(

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("snippet")
    var snippet: Snippet? = null

) : Serializable

data class ResourceId(

    @SerializedName("videoId")
    var videoId: String? = null

) : Serializable


