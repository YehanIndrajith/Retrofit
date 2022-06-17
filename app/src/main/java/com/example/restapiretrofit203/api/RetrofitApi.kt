package com.example.restapiretrofit203.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RetrofitApi {

    @GET("posts")
    fun getPosts() : Call<List<Post>>

    @GET("comments")
    fun getComments() : Call<List<Comments>>

companion object{

    var BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun create(): RetrofitApi{

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(RetrofitApi::class.java)

    }

}

}