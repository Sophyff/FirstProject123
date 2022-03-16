package com.example.firstproject.data.remote

data class LoginResponse(
    val message: String,
    val status: Int,
    val user: User
)