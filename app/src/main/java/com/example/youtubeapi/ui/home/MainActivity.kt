package com.example.youtubeapi.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.firstapp.extensions.showToast
import com.example.youtubeapi.R
import com.example.youtubeapi.adapter.MainAdapter
import com.example.youtubeapi.data.models.PlaylistItems
import com.example.youtubeapi.data.network.Status
import com.example.youtubeapi.interfa.OnItemClickListener
import com.example.youtubeapi.ui.detail.DetailVideoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private var listUrlMA : MutableList<PlaylistItems> = mutableListOf()
    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainViewModel


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setAdapter()
        fetchPlaylists()
    }
    private fun setAdapter(){
        adapter = MainAdapter(this)
        recyclerView.adapter = adapter
        val  snap = LinearSnapHelper()
        snap.attachToRecyclerView(recyclerView)
    }

    private fun fetchPlaylists() {
        viewModel.fetchPlaylists().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.items?.let { result -> adapter.addItems(result) }
                    listUrlMA = it.data?.items!!
                }
                Status.ERROR -> showToast(it.message.toString())
            }
        })
    }


    override fun itemClick(position: Int) {
       val intent = Intent(this, DetailVideoActivity::class.java)
        intent.putExtra("id", listUrlMA[position].id)
          startActivity(intent)
    }
}

