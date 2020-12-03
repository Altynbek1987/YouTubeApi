package com.example.youtubeapi.interfa

import com.example.youtubeapi.data.models.DetailVideo

interface OnItemClickListener {
    fun itemClick(position:Int)
    fun itemClick(model: DetailVideo)

}