package com.example.youtubeapi.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AlertDialog
import com.example.youtubeapi.R
import com.example.youtubeapi.data.local.pref.Preferences
import java.util.*

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

fun loadLocale(context: Context) {
    var language: String? = Preferences.getInstance(context)?.preference
    if (language != null) {
        setLocale(language, context)
    }
}

fun Activity.changeLanguage() {
    val listItems = arrayOf("Русский", "English", "한국어", "Кыргызча")
    val mBuilder = AlertDialog.Builder(this)
    mBuilder.setTitle(getString(R.string.выберите_язык))
    mBuilder.setSingleChoiceItems(listItems, -1) { dialog, which ->
        when (which) {
            0 -> {
                setLocale("ru", this)
            }
            1 -> {
                setLocale("chr", this)
            }
            2 -> {
                setLocale("ko", this)
            }
            3 -> {
                setLocale("ky", this)
            }
        }
        this.recreate()
        dialog.dismiss()
    }
    val mDialog = mBuilder.create()
    mDialog.show()
}

@SuppressLint("ResourceType")
fun Activity.format() {
    val listItems = arrayOf("1080", "720", "480")
    val mBuilder = AlertDialog.Builder(this)
    mBuilder.setTitle(getString(R.string.выберите_язык))
    mBuilder.setSingleChoiceItems(listItems, -1) { dialog, which ->
        when (which) {
            0 -> {

            }
            1 -> {

            }
            2 -> {

            }
        }
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.drawable.ic_groupbutton,null)
        mBuilder.setView(dialogLayout)

        this.recreate()
        dialog.dismiss()
    }
    val mDialog = mBuilder.create()
    mDialog.show()



}

//fun withEditText() {
//    val builder = AlertDialog.Builder(this)
//    val inflater = layoutInflater
//    builder.setTitle("Add URL HTTPS")
//    val dialogLayout = inflater.inflate(R.drawable.ic_groupbutton, null)
//    //val editText = dialogLayout.findViewById<EditText>(R.id.ed_add_url)
//    builder.setView(dialogLayout)
//    builder.setPositiveButton("Add") { dialogInterface, i ->
//
//    }
//    builder.show()
//}