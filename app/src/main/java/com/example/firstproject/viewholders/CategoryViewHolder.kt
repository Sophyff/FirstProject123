package com.example.firstproject.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.firstproject.data.Constants
import com.example.firstproject.data.remote.Category
import com.example.firstproject.databinding.ViewHolderCategoryBinding
import com.squareup.picasso.Picasso

class CategoryViewHolder(val binding: ViewHolderCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(category: Category) {
        binding.tvCategoryName.text = category.category_name
        val imgEndPoint=category.category_image_url
        val url="${Constants.BASE_URL_IMG}$imgEndPoint"
        Picasso.get().load(url).into(binding.ivImg)
    }
}