package com.example.firstproject.activities.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisapp.data.Product
import com.example.firstproject.activities.fragments.viewholders.CartProductViewHolder
import com.example.firstproject.activities.fragments.viewholders.ProductViewHolder
import com.example.firstproject.data.remote.Category
import com.example.firstproject.databinding.ViewHolderCartProductBinding
import com.example.firstproject.databinding.ViewHolderProductBinding

class CartProductAdapter(val list:List<Product>):RecyclerView.Adapter<CartProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding= ViewHolderCartProductBinding.inflate(layoutInflater, parent, false)
        return CartProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        val product=list[position]
        holder.bind(product)

        holder.itemView.setOnClickListener {
            if(this::productSelectedListener.isInitialized) {
                productSelectedListener(product, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private lateinit var productSelectedListener: (Product, Int) -> Unit

    fun setOnProductSelectedListener(listner: (Product, Int) -> Unit) {
        productSelectedListener = listner
    }
}