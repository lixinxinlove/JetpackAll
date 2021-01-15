package com.lixinxinlove.all.activity

import android.os.Bundle
import android.transition.Explode
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lixinxinlove.all.R
import com.lixinxinlove.all.adapter.TypeAdapter

class ExplodeActivity : AppCompatActivity() {
    private lateinit var typeRecyclerView: RecyclerView
    private lateinit var adapter: TypeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explode)
        //进入退出效果 注意这里 创建的效果对象是 Explode()
        //进入退出效果 注意这里 创建的效果对象是 Explode()
        window.enterTransition = Explode().setDuration(2000)
        window.exitTransition = Explode().setDuration(2000)


        typeRecyclerView = findViewById(R.id.rlv)
        typeRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TypeAdapter()
        typeRecyclerView.adapter = adapter
    }
}