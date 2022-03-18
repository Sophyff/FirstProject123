package com.example.firstproject.data.local

data class CartProduct(
    val item_id:Int,
    val name:String,
    val desc:String,
    val img_url:String,
    val price:Float,
    val quantity:Int,
    val amount:Float
)

//CREATE TABLE cartProduct(
//item_id INTEGER PRIMARY KEY,
//name TEXT,
//desc TEXT,
//img_url TEXT,
//price FLOAT,
//quantity INTEGER,
//amount FLOAT
//)