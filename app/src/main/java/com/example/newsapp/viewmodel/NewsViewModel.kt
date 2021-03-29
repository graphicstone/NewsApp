package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.db.entity.News
import com.example.newsapp.listener.ResultListener
import com.example.newsapp.model.NewsModel
import com.example.newsapp.repository.DatabaseRepository
import com.example.newsapp.repository.NewsRepository

class NewsViewModel(application: Application) : ViewModel(), ResultListener {

    private val mNewsRepository = NewsRepository()
    private val mDatabaseRepository = DatabaseRepository(application)
    private val mArticle: LiveData<List<News>> = mDatabaseRepository.getAllNews()
    val mNewsData = MutableLiveData<NewsModel>()


    fun getTrendingNews() = mNewsRepository.getNews(this)

    fun insert(news: News) {
        mDatabaseRepository.insert(news)
    }

    fun deleteAll() {
        mDatabaseRepository.deleteAllNews()
    }

    fun getAllNews(): LiveData<List<News>> {
        return mArticle
    }

    override fun onSuccess(`object`: Any) {
        mNewsData.value = `object` as NewsModel
    }

    override fun onFailure(`object`: Any) {
    }
}