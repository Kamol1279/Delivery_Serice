package com.example.deliveryserice.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.deliveryserice.presentation.activity.SettingsActivity
import com.example.deliveryserice.databinding.FragmentProfileBinding
import com.example.deliveryserice.presentation.activity.HistoryActivity

class ProfileFragments : Fragment() {
    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(inflater)

        binding.settings.setOnClickListener {
            startActivity(Intent(this.context , SettingsActivity::class.java))

        }
        binding.settinsText.setOnClickListener {
            startActivity(Intent(this.context , SettingsActivity::class.java))

        }

        binding.history.setOnClickListener {
            startActivity(Intent(this.context , HistoryActivity::class.java))
        }
        binding.historyText.setOnClickListener {
            startActivity(Intent(this.context , HistoryActivity::class.java))
        }
        binding.about.setOnClickListener {


        }

        return binding.root
    }

}