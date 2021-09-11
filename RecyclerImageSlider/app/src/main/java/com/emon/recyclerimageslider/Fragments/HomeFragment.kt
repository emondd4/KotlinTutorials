package com.emon.recyclerimageslider.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emon.recyclerimageslider.Adapter.ImageAdapter
import com.emon.recyclerimageslider.R
import com.emon.recyclerimageslider.ViewModel.ImageViewModel
import com.squareup.picasso.Picasso


class HomeFragment : Fragment() {

    private lateinit var imageAdapter: ImageAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var viewModel: ImageViewModel
    private lateinit var imageView: ImageView
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        viewModel = ViewModelProviders.of(requireActivity()).get(ImageViewModel::class.java)

        imageView = view.findViewById(R.id.homeImageView)
        recyclerView = view.findViewById(R.id.recyclerImage)

        layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        val images = arrayOf<String>(
            "https://images.unsplash.com/photo-1628191011993-69070758764f?ixid=MnwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
            "https://images.unsplash.com/photo-1556543365-e08680c86612?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDd8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1566863985472-5db179562d0f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDl8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1628191011123-e7f6b5ec16a9?ixid=MnwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1051&q=80",
            "https://images.unsplash.com/photo-1628191079582-f982c2fe327b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDIwfHx8ZW58MHx8fHw%3D&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1542587227-8802646daa56?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
            "https://images.unsplash.com/photo-1504439158909-5a2f08876082?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDExfHx8ZW58MHx8fHw%3D&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1439920120577-eb3a83c16dd7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDE2fHx8ZW58MHx8fHw%3D&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1444840535719-195841cb6e2b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDE3fHx8ZW58MHx8fHw%3D&auto=format&fit=crop&w=500&q=60")

        imageAdapter = ImageAdapter(requireActivity(),images,viewModel)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = imageAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getImagePath().observe(viewLifecycleOwner, object: Observer<String> {
            override fun onChanged(t: String?) {
                Picasso.get().load(t).placeholder(R.drawable.ic_image).into(imageView)
            }
        })
    }
}