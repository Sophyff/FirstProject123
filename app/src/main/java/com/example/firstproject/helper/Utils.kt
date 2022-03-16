package com.aapolis.apolisapp


fun isValidMobileNumber(mobileNo: String):Boolean {
    val regex = Regex("[0-9]{10}")
    return regex.matches(mobileNo)
}

fun isValidPassword(password: String): Boolean {
    if(password.length<6) return false

    val regex = Regex("[A-Za-z0-9]+")
    return regex.matches(password)
}