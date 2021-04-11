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
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
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

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.update_firstName_EditText.setText(args.currentUser.firstName)
        view.update_lastName_EditText.setText(args.currentUser.lastName)
        view.update_age_EditText.setText(args.currentUser.age.toString())

        view.update_add_btn.setOnClickListener {

            updateItem()
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)

        }

        return view
    }

    private fun updateItem(){

        val firstName = update_firstName_EditText.text.toString()
        val lastName = update_lastName_EditText.text.toString()
        val age = update_age_EditText.text.toString().toInt()

        if (inputCheck(firstName,lastName,age)){

            val updateUser = User(args.currentUser.id,firstName,lastName,age)
            userViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(),"Successfully Updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

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