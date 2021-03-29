package com.example.newsapp.network

import com.example.newsapp.model.NewsModel
import com.example.newsapp.util.API_KEY
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("top-headlines")
    fun getNews(
        @Query("apiKey") auth: String = API_KEY,
        @Query("country") country: String = "in"
    ): Call<NewsModel>

    companion object {
        fun serviceRequest(url: String): NetworkService {
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(NetworkService::class.java)
        }
    }

}