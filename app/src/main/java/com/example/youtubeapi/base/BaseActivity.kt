package com.example.youtubeapi.base

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.firstapp.extensions.showToast
import com.example.youtubeapi.R
import com.example.youtubeapi.extensions.loadLocale

abstract class BaseActivity<ViewModel : BaseViewModel, B : ViewBinding> :
    AppCompatActivity() {

    abstract val viewModel: ViewModel
    lateinit var binding: B
    private val ID_HOME = 1
    private val ID_MESSAGE = 2
    private val ID_ACCOUNT = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        setupViews()
        setupLiveData()
        setupFetchRequests()
        showError()
    }

    abstract fun getViewBinding() : B

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    override fun onResume() {
        loadLocale(this)
        super.onResume()
    }

    abstract fun setupViews()
    abstract fun setupLiveData()
    abstract fun setupFetchRequests()

    private fun showError() {
        viewModel.errorMessage.observeForever {
            showToast(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    fun toggleFullScreen() {
        if (window.decorView.systemUiVisibility == View.SYSTEM_UI_FLAG_VISIBLE) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }
}


