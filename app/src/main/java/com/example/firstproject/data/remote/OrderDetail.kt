package com.example.firstproject.data.remote

data class OrderDetailResponse(
    val message: String,
    val order: OrderDetail,
    val status: Int
)

data class OrderDetail(
    val address: String,
    val address_title: String,
    val bill_amount: String,
    val items: List<OrderItem>,
    val order_date: String,
    val order_id: String,
    val order_status: String,
    val payment_method: String
)

data class OrderItem(
    val amount: String,
    val description: String,
    val product_id: String,
    val product_image_url: String,
    val product_name: String,
    val quantity: String,
    val unit_price: String
)