package com.example.deliveryserice.presentation.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
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
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderActivity : AppCompatActivity() {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: com.google.android.gms.location.LocationRequest

    var long = ""
    var lat = ""
    var PERMISSION_ID = 0
    lateinit var binding: ActivityOrderBinding
    lateinit var api: DeliveryService
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

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        binding.getLocation.setOnClickListener {
            getLocation()
        }


        binding.order.setOnClickListener {
            if (binding.name.text.isNotEmpty() && binding.phone.text.isNotEmpty()) {

                val coroutineExceptionHandler = CoroutineExceptionHandler{_,throwable->
                    throwable.printStackTrace()
                }
                lifecycleScope.launch(Dispatchers.IO ) {
                    val list = priceDao.getProducts()

                    api.postOrder(
                        Order(
                            binding.name.text.toString(),
                            binding.phone.text.toString(),
                            binding.value.text.toString(),
                            long,
                            lat,
                            list,
                        ))

                    priceDao.deleteAll(list)

                    launch(Dispatchers.Main) {
                        finish()
                    }
                }

            }
            else {
                Snackbar.make(binding.root, "Malumotlarni kiriting", Snackbar.LENGTH_LONG).show()
            }


        }
    }

    private fun getLocation() {
        if (checkPermission()) {
            if (isLocationEnabled()) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    val location = task.result

                    if (location == null) {
                        getNewLocation()

                    } else {
                        long = location.longitude.toString()
                        lat = location.latitude.toString()

                        Snackbar.make(binding.root , "lokatsiya olindi" , Snackbar.LENGTH_LONG ).show()

                    }
                }
            } else {
                Snackbar.make(binding.root, "Iltimos GPS ni yoking", Snackbar.LENGTH_LONG).show()
            }
        } else {
            requestPermission()
        }
    }


    private fun getNewLocation() {
        locationRequest = com.google.android.gms.location.LocationRequest()
        locationRequest.priority = com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 2
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            super.onLocationResult(p0)
            var lostLocation = p0.lastLocation

            long = lostLocation.longitude.toString()
            lat = lostLocation.latitude.toString()
        }
    }


    private fun checkPermission(): Boolean {

        return ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            PERMISSION_ID
        )
    }

    private fun isLocationEnabled(): Boolean {
        val locationManger = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManger.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManger.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Debug:", "You Have the Permission")
            }
        }
    }


}