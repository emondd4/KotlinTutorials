package com.emon.roomcrud.Fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.emon.roomcrud.R
import com.emon.roomcrud.Model.User
import com.emon.roomcrud.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class addFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_add, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.add_btn.setOnClickListener {

            insertDataToDatabase()
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)

        }

        return view
    }

    private fun insertDataToDatabase() {

        val firstName = firstName_EditText.text.toString()
        val lastName = lastName_EditText.text.toString()
        val age = age_EditText.text.toString().toInt()

        if (inputCheck(firstName,lastName,age)){

            val user = User(0,firstName,lastName,age)
            userViewModel.addUser(user)
            Toast.makeText(requireContext(),"Successfully Added",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(),"Please Fill All Field",Toast.LENGTH_SHORT).show()
        }


    }

    private fun inputCheck(firstName: String, lastName: String, age: Int) : Boolean {

        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(
            age.toString()
        ))

    }

}