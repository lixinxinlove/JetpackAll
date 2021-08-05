package com.lixinxinlove.all

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lixinxinlove.all.adapter.TypeAdapter
import com.lixinxinlove.all.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    var isChange = true


    private lateinit var typeRecyclerView: RecyclerView

    @Inject lateinit var adapter: TypeAdapter

    private lateinit var indexView: View

    private var mScrollRange = 0f //RecyclerView 可以滑动的距离

    private val sc = 140.dp.toInt()
    private var mmRvScrollX = 0f // 列表滑动距离

    private var left = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        indexView = findViewById(R.id.index_view)

        typeRecyclerView = findViewById(R.id.type_recycler_view)
        typeRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
       // typeRecyclerView.layoutManager = LinearLayoutManager(this)
       // adapter = TypeAdapter()
        typeRecyclerView.adapter = adapter


        typeRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            var mmRvScrollY = 0 // 列表滑动距离

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                mmRvScrollX += dx

                // mmRvScrollY += dy

                // Log.e(TAG, "mmRvScrollY==$mmRvScrollY")


                if (mScrollRange > 0) {


                    left = ((mmRvScrollX / mScrollRange) * sc).toInt()

                    val lp = indexView.layoutParams as FrameLayout.LayoutParams

                    lp.marginStart = left

                    indexView.layoutParams = lp


                    Log.e(TAG, "left==$left")

                }


                Log.e(TAG, "mmRvScrollX==$mmRvScrollX")
                Log.e(TAG, "mScrollRange==$mScrollRange")


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


                mScrollRange = (hScrollRange - hScrollExtent).toFloat()


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


    fun doClick(view: View) {
        var name = "com.lixinxinlove.all.mylogo"
        val pm = packageManager
        if (isChange) {//第二次进来isChange会被赋值为true，所以需要点击两次才会恢复成原来的icon,真正需求的话需要缓存起来，而不是成员变量
            isChange = false
            //关注 COMPONENT_ENABLED_STATE_DISABLED 和 COMPONENT_ENABLED_STATE_ENABLED
            pm.setComponentEnabledSetting(
                ComponentName(this, "com.lixinxinlove.all.MainActivity"),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
            pm.setComponentEnabledSetting(
                ComponentName(this, name),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
        } else {
            pm.setComponentEnabledSetting(
                ComponentName(this, "com.lixinxinlove.all.MainActivity"),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
            pm.setComponentEnabledSetting(
                ComponentName(this, name),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
        }
    }

}

