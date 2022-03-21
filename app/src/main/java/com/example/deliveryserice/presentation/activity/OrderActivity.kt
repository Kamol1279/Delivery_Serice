package com.example.deliveryserice.presentation.activity

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.deliveryserice.databinding.ActivityOrderBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import java.util.jar.Manifest

class OrderActivity : AppCompatActivity() {

    lateinit var binding: ActivityOrderBinding



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val value = intent.getIntExtra("VALUE", 0)

        binding.value.text = value.toString()


        binding.order.setOnClickListener {
            if (binding.name.text.isEmpty() || binding.phone.text.isEmpty() || binding.adress.text.isEmpty()) {
                Snackbar.make(binding.root, "Malumotlarni kiriting", Snackbar.LENGTH_LONG).show()

            } else {

                //метод для отправки данных пользователя

            }
        }
    }




}