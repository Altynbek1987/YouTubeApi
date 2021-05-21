package com.example.youtubeapi.ui.video

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.example.firstapp.extensions.isOnline
import com.example.firstapp.extensions.showToast
import com.example.youtubeapi.R
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.data.models.DetailVideo
import com.example.youtubeapi.databinding.ActivityDetailVideoBinding
import com.example.youtubeapi.interfa.OnItemClickListener
import com.example.youtubeapi.ui.fragment.ActionBottomDialogFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.r0adkll.slidr.Slidr
import org.koin.android.ext.android.inject

class DetailVideoActivity : BaseActivity<DetailVideoViewModel, ActivityDetailVideoBinding>(),
    ActionBottomDialogFragment.ItemClickListener, OnItemClickListener {
    override val viewModel by inject<DetailVideoViewModel>()
    private var listDialogg: MutableList<DetailVideo> = mutableListOf()
    private var dataV: String? = ""

    override fun getViewBinding() = ActivityDetailVideoBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_YouTubeApi)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding.imageDownload.setOnClickListener {
            detaillist?.let { it1 ->
                ActionBottomDialogFragment.showFragment(
                    detaillist = it1,
                    supportFragmentManager = supportFragmentManager,
                    onItemClickListener = this
                )
            }
        }
        Slidr.attach(this)
    }

    override fun onStart() {
        super.onStart()
        viewModel.networkLiveData().observe(this, Observer {isConnected ->
            if (isConnected){
                binding.layoutDisconnectDva.visibility = View.GONE

                binding.layoutConnectDva.visibility = View.VISIBLE
            }else{
                binding.layoutConnectDva.visibility = View.GONE
                binding.layoutDisconnectDva.visibility = View.VISIBLE
            }
        })
    }

    override fun onStop() {
        super.onStop()
        viewModel.networkLiveData().removeObservers(this)
    }

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun setupViews() {
        getIntentVideo()
        toggleFullScreen()
        videoBSh()
    }

    override fun setupLiveData() {}

    override fun setupFetchRequests() {
    }

    fun getIntentVideo() {
        if (isOnline(this)) {
        positionVideo?.let {
            if (dataV.isNullOrEmpty()) dataV =
                detaillist?.get(positionVideo!!)?.snippet?.resourceId?.videoId
            playerYT(dataV)
            binding.tvTitleDetailVideo.text = detaillist?.get(positionVideo!!)?.snippet?.title
            binding.tvDescriptionDetailVideo.text =
                detaillist?.get(positionVideo!!)?.snippet?.description
        }
    }else{
            this.showToast("Подключитесь к интернету")
        }
    }

    fun videoBSh() {
        dataV = intent.getStringExtra("idV")
    }

    fun playerYT(videoId: String?) {
        val youTubePlayerView =
            findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                if (videoId != null) {
                    youTubePlayer.loadVideo(videoId, 0F)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.youtubePlayerView.release()
    }

    override fun onItemClick(item: String?) {}

    override fun itemClick(position: Int) {
        position.let {
            detaillist?.let { it1 ->
                instanceActivity(
                    this,
                    it1, it
                )
            }
        }
        finish()
    }

    override fun itemClick(model: DetailVideo) {
        ActionBottomDialogFragment.showFragment(
            detaillist = listDialogg,
            supportFragmentManager = supportFragmentManager,
            onItemClickListener = this
        )
    }

    companion object {
        var detaillist: MutableList<DetailVideo>? = null
        var positionVideo: Int? = null

        fun instanceActivity(
            context: Context,
            detaillist: MutableList<DetailVideo>,
            positionVideo: Int
        ) {
            this.positionVideo = positionVideo
            val intent = Intent(context, DetailVideoActivity::class.java)
            this.detaillist = detaillist
            context.startActivity(intent)
        }
    }
}