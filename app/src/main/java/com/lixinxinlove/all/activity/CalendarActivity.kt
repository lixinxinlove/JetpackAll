package com.lixinxinlove.all.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.marginTop
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.lixinxinlove.all.R
import com.lixinxinlove.all.adapter.CalendarAdapter
import com.lixinxinlove.all.base.BaseActivity
import com.lixinxinlove.all.dp
import java.util.*

class CalendarActivity : BaseActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var mAdapter: CalendarAdapter
    private lateinit var mData: MutableList<Calendar>


    private lateinit var view_bottom: View

    val top = 40.dp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        viewPager = findViewById(R.id.view_pager_time)
        view_bottom = findViewById(R.id.view_bottom)

        mData = mutableListOf()
        for (i in 0..20) {
            val calendar = Calendar.getInstance()
            calendar.set(2020, i, 1)
            mData.add(calendar)
        }
        mAdapter = CalendarAdapter(mData)
        viewPager.adapter = mAdapter
        viewPager.offscreenPageLimit = 5


        var pa:LinearLayout.LayoutParams= view_bottom.layoutParams as LinearLayout.LayoutParams


        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }


            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                Log.e(TAG, "positionOffset=$positionOffset")
                // Log.e(TAG, "positionOffsetPixels=$positionOffsetPixels")

                pa.topMargin= (-positionOffset * top).toInt()
                view_bottom.layoutParams=pa
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }
}


