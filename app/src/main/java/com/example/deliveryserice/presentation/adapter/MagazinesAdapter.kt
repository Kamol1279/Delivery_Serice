package com.example.deliveryserice.presentation.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryserice.R
import com.example.deliveryserice.databinding.ItemMagazinesBinding
import com.example.deliveryserice.domain.Magazine



class MagazinesAdapter (private val magazines : List<Magazine> , private val callbeck : CallbeckMagazines ):RecyclerView.Adapter<MagazinesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MagazinesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding:ItemMagazinesBinding = ItemMagazinesBinding.inflate(inflater)
        return MagazinesViewHolder(binding , callbeck)
    }



    override fun onBindViewHolder(holder: MagazinesViewHolder, position: Int) {
        holder.bind(magazines[position])
    }


    override fun getItemCount(): Int {
        return magazines.size
    }



}
class MagazinesViewHolder ( private val binding: ItemMagazinesBinding , private val callbeck: CallbeckMagazines) : RecyclerView.ViewHolder(binding.root){

    fun bind ( magazine: Magazine){

        binding.nameMagazine.text=magazine.name
        binding.type.text = magazine.typeOfMagazine


        ViewCompat.setBackgroundTintList(
            binding.background , ColorStateList.valueOf(
                ContextCompat.getColor(itemView.context, getColors())
        ))

        binding.background.setOnClickListener {
            callbeck.onClick(magazine)

            }
        }
    }


fun getColors():Int {
    val colors = mutableListOf<Int>(
        R.color.blue,
        R.color.black,
        R.color.white,
        R.color.teal_700,
        R.color.teal_200,
        R.color.purple_200
    )
    return colors.random()
}

fun interface CallbeckMagazines {
    fun onClick (magazine: Magazine)
}

