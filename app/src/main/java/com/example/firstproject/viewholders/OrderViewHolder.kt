package com.example.firstproject.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.firstproject.data.Constants
import com.example.firstproject.data.remote.Order
import com.example.firstproject.databinding.ViewHolderOrderBinding
import com.squareup.picasso.Picasso

class OrderViewHolder(val binding: ViewHolderOrderBinding) :RecyclerView.ViewHolder(binding.root){
    fun bind(order: Order){
        binding.tvOrderId.text=order.order_id
        binding.tvOrderStatus.text= order.order_status
        binding.tvOrderDate.text=order.order_date
        binding.tvAddress.text="${order.address_title} : ${order.address}"
        binding.tvOrderAmount.text="\$ ${order.bill_amount}"
    }
}