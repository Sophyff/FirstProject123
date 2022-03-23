package com.example.firstproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstproject.data.remote.Order
import com.example.firstproject.viewholders.OrderViewHolder
import com.example.firstproject.databinding.ViewHolderOrderBinding

class OrderAdapter(val list:List<Order>):RecyclerView.Adapter<OrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding=ViewHolderOrderBinding.inflate(layoutInflater, parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order=list[position]
        holder.bind(order)

        holder.itemView.setOnClickListener {
            if(this::orderSelectedListener.isInitialized) {
                orderSelectedListener(order, position)
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    private lateinit var orderSelectedListener: (Order, Int) -> Unit

    fun setOnOrderSelectedListener(listner: (Order, Int) -> Unit) {
        orderSelectedListener = listner
    }
}