package com.bodhivruksha.show_api_data_in_multiple_tabs.api

import com.bodhivruksha.show_api_data_in_multiple_tabs.model.Post
import com.bodhivruksha.show_api_data_in_multiple_tabs.model.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServiceInterface {

    @GET("users")
    fun getUserList(): Observable<List<User>>

    @GET("posts")
    fun getPostList(): Observable<List<Post>>

    @GET("posts")
    fun getPostsList(): Call<List<Post>>

    companion object Factory {
        fun create1(): ApiServiceInterface {
            val retrofit = Retrofit.Builder()
                //.addCallAdapterFactory()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }

        fun create(): ApiServiceInterface {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}