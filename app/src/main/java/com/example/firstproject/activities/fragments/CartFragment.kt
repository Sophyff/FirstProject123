package com.example.firstproject.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisapp.data.Product
import com.example.firstproject.R
import com.example.firstproject.activities.fragments.adapter.CartProductAdapter
import com.example.firstproject.activities.fragments.adapter.CategoryAdapter
import com.example.firstproject.data.local.CartProduct
import com.example.firstproject.data.remote.Category
import com.example.firstproject.databinding.FragmentCartBinding


class CartFragment : Fragment() {
    lateinit var binding: FragmentCartBinding
    lateinit var productList:List<CartProduct>
    lateinit var adapter: CartProductAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCartBinding.inflate(layoutInflater)
        val view = binding.root
        //rvCartProducts=view.findViewById(R.id.rvCartProducts)
        binding.rvCartProducts.layoutManager = LinearLayoutManager(view.getContext())


        return view
    }

    companion object {

    }
}