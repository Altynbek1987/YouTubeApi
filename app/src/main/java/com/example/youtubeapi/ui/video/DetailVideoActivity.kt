package com.example.youtubeapi.ui.video

import android.os.Bundle
import com.example.firstapp.extensions.loadImage
import com.example.youtubeapi.R
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.data.models.DetailVideo
import com.example.youtubeapi.data.network.Status
import kotlinx.android.synthetic.main.activity_detail_video.*
import org.koin.android.ext.android.inject

class DetailVideoActivity : BaseActivity<DetailVideoViewModel>(R.layout.activity_detail_video) {
    override val viewModel by inject<DetailVideoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_YouTubeApi)
        super.onCreate(savedInstanceState)
    }

    override fun setupViews() {
        getIntentVideo()
    }
    override fun setupLiveData() {
    }

    fun getIntentVideo(){
        val url = intent.getSerializableExtra("keyUrl")
        val title = intent.getSerializableExtra("keyTitle")
        val description = intent.getSerializableExtra("keyDescription")
        image_detail_video_activity.loadImage(url.toString())
        tv_title_detail_video.text = title.toString()
        tv_description_detail_video.text = description.toString()
    }


    override fun setupFetchRequests() {

    }


}
