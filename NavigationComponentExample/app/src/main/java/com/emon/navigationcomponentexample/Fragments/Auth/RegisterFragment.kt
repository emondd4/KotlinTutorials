package com.emon.navigationcomponentexample.Fragments.Auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.emon.navigationcomponentexample.R


class RegisterFragment : Fragment() {

    lateinit var login: TextView
    lateinit var register: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_register, container, false)

        login = view.findViewById(R.id.signup_textView)
        register = view.findViewById(R.id.register_btn)

        login.setOnClickListener {

            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

        }

        register.setOnClickListener {

            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

        }

        return view
    }

}