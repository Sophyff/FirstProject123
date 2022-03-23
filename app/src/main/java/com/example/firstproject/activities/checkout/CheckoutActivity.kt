package com.example.firstproject.activities.checkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.firstproject.adapter.CheckoutViewPagerAdapter
import com.example.firstproject.data.remote.Addresse
import com.example.firstproject.databinding.ActivityCheckoutBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CheckoutActivity : AppCompatActivity(){
    lateinit var binding: ActivityCheckoutBinding
    lateinit var viewModel:CheckoutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this)[CheckoutViewModel::class.java]

        binding.vpCheckout.adapter= CheckoutViewPagerAdapter(this)

        TabLayoutMediator(binding.tabHeader,binding.vpCheckout){
            tab, position -> if(position==0){
                tab.text="Cart Items"
            }
            else if(position==1){
                tab.text="Delivery"
            }else if(position==2){
                tab.text="Payment"
            }else{
                tab.text="Summary"
            }
        }.attach()
    }


}