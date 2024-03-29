package com.example.didplaylistapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var  recyclerView: RecyclerView
    lateinit var adapter: MyAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.recycler)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterfaces::class.java)

        val retrofitData = retrofit.getProductsInfo()
        retrofitData.enqueue(object : Callback<OurData?> {
            override fun onResponse(call: Call<OurData?>, response: Response<OurData?>) {
                        var responseBody = response.body()
                        val productList = responseBody?.products!!
                    adapter= MyAdapter(this@MainActivity, productList)
                recyclerView.adapter=adapter
                recyclerView.layoutManager= LinearLayoutManager(this@MainActivity)

            }

            override fun onFailure(call: Call<OurData?>, t: Throwable) {
                    Log.d("MainActivity", "onFailure: "+t.message)

            }
        })

    }
}