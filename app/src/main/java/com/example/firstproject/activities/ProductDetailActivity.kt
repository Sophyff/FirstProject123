package com.example.firstproject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.firstproject.adapter.ViewPagerAdapter
import com.example.firstproject.data.Constants
import com.example.firstproject.data.local.CartProduct
import com.example.firstproject.data.local.CartProductDao
import com.example.firstproject.data.remote.ProductDetail
import com.example.firstproject.data.remote.ProductDetailResponse
import com.example.firstproject.data.remote.SubCategoryResponse
import com.example.firstproject.databinding.ActivityProductDetailBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class ProductDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductDetailBinding
    lateinit var queue:RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        queue= Volley.newRequestQueue(baseContext)

        val productId=intent.extras?.getString("product_id")?.toInt()?:0
        Log.d("Tag","To get the subcategory, the category is $productId")

        if(productId>0){
            loadProductDetail(productId)
        }

    }

   private fun loadProductDetail(productId:Int){
       val url = "${Constants.BASE_URL}Product/details/$productId"
       Log.d("Tag","get subcategory by category id;  url is: $url")

       val request = StringRequest(
           Request.Method.GET,
           url,
           {
               val gson = Gson()
               val response = gson.fromJson(it, ProductDetailResponse::class.java)
               if(response.status == 0) {
                    //api call is success
                        val productDetail=response.product
                        setProductDetail(productDetail)
                   Log.d("Tag","product detail; title is: ${productDetail.description}")
               } else {
                   Toast.makeText(baseContext, "Unknown error. Please retry.", Toast.LENGTH_LONG).show()
               }
           },
           {
               it.printStackTrace()
               Toast.makeText(baseContext, "Error is : $it", Toast.LENGTH_LONG).show()
           }
       )
       queue.add(request)
    }

    private fun setProductDetail(productDetail: ProductDetail){
        val imgEndPoint=productDetail.images[0]  //todo need to load all the images
        val url="${Constants.BASE_URL_IMG}$imgEndPoint"
        Picasso.get().load(url).into(binding.ivProductImg)

        binding.tvProductName.text=productDetail.product_name
        binding.tvProductDesc.text=productDetail.description
        binding.rbRating.rating=productDetail.average_rating.toFloat()
        binding.tvProductPrice.text= "\$ ${productDetail.product_name}"

        //todo other product details but without data

        binding.tvModelName.text=productDetail.specifications[0].specification
        binding.tvAddToCart.setOnClickListener {
            val dao= CartProductDao(binding.root.context)
            val id=productDetail.product_id.toInt()
            if(!dao.isProductInCart(id)){
                val name=productDetail.product_name
                val desc=productDetail.description
                val img_url=productDetail.product_image_url
                val price=productDetail.price.toFloat()

                val product=CartProduct(id,name,desc,img_url,price,1,price)
                val id=dao.addProduct(product)
                if(id>0) {
                    Toast.makeText(binding.root.context, "successfully add product to cart", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(binding.root.context, "Failed. Please try again", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}