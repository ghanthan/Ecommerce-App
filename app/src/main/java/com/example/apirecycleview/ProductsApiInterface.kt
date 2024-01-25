package com.example.apirecycleview

import retrofit2.Call
import retrofit2.http.GET

interface ProductsApiInterface {


    @GET("products")
    fun getProductsList(): Call<Data>

}