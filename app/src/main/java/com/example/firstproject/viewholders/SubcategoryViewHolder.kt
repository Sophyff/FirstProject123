package com.example.firstproject.viewholders

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisapp.data.ProductResponse
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.firstproject.activities.ProductDetailActivity
import com.example.firstproject.adapter.ProductAdapter

import com.example.firstproject.data.Constants
import com.example.firstproject.data.local.CartProduct
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
                        val myIntent= Intent(binding.root.context, ProductDetailActivity::class.java)
                        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        myIntent.putExtra("product_id",product.product_id)
                        binding.root.context.startActivity(myIntent)
                    }

                    adapter.setOnAddToCartSelectedListener { product, i ->
                        //add to cart
                        val dao=CartProductDao(binding.root.context)
                        if(!dao.isProductInCart(product.product_id.toInt())){
                            val item=CartProduct(product.product_id.toInt(),
                                product.product_name,
                                product.description,
                                product.product_image_url,
                                product.price.toFloat(),
                                1,
                                product.price.toFloat(),
                            )
                            val id=dao.addProduct(item)
                            if(id>0) {
                                Toast.makeText(binding.root.context, "successfully add product to cart", Toast.LENGTH_LONG).show()
                            }
                            else{
                                Toast.makeText(binding.root.context, "Failed. Please try again", Toast.LENGTH_LONG).show()
                            }
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