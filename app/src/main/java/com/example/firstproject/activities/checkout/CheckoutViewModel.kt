package com.example.firstproject.activities.checkout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstproject.data.local.DeliveryAddress

class CheckoutViewModel: ViewModel() {
    val addr =MutableLiveData<DeliveryAddress>()
    val paymentOption=MutableLiveData<String>()
}