package com.example.youtubeapi.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class PlayList(
    @PrimaryKey(autoGenerate = true)

    @SerializedName("id")
    @Expose
    var id: Long? = null,

    @SerializedName("kind")
    @Expose
    var kind: String? = null,

    @SerializedName("etag")
    @Expose
    var etag: String? = null,

    @SerializedName("items")
    @Expose
    var items: MutableList<PlaylistItems>? = null,

    @SerializedName("nextPageToken")
    @Expose
    var nextPageToken: String? = null
)



data class PlaylistItems(

    @SerializedName("kind")
    @Expose
    var kind: String? = null,

    @SerializedName("etag")
    @Expose
    var etag: String? = null,

    @SerializedName("id")
    @Expose
    var id: String? = null,

    var pageInfo: PageInfo? = null,

    @SerializedName("snippet")
    @Expose
    var snippet: Snippet? = null,

    @SerializedName("contentDetails")
    @Expose
    var contentDetails: ContentDetails? = null,

    @SerializedName("nextPageToken")
    @Expose
    var nextPageToken: String? = null

)

data class PageInfo(
    @SerializedName("totalResults")
    @Expose
    var totalResults: String? = null,
    @SerializedName("resultsPerPage")
    @Expose
    var resultsPerPage: String? = null

)

data class Snippet(
    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String? = null,

    @SerializedName("channelId")
    @Expose
    var channelId: String? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("thumbnails")
    @Expose
    var thumbnails: Thumbnails? = null,

    @SerializedName("playlistId")
    @Expose
    var playlistId: String? = null
)

data class Thumbnails(
    @SerializedName("medium")
    @Expose
    var medium: Medium? = null
)

data class Medium(
    @SerializedName("url")
    @Expose
    var url: String? = null
)

data class ContentDetails(
    @SerializedName("itemCount")
    @Expose
    var itemCount: String? = null

)



