package com.example.firstapp.extensions

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.youtubeapi.Preferences
import com.example.youtubeapi.R
import java.util.*

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(url: String, placeholder: Int = 0) {
    Glide.with(this.context)
        .load(url)
        .placeholder(placeholder)
        .into(this)
}

fun View.gone(){
this.visibility = View.GONE
}
fun View.visible(){
    this.visibility = View.VISIBLE
}
private fun setLocale(s: String, context:Context) {
    val locale = Locale(s)
    Locale.setDefault(locale)
    val config = Configuration()
    config.locale = locale
    context.resources.updateConfiguration(
        config,
        context.resources.displayMetrics)
    Preferences.getInstance(context)?.saveLanguage(s)
}
fun loadLocale(context: Context) {
    var language: String? = Preferences.getInstance(context)?.preference
    if (language != null) {
        setLocale(language,context)
    }
}
fun Activity.changeLanguage() {
    val listItems = arrayOf("Русский","English","Корейский","Кыргызский")
    val mBuilder = AlertDialog.Builder(this)

    mBuilder.setTitle(getString(R.string.выберите_язык))
    mBuilder.setSingleChoiceItems(listItems, -1) { dialog, which ->
        when (which) {
            0 -> {
                setLocale("ru",this)
            }
            1 -> {
                setLocale("chr-rUS",this)
            }
            2 -> {
                setLocale("ko-rKR",this)
            }
            3 -> {
                setLocale("ky",this)
            }

        }
        this.recreate()
        dialog.dismiss()
    }
    val mDialog = mBuilder.create()
    mDialog.show()
}



