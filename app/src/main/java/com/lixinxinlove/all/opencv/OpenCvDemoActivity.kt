package com.lixinxinlove.all.opencv

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lixinxinlove.all.R
import com.lixinxinlove.all.adapter.OpenCvDemoAdapter
import com.lixinxinlove.all.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OpenCvDemoActivity : BaseActivity() {

    @Inject
    lateinit var mAdapter: OpenCvDemoAdapter

    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_cv_demo)


        mRecyclerView = findViewById(R.id.list_view)

        mRecyclerView.layoutManager=LinearLayoutManager(this)
        mRecyclerView.adapter=mAdapter


    }
}