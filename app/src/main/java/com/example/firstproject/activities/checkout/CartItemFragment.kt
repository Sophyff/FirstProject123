package com.example.firstproject.activities.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstproject.R
import com.example.firstproject.adapter.CheckoutItemAdapter
import com.example.firstproject.data.local.CartProduct
import com.example.firstproject.data.local.CartProductDao
import com.example.firstproject.databinding.FragmentCartItemBinding


class CartItemFragment : Fragment() {
    lateinit var binding:FragmentCartItemBinding
    lateinit var dao: CartProductDao
    lateinit var productList:ArrayList<CartProduct>
    lateinit var adapter:CheckoutItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCartItemBinding.inflate(layoutInflater)

        binding.rvCheckoutItems.layoutManager = LinearLayoutManager(binding.root.getContext())

        dao= CartProductDao(binding.root.context)
        productList=dao.getCartProducts()
        adapter= CheckoutItemAdapter(productList)
        binding.rvCheckoutItems.adapter=adapter

        calculateTotal()

        return binding.root
    }

    fun calculateTotal() {
        var sum = 0f
        productList.forEach{
            sum = sum + it.amount
        }
        binding.tvTotalAmount.text = "\$ $sum"
    }
}