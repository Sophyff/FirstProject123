package com.example.firstproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstproject.data.remote.OrderItem
import com.example.firstproject.databinding.ViewHolderCheckoutItemBinding
import com.example.firstproject.viewholders.OrderItemViewHolder

class OrderItemAdapter(val list:List<OrderItem>):RecyclerView.Adapter<OrderItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder{
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding= ViewHolderCheckoutItemBinding.inflate(layoutInflater, parent, false)
        return OrderItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        val product=list[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}