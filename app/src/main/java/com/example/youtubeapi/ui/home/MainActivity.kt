@file:Suppress("PLUGIN_WARNING")

package com.example.youtubeapi.ui.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearSnapHelper
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
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
    private val ID_HOME = 1
    private val ID_MESSAGE = 2
    private val ID_ACCOUNT = 3
    private var listUrlMA: MutableList<PlaylistItems> = mutableListOf()
    private lateinit var adapter: MainAdapter
    override val viewModel by inject<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_YouTubeApi)
        super.onCreate(savedInstanceState)
        val bottomNavigation =
            findViewById<MeowBottomNavigation>(R.id.bottomNavigation)
        bottomNavigation.add(MeowBottomNavigation.Model(ID_MESSAGE, R.drawable.ic_message))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_account))
        bottomNavigation.setOnClickMenuListener { item ->
        }

        bottomNavigation.setOnShowListener { item ->
            val name: String
            when (item.id) {
                ID_MESSAGE -> name = "Message"
                ID_HOME -> name = "Home"
                ID_ACCOUNT -> { name = "Account"
                }
                else -> name = ""
            }
        }
        // bottomNavigation.setCount(ID_NOTIFICATION,"4");
        bottomNavigation.show(ID_HOME, true)
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
    @RequiresApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_search, menu)
        val onActionExpandListener: MenuItem.OnActionExpandListener = @RequiresApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(menuItem: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
                return true
            }
        }
        menu?.findItem(R.id.action_search)?.setOnActionExpandListener(onActionExpandListener)
        val search = menu?.findItem(R.id.action_search)?.actionView as SearchView
        search.queryHint = "Поиск"
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    override fun itemClick(item: DetailVideo) {}
}
