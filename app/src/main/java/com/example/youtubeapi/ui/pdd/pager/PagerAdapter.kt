package com.example.youtubeapi.ui.pdd.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.youtubeapi.ui.pdd.fragments.fines.FinesFragment
import com.example.youtubeapi.ui.pdd.fragments.pdd.PddFragment
import com.example.youtubeapi.ui.pdd.fragments.signs.SignsFragment
import com.example.youtubeapi.ui.pdd.fragments.tests.TestsFragment
import java.util.*

class PagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 4
    }


    override fun getItem(position: Int): Fragment {

       when(position){
           0 -> {
               return PddFragment()
           }
           1 -> {
               return FinesFragment()
           }
           2 -> {
               return SignsFragment()
           }
           3 -> {
               return TestsFragment()
           }
           else -> {
               return PddFragment()
           }
       }
    }
}