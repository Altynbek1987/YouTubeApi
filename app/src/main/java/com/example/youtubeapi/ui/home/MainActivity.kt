package com.example.youtubeapi.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.youtubeapi.R
import com.example.youtubeapi.adapter.MainAdapter
import com.example.youtubeapi.data.models.PlayList
import com.example.youtubeapi.data.models.PlaylistItems
import com.example.youtubeapi.interfa.OnItemClickListener
import com.example.youtubeapi.ui.detail.DetailVideoActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity: AppCompatActivity(), OnItemClickListener {
    private var listUrlMA : MutableList<PlaylistItems> = mutableListOf()
    private lateinit var adapter: MainAdapter
    private val viewModel by inject<MainViewModel>()
    private lateinit var playList:PlayList
    private lateinit var playlistItems: PlaylistItems

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAdapter()
        fetchPlaylists()
    }
    private fun setAdapter(){
        adapter = MainAdapter(this)
        recyclerView.adapter = adapter
        saveRoom()
        val  snap = LinearSnapHelper()
        snap.attachToRecyclerView(recyclerView)
    }
    private fun fetchPlaylists() {
        viewModel.fetchPlaylists()
        viewModel.playlistItems.observe(this, Observer {
            adapter.addItems(it)
            listUrlMA = it
        })
//        viewModel.fetchPlaylists().observe(this, Observer {
//            when (it.status) {
//                Status.SUCCESS -> {
//                    it.data?.items?.let { result -> adapter.addItems(result) }
//                    listUrlMA = it.data?.items!!
//                }
//                Status.ERROR -> showToast(it.message.toString())
//            }
//        })
    }

    fun saveRoom(){


//        viewModel.save()
//        this.showToast("Save to room")
//        Log.e("ooo"," MainActivity override fun onBackPressed() "  +viewModel.save(playList))
    }

    override fun itemClick(position: Int) {
       // DetailVideoActivity.instanceActivity(this, )
       val intent = Intent(this, DetailVideoActivity::class.java)
        intent.putExtra("id",listUrlMA[position].id)
          startActivity(intent)
        Log.e("ooo","itemClick(position: Int)"+ listUrlMA[position].id)
    }
}

