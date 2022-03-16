package com.example.deliveryserice.data.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object  Retrofit {

    private const val URL ="https://marketapi.pythonanywhere.com/get/"

    val apiService by lazy { getRetrofit().create(DeliveryService::class.java) }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}