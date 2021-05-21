package com.example.youtubeapi.ui.detail

import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.firstapp.extensions.isOnline
import com.example.firstapp.extensions.loadImage
import com.example.firstapp.extensions.showToast
import com.example.youtubeapi.R
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.data.models.DetailVideo
import com.example.youtubeapi.data.network.Status
import com.example.youtubeapi.databinding.ActivityDetailPlayListBinding
import com.example.youtubeapi.databinding.ActivityMainBinding
import com.example.youtubeapi.interfa.OnItemClickListener
import com.example.youtubeapi.ui.detail.adapter.DetailPlayListAdapter
import com.example.youtubeapi.ui.video.DetailVideoActivity
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class DetailPlayListActivity :
    BaseActivity<DetailPlayListViewModel, ActivityDetailPlayListBinding>(), OnItemClickListener {
    private var listDetail: MutableList<DetailVideo> = mutableListOf()
    private lateinit var adapter: DetailPlayListAdapter
    override val viewModel by inject<DetailPlayListViewModel>()
    private var data: String? = ""

    override fun getViewBinding() = ActivityDetailPlayListBinding.inflate(layoutInflater)

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun setupViews() {
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            if (isOnline(this)) {
                DetailVideoActivity.instanceActivity(this, listDetail, 0)
            }else{
                this.showToast("Подключитесь к интернету")
            }
            Snackbar.make(view, "Подключение к Интернету отсутствует", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        setDetailAdapter()
        getIntentData()
        toggleFullScreen()
    }

    override fun onStart() {
        super.onStart()
        viewModel.networkLiveData().observe(this, Observer {isConnected ->
            if (isConnected){

                binding.includeNoInternet.layoutDisconnectt.visibility = View.GONE
                binding.fab.visibility = View.VISIBLE
                binding.includeYesInternet.layoutConnectt.visibility = View.VISIBLE
            }else{
                binding.includeYesInternet.layoutConnectt.visibility = View.GONE
                binding.includeNoInternet.layoutDisconnectt.visibility = View.VISIBLE
                binding.fab.visibility = View.GONE

            }
        })
    }

    override fun onStop() {
        super.onStop()
        viewModel.networkLiveData().removeObservers(this)
    }

    override fun setupLiveData() {
        fetchPlayListsItems()
    }

    override fun setupFetchRequests() {}

    fun setDetailAdapter() {
        adapter =
            DetailPlayListAdapter(this)
        binding.includeYesInternet.recyclerViewDetailPlayList.adapter = adapter
        val snap = LinearSnapHelper()
        snap.attachToRecyclerView(binding.includeYesInternet.recyclerViewDetailPlayList)
    }

    private fun getIntentData() {
        data = intent.getStringExtra("id")
        Log.e("ololo", "Ok" )
    }

    private fun fetchPlayListsItems() {
        data?.let { data ->
            viewModel.fetchPlayListsItems(data, null).observe(this, Observer { it ->
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.items?.let { adapter.detailItems(it) }
                        listDetail = it.data?.items!!
                        Log.e("ololo", "SUCCESS" )
                        binding.toolbarImage.loadImage(it.data.items?.get(0)?.snippet?.thumbnails?.medium?.url.toString())
                    }
                    Status.ERROR -> {
                        saveRoom()
                        Log.e("ololo", "ERROR" )
                    }
                }
            })
        }
    }

    fun saveRoom() {
        viewModel.loadDataVideo()
        viewModel.detailPlaylists.observe(this, Observer {
            adapter.detailItems(it)
        })
    }

    override fun itemClick(position: Int) {
           DetailVideoActivity.instanceActivity(this, listDetail, position)
    }

    override fun itemClick(model: DetailVideo) {}
}