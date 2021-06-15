package com.example.youtubeapi.ui.pdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.youtubeapi.R
import com.example.youtubeapi.ui.pdd.pager.PagerAdapter

class PDDActivity : AppCompatActivity() {
    private val ID_HOME = 1
    private val ID_MESSAGE = 2
    private val ID_ACCOUNT = 3
    private val ID_MONEY = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pddactivity)

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = PagerAdapter(supportFragmentManager)


        val bottomNavigation =
            findViewById<MeowBottomNavigation>(R.id.bottomNavigation)
        // Надо соединить bottomNavigation и viewPager



        bottomNavigation.add(MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_car))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_MESSAGE, R.drawable.ic_message))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_account))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_MONEY, R.drawable.ic_money))
          //setupWithViewPager(viewPager)

        bottomNavigation.setOnClickMenuListener { item ->
        }

        bottomNavigation.setOnShowListener { item ->
            val name: String
            when (item.id) {
                ID_HOME -> {name = viewPager.setCurrentItem(0, false).toString()
                }
                ID_MESSAGE -> {name = viewPager.setCurrentItem(1, false).toString()}
                ID_MONEY -> {
                    name = viewPager.setCurrentItem(2, false).toString()
                }
                ID_ACCOUNT -> { name = viewPager.setCurrentItem(3, false).toString()

                }
                else -> name = ""
            }
        }

        // bottomNavigation.setCount(ID_NOTIFICATION,"4");
        bottomNavigation.show(ID_HOME, true)


        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                //alerta("menu",position.toString())
            }

            override fun onPageSelected(position: Int) {
               // bottomNavigationView!!.getMenu().getItem(position).setChecked(true)
                findViewById<MeowBottomNavigation>(R.id.bottomNavigation)


            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })


    }
}