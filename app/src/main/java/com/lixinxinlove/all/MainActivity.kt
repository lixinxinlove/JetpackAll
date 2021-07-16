package com.lixinxinlove.all

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
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
        typeRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        adapter = TypeAdapter()
        typeRecyclerView.adapter = adapter


        typeRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            var mmRvScrollY = 0 // 列表滑动距离

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


                mmRvScrollY += dy

                Log.e(TAG, "mmRvScrollY==$mmRvScrollY")

            }
        })


        typeRecyclerView.viewTreeObserver.addOnGlobalLayoutListener(object :
            OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                typeRecyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this);

                val bottomHeight = typeRecyclerView.height
                Log.e(TAG, "bottomHeight: $bottomHeight")

                val bottomMeasuredHeight = typeRecyclerView.measuredHeight
                Log.e(TAG, "bottomMeasuredHeight: $bottomMeasuredHeight")

                val rvHeight = typeRecyclerView.height
                Log.e(TAG, "rvHeight: $rvHeight")

                val rvMeasuredHeight = typeRecyclerView.measuredHeight
                Log.e(TAG, "rvMeasuredHeight: $rvMeasuredHeight")

                val count = typeRecyclerView.childCount
                Log.e(TAG, "count: $count")


                //列表总长度
                val verticalScrollRange = typeRecyclerView.computeVerticalScrollRange()
                //可见区域长度
                val verticalScrollExtent = typeRecyclerView.computeVerticalScrollExtent()
                //向上滑动的大概距离
                val verticalScrollOffset = typeRecyclerView.computeVerticalScrollOffset()


                Log.e(TAG, "列表总长度: $verticalScrollRange")
                Log.e(TAG, "可见区域长度: $verticalScrollExtent")
                // Log.e(TAG, "向上滑动的大概距离: $verticalScrollOffset")


                Log.e(TAG, "向上滑动的大概距离: ${verticalScrollRange - verticalScrollExtent}")



                //列表总长度
                val hScrollRange = typeRecyclerView.computeHorizontalScrollRange()
                //可见区域长度
                val hScrollExtent = typeRecyclerView.computeHorizontalScrollExtent()
                //向上滑动的大概距离
                val hScrollOffset = typeRecyclerView.computeHorizontalScrollOffset()


                Log.e(TAG, "向左滑动的大概距离: ${hScrollRange - hScrollExtent}")




            }
        })


    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val home = Intent(Intent.ACTION_MAIN)
            home.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            home.addCategory(Intent.CATEGORY_HOME)
            startActivity(home)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}

