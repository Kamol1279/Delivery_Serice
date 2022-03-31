package com.example.deliveryserice.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.deliveryserice.domain.Products

@Dao
interface PricesDao {

    @Query("select * from Products")
    fun getProducts () : List<Products>

    @Insert
    fun insert (vararg prosuct :Products)

    @Delete
    fun delet (products: Products)

}
