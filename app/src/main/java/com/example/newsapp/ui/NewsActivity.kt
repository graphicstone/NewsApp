package com.example.newsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.base.BaseActivity
import com.example.newsapp.databinding.ActivityNewsBinding
import com.example.newsapp.db.entity.News
import com.example.newsapp.model.NewsModel
import com.example.newsapp.viewmodel.NewsViewModel
import com.example.newsapp.viewmodel.NewsViewModelFactory

class NewsActivity : BaseActivity() {

    private lateinit var mBinding: ActivityNewsBinding
    private lateinit var mViewModel: NewsViewModel
    private val mNewsList: ArrayList<News> = arrayListOf()
    private val mAdapter: NewsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
        initAdapter()
        getDataFromRoom()
        fetchData()
    }

    private fun init() {
        mViewModel = ViewModelProvider(
            this,
            NewsViewModelFactory(application)
        ).get(NewsViewModel::class.java)
    }

    private fun initAdapter() {
        mBinding.rvNews.layoutManager = LinearLayoutManager(this)
        mBinding.rvNews.adapter = mAdapter
    }

    private fun getDataFromRoom() {
        mViewModel.getAllNews().observe(this, {
            mNewsList.clear()
            mNewsList.addAll(it)
            mAdapter.setData(mNewsList)
            mAdapter.notifyDataSetChanged()
            mBinding.mainLoader.visibility = View.GONE
            if (mNewsList.isEmpty())
                mBinding.noResult.visibility = View.VISIBLE
            else
                mBinding.noResult.visibility = View.GONE
        })
    }

    private fun fetchData() {
        mViewModel.getTrendingNews()
        mViewModel.mNewsData.observe(this, {
            var needsUpdate = false
            if (mNewsList.isNotEmpty()) {
                for (i in mNewsList.indices) {
                    if (it.articles[i].publishedAt != mNewsList[i].publishedAt) {
                        needsUpdate = true
                        break
                    }
                }
            }
            when {
                mNewsList.isEmpty() -> {
                    mViewModel.deleteAll()
                    saveDataInRoom(it)
                }
                needsUpdate -> {
                    Log.d("STATUS", "UPDATING DB")
                    mViewModel.deleteAll()
                    saveDataInRoom(it)
                }
                else -> {
                    Log.d("STATUS", "NOT UPDATING DB")
                }
            }
        })
    }

    private fun saveDataInRoom(newsModel: NewsModel) {
        newsModel.articles.forEach {
            val news = News(
                author = it.author ?: getString(R.string.author_not_available),
                title = it.title ?: getString(R.string.title_not_available),
                description = it.description ?: getString(R.string.desc_not_available),
                url = it.url ?: getString(R.string.url_not_available),
                urlToImage = it.urlToImage ?: getString(R.string.image_url_not_available),
                publishedAt = it.publishedAt ?: getString(R.string.published_not_available),
                content = it.content ?: getString(R.string.content_not_available)
            )
            mViewModel.insert(news)
        }
    }
}