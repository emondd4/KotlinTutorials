package com.emon.roomcrud.Fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.emon.roomcrud.Model.User
import com.emon.roomcrud.R
import com.emon.roomcrud.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_update, container, false)

        view.update_firstName_EditText.setText(args.currentUser.firstName)
        view.update_lastName_EditText.setText(args.currentUser.lastName)
        view.update_age_EditText.setText(args.currentUser.age.toString())

        view.update_add_btn.setOnClickListener {

            updateItem()

        }

        return view
    }

    private fun updateItem(){

        val firstName = update_firstName_EditText.text.toString()
        val lastName = update_lastName_EditText.text.toString()
        val age = update_age_EditText.text.toString().toInt()

        if (inputCheck(firstName,lastName,age)){

            val user = User(args.currentUser.id,firstName,lastName,age)
            userViewModel.addUser(user)
            Toast.makeText(requireContext(),"Successfully Updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(),"Please Fill All Field", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Int) : Boolean {

        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(
            age.toString()
        ))

    }
}