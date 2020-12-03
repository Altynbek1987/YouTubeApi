package com.example.youtubeapi.ui.video

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import com.example.youtubeapi.R
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.data.models.DetailVideo
import com.example.youtubeapi.data.models.PlayListDetail
import com.example.youtubeapi.interfa.OnItemClickListener
import com.example.youtubeapi.ui.fragment.ActionBottomDialogFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_detail_video.*
import org.koin.android.ext.android.inject


class DetailVideoActivity : BaseActivity<DetailVideoViewModel>(R.layout.activity_detail_video),
    ActionBottomDialogFragment.ItemClickListener,OnItemClickListener {
    override val viewModel by inject<DetailVideoViewModel>()
    private var listDialogg: MutableList<DetailVideo> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_YouTubeApi)
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()

        image_download.setOnClickListener {
            val addPhotoBottomDialogFragment =
                ActionBottomDialogFragment.newInstance(listDialogg)
            addPhotoBottomDialogFragment?.show(
                supportFragmentManager,
                ActionBottomDialogFragment.TAG
            )
        }
    }
    override fun setupViews() {
        getIntentVideo()
        toggleFullScreen()
    }
    override fun setupLiveData() {}
    override fun setupFetchRequests() {}

    fun getIntentVideo(){
        val modelDetailVideo = intent.getSerializableExtra("item") as DetailVideo
        playerYT(modelDetailVideo.snippet?.resourceId?.videoId)
        tv_title_detail_video.text = modelDetailVideo.snippet?.title
        listDialogg.add(modelDetailVideo)
        tv_description_detail_video.text = modelDetailVideo.snippet?.description
    }
    fun playerYT(videoId: String?) {
        val youTubePlayerView =
            findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                Log.e("www","$videoId")
                if (videoId != null) {
                    youTubePlayer.loadVideo(videoId, 0F)
                }
            }
        })
    }

    override fun onItemClick(item: String?) {
        TODO("Not yet implemented")
    }

    override fun itemClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun itemClick(model: DetailVideo) {
        val bundle = Bundle()
        bundle.putSerializable("model",model)
        val fragmentt = ActionBottomDialogFragment(listDialogg)
        fragmentt.show(supportFragmentManager,fragmentt.tag)
//        val intent = Intent(this,ActionBottomDialogFragment::class.java)
//        intent.putExtra("model",model)
//        Log.e("www","ActionBottomDialogFragment itemClick(model: DetailVideo) ${model.snippet?.resourceId?.videoId}")
//        startActivity(intent)
    }

    fun huy(model: DetailVideo){


    }


}
































//private val ActionBottomDialogFragment.Companion.TAG: String?
//    get() {
//        TODO("Not yet implemented")
//    }


//    companion object {
//        var detaillist: DetailVideo? = null
//        fun instanceActivity(activity: Activity?, detaillist: DetailVideo) {
//            val intent = Intent(activity, DetailVideoActivity::class.java)
//            this.detaillist = detaillist
//            activity?.startActivity(intent)
//        }
//
//    }