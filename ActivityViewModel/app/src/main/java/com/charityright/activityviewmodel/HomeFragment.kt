package com.charityright.activityviewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

class HomeFragment : Fragment() {

    private lateinit var btn: Button
    private lateinit var btn1: Button
    private lateinit var activityViewModel: ActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        activityViewModel = ViewModelProviders.of(requireActivity()).get(ActivityViewModel::class.java)

        btn = view.findViewById(R.id.button)
        btn1 = view.findViewById(R.id.button1)

        btn.setOnClickListener {
            activityViewModel.showView()
        }

        btn1.setOnClickListener {
            activityViewModel.hideView()
        }

        return view
    }
}