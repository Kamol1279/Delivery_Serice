package com.example.deliveryserice.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.deliveryserice.domain.Products

@Database(entities = [Products::class], version = 1 , exportSchema = false)
abstract class PriceDatabes : RoomDatabase() {


    abstract fun productsDao(): PricesDao

    companion object {
        fun getdatabase(context: Context):PriceDatabes {

            return  Room.databaseBuilder( context.applicationContext , PriceDatabes::class.java , "price" ).build()
        }
    }

}