package com.example.youtubeapi.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlayListDetail(
    var kind: String? = null,
    var etag: String? = null,
    var nextPageToken: String? = null,
    var prevPageToken:String? = null,
    var items: MutableList<DetailVideo>? = null
)
data class DetailVideo(
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
    var snippetD: SnippetD? = null,
    var pageInfo : PageInfo? = null
)
data class SnippetD(
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
    var thumbnailsD: ThumbnailsD? = null
)
data class ThumbnailsD(
    @SerializedName("medium")
    @Expose
    var mediumD: MediumD? = null
)
data class MediumD(
    @SerializedName("url")
    @Expose
    var urlD: String? = null
)
data class PageInfo(
    @SerializedName("totalResults")
    @Expose
    var totalResults: String? = null,
    @SerializedName("resultsPerPage")
    @Expose
    var resultsPerPage: String? = null

)