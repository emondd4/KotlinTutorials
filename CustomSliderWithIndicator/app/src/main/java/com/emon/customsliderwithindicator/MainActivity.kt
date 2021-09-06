package com.emon.customsliderwithindicator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.emon.customsliderwithindicator.ViewPagerAdapter.Pager_Adapter
import com.emon.customsliderwithindicator.ViewPagerFragments.Fragment_One
import com.emon.customsliderwithindicator.ViewPagerFragments.Fragment_Three
import com.emon.customsliderwithindicator.ViewPagerFragments.Fragment_Two

class MainActivity : AppCompatActivity() {

    private lateinit var pagerview_adapter: Pager_Adapter
    private lateinit var my_viewpaper: ViewPager
    private lateinit var first_indicator: ImageView
    private lateinit var second_indicator: ImageView
    private lateinit var third_indicator: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        my_viewpaper = findViewById(R.id.my_viewpaper)
        first_indicator = findViewById(R.id.first_indicator)
        second_indicator = findViewById(R.id.second_indicator)
        third_indicator = findViewById(R.id.third_indicator)

        pagerview_adapter = Pager_Adapter(supportFragmentManager)
        add_MyFragment()
        my_viewpaper.adapter = pagerview_adapter
        my_viewpaper.addOnPageChangeListener(MyListener(this::onPageSelected))

    }

    @SuppressLint("SetTextI18n")
    private fun onPageSelected(position: Int) {
        when (position) {
            0 -> {
                third_indicator.setImageResource(R.drawable.disable_circle)
                second_indicator.setImageResource(R.drawable.disable_circle)
                first_indicator.setImageResource(R.drawable.active_circle)
            }
            1 -> {
                third_indicator.setImageResource(R.drawable.disable_circle)
                second_indicator.setImageResource(R.drawable.active_circle)
                first_indicator.setImageResource(R.drawable.disable_circle)
            }
            2 -> {
                third_indicator.setImageResource(R.drawable.active_circle)
                second_indicator.setImageResource(R.drawable.disable_circle)
                first_indicator.setImageResource(R.drawable.disable_circle)
            }
        }
    }

    private fun add_MyFragment() {
        pagerview_adapter.addFragment(Fragment_One())
        pagerview_adapter.addFragment(Fragment_Two())
        pagerview_adapter.addFragment(Fragment_Three())
    }

    class MyListener(private val closure: (Int) -> Unit) : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(p0: Int) {

        }

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

        }

        override fun onPageSelected(position: Int) = closure(position)
    }

}