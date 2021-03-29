package com.example.newsapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newsapp.db.entity.News

@Dao
interface NewsDao {
    @Insert
    fun insert(news: News)

    @Query("DELETE from news_table")
    fun deleteAllNews()

    @Query("SELECT * FROM news_table")
    fun getAllNews(): LiveData<List<News>>
}