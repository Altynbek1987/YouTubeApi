package com.example.youtubeapi.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.extensions.loadImage
import com.example.youtubeapi.R
import com.example.youtubeapi.data.models.DetailVideo
import com.example.youtubeapi.interfa.OnItemClickListener

class AdapterDialog (var onItemClickListener: OnItemClickListener): RecyclerView.Adapter<AdapterDialog.DialogHolder>(){

    var listBottomSh: MutableList<DetailVideo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bottom_sheet_holder,parent,false)
        return DialogHolder(view)
    }

    override fun getItemCount(): Int {
        return listBottomSh.size
    }

    override fun onBindViewHolder(holder: DialogHolder, position: Int) {
        holder.dialogBind(listBottomSh[position])
    }

    fun videoDialog(item: MutableList<DetailVideo>) {
        listBottomSh.addAll(item)
        notifyDataSetChanged()
    }
    inner class DialogHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageBottomSheet : ImageView = itemView.findViewById(R.id.image_bottom_sheet)

        fun dialogBind(detailVideo: DetailVideo) {
            imageBottomSheet.loadImage(detailVideo.snippet?.thumbnails?.medium?.url.toString())
//            itemView.setOnClickListener {
//                onItemClickListener.itemClick(listBottomSh[adapterPosition])
//            }
        }


    }
}