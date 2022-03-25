package com.example.firstproject.activities.fragments

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.firstproject.R
import com.example.firstproject.activities.checkout.CheckoutActivity
import com.example.firstproject.adapter.CartProductAdapter
import com.example.firstproject.adapter.CategoryAdapter
import com.example.firstproject.data.local.CartProduct
import com.example.firstproject.data.local.CartProductDao
import com.example.firstproject.data.remote.Category
import com.example.firstproject.databinding.FragmentCartBinding


class CartFragment : Fragment() {
    lateinit var binding: FragmentCartBinding
    lateinit var productList:ArrayList<CartProduct>
    lateinit var adapter: CartProductAdapter
    lateinit var dao:CartProductDao

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

        dao= CartProductDao(binding.root.context)
        productList=dao.getCartProducts()

        if(productList.size==0){
            binding.tvNoItem.visibility=View.VISIBLE
        }else{
            binding.tvNoItem.visibility=View.GONE

            adapter= CartProductAdapter(productList)
            binding.rvCartProducts.adapter=adapter

            changeQuantityListener(adapter)

            binding.btnCheckOut.setOnClickListener {
                startActivity(Intent(view.context,CheckoutActivity::class.java))
            }
        }

        return view
    }

    private fun changeQuantityListener(adapter: CartProductAdapter){
        adapter.setOnAddQuantityClickListener { product, position ->
            product.quantity =product.quantity+1
            product.amount=product.amount + product.price
            val result=dao.updateProduct(product)
            if(result>0){
                Log.d("tag", "update in the cart product table successfully")
            }
            adapter.notifyItemChanged(position)
        }

        adapter.setOnSubQuantityClickListener { product, position ->
            product.quantity =product.quantity-1
            if(product.quantity<1){
                val response=dao.deleteProduct(product.item_id)
                if(response>0){
                    Log.d("tag", "item in cart delete successfully")
                }else(
                        Log.d("tag", "item in cart delete failed")
                )
                productList.removeAt(position)
                adapter.notifyDataSetChanged()
            }else {
                product.quantity =product.quantity-1
                product.amount=product.amount - product.price
                val result=dao.updateProduct(product)
                if(result>0){
                    Log.d("tag", "update in the cart product table successfully")
                }
                adapter.notifyItemChanged(position)
            }
        }



    }

}