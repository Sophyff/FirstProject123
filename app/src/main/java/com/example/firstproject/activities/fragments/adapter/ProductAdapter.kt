package com.example.firstproject.activities.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisapp.data.Product
import com.example.firstproject.activities.fragments.viewholders.ProductViewHolder
import com.example.firstproject.data.remote.Category
import com.example.firstproject.databinding.ViewHolderProductBinding

class ProductAdapter(val list:List<Product>):RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding=ViewHolderProductBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
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