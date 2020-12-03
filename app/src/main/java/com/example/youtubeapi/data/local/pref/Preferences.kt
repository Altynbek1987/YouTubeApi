package com.example.youtubeapi.data.local.pref

import android.content.Context
import android.content.SharedPreferences

class Preferences (context: Context) {
    private var preferences: SharedPreferences? = null
    val preference: String?
        get() = preferences?.getString("language_", "")

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