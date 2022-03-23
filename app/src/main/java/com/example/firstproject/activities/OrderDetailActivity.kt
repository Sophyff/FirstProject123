package com.example.firstproject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.firstproject.adapter.CheckoutItemAdapter
import com.example.firstproject.adapter.OrderItemAdapter
import com.example.firstproject.data.Constants
import com.example.firstproject.data.local.CartProduct

import com.example.firstproject.data.remote.OrderDetail
import com.example.firstproject.data.remote.OrderDetailResponse
import com.example.firstproject.databinding.ActivityOrderDetailBinding
import com.google.gson.Gson


class OrderDetailActivity : AppCompatActivity() {
    lateinit var binding:ActivityOrderDetailBinding
    lateinit var queue: RequestQueue
    lateinit var adapter: OrderItemAdapter
    var orderId=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityOrderDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queue= Volley.newRequestQueue(baseContext)
        binding.rvItems.layoutManager = LinearLayoutManager(baseContext)

        orderId= intent?.extras?.getString("order_id")?.toInt()!!

        if(orderId!=0){
            loadOrderDetail(orderId)
        }

    }

    private fun loadOrderDetail(orderId:Int){
        val url = "${Constants.BASE_URL}Order?order_id=$orderId"
        Log.d("Tag","get order detail by id $orderId;  url is: $url")

        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val gson = Gson()
                val response = gson.fromJson(it, OrderDetailResponse::class.java)
                if(response.status == 0) {
                    //api call is success
                    val orderDetail=response.order
                    setOrderDetail(orderDetail)
                    Log.d("Tag","response in order detail: order id: ${orderDetail.order_id}")
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

    private fun setOrderDetail(orderDetail: OrderDetail) {
        val list=orderDetail.items
        adapter=OrderItemAdapter(list)
        binding.rvItems.adapter=adapter

        binding.orderAmount.text="Total: ${orderDetail.bill_amount}"
        binding.orderId.text="Order Id: ${orderDetail.order_id}"
        binding.orderStatus.text="Order Status: ${orderDetail.order_status}"
        binding.orderDate.text =orderDetail.order_date
    }
}