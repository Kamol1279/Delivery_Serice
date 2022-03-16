package com.example.deliveryserice.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.deliveryserice.databinding.ActivityOrderBinding
import com.google.android.material.snackbar.Snackbar

class OrderActivity : AppCompatActivity() {

    lateinit var binding: ActivityOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val value = intent.getIntExtra("VALUE", 0)

        binding.value.text = value.toString()


        binding.order.setOnClickListener {
            if (binding.name.text.isEmpty() || binding.phone.text.isEmpty() || binding.adress.text.isEmpty()) {
                Snackbar.make(binding.root, "Malumotlarni kiriting", Snackbar.LENGTH_LONG).show()

            } else {
                //метод для отправки данных пользователя

            }
        }
    }

}