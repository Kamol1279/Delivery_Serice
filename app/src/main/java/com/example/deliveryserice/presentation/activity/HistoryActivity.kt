package com.example.deliveryserice.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.deliveryserice.databinding.ActivityHistoryBinding
import com.example.deliveryserice.databinding.ActivityMainBinding

class HistoryActivity:AppCompatActivity() {
    lateinit var binding : ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}