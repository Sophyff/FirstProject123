package com.example.firstproject.data.local

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.aapolis.apolisapp.data.Product
//
//CREATE TABLE cartProduct(
//item_id INTEGER PRIMARY KEY,
//name TEXT,
//desc TEXT,
//img_url TEXT,
//price FLOAT,
//quantity INTEGER,
//amount FLOAT
//)
class CartProductDao(context: Context) {
    private val db = DBHelper(context).writableDatabase

    public fun addProduct(product: Product, qty:Int): Long {
        val values = ContentValues()
        values.put("item_id", product.product_id)
        values.put("name", product.product_name)
        values.put("desc", product.description)
        values.put("img_url", product.product_image_url)
        values.put("price", product.price)
        values.put("quantity", qty)
        values.put("amount", product.price.toFloat()*qty)

        return db.insert("cartProduct", null, values)
    }

    @SuppressLint("Range")
    public fun getCartProducts(): ArrayList<CartProduct> {
        val products = ArrayList<CartProduct>()
        val cursor = db.query("product", null, null, null, null, null, null)
        while(cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex("item_id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val desc = cursor.getString(cursor.getColumnIndex("desc"))
            val imageUrl = cursor.getString(cursor.getColumnIndex("img_url"))
            val price = cursor.getFloat(cursor.getColumnIndex("price"))
            val qty = cursor.getInt(cursor.getColumnIndex("quantity"))
            val amount = cursor.getFloat(cursor.getColumnIndex("amount"))

            val product = CartProduct(id, name, desc,imageUrl,price,qty,amount)

            products.add(product)
        }
        return products
    }

}

