package com.example.newsapp.ui

import android.os.Bundle
import com.example.newsapp.base.BaseActivity
import com.example.newsapp.databinding.ActivityAnimationBinding

class AnimationActivity : BaseActivity() {

    private lateinit var mBinding: ActivityAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.tvTapToGoBack.setOnClickListener { onBackPressed() }
    }
}