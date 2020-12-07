package com.example.youtubeapi.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class PlayList(
    @PrimaryKey(autoGenerate = true)

    @SerializedName("id")
    var id: Long? = null,

    @SerializedName("kind")
    var kind: String? = null,

    @SerializedName("etag")
    var etag: String? = null,

    @SerializedName("items")
    var items: MutableList<PlaylistItems>? = null,

    @SerializedName("nextPageToken")
    var nextPageToken: String? = null
): Serializable

data class PlaylistItems(

    @SerializedName("kind")
    var kind: String? = null,

    @SerializedName("etag")
    var etag: String? = null,

    @SerializedName("id")
    var id: String? = null,

    var pageInfo: PageInfo? = null,

    @SerializedName("snippet")
    var snippet: Snippet? = null,

    @SerializedName("contentDetails")
    var contentDetails: ContentDetails? = null,

    @SerializedName("nextPageToken")
    var nextPageToken: String? = null

): Serializable

data class PageInfo(

    @SerializedName("totalResults")
    var totalResults: String? = null,

    @SerializedName("resultsPerPage")
    var resultsPerPage: String? = null

): Serializable

data class Snippet(

    @SerializedName("publishedAt")
    var publishedAt: String? = null,

    @SerializedName("channelId")
    var channelId: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("thumbnails")
    var thumbnails: Thumbnails? = null,

    @SerializedName("playlistId")
    var playlistId: String? = null,

    @SerializedName("resourceId")
    var resourceId: ResourceId? = null

) : Serializable

data class Thumbnails(

    @SerializedName("medium")
    var medium: Medium? = null

): Serializable

data class Medium(

    @SerializedName("url")
    var url: String? = null

): Serializable

data class ContentDetails(

    @SerializedName("itemCount")
    var itemCount: String? = null

): Serializable



