package com.emon.customsliderwithindicator.ViewPagerAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class Pager_Adapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    private var fragmentList: MutableList<Fragment> = ArrayList()

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragment(fragment:Fragment){
        fragmentList.add(fragment)
    }

}