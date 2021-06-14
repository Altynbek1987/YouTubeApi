package com.example.youtubeapi.ui.pdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.youtubeapi.R

class PDDActivity : AppCompatActivity() {
    private val ID_HOME = 1
    private val ID_MESSAGE = 2
    private val ID_ACCOUNT = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pddactivity)
        val bottomNavigation =
            findViewById<MeowBottomNavigation>(R.id.bottomNavigation)
        bottomNavigation.add(MeowBottomNavigation.Model(ID_MESSAGE, R.drawable.ic_message))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_car))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_account))
        bottomNavigation.setOnClickMenuListener { item ->
        }

        bottomNavigation.setOnShowListener { item ->
            val name: String
            when (item.id) {
                ID_HOME -> name = "Home"
                ID_MESSAGE -> name = "Message"
                ID_ACCOUNT -> { name = "Account"
                }
                else -> name = ""
            }
        }
        // bottomNavigation.setCount(ID_NOTIFICATION,"4");
        bottomNavigation.show(ID_HOME, true)

    }
}