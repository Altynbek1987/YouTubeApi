package com.example.youtubeapi.base

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.LocaleList
import android.preference.PreferenceManager
import android.provider.SyncStateContract
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.multidex.MultiDex
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.firstapp.extensions.changeLanguage
import com.example.firstapp.extensions.loadLocale
import com.example.firstapp.extensions.showToast
import com.example.youtubeapi.Preferences
import com.example.youtubeapi.R
import org.koin.androidx.viewmodel.ext.android.getViewModel
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.edit, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action -> {
                changeLanguage()
            }
        }
        return true
    }
}


