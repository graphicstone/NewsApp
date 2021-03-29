package com.example.newsapp.model


import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    val id: String, // the-times-of-india
    @SerializedName("name")
    val name: String // The Times of India
)