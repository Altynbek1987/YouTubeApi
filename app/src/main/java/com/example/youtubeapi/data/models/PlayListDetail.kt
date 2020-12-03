package com.example.youtubeapi.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class PlayListDetail(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    var nextPageToken: String? = null,

    var items: MutableList<DetailVideo>? = null
)

data class DetailVideo(
    var id: String? = null,
    var snippet: Snippet? = null
) : Serializable

data class ResourceId(
    var videoId: String? = null
) : Serializable


