package com.example.deliveryserice.data.Api

import com.example.deliveryserice.domain.Magazine
import com.example.deliveryserice.domain.Order
import com.example.deliveryserice.domain.Products
import com.example.deliveryserice.domain.ProductsApi
import com.google.gson.Gson
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.Locale

interface DeliveryService {

    @GET("magazines/")
    suspend fun getMagazine():List<Magazine>


    @GET("magazines/{id}/menu/")
    suspend fun getProducts(@Path ("id") id:Int ):List<ProductsApi>

    @POST("register/")
    suspend fun postOrder( @Body order : Order) : Order

}

