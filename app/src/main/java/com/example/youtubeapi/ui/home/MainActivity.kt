@file:Suppress("PLUGIN_WARNING")

package com.example.youtubeapi.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.youtubeapi.R
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.data.models.DetailVideo
import com.example.youtubeapi.data.models.PlaylistItems
import com.example.youtubeapi.interfa.OnItemClickListener
import com.example.youtubeapi.ui.detail.DetailPlayListActivity
import com.example.youtubeapi.ui.home.adapter.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<MainViewModel>(R.layout.activity_main), OnItemClickListener {
    private var listUrlMA: MutableList<PlaylistItems> = mutableListOf()
    private lateinit var adapter: MainAdapter
    override val viewModel by inject<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_YouTubeApi)
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        viewModel.networkLiveData().observe(this, Observer { isConnected ->
            if (isConnected) {
                layoutDisconnect.visibility = View.GONE

                layoutConnect.visibility = View.VISIBLE
            } else {
                layoutConnect.visibility = View.GONE
                layoutDisconnect.visibility = View.VISIBLE
            }
        })
    }

    override fun onStop() {
        super.onStop()
        try {
            viewModel.networkLiveData().removeObservers(this)
        } catch (e: IllegalArgumentException ) {
        }
    }

    override fun setupViews() {
        setAdapter()
    }

    override fun setupLiveData() {
        fetchPlaylists()
    }

    override fun setupFetchRequests() {}

    private fun setAdapter() {
        adapter = MainAdapter(this)
        recyclerView.adapter = adapter
        val snap = LinearSnapHelper()
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
        intent.putExtra("id", listUrlMA[position].id)
        startActivity(intent)
    }

    override fun itemClick(item: DetailVideo) {}
}
