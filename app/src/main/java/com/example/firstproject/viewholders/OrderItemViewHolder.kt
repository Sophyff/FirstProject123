package com.example.firstproject.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.firstproject.data.Constants
import com.example.firstproject.data.remote.OrderItem
import com.example.firstproject.databinding.ViewHolderCheckoutItemBinding
import com.squareup.picasso.Picasso

class OrderItemViewHolder(val binding: ViewHolderCheckoutItemBinding) :RecyclerView.ViewHolder(binding.root){
    fun bind(item: OrderItem){
        binding.tvProductName.text=item.product_name
        binding.tvPrice.text= "\$ ${item.unit_price}"
        binding.tvQty.text=item.quantity.toString()
        binding.tvAmount.text=item.amount.toString()


        val imgEndPoint=item.product_image_url
        val url="${Constants.BASE_URL_IMG}$imgEndPoint"
        Picasso.get().load(url).into(binding.ivProductImg)
    }
}