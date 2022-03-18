package com.example.firstproject.data

object Constants {
    const val BASE_URL = "https://psmobitech.com/myshop/index.php/"
    const val BASE_URL_IMG="https://psmobitech.com/myshop/images/"
    const val TAG = "MyLogTag"

    const val DB_NAME = "eCommence"
    const val DB_VERSION = 1


//    data class Product(
//        val product_id: String,
//        val category_id: String,
//        val average_rating: String,
//        val category_name: String,
//        val description: String,
//        val price: String,
//        val product_image_url: String,
//        val product_name: String,
//        val sub_category_id: String,
//        val subcategory_name: String
//    )
    const val CREATE_CART_ITEM_TABLE = """
        CREATE TABLE cartProduct(
            item_id INTEGER PRIMARY KEY,
            name TEXT,
            desc TEXT,
            img_url TEXT,
            price FLOAT,
            quantity INTEGER,
            amount FLOAT
        )
    """
}