package com.example.youtubeapi.ui.video

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
    private var dataV: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_YouTubeApi)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        image_download.setOnClickListener {
            detaillist?.let { it1 ->
                ActionBottomDialogFragment.showFragment(
                    detaillist = it1,
                    supportFragmentManager = supportFragmentManager,
                    onItemClickListener = this
                )
            }
        }
    }

    override fun setupViews() {
        getIntentVideo()
        toggleFullScreen()
        videoBSh()
    }

    override fun setupLiveData() {}

    override fun setupFetchRequests() {}

    fun getIntentVideo() {
        positionVideo?.let {
            if (dataV.isNullOrEmpty()) dataV =
                detaillist?.get(positionVideo!!)?.snippet?.resourceId?.videoId
            playerYT(dataV)
            tv_title_detail_video.text = detaillist?.get(positionVideo!!)?.snippet?.title
            tv_description_detail_video.text =
                detaillist?.get(positionVideo!!)?.snippet?.description
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
//    fun playBottomSh(){
//        dataV.let { playerYT(it) }
//    }

    override fun onDestroy() {
        super.onDestroy()
        youtube_player_view.release()
    }

    override fun onItemClick(item: String?) {
        TODO("Not yet implemented")
    }

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

//android:configChanges="orientation|screenSize|keyboardHidden|smallestScreenSize|screenLayout"  Сохранение состояния втавить нада в манифест


