package com.example.youtubeapi.ui.detail

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.firstapp.extensions.loadImage
import com.example.firstapp.extensions.showToast
import com.example.youtubeapi.R
import com.example.youtubeapi.adapter.DetailAdapter
import com.example.youtubeapi.data.models.DetailVideo
import com.example.youtubeapi.data.network.Status
import kotlinx.android.synthetic.main.activity_detail_video.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_scrolling.*

class DetailVideoActivity : AppCompatActivity() {
    private lateinit var adapter: DetailAdapter
    private lateinit var detailViewModel: DetailViewModel
    private var data:String?=""
    private var listDetail : MutableList<DetailVideo> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_video)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        setDetailAdapter()
        getIntentData()
        fetchPlayListsItems()
    }

    fun setDetailAdapter(){
        adapter = DetailAdapter()
        recyclerViewDetail.adapter = adapter
        val  snap = LinearSnapHelper()
        snap.attachToRecyclerView(recyclerView)
    }

    private fun getIntentData(){
       data = intent.getStringExtra("id")

    }

    private fun fetchPlayListsItems(){
        data?.let {
            detailViewModel.fetchPlayListsItems(null,it).observe(this, Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.items?.let { adapter.detailItems(it) }
                        listDetail = it.data?.items!!
                        if (it.data.nextPageToken!=null){
                            detailViewModel.fetchPlayListsItems(it.data.nextPageToken!!,data!!).observe(this, Observer {
                                it.data?.items?.let { adapter.detailItems(it) }
                            })
                        }
                        image_toolbar.loadImage(it.data?.items?.get(0)?.snippetD?.thumbnailsD?.mediumD?.urlD.toString())
                    }
                    Status.ERROR -> showToast(it.message.toString())
                }
            })
        }
    }


}