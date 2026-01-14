package com.example.retrofitglide

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitglide.api.ApiConfig
import com.example.retrofitglide.user.UserAdapter
import com.example.retrofitglide.user.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var rvUsers: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)

        rvUsers = findViewById(R.id.rvUsers)
        adapter = UserAdapter(arrayListOf())
        rvUsers.adapter = adapter
        rvUsers.layoutManager = LinearLayoutManager(this)

        getDataFromApi()
    }

    // ✅ A.4 ADA DI SINI (BENAR)
    private fun getDataFromApi() {
        progressBar.visibility = View.VISIBLE

        val client = ApiConfig.getApiService().getListUsers()
        client.enqueue(object : Callback<UserResponse> {

            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    response.body()?.let {
                        adapter.setData(it.data)
                    }
                } else {
                    Log.e("Main", "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(
                    this@MainActivity,
                    "Gagal mengambil data",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
