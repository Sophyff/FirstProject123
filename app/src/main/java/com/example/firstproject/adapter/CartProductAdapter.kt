package com.example.firstproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstproject.viewholders.CartProductViewHolder
import com.example.firstproject.viewholders.ProductViewHolder
import com.example.firstproject.data.local.CartProduct
import com.example.firstproject.databinding.ViewHolderCartProductBinding
import com.example.firstproject.databinding.ViewHolderProductBinding

class CartProductAdapter(val list:List<CartProduct>):RecyclerView.Adapter<CartProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding= ViewHolderCartProductBinding.inflate(layoutInflater, parent, false)
        return CartProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        val product=list[position]
        holder.bind(product)

        holder.binding.ivAdd.setOnClickListener {
            if (this::addQuantityClickListener.isInitialized) {
                addQuantityClickListener(product, position)
            }
        }

        holder.binding.ivMinus.setOnClickListener {
            if (this::subQuantityClickListener.isInitialized) {
                subQuantityClickListener(product, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private lateinit var addQuantityClickListener: (CartProduct, Int) -> Unit
    fun setOnAddQuantityClickListener(listener: (CartProduct, Int) -> Unit) {
        addQuantityClickListener= listener
    }

    private lateinit var subQuantityClickListener: (CartProduct, Int) -> Unit
    fun setOnSubQuantityClickListener(listener: (CartProduct, Int) -> Unit) {
        subQuantityClickListener= listener
    }
}