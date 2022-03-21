package com.example.firstproject.data.remote

data class ProductResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)

data class Product(
    val product_id: String,
    val category_id: String,
    val average_rating: String,
    val category_name: String,
    val description: String,
    val price: String,
    val product_image_url: String,
    val product_name: String,
    val sub_category_id: String,
    val subcategory_name: String
)