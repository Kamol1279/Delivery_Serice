package com.example.deliveryserice.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.deliveryserice.databinding.ActivityAboutBinding
import com.example.deliveryserice.databinding.ActivityHistoryBinding
import com.example.deliveryserice.databinding.ActivityMainBinding

class AboutActivity:AppCompatActivity() {

    lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

