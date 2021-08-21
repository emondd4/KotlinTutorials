package com.example.viewbinding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.viewbinding.databinding.FragmentExampleBinding


class ExampleFragment : Fragment() {
    private var _binding: FragmentExampleBinding? = null
    private val binding get() = _binding!! //getter of _binding so that every time we dont have to check null safety

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExampleBinding.inflate(inflater, container, false)

        binding.fragmentTextView1.text = "Fragment Text 1"
        binding.fragmentTextView2.text = "Fragment Text 2"
        binding.includeLayout.includeTextView1.text = "Include Text 1"
        binding.includeLayout.includeTextView2.text = "Include Text 2"

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}