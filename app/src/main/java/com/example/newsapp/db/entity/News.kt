package com.example.newsapp.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class News(
    var author: String,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}