package com.example.firstproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.firstproject.adapter.ProductAdapter
import com.example.firstproject.data.Constants
import com.example.firstproject.data.local.CartProduct
import com.example.firstproject.data.local.CartProductDao
import com.example.firstproject.data.remote.ProductResponse
import com.example.firstproject.databinding.ActivitySearchBinding
import com.google.gson.Gson

class SearchActivity : AppCompatActivity() {
    lateinit var binding:ActivitySearchBinding
    lateinit var queue:RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        queue=Volley.newRequestQueue(baseContext)

        val keyword=intent?.extras?.getString("keyword")?:""
        searchProduct(keyword)
    }

    private fun searchProduct(keyword:String){
        val url = "${Constants.BASE_URL}Product/search?query=$keyword"
        Log.d("Tag","get subcategory by category id;  url is: $url")

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

                        val myIntent= Intent(baseContext, ProductDetailActivity::class.java)
                        myIntent.putExtra("product_id",product.product_id)
                        binding.root.context.startActivity(myIntent)
                    }

                    adapter.setOnAddToCartSelectedListener { product, i ->
                        //add to cart
                        val dao= CartProductDao(baseContext)
                        if(!dao.isProductInCart(product.product_id.toInt())){
                            val item= CartProduct(product.product_id.toInt(),
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