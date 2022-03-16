package com.example.deliveryserice.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deliveryserice.data.Api.DeliveryService
import com.example.deliveryserice.data.Api.Retrofit
import com.example.deliveryserice.data.PriceDatabes
import com.example.deliveryserice.data.PricesDao
import com.example.deliveryserice.databinding.ActivityPricesBinding
import com.example.deliveryserice.domain.Products
import com.example.deliveryserice.presentation.adapter.CallbeckProducts
import com.example.deliveryserice.presentation.adapter.PriceAdapter
import com.example.deliveryserice.presentation.fragments.MagazineFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PriceActivity : AppCompatActivity(), CallbeckProducts {

    lateinit var database: PriceDatabes
    lateinit var priceDao: PricesDao
    lateinit var api : DeliveryService

    lateinit var binding: ActivityPricesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPricesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = PriceDatabes.getdatabase(this)
        priceDao = database.productsDao()


        api =Retrofit.apiService

        val magazine =intent.getStringExtra("NAME")
        val id = intent.getLongExtra("ID", 0)

        binding.magazine.text = magazine



        lifecycleScope.launch(Dispatchers.IO) {

            val list = api.getProducts(id)
            val adapter = PriceAdapter(list, this@PriceActivity , callbeck = { prices ->
                sell(prices)
            })
            launch(Dispatchers.Main) {
                binding.recyclerPrice.adapter = adapter
            }
        }

        binding.Exit.setOnClickListener {
            finish()
        }

        binding.recyclerPrice.layoutManager = GridLayoutManager(this, 2)

    }

    override fun sell(products: Products) {
        lifecycleScope.launch(Dispatchers.IO) {

            priceDao.insert(Products(products.id, products.img, products.magazine, products.title, products.price))

            launch(Dispatchers.Main) {
                Snackbar.make(binding.root,"${products.title} savatga solindi" , Snackbar.LENGTH_LONG).show()
            }

        }
    }
}

