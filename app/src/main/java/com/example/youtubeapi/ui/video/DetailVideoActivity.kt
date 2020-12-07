package com.example.youtubeapi.ui.video

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import com.example.youtubeapi.R
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.data.models.DetailVideo
import com.example.youtubeapi.interfa.OnItemClickListener
import com.example.youtubeapi.ui.fragment.ActionBottomDialogFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_detail_video.*
import org.koin.android.ext.android.inject

class DetailVideoActivity : BaseActivity<DetailVideoViewModel>(R.layout.activity_detail_video),
    ActionBottomDialogFragment.ItemClickListener, OnItemClickListener {
    override val viewModel by inject<DetailVideoViewModel>()
    private var listDialogg: MutableList<DetailVideo> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_YouTubeApi)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        image_download.setOnClickListener {
            detaillist?.let { it1 ->
                ActionBottomDialogFragment.showFragment(
                    supportFragmentManager = supportFragmentManager,
                    detaillist = it1
                )
            }
        }
    }

    override fun setupViews() {
        getIntentVideo()
        toggleFullScreen()
    }

    override fun setupLiveData() {}

    override fun setupFetchRequests() {}

    fun getIntentVideo() {
        playerYT(detaillist?.get(0)?.snippet?.resourceId?.videoId)
        tv_title_detail_video.text = detaillist?.get(0)?.snippet?.title
        tv_description_detail_video.text = detaillist?.get(0)?.snippet?.description
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
        youtube_player_view.release()
    }

    override fun onItemClick(item: String?) {
        TODO("Not yet implemented")
    }

    override fun itemClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun itemClick(model: DetailVideo) {
        ActionBottomDialogFragment.showFragment(
            supportFragmentManager = supportFragmentManager,
            detaillist = listDialogg
        )
    }

    companion object {
        var detaillist: MutableList<DetailVideo>? = null
        fun instanceActivity(context: Context, detaillist: MutableList<DetailVideo>) {
            val intent = Intent(context, DetailVideoActivity::class.java)
            this.detaillist = detaillist
            context.startActivity(intent)
        }
    }
}

//android:configChanges="orientation|screenSize|keyboardHidden|smallestScreenSize|screenLayout"  Сохранение состояния втавить нада в манифест


