package com.emon.customtablayout2

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class MainActivity : AppCompatActivity() {

    var tabLayout: TabLayout? = null
    lateinit var viewPager2: ViewPager2
    lateinit var fragmentsAdapter: FragmentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager2 = findViewById(R.id.viewPager)

        val fm: FragmentManager = supportFragmentManager
        fragmentsAdapter = FragmentsAdapter(fm, lifecycle)
        viewPager2.adapter = fragmentsAdapter

        for (i in 0 until tabLayout!!.tabCount) {
            val tab = tabLayout!!.getTabAt(i)
            if (tab != null) {
                val tabTextView = TextView(this)
                tab.customView = tabTextView
                tabTextView.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
                tabTextView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                tabTextView.text = tab.text
                if (i == 0) {
                    tabTextView.textSize = 25f
                    tabTextView.setTextColor(resources.getColor(R.color.teal_700))
                }
            }
        }

        tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val vg = tabLayout!!.getChildAt(0) as ViewGroup
                val vgTab = vg.getChildAt(tab.position) as ViewGroup
                val tabChildsCount = vgTab.childCount
                for (i in 0 until tabChildsCount) {
                    val tabViewChild: View = vgTab.getChildAt(i)
                    if (tabViewChild is TextView) {
                        (tabViewChild as TextView).textSize = 25f
                        (tabViewChild as TextView).setTextColor(resources.getColor(R.color.teal_700))
                    }
                }

                //viewpager
                viewPager2.currentItem = tab.position

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val vg = tabLayout!!.getChildAt(0) as ViewGroup
                val vgTab = vg.getChildAt(tab.position) as ViewGroup
                val tabChildsCount = vgTab.childCount
                for (i in 0 until tabChildsCount) {
                    val tabViewChild: View = vgTab.getChildAt(i)
                    if (tabViewChild is TextView) {
                        (tabViewChild as TextView).textSize = 16f
                        (tabViewChild as TextView).setTextColor(resources.getColor(R.color.teal_700))
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout!!.selectTab(tabLayout!!.getTabAt(position))
            }
        })
    }
}