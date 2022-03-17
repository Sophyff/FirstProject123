package com.example.firstproject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.firstproject.R
import com.example.firstproject.activities.fragments.adapter.ViewPagerAdapter
import com.example.firstproject.data.Constants
import com.example.firstproject.data.remote.SubCategoryResponse
import com.example.firstproject.data.remote.Subcategory
import com.example.firstproject.databinding.ActivityProductByCategoryBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class ProductByCategoryActivity : AppCompatActivity() {
    lateinit var queue: RequestQueue
    lateinit var adapter: ViewPagerAdapter
    lateinit var list: List<Subcategory>
    lateinit var binding:ActivityProductByCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProductByCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queue= Volley.newRequestQueue(baseContext)
        val categoryId=intent.extras?.getString("category_id")?.toInt()
        Log.d("Tag","To get the subcategory, the category is $categoryId")

        if(categoryId!=0){
            loadSubCategories(categoryId)
            Log.d("Tag","To get the subcategory, the category is $categoryId")
        }
    }

    private fun loadSubCategories(categoryId:Int?) {

        val url = "${Constants.BASE_URL}SubCategory?category_id=$categoryId"
        Log.d("Tag","get subcategory by category id;  url is: $url")
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val gson = Gson()
                val response = gson.fromJson(it, SubCategoryResponse::class.java)
                if(response.status == 0) {

                    list = response.subcategories
                    adapter = ViewPagerAdapter(list)
                    binding.viewPager.adapter = adapter
                    val mediator = TabLayoutMediator(binding.tabs, binding.viewPager) {
                            tab, position ->
                        tab.text = list[position].subcategory_name
                    }
                    mediator.attach()
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
}