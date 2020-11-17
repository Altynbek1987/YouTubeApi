package com.example.youtubeapi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.extensions.loadImage
import com.example.youtubeapi.R
import com.example.youtubeapi.models.PlaylistItems

class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var listUrl: MutableList<PlaylistItems> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_holder,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listUrl.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(listUrl[position])
    }

    fun addItems(item: MutableList<PlaylistItems>) {
        this.listUrl = item
        notifyDataSetChanged()
    }

   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
       val image : ImageView = itemView.findViewById(R.id.image_holder)
       val title: TextView = itemView.findViewById(R.id.tv_title)
       val count: TextView = itemView.findViewById(R.id.tv_amount_series)

        fun onBind(playList: PlaylistItems) {
            image.loadImage(playList.snippet?.thumbnails?.medium?.url.toString())
            title.text = playList.snippet?.title
            Log.v("RESULT_Adapter",toString())

            image.setOnClickListener {

            }

        }

    }
}



