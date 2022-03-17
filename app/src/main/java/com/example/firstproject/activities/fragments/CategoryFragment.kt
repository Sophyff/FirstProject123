package com.example.firstproject.activities.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.firstproject.R
import com.example.firstproject.activities.ProductByCategoryActivity
import com.example.firstproject.activities.fragments.adapter.CategoryAdapter
import com.example.firstproject.data.Constants
import com.example.firstproject.data.remote.Category
import com.example.firstproject.data.remote.CategoryResponse
import com.example.firstproject.databinding.FragmentCategoryBinding
import com.google.gson.Gson


class CategoryFragment : Fragment() {
    lateinit var queue: RequestQueue
    lateinit var categoryList:List<Category>
    lateinit var adapter: CategoryAdapter
    lateinit var  rvCategory:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        rvCategory=view.findViewById(R.id.rvCategories)
        rvCategory.layoutManager = GridLayoutManager(view.getContext(), 2)

        queue= Volley.newRequestQueue(view.getContext())
        getCategory()
        return view
    }

    private fun getCategory(){
        val url = "${Constants.BASE_URL}Category"
        //val url="https://www.themealdb.com/api/json/v1/1/categories.php"
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val gson = Gson()
                val categories = gson.fromJson(it, CategoryResponse::class.java)

                categoryList= categories.categories

                Log.d("tag", "the category list is $categories")
                adapter = CategoryAdapter(categoryList)
                rvCategory.adapter = adapter
                adapter.setOnCategorySelectedListener{
                        category, position ->
                    val categoryId=category.category_id
                    val intent=Intent(view?.getContext(), ProductByCategoryActivity::class.java)
                    intent.putExtra("category_id",categoryId)
                    startActivity(intent)
                }

            },
            {
                it.printStackTrace()
               // Toast.makeText(this, "Error is : $it", Toast.LENGTH_LONG).show()
            }
        )
        val retryPolicy = DefaultRetryPolicy(10 * 1000, 3, 1.5f)
        request.retryPolicy = retryPolicy
        queue.add(request)
    }
}