package com.example.firstproject.data.remote

data class getOrdersResponse(
    val message: String,
    val orders: List<Order>,
    val status: Int
)

data class Order(
    val address: String,
    val address_title: String,
    val bill_amount: String,
    val order_date: String,
    val order_id: String,
    val order_status: String,
    val payment_method: String
)