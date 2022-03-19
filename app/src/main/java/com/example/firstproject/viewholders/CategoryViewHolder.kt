package com.example.firstproject.viewholders

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisapp.data.ProductResponse
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.firstproject.adapter.ProductAdapter

import com.example.firstproject.data.Constants
import com.example.firstproject.data.remote.Category
import com.example.firstproject.data.remote.Subcategory
import com.example.firstproject.databinding.ViewHolderCategoryBinding
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.net.URLEncoder

class CategoryViewHolder(val binding: ViewHolderCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(category: Category) {
        binding.tvCategoryName.text = category.category_name
        val imgEndPoint=category.category_image_url
        val url="${Constants.BASE_URL_IMG}$imgEndPoint"
        Picasso.get().load(url).into(binding.ivImg)
    }
}