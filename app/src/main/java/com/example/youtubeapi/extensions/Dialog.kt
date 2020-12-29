package com.example.youtubeapi.extensions

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.youtubeapi.R
import com.example.youtubeapi.data.local.pref.Preferences
import java.util.*

@RequiresApi(Build.VERSION_CODES.GINGERBREAD)
private fun setLocale(s: String, context: Context) {
    val locale = Locale(s)
    Locale.setDefault(locale)
    val config = Configuration()
    config.locale = locale
    context.resources.updateConfiguration(
        config,
        context.resources.displayMetrics
    )
    Preferences.getInstance(context)?.saveLanguage(s)
}

@RequiresApi(Build.VERSION_CODES.GINGERBREAD)
fun loadLocale(context: Context) {
    var language: String? = Preferences.getInstance(context)?.preference
    if (language != null) {
        setLocale(language, context)
    }
}

@RequiresApi(Build.VERSION_CODES.GINGERBREAD)
fun Activity.changeLanguage() {
    val listItems = arrayOf("Русский", "English", "한국어", "Кыргызча")
    val mBuilder = AlertDialog.Builder(this)
    mBuilder.setTitle(getString(R.string.выберите_язык))
    mBuilder.setSingleChoiceItems(listItems, -1) { dialog, which ->
        when (which) {
            0 -> setLocale("ru", this)
            1 -> setLocale("chr", this)
            2 -> setLocale("ko", this)
            3 -> setLocale("ky", this)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            this.recreate()
        }
        dialog.dismiss()
    }
    val mDialog = mBuilder.create()
    mDialog.show()
}