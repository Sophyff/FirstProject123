package com.example.firstproject.activities.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.firstproject.R
import com.example.firstproject.activities.OrderDetailActivity
import com.example.firstproject.adapter.OrderAdapter
import com.example.firstproject.data.Constants
import com.example.firstproject.data.remote.getOrdersResponse
import com.example.firstproject.databinding.FragmentOrdersBinding
import com.google.gson.Gson

class OrdersFragment : Fragment() {
    lateinit var binding: FragmentOrdersBinding
    var userId=""
    lateinit var queue: RequestQueue
    lateinit var adapter : OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentOrdersBinding.inflate(layoutInflater)

        val pref = this.getActivity()?.getSharedPreferences("User", AppCompatActivity.MODE_PRIVATE)
        userId=pref?.getString("user_id","")!!
        Log.d("user id","$userId")
        queue= Volley.newRequestQueue(binding.root.context)

        binding.rvOrders.layoutManager= LinearLayoutManager(binding.root.context)

        if(userId!=""){
            getOrders()
        }

        return binding.root
    }

    private fun getOrders(){
        val url = "${Constants.BASE_URL}Order/UserOrders/$userId"
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val gson = Gson()
                val response= gson.fromJson(it, getOrdersResponse::class.java)

                val list= response.orders

                Log.d("tag", "the order list is $list")
                adapter = OrderAdapter(list)
                binding.rvOrders.adapter = adapter
                adapter.setOnOrderSelectedListener { order, position ->
                    val intent= Intent(view?.getContext(), OrderDetailActivity::class.java)
                    intent.putExtra("order_id",order.order_id)
                    startActivity(intent)
                }

            },
            {
                it.printStackTrace()
                // Toast.makeText(this, "Error is : $it", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(request)
    }

}