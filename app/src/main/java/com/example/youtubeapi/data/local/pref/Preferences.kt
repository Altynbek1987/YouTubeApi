package com.example.youtubeapi.data.local.pref

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi

class Preferences(context: Context) {
    private var preferences: SharedPreferences? = null
    val preference: String?
        get() = preferences?.getString("language_", "")

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    fun saveLanguage(s: String) {
        preferences?.edit()?.putString("language_", s)?.apply()
    }

    companion object {
        @Volatile
        var instance: Preferences? = null
        fun getInstance(context: Context): Preferences? {
            if (instance == null) Preferences(
                context
            )
            return instance
        }
    }

    init {
        instance = this
        preferences = context.getSharedPreferences("my_language", Context.MODE_PRIVATE)
    }
}