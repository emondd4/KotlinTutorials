package com.charityright.fragmentviewmodel.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.charityright.fragmentviewmodel.FragmentViewModel
import com.charityright.fragmentviewmodel.R

class SecondFragment : Fragment() {

    private lateinit var btn: Button
    private lateinit var viewModel: FragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_second, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(FragmentViewModel::class.java)

        btn = view.findViewById(R.id.button2)

        btn.setOnClickListener {
            viewModel.sendMessageToA("Fragment Second")
        }

        return view
    }

}