package com.example.youtubeapi.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

    data class PlayList(
            @SerializedName("kind")
            @Expose
            var kind: String? = null,
            @SerializedName("etag")
            @Expose
            var etag: String? = null,
            @SerializedName("items")
            @Expose
            var items: MutableList<PlaylistItems>? = null
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
            @SerializedName("snippet")
            @Expose
            var snippet: Snippet? = null,
            @SerializedName("pageInfo")
            @Expose
            var pageInfo: PageInfo? = null
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
            var thumbnails: Thumbnails? = null
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

    data class PageInfo(
            @SerializedName("totalResults")
            @Expose
            var totalResults: String? = null,
            @SerializedName("resultsPerPage")
            @Expose
            var resultsPerPage: String? = null
    )


