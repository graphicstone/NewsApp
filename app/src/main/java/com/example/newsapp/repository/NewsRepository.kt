package com.example.newsapp.repository

import com.example.newsapp.listener.ResultListener
import com.example.newsapp.model.NewsModel
import com.example.newsapp.network.NetworkService
import com.example.newsapp.util.BASE_URL
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class NewsRepository {
    private val call = NetworkService.serviceRequest(BASE_URL)

    fun getNews(resultListener: ResultListener) {
        call.getNews().enqueue(object : Callback,
            retrofit2.Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                response.body()?.let { resultListener.onSuccess(it) }
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                t.message?.let { resultListener.onFailure(it) }
            }
        })
    }
}