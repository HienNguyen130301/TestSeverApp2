package com.example.testseverapp2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testseverapp2.model.Product
import com.example.testseverapp2.model.User
import com.example.testseverapp2.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class MainActivity : AppCompatActivity() {


    lateinit var txtmail : EditText
    lateinit var txtpw : EditText
    lateinit var login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        testpush()
        getAllUsers()

        txtmail = findViewById(R.id.txtlogin)
        txtpw = findViewById(R.id.txtpw)
        login = findViewById(R.id.login)

        login.setOnClickListener {
            val txt1 = txtmail.text.toString().trim() // Move text retrieval here
            val txt2 = txtpw.text.toString().trim()

            val list = listOf<Product>()
            val user = User(id = null, username = txt1, email = "hiendz3@example.com",lat = 9999.9999,lng = 999999.99, phone = "666",role = "hehe", password = txt2,products = list)

            val call = ApiClient.apiService.loginUser(user)
            call.enqueue(object : Callback<String>{
                override fun onResponse(p0: Call<String>, p1: Response<String>) {
                    if (p1.isSuccessful) {
                        val message = p1.body() // Response message
                        Log.d("hien", "success8: ${message}")
                    } else {
                        // Handle error response
                        val errorMessage = p1.errorBody()?.string() // Error message
                        Log.d("hien", "success9: ${errorMessage}, $p1")
                    }
                }

                override fun onFailure(p0: Call<String>, p1: Throwable) {
                    Log.d("hien", "onFailure: ${p1.message}")
                }
            })
        }
    }

    fun testpush() {
        val list = listOf<Product>()
        val user = User(id = 9999, username = "hiendzvcl8", email = "hiendz8@example.com",lat = 9999.9999,lng = 999999.99, phone = "666",role = "hehe", password = "666",products = list)
         val call = ApiClient.apiService.registerUser(user)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val message = response.body() // Response message
                    Log.d("hien", "success: ${message}")
                } else {
                    // Handle error response
                    val errorMessage = response.errorBody()?.string() // Error message
                    Log.d("hien", "success2: ${errorMessage}, $call, $response")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("hien", "onFailure: ${t.message}")
            }
        })
    }
    fun getAllUsers(){
        val call = ApiClient.apiService.getAllUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val userList = response.body()
                    userList?.forEach { user ->
                        Log.d("MainActivity", "User: ${user.username}")
                    }
                } else {
                    Log.e("MainActivity", "Failed to retrieve users: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("MainActivity", "Error retrieving users: ${t.message}", t)
            }
        })
    }
}