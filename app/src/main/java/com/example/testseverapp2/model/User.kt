package com.example.testseverapp2.model

data class User(
    val id: Long?,
    val username: String?,
    val email: String?,
    val lat: Double?,
    val lng: Double?,
    val phone: String?,
    val role: String?,
    val password: String?,
    val products: List<Product>?
)
