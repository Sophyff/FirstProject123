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

    public fun isProductInCart(id:Int):Boolean{
        val sql="Select * from cartProduct where item_id=$id"
        val cursor=db.rawQuery(sql,null,null)
        if(cursor.moveToNext()){
            return true
        }
        return false
    }

    public fun addProduct(product: CartProduct): Long {
        val values = ContentValues()
        values.put("item_id", product.item_id.toInt())
        values.put("name", product.name)
        values.put("desc", product.desc)
        values.put("img_url", product.img_url)
        values.put("price", product.price)
        values.put("quantity", product.quantity)
        values.put("amount", product.amount)

        return db.insert("cartProduct", null, values)
    }

    //UPDATE product SET price = price + 50 WHERE id = 1
    public fun updateProduct(product:CartProduct):Int{
        val values = ContentValues()
        values.put("item_id", product.item_id.toInt())
        values.put("name", product.name)
        values.put("desc", product.desc)
        values.put("img_url", product.img_url)
        values.put("price", product.price)
        values.put("quantity", product.quantity+1)
        values.put("amount", product.amount+product.price)
        return db.update("cartProduct",values,"item_id = ${product.item_id}",null)
    }

    @SuppressLint("Range")
    public fun getCartProducts(): ArrayList<CartProduct> {
        val products = ArrayList<CartProduct>()
        val cursor = db.query("cartProduct", null, null, null, null, null, null)
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

    public fun deleteProduct(id:Int):Int{
        val result=db.delete("cartProduct","WHERE item_id = $id",null)
        return result
    }

}

