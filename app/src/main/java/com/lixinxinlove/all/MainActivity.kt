package com.lixinxinlove.all

import android.os.Bundle
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lixinxinlove.all.adapter.TypeAdapter
import com.lixinxinlove.all.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var typeRecyclerView: RecyclerView
    private lateinit var adapter: TypeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        typeRecyclerView = findViewById(R.id.type_recycler_view)
        typeRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TypeAdapter()
        typeRecyclerView.adapter = adapter
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }
}