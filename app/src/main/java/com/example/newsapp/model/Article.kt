package com.example.newsapp.model


import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("source")
    val source: Source,
    @SerializedName("author")
    val author: String, // TIMESOFINDIA.COM
    @SerializedName("title")
    val title: String, // President Ram Nath Kovind referred to AIIMS - Times of India
    @SerializedName("description")
    val description: String, // India News: The 75-year-old President had complained of chest discomfort in the morning and thereafter he was rushed to the military hospital.
    @SerializedName("url")
    val url: String, // https://timesofindia.indiatimes.com/india/president-ram-nath-kovind-referred-to-aiims/articleshow/81721211.cms
    @SerializedName("urlToImage")
    val urlToImage: String, // https://static.toiimg.com/thumb/msid-81721206,width-1070,height-580,imgsize-169255,resizemode-75,overlay-toi_sw,pt-32,y_pad-40/photo.jpg
    @SerializedName("publishedAt")
    val publishedAt: String, // 2021-03-27T07:35:00Z
    @SerializedName("content")
    val content: String // The President has been under observation after a routine medical checkup. He thanks all who enquired about his health and wished him well.— President of India (@rashtrapatibhvn) 1616828687000
)