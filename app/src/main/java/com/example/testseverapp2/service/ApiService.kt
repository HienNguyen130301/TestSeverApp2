package com.example.testseverapp2.service

import com.example.testseverapp2.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("/api/auth/register")
    fun registerUser(@Body user: User): Call<String>

    @POST("/api/auth/login")
    fun loginUser(@Body user: User): Call<String>

    @GET("/api/users")
    fun getAllUsers(): Call<List<User>>
}
