package com.example.ejercicioclase.ui

data class PaymentState(
    val fullName : String = "",
    val creditCard : Int = 0,
    val expirationDate: Int = 0,
    val CVV : Int = 0
)