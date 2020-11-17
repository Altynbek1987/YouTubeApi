package com.example.youtubeapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.youtubeapi.R
import com.example.youtubeapi.adapter.MainAdapter
import com.example.youtubeapi.models.PlayList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var listUrlMA : MutableList<PlayList> = mutableListOf()
    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        fetchPlaylists()
        setAdapter()
    }
    private fun setAdapter(){
        adapter = MainAdapter()
        recyclerView.adapter = adapter
        val  snap = LinearSnapHelper()
        snap.attachToRecyclerView(recyclerView)
    }

    private fun fetchPlaylists(){
        viewModel.fetchMain().observe(this, Observer {
                adapter.addItems(it?.items!!)
        })
    }
}