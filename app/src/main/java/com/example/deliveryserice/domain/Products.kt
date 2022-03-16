package com.example.deliveryserice.domain

import android.icu.text.CaseMap.Title
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Products(

    @PrimaryKey
    val id : Long? = null,

    val  img : String,
    val magazine : String,
    val title : String,
    val price : Int

) {
}