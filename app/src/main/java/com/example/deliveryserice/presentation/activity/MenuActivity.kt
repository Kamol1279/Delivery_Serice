package com.example.deliveryserice.presentation.activity

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.deliveryserice.ConnectionLiveData
import com.example.deliveryserice.R
import com.example.deliveryserice.databinding.ActivityMenuBinding
import com.example.deliveryserice.presentation.fragments.CardFragment
import com.example.deliveryserice.presentation.fragments.DisconnectFragments
import com.example.deliveryserice.presentation.fragments.MagazineFragment
import com.example.deliveryserice.presentation.fragments.ProfileFragments

class MenuActivity:AppCompatActivity() {

    lateinit var cld : ConnectionLiveData
    lateinit var binding: ActivityMenuBinding

    val magazinesFragment = MagazineFragment()
    val cardFragment = CardFragment()
    val profileFragment = ProfileFragments()
    val disconnectFragment = DisconnectFragments()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)


        checkNetworkConnect(application)


    }


    private fun checkNetworkConnect(application: Application) {

        cld = ConnectionLiveData(application)

        cld.observe(this){ isConnected ->

            if (isConnected){

                binding.menu.setOnItemSelectedListener { val id = it.itemId

                    val fragment = when (id){
                        R.id.home -> magazinesFragment
                        R.id.card -> cardFragment
                        R.id.profile -> profileFragment
                        else ->magazinesFragment
                    }

                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment , fragment)
                        .commit()

                    true
                }


                binding.menu.selectedItemId= R.id.home
            }

            else {

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment , disconnectFragment)
                    .commit()

            }

        }

    }

}