package com.example.deliveryserice.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryserice.databinding.ItemProductsBinding
import com.example.deliveryserice.domain.ProductsApi
import com.squareup.picasso.Picasso


class PriceAdapter (private val price : List<ProductsApi> , private val context: Context , private val callbeck: CallbeckProducts  ) : RecyclerView.Adapter<PriceViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding:ItemProductsBinding = ItemProductsBinding.inflate(inflater)


        return PriceViewHolder(binding , callbeck  )
    }


    override fun onBindViewHolder(holder: PriceViewHolder, position: Int) {

        holder.bind(price[position] , context )
    }

    override fun getItemCount(): Int {
        return price.size
    }


}
class PriceViewHolder ( private  val binding: ItemProductsBinding , private val callbeck: CallbeckProducts ):RecyclerView.ViewHolder(binding.root){


    fun bind(products: ProductsApi, context: Context ){
        binding.name.text=products.title
        binding.value.text=products.price.toString()

       Picasso.with(context)
           .load(products.img)
           .into(binding.image)


        binding.sell.setOnClickListener{
            callbeck.sell(products)
        }
    }

}

fun interface CallbeckProducts {
    fun sell(products: ProductsApi)
}