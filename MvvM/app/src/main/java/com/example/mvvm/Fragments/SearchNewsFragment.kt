package com.example.mvvm.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvm.MainActivity
import com.example.mvvm.NewsViewModel
import com.example.mvvm.R


class SearchNewsFragment : Fragment() {

    lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_search_news, container, false)

        viewModel = (activity as MainActivity).viewModel

        return view
    }
}