package com.example.newsapp.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["title"])
data class NewsModel(
    @SerializedName("status")
    val status: String, // ok
    @SerializedName("totalResults")
    val totalResults: Int, // 20
    @SerializedName("articles")
    val articles: List<Article>
)