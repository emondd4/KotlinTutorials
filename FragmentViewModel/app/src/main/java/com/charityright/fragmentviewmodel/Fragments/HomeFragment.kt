package com.charityright.fragmentviewmodel.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.charityright.fragmentviewmodel.FragmentViewModel
import com.charityright.fragmentviewmodel.R
import com.google.android.material.tabs.TabLayout
import java.lang.Exception


class HomeFragment : Fragment() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2
    lateinit var fragmentsAdapter: FragmentsAdapter

    lateinit var tabPro: TextView

    lateinit var viewModel: FragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)


        tabLayout = view.findViewById(R.id.tablayout)
        viewPager2 = view.findViewById(R.id.viewPager)

        tabPro = view.findViewById(R.id.tabProperty)


        val fm: FragmentManager? = activity?.supportFragmentManager
        fragmentsAdapter = FragmentsAdapter(fm!!,lifecycle)
        viewPager2.adapter = fragmentsAdapter

        tabLayout.addTab(tabLayout.newTab().setText("First"))
        tabLayout.addTab(tabLayout.newTab().setText("Second"))

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(FragmentViewModel::class.java)
        viewModel.getMessageContainerA().observe(viewLifecycleOwner, object: Observer<String>{
            override fun onChanged(t: String?) {
                tabPro.text = t
            }

        })
    }

}


