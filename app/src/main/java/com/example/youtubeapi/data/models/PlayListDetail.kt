package com.example.youtubeapi.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
@Entity
data class PlayListDetail(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    var kind: String? = null,

    var etag: String? = null,

    var nextPageToken: String? = null,

    var prevPageToken:String? = null,

    var items: MutableList<DetailVideo>? = null
)
data class DetailVideo(

    var kind: String? = null,

    var etag: String? = null,

    var id: String? = null,

    var snippet: Snippet? = null
)


