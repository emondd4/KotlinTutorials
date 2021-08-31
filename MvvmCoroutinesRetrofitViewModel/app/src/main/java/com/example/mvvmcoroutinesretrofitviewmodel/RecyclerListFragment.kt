package com.example.mvvmcoroutinesretrofitviewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcoroutinesretrofitviewmodel.Adapters.MyAdapter
import com.example.mvvmcoroutinesretrofitviewmodel.Models.Item
import com.example.mvvmcoroutinesretrofitviewmodel.Models.RecyclerList
import com.example.mvvmcoroutinesretrofitviewmodel.ViewModel.MainActivityViewModel


class RecyclerListFragment : Fragment() {

    private lateinit var recyclerAdapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_recycler_list, container, false)

        initView(view)
        initViewModel()
        return view
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<RecyclerList> {
            if (it != null){
                recyclerAdapter.setUpdateData(it.items as ArrayList<Item>)
            }else{
                Toast.makeText(activity,"Error in getting data",Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    private fun initView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerAdapter = MyAdapter()
        recyclerView.adapter = recyclerAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecyclerListFragment()
    }
}