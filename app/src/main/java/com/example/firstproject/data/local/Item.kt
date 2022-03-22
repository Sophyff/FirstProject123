package com.example.firstproject.data.local

data class DeliveryAddress(
    var title: String,
    var address: String

)

data class Item(
    var product_id: Int,
    var quantity: Int,
    var unit_price: Float
)