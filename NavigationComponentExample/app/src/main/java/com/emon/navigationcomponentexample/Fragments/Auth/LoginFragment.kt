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


class LoginFragment : Fragment() {

    lateinit var register: TextView
    lateinit var forgot: TextView
    lateinit var login: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)

        register = view.findViewById(R.id.signup_textView)
        forgot = view.findViewById(R.id.forgot_text)
        login = view.findViewById(R.id.login_Btn)

        register.setOnClickListener {

            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

        }

        forgot.setOnClickListener {

            findNavController().navigate(R.id.action_loginFragment_to_forgotPassFragment)

        }

        login.setOnClickListener {

            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

        }

        return view

    }

}