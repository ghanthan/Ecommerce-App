package com.example.apirecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

lateinit var adapter:ProductsAdapter
lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productList = retroFitProductApiCall()

        productList.enqueue(object : Callback<Data?> {
            override fun onResponse(call: Call<Data?>, response: Response<Data?>) {
             val dataList = response.body()?.products!!
                adapter = ProductsAdapter(this@MainActivity, dataList)
                recyclerView = findViewById(R.id.productRecyclerView)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            }

            override fun onFailure(call: Call<Data?>, t: Throwable) {
                Log.e("ERROR",t.message.toString())
                Toast.makeText(this@MainActivity,"Request to Product List failed",Toast.LENGTH_LONG).show()

            }
        })


    }

    private fun retroFitProductApiCall(): Call<Data> {

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsApiInterface::class.java)

        return retrofitBuilder.getProductsList()
    }
}