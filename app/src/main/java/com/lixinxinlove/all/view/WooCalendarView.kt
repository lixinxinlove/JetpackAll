package com.lixinxinlove.all.view

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.lixinxinlove.all.R
import com.lixinxinlove.all.adapter.CalendarAdapter
import com.lixinxinlove.all.database.entity.DayEntity
import com.lixinxinlove.all.database.entity.MonthEntity
import com.lixinxinlove.all.dp
import com.lixinxinlove.all.util.DateTimeUtilsKt
import java.util.*

/**
 *   author ： lixinxin
 *   time    ： 2021/6/25
 *   email：895330766@qq.com
 */
class WooCalendarView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var todayDate: Date

    private var mContext: Context = context

    private var dayHeight: Int = 40.dp.toInt()

    private lateinit var ivLastDate: ImageView
    private lateinit var ivNextDate: ImageView
    private lateinit var dataTitle: TextView

    private lateinit var viewPager: ViewPager2
    private lateinit var mAdapter: CalendarAdapter

    private val monthList: MutableList<MonthEntity> = mutableListOf()

    var redPointList: MutableList<Long> = mutableListOf()

    //根据需求，只展示当前月前3个月和后2个月的日期数据
    private var mCurrentItem = 3


    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        mContext = context
        initData()
    }

    constructor(context: Context) : this(context, null) {
        mContext = context
        initData()
    }

    private fun initData() {
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.woo_calendar_view_layout, this)

        setListener()
    }

    fun startLoadView() {
        val calendar = Calendar.getInstance()
        todayDate = calendar.time

        calendar.set(Calendar.DATE, 1)
        calendar.add(Calendar.MONTH, -4)

        for (i in 0..5) {
            calendar.add(Calendar.MONTH, +1)
            monthList.add(getMonth(calendar))
        }
        //默认选择当前的周
        // 初始化数据
        mAdapter = CalendarAdapter(monthList)
        viewPager.offscreenPageLimit = 3
        viewPager.adapter = mAdapter
        dataTitle.text = mAdapter.getItem(mCurrentItem).yearMonth
        setHeight()

        viewPager.setCurrentItem(mCurrentItem, false)
    }

    private fun setListener() {
        ivLastDate.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem - 1
        }
        ivNextDate.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem + 1
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mCurrentItem = position
                dataTitle.text = mAdapter.getItem(position).yearMonth
                setHeight()
            }
        })


    }

    private fun setHeight() {
        val height: Int = (mAdapter.getItem(viewPager.currentItem).days.size / 7) * dayHeight
        val animator = ValueAnimator.ofInt(viewPager.height, height)
        animator.duration = 400
        val lp = viewPager.layoutParams

        animator.addUpdateListener {
            lp.height = (it.animatedValue as Int)
            viewPager.layoutParams = lp
        }
        animator.start()
    }

    private fun getMonth(calendar: Calendar): MonthEntity {


        var tempCalendar: Calendar = calendar.clone() as Calendar
        val list: MutableList<DayEntity> = mutableListOf()
        var dayOfWeek = tempCalendar[Calendar.DAY_OF_WEEK] - 1
        if (dayOfWeek == 0) {
            dayOfWeek = 7
        }
        //  Log.e("getDays", "星期$dayOfWeek")

        //添加上个月的日期部分
        for (i in 1 until dayOfWeek) {
            tempCalendar.add(Calendar.DATE, -1)
            list.add(0, DayEntity("", tempCalendar.time))
        }

        tempCalendar = calendar.clone() as Calendar
        //添加当前月
        val maxDay = tempCalendar.getActualMaximum(Calendar.DATE)
        for (i in 1..maxDay) {
            list.add(DayEntity("$i", tempCalendar.time, true))
            tempCalendar.add(Calendar.DATE, 1)
        }

        var lastDayWeek = tempCalendar[Calendar.DAY_OF_WEEK] - 1

        val time = tempCalendar.time

        // Log.e("LastTime", getTime(time, "yyyy-MM-dd"))

        if (lastDayWeek == 0) {
            lastDayWeek = 7
        }

        Log.e("getDays", "星期$lastDayWeek")
        //添加下个月的部分
        if (lastDayWeek != 1) {
            for (i in lastDayWeek..7) {
                list.add(DayEntity("$i", tempCalendar.time))
                tempCalendar.add(Calendar.DATE, 1)
            }
        }

        return MonthEntity(DateTimeUtilsKt.getTime(calendar.time, "yyyy年MM月"), list)
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        dayHeight = (w - 32.dp.toInt()) / 7
    }


}