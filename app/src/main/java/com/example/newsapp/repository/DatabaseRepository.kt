package com.example.newsapp.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.newsapp.db.NewsDatabase
import com.example.newsapp.db.dao.NewsDao
import com.example.newsapp.db.entity.News

class DatabaseRepository(application: Application) : NewsDao {

    private var newsDao: NewsDao
    private var allNews: LiveData<List<News>>

    init {
        val database = NewsDatabase.getInstance(application)
        newsDao = database.newsDao()
        allNews = database.newsDao().getAllNews()
    }

    override fun insert(news: News) {
        InsertNoteAsyncTask(newsDao).execute(news)
    }

    override fun deleteAllNews() {
        DeleteAllNotesAsyncTask(newsDao).execute()
    }

    override fun getAllNews(): LiveData<List<News>> {
        return allNews
    }
}

class DeleteAllNotesAsyncTask(private val newsDao: NewsDao) : AsyncTask<News, Unit, Unit>() {
    override fun doInBackground(vararg news: News) {
        newsDao.deleteAllNews()
    }
}

class InsertNoteAsyncTask(private val newsDao: NewsDao) : AsyncTask<News, Unit, Unit>() {
    override fun doInBackground(vararg news: News?) {
        for (i in news.indices) {
            news[i]?.let { newsDao.insert(it) }
        }
    }
}
