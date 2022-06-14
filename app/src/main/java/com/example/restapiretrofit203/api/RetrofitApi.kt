package com.example.restapiretrofit203.api

import android.telecom.Call



interface RetrofitApi {

    @GET("posts")
    fun getPosts() : Call<List<Post>>

    @GET("comments")
    fun getComments() : Call<List<Comments>>

companion object{

    var BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun create(): RetrofitApi{

        val retrofit = Retrofit.Bilder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(RetrofitAPI::class.java)

    }

}

}