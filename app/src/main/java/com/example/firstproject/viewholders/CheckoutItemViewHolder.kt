package com.example.firstproject.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisapp.data.Product
import com.example.firstproject.data.Constants
import com.example.firstproject.data.local.CartProduct
import com.example.firstproject.databinding.ActivityProductByCategoryBinding
import com.example.firstproject.databinding.ViewHolderCartProductBinding
import com.example.firstproject.databinding.ViewHolderCheckoutItemBinding
import com.example.firstproject.databinding.ViewHolderProductBinding
import com.squareup.picasso.Picasso

class CheckoutItemViewHolder(val binding: ViewHolderCheckoutItemBinding) :RecyclerView.ViewHolder(binding.root){
    fun bind(product: CartProduct){
        binding.tvProductName.text=product.name
        binding.tvPrice.text= "\$ ${product.price}"
        binding.tvQty.text=product.quantity.toString()
        binding.tvAmount.text=product.amount.toString()


        val imgEndPoint=product.img_url
        val url="${Constants.BASE_URL_IMG}$imgEndPoint"
        Picasso.get().load(url).into(binding.ivProductImg)
    }
}