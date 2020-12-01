package com.example.youtubeapi.ui.home

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.youtubeapi.R
import com.example.youtubeapi.adapter.MainAdapter
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.data.models.PlaylistItems
import com.example.youtubeapi.interfa.OnItemClickListener
import com.example.youtubeapi.ui.detail.DetailPlayListActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import java.util.*

class MainActivity: BaseActivity<MainViewModel>(R.layout.activity_main), OnItemClickListener {
    private var listUrlMA : MutableList<PlaylistItems> = mutableListOf()
    private lateinit var adapter: MainAdapter
    override val viewModel by inject<MainViewModel>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_YouTubeApi)
        super.onCreate(savedInstanceState)
    }

    override fun setupViews() {
        setAdapter()
    }
    override fun setupLiveData() {
        fetchPlaylists()
    }

    private fun setAdapter(){
        adapter = MainAdapter(this)
        recyclerView.adapter = adapter
        val  snap = LinearSnapHelper()
        snap.attachToRecyclerView(recyclerView)
    }
    private fun fetchPlaylists() {
        viewModel.fetchPlaylists()
        viewModel.playlistItems.observe(this, Observer {
            adapter.addItems(it)
            listUrlMA = it
        })
    }

    override fun itemClick(position: Int) {
       val intent = Intent(this, DetailPlayListActivity::class.java)
        intent.putExtra("id",listUrlMA[position].id)
          startActivity(intent)
        Log.e("ooo","itemClick(position: Int)"+ listUrlMA[position].id)
    }


    override fun setupFetchRequests() {

    }



}

