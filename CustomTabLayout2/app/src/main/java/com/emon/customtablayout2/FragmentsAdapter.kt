package com.emon.customtablayout2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.emon.customtablayout2.Fragments.FirstFragment
import com.emon.customtablayout2.Fragments.SecondFragment
import com.emon.customtablayout2.Fragments.ThirdFragment

class FragmentsAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0-> {
                return FirstFragment()
            }
            1->{
                return SecondFragment()
            }
            2->{
                return ThirdFragment()
            }
        }
        return FirstFragment()
    }
}