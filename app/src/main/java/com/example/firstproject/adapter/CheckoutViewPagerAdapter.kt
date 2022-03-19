package com.example.firstproject.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.firstproject.activities.checkout.CartItemFragment
import com.example.firstproject.activities.checkout.DeliveryFragment
import com.example.firstproject.activities.checkout.PaymentFragment
import com.example.firstproject.activities.checkout.SummaryFragment

class CheckoutViewPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    var size=4
    override fun getItemCount(): Int {
        return size
    }

    override fun createFragment(position: Int): Fragment {
        if(position ==0){
            return CartItemFragment()
        }else if(position==1){
            return DeliveryFragment()
        }else if(position ==2){
            return PaymentFragment()
        }else{
            return SummaryFragment()
        }
    }
}