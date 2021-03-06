package com.example.deliveryserice.presentation.fragments

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deliveryserice.ConnectionLiveData
import com.example.deliveryserice.R
import com.example.deliveryserice.data.Api.DeliveryService
import com.example.deliveryserice.data.Api.Retrofit
import com.example.deliveryserice.databinding.FragmentMagazinesBinding
import com.example.deliveryserice.domain.Magazine
import com.example.deliveryserice.presentation.activity.PriceActivity
import com.example.deliveryserice.presentation.adapter.CallbeckMagazines
import com.example.deliveryserice.presentation.adapter.MagazinesAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MagazineFragment : Fragment() , CallbeckMagazines {
    lateinit var api : DeliveryService

    lateinit var connectivityManager : ConnectivityManager
    private lateinit var cld : ConnectionLiveData


    lateinit var binding: FragmentMagazinesBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMagazinesBinding.inflate(inflater)



            lifecycleScope.launch(Dispatchers.IO) {

                api = Retrofit.apiService
                val list = api.getMagazine()


                val adapter = MagazinesAdapter(list, callbeck = { price ->
                    onClick(price)
                })

                launch(Dispatchers.Main) {

                    binding.recyclerMagazine.adapter = adapter
                }
            }











        binding.recyclerMagazine.layoutManager = GridLayoutManager(this.context, 2)




        return binding.root


    }




    override fun onClick(magazine: Magazine) {
        val id = magazine.id
        val name = magazine.name

        startActivity(Intent(this.context, PriceActivity::class.java).putExtra("NAME" , name).putExtra("ID" , id))
    }



}

