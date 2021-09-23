package com.charityright.custombottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.charityright.custombottomnavigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeLayout.setOnClickListener {
            binding.homeIcon.visibility = View.GONE
            binding.homeCard.visibility = View.VISIBLE

            //rest of all
            binding.bookIcon.visibility = View.VISIBLE
            binding.bookCard.visibility = View.GONE

            binding.calenderIcon.visibility = View.VISIBLE
            binding.calenderCard.visibility = View.GONE

            binding.boxIcon.visibility = View.VISIBLE
            binding.boxCard.visibility = View.GONE
        }

        binding.bookLayout.setOnClickListener {
            binding.bookIcon.visibility = View.GONE
            binding.bookCard.visibility = View.VISIBLE

            //rest of all
            binding.homeIcon.visibility = View.VISIBLE
            binding.homeCard.visibility = View.GONE

            binding.calenderIcon.visibility = View.VISIBLE
            binding.calenderCard.visibility = View.GONE

            binding.boxIcon.visibility = View.VISIBLE
            binding.boxCard.visibility = View.GONE
        }

        binding.calenderLayout.setOnClickListener {
            binding.calenderIcon.visibility = View.GONE
            binding.calenderCard.visibility = View.VISIBLE

            //rest of all
            binding.bookIcon.visibility = View.VISIBLE
            binding.bookCard.visibility = View.GONE

            binding.homeIcon.visibility = View.VISIBLE
            binding.homeCard.visibility = View.GONE

            binding.boxIcon.visibility = View.VISIBLE
            binding.boxCard.visibility = View.GONE
        }

        binding.boxLayout.setOnClickListener {
            binding.boxIcon.visibility = View.GONE
            binding.boxCard.visibility = View.VISIBLE

            //rest of all
            binding.bookIcon.visibility = View.VISIBLE
            binding.bookCard.visibility = View.GONE

            binding.calenderIcon.visibility = View.VISIBLE
            binding.calenderCard.visibility = View.GONE

            binding.homeIcon.visibility = View.VISIBLE
            binding.homeCard.visibility = View.GONE
        }

        binding.addLayout.setOnClickListener {
            binding.bookIcon.visibility = View.VISIBLE
            binding.bookCard.visibility = View.GONE

            binding.calenderIcon.visibility = View.VISIBLE
            binding.calenderCard.visibility = View.GONE

            binding.homeIcon.visibility = View.VISIBLE
            binding.homeCard.visibility = View.GONE

            binding.boxIcon.visibility = View.VISIBLE
            binding.boxCard.visibility = View.GONE
        }

    }
}