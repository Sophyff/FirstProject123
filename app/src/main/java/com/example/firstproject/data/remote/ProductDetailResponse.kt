package com.example.firstproject.data.remote

data class ProductDetailResponse(
    val message: String,
    val product: ProductDetail,
    val status: Int
)

data class ProductDetail(
    val average_rating: String,
    val category_id: String,
    val description: String,
    val images: List<Image>,
    val is_active: String,
    val price: String,
    val product_id: String,
    val product_image_url: String,
    val product_name: String,
    val reviews: List<Any>,
    val specifications: List<Specification>,
    val sub_category_id: String
)

data class Image(
    val display_order: String,
    val image: String
)

data class Specification(
    val display_order: String,
    val specification: String,
    val specification_id: String,
    val title: String
)