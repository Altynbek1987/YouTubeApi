package com.example.youtubeapi.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.extensions.loadImage
import com.example.youtubeapi.R
import com.example.youtubeapi.data.models.DetailVideo
import com.example.youtubeapi.interfa.OnItemClickListener

class DetailPlayListAdapter(var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<DetailPlayListAdapter.DetailViewHolder>() {

    var detailList: MutableList<DetailVideo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.detail_play_list_holder, parent, false)
        return DetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return detailList.count()
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.detailBind(detailList[position])
    }

    fun detailItems(item: MutableList<DetailVideo>) {
        detailList.addAll(item)
        notifyDataSetChanged()
    }

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageVideo: ImageView = itemView.findViewById(R.id.image_detail)
        val titleVideo: TextView = itemView.findViewById(R.id.tv_title_video)

        fun detailBind(detailVideo: DetailVideo) {
            imageVideo.loadImage(detailVideo.snippet?.thumbnails?.medium?.url.toString())
            titleVideo.text = detailVideo.snippet?.title

            itemView.setOnClickListener {
                onItemClickListener.itemClick(adapterPosition)
            }
        }
    }
}
