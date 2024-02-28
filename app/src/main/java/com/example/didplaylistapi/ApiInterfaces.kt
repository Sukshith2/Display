package com.example.didplaylistapi

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterfaces {
    @GET("products")
    fun getProductsInfo() : Call<OurData>

}