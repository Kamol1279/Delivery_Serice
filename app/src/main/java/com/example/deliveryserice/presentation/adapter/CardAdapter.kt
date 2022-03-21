package com.example.deliveryserice.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryserice.databinding.ItemProductsBinding
import com.example.deliveryserice.domain.Products
import com.squareup.picasso.Picasso

class CardAdapter(private val products: List<Products> , private val context: Context, private val callbeck: CallbeckCard ) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemProductsBinding = ItemProductsBinding.inflate(inflater)

        return CardViewHolder(binding, callbeck)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        holder.bind(products[position] , context)
    }

    fun remove(product: Products) {

        (products as MutableList).remove(product)
        notifyDataSetChanged()

    }


    override fun getItemCount(): Int {
        return products.size
    }

    class CardViewHolder(private val binding: ItemProductsBinding, private val callbeck: CallbeckCard) : RecyclerView.ViewHolder(binding.root) {

        fun bind(products: Products , context: Context) {



            binding.name.text = products.title
            binding.value.text = products.price.toString()
            binding.sell.text = " delet  "

            Picasso.with(context)
                .load(products.img)
                .into(binding.image)

            binding.sell.setOnClickListener {
                callbeck.delet(products)

            }
        }
    }


    fun interface CallbeckCard {
        fun delet(products: Products)
    }

}