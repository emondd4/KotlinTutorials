package com.emon.navigationcomponentexample.Fragments.Auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.emon.navigationcomponentexample.R

class ForgotPassFragment : Fragment() {

    lateinit var forgot: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_forgot_pass, container, false)

        forgot = view.findViewById(R.id.send_email_btn)

        forgot.setOnClickListener {

            findNavController().navigate(R.id.action_forgotPassFragment_to_loginFragment)

        }

        return view
    }
}