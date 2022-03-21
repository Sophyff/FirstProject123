package com.example.firstproject.activities.checkout

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstproject.R
import com.example.firstproject.adapter.CheckoutItemAdapter
import com.example.firstproject.data.local.CartProduct
import com.example.firstproject.data.local.CartProductDao
import com.example.firstproject.data.remote.Addresse
import com.example.firstproject.databinding.FragmentCartItemBinding
import com.example.firstproject.databinding.FragmentSummaryBinding

class SummaryFragment : Fragment() {
    lateinit var binding: FragmentSummaryBinding
    lateinit var dao: CartProductDao
    lateinit var productList:ArrayList<CartProduct>
    lateinit var adapter: CheckoutItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSummaryBinding.inflate(layoutInflater)
        binding.rvCheckoutItems.layoutManager = LinearLayoutManager(binding.root.getContext())

        val address= arguments?.getParcelable<Addresse>("address")
        Log.d("summary","title: ${address?.title}  address: ${address?.address}")

        if(address!=null){
            binding.tvAddressTitle.text=address.title
            binding.tvAddress.text=address.address
        }

        dao= CartProductDao(binding.root.context)
        productList=dao.getCartProducts()
        adapter= CheckoutItemAdapter(productList)
        binding.rvCheckoutItems.adapter=adapter

        calculateTotalAmount()
        return binding.root


    }
     private fun calculateTotalAmount() {
        var sum = 0f
        productList.forEach{
            sum = sum + it.amount
        }
        binding.tvTotalAmount.text = "\$ $sum"
    }

}