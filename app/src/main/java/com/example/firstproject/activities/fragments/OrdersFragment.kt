package com.example.firstproject.activities.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.firstproject.R
import com.example.firstproject.activities.checkout.Communitor
import com.example.firstproject.adapter.AddressAdapter
import com.example.firstproject.data.remote.Addresse
import com.example.firstproject.databinding.FragmentDeliveryBinding
import com.example.firstproject.databinding.FragmentOrdersBinding

class OrdersFragment : Fragment() {
    lateinit var binding: FragmentOrdersBinding
    var userId=""
    lateinit var queue: RequestQueue
    lateinit var addressList:List<Addresse>
    lateinit var adapter : AddressAdapter

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
        //   getOrders()

        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

}