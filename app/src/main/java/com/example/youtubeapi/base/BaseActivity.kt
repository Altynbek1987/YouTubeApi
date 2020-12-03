package com.example.youtubeapi.base

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.extensions.showToast
import com.example.youtubeapi.extensions.loadLocale
import java.util.*

abstract class BaseActivity <ViewModel:BaseViewModel>(private var layoutId:Int): AppCompatActivity() {
    abstract val viewModel: ViewModel
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        initLanguage()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        setupViews()
        setupLiveData()
        setupFetchRequests()
        showError()
    }

    override fun onResume() {
        loadLocale(this)
        super.onResume()
    }

    private fun initLanguage() {
    }

    abstract fun setupViews()
    abstract fun setupLiveData()
    abstract fun setupFetchRequests()

    private fun showError() {
        viewModel.errorMessage.observeForever {
            showToast(it)
        }
    }


    fun toggleFullScreen() {
        if (window.decorView.systemUiVisibility == View.SYSTEM_UI_FLAG_VISIBLE) {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }


}


