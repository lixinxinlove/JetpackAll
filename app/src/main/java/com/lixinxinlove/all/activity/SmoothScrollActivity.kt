package com.lixinxinlove.all.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lixinxinlove.all.R
import com.lixinxinlove.all.adapter.SmoothScrollAdapter
import com.lixinxinlove.all.base.BaseActivity
import com.lixinxinlove.all.view.SmoothScrollLayoutManager

class SmoothScrollActivity : BaseActivity() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var mAdapter: SmoothScrollAdapter

    private lateinit var mData: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smooth_scroll)
        recyclerView = findViewById(R.id.list_view)
        recyclerView.layoutManager = SmoothScrollLayoutManager(this)


        mData = mutableListOf()
        for (i in 0..5) {
            mData.add("Lee-> $i")
        }

        mAdapter = SmoothScrollAdapter(mData)

        recyclerView.adapter = mAdapter

    }

    fun onAdd(view: View) {
        mData.add("add")
        mAdapter.notifyItemInserted(mData.size - 1)
        recyclerView.smoothScrollToPosition(mData.size-1)
    }
}