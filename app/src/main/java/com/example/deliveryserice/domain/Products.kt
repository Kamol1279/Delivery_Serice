package com.example.deliveryserice.domain

import android.icu.text.CaseMap.Title
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Products(
    @PrimaryKey(autoGenerate = true)
    val id : Int?= null ,
    val  img : String,
    val title : String,
    val price : Int ,


) {
}