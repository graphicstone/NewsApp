package com.example.newsapp.listener

interface ResultListener {
    fun onSuccess(`object`: Any)
    fun onFailure(`object`: Any)
}