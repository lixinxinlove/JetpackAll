package com.lixinxinlove.all.activity

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.lixinxinlove.all.R
import com.lixinxinlove.all.adapter.CalendarAdapter
import com.lixinxinlove.all.base.BaseActivity
import java.util.*

class CalendarActivity : BaseActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var mAdapter: CalendarAdapter
    private lateinit var mData: MutableList<Calendar>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        viewPager = findViewById(R.id.view_pager_time)
        mData = mutableListOf()
        for (i in 0..20) {
            val calendar = Calendar.getInstance()
            calendar.set(2020, i, 1)
            mData.add(calendar)
        }
        mAdapter = CalendarAdapter(mData)
        viewPager.adapter = mAdapter
        viewPager.offscreenPageLimit = 5
    }
}