package com.example.firstproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisapp.data.Product
import com.example.firstproject.viewholders.CartProductViewHolder
import com.example.firstproject.viewholders.ProductViewHolder
import com.example.firstproject.data.local.CartProduct
import com.example.firstproject.data.remote.Category
import com.example.firstproject.databinding.ViewHolderCartProductBinding
import com.example.firstproject.databinding.ViewHolderCheckoutItemBinding
import com.example.firstproject.databinding.ViewHolderProductBinding
import com.example.firstproject.viewholders.CheckoutItemViewHolder

class CheckoutItemAdapter(val list:List<CartProduct>):RecyclerView.Adapter<CheckoutItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutItemViewHolder{
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding= ViewHolderCheckoutItemBinding.inflate(layoutInflater, parent, false)
        return CheckoutItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CheckoutItemViewHolder, position: Int) {
        val product=list[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}