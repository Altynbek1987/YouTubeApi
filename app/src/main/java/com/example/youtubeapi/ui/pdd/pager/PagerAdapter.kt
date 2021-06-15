package com.example.youtubeapi.ui.pdd.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.youtubeapi.R
import com.example.youtubeapi.ui.pdd.fragments.fines.FinesFragment
import com.example.youtubeapi.ui.pdd.fragments.pdd.PddFragment
import com.example.youtubeapi.ui.pdd.fragments.signs.SignsFragment
import com.example.youtubeapi.ui.pdd.fragments.tests.TestsFragment

class PagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 4
    }


    override fun getItem(position: Int): Fragment {
       when(position){
           0 -> {
               return FinesFragment()
           }
           1 -> {
               return PddFragment()
           }
           2 -> {
               return SignsFragment()
           }
           3 -> {
               return TestsFragment()
           }
           else -> {
               return FinesFragment()
           }
       }
    }


    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Tab 1"
            }
            1 -> {
                return "Tab 2"
            }
            2 -> {
                return "Tab 3"
            }
            4 -> {
                return "Tab 4"
            }
        }
        return super.getPageTitle(position)
    }


}