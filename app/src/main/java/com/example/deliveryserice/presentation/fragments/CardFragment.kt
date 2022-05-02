package com.example.deliveryserice.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deliveryserice.data.PriceDatabes
import com.example.deliveryserice.data.PricesDao
import com.example.deliveryserice.databinding.FragmentCardBinding
import com.example.deliveryserice.domain.Products
import com.example.deliveryserice.presentation.activity.OrderActivity
import com.example.deliveryserice.presentation.adapter.CardAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardFragment : Fragment(), CardAdapter.CallbeckCard {

    lateinit var database: PriceDatabes
    lateinit var priceDao : PricesDao

    var value = 0

    lateinit var binding: FragmentCardBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCardBinding.inflate(inflater)

        database = PriceDatabes.getdatabase(this.requireContext())
        priceDao = database.productsDao()


        lifecycleScope.launch(Dispatchers.IO) {

            val list = priceDao.getProducts()

            launch(Dispatchers.Main){

                binding.price.text = editValue(list)

                val adapter = CardAdapter(list ,this@CardFragment.requireContext() ,callbeck = { prices ->
                    lifecycleScope.launch(Dispatchers.IO) {

                        priceDao.delete(prices)

                        launch(Dispatchers.Main) {
                            (binding.recyclerSells.adapter as CardAdapter).remove(prices)
                            newValue(prices)

                            Snackbar.make(binding.root , "${prices.title} savatdan olip tashlandi" , Snackbar.LENGTH_LONG).show()

                        }
                    }

                })

                binding.recyclerSells.adapter = adapter

            }
        }


        binding.order.setOnClickListener {

            startActivity(Intent(this.context, OrderActivity::class.java).putExtra("VALUE", value))
            value = 0
        }



        binding.recyclerSells.layoutManager = GridLayoutManager(this.context, 2)

        return binding.root
    }

    override fun delet(products: Products) {

    }
    fun editValue (list : List<Products>) : String {

        for (i in list) {
            value += i.price
        }
        return value.toString()
    }
    fun newValue(prices : Products){
        val newValue = value - prices.price
        value = newValue
        binding.price.text = value.toString()
    }
}


