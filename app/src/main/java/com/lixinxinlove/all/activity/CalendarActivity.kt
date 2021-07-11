package com.lixinxinlove.all.activity

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.lixinxinlove.all.R
import com.lixinxinlove.all.adapter.CalendarAdapter
import com.lixinxinlove.all.base.BaseActivity
import com.lixinxinlove.all.database.entity.DayEntity
import com.lixinxinlove.all.database.entity.MonthEntity
import com.lixinxinlove.all.dp
import com.lixinxinlove.all.util.DateTimeUtilsKt
import java.util.*

class CalendarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
    }
}


