package com.example.firstproject.activities.fragments.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisapp.data.Product
import com.example.firstproject.data.Constants
import com.example.firstproject.databinding.ActivityProductByCategoryBinding
import com.example.firstproject.databinding.ViewHolderProductBinding
import com.squareup.picasso.Picasso

class ProductViewHolder(val binding: ViewHolderProductBinding) :RecyclerView.ViewHolder(binding.root){
    fun bind(product: Product){
        binding.tvProductName.text=product.product_name
        binding.tvProductPrice.text= "\$ ${product.price}"
        binding.tvProductDesc.text=product.description

        val imgEndPoint=product.product_image_url
        val url="${Constants.BASE_URL_IMG}$imgEndPoint"
        Picasso.get().load(url).into(binding.ivProductImg)
    }
}