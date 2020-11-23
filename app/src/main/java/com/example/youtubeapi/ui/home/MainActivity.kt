package com.example.youtubeapi.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.room.RoomDatabase
import com.example.firstapp.extensions.showToast
import com.example.youtubeapi.R
import com.example.youtubeapi.adapter.MainAdapter
import com.example.youtubeapi.data.local.room.AppDatabase
import com.example.youtubeapi.data.models.PlayList
import com.example.youtubeapi.data.models.PlaylistItems
import com.example.youtubeapi.data.network.Status
import com.example.youtubeapi.interfa.OnItemClickListener
import com.example.youtubeapi.ui.detail.DetailVideoActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity (var roomDatabase: AppDatabase): AppCompatActivity(), OnItemClickListener {
    private var listUrlMA : MutableList<PlayList> = mutableListOf()
    private lateinit var adapter: MainAdapter
    private val viewModel by inject<MainViewModel>()
    private lateinit var playList: PlayList


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        viewModel.fetchPlaylists()
        viewModel.playlists.observe(this, Observer {
            adapter.addItems(it)
            Log.e("ooo","MainActivity fetchPlaylists()"+ adapter.addItems(it))

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

    override fun onBackPressed() {
        viewModel.save(playList)
        Log.e("ooo"," MainActivity override fun onBackPressed() "  +viewModel.save(playList))
        super.onBackPressed()
    }




    override fun itemClick(position: Int) {
       // DetailVideoActivity.instanceActivity(this, )
       val intent = Intent(this, DetailVideoActivity::class.java)
        intent.putExtra("id",listUrlMA[position].id)
          startActivity(intent)
        Log.e("ooo","itemClick(position: Int)"+ listUrlMA[position].id)
    }
}

