package com.example.firstproject.activities.fragments.viewholders

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisapp.data.ProductResponse
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.firstproject.activities.fragments.adapter.ProductAdapter

import com.example.firstproject.data.Constants
import com.example.firstproject.data.local.CartProductDao
import com.example.firstproject.data.remote.Category
import com.example.firstproject.data.remote.Subcategory
import com.example.firstproject.databinding.ViewHolderCategoryBinding
import com.example.firstproject.databinding.ViewHolderSubCategoryBinding
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class SubcategoryViewHolder(val binding: ViewHolderSubCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
//    fun bind(subcategory: Subcategory) {
//        binding.tvSubCategory.text = subcategory.subcategory_name
//    }

    val queue = Volley.newRequestQueue(binding.root.context)
    fun bind(subcategory: Subcategory) {
        val id=subcategory.subcategory_id
        val url = "${Constants.BASE_URL}SubCategory/products/$id"
        Log.d("Tag","get products by subcategory_id url is: $url")
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val gson = Gson()
                val response = gson.fromJson(it, ProductResponse::class.java)
                if(response.status == 0) {
                    val adapter = ProductAdapter(response.products)
                    binding.rvProducts.layoutManager = LinearLayoutManager(binding.root.context)
                    binding.rvProducts.adapter = adapter
                    adapter.setOnProductSelectedListener { product, i ->
                        //todo go to the product detail activity
                    }

                    adapter.setOnAddToCartSelectedListener { product, i ->
                        //add to cart
                        val dao=CartProductDao(binding.root.context)
                        val id=dao.addProduct(product,1)
                        if(id>0) {
                            Toast.makeText(binding.root.context, "successfully add product to cart", Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(binding.root.context, "Failed. Please try again", Toast.LENGTH_LONG).show()
                        }
                    }

                } else {
                    Log.i("Tag", "request for products by sub-category was failed")
                }
            },
            {
                it.printStackTrace()

            }
        )

        queue.add(request)
    }
}