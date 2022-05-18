package com.example.deliveryserice.domain

data class Order(

    val name : String,
    val phone_num : String,
    val summa : String,
    val long : String,
    val lat : String,
    val products : List<Products>

)
