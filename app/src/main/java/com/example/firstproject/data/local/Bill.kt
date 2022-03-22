package com.example.firstproject.data.local

data class Bill(
    var bill_amount: Float,
    var delivery_address: DeliveryAddress,
    var items: List<Item>,
    var payment_method: String,
    var user_id: Int
)

