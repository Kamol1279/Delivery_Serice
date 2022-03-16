package com.example.deliveryserice.data.Api

import com.example.deliveryserice.domain.Magazine
import com.example.deliveryserice.domain.Products
import com.google.gson.Gson
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.Locale

interface DeliveryService {

    @GET("magazines/")
    suspend fun getMagazine():List<Magazine>


    @GET("magazines/{id}/menu/")
    suspend fun getProducts(@Path ("id") id:Long ):List<Products>

}

