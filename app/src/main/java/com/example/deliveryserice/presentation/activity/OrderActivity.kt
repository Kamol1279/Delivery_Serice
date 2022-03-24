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
import androidx.lifecycle.lifecycleScope
import com.example.deliveryserice.data.Api.DeliveryService
import com.example.deliveryserice.data.Api.Retrofit
import com.example.deliveryserice.data.PriceDatabes
import com.example.deliveryserice.data.PricesDao
import com.example.deliveryserice.databinding.ActivityOrderBinding
import com.example.deliveryserice.domain.Order
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.jar.Manifest

class OrderActivity : AppCompatActivity() {

    lateinit var binding: ActivityOrderBinding
    lateinit var api : DeliveryService
    lateinit var database: PriceDatabes
    lateinit var priceDao: PricesDao



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val value = intent.getIntExtra("VALUE", 0)

        binding.value.text = value.toString()

        database = PriceDatabes.getdatabase(this)
        priceDao = database.productsDao()

        api = Retrofit.apiService


        binding.order.setOnClickListener {
            if (binding.name.text.isEmpty() || binding.phone.text.isEmpty() || binding.adress.text.isEmpty()) {
                Snackbar.make(binding.root, "Malumotlarni kiriting", Snackbar.LENGTH_LONG).show()

            }

            else {
                lifecycleScope.launch(Dispatchers.IO){
                    val list = priceDao.getProducts()

                    api.postOrder(Order( binding.name.text.toString() , binding.adress.text.toString() , binding.phone.text.toString() ,
                    binding.value.text.toString(), list))

                    launch(Dispatchers.Main){
                        database.clearAllTables()
                    }
                }


            }
        }
    }




}