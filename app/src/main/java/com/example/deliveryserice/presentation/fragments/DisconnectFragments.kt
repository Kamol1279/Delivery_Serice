package com.example.deliveryserice.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.deliveryserice.databinding.FragmentDisconnectBinding
import com.example.deliveryserice.databinding.FragmentMagazinesBinding
import com.example.deliveryserice.presentation.activity.MenuActivity

class DisconnectFragments : Fragment() {

    lateinit var binding: FragmentDisconnectBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDisconnectBinding.inflate(inflater)

        return binding.root
    }
}