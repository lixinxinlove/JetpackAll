package com.lixinxinlove.all.adapter

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lixinxinlove.all.R
import com.lixinxinlove.all.database.entity.DayEntity
import java.text.SimpleDateFormat
import java.util.*

/**
 * 月
 */
class CalendarAdapter(data: MutableList<Calendar>) :
    BaseQuickAdapter<Calendar, BaseViewHolder>(R.layout.item_calendar, data) {

    private val spanCount = 7

    override fun convert(holder: BaseViewHolder, mCalendar: Calendar) {

        holder.setText(R.id.date_text, getTime(mCalendar.time, "yyyy-MM"))

        val monthView: RecyclerView = holder.getView(R.id.item_month_recycler_view)
        monthView.layoutManager = GridLayoutManager(context, spanCount)
        val mAdapter = MonthAdapter(getDays(mCalendar))
        mAdapter.setOnItemClickListener { adapter, view, position ->

            //${position / 7}  计算点击的行

            Toast.makeText(context, "点击的是第${position}----${position / 7}", Toast.LENGTH_LONG).show()
            Log.e("CalendarAdapter", "点击的是第${position}----${position / 7}")
        }
        monthView.adapter = mAdapter
    }


    private fun getDays(calendar: Calendar): MutableList<DayEntity> {

        var tempCalendar: Calendar = calendar.clone() as Calendar
        //tempCalendar.add(Calendar.MONDAY, -1)
        val pMMaxDay = tempCalendar.getActualMaximum(Calendar.DATE)
        val list: MutableList<DayEntity> = mutableListOf()
        var dayOfWeek = tempCalendar[Calendar.DAY_OF_WEEK] - 1
        if (dayOfWeek==0){
            dayOfWeek=7
        }
        Log.e("getDays", "星期$dayOfWeek")

        //添加上个月的日期部分
        for (i in 1 until dayOfWeek) {
            tempCalendar.add(Calendar.DATE, -1)
            list.add(0, DayEntity("", tempCalendar.time))
        }

        tempCalendar= calendar.clone() as Calendar
        //添加当前月
        val maxDay = tempCalendar.getActualMaximum(Calendar.DATE)
        for (i in 1..maxDay) {

            list.add(DayEntity("$i", tempCalendar.time))
            tempCalendar.add(Calendar.DATE, 1)
        }
        var lastDayWeek = tempCalendar[Calendar.DAY_OF_WEEK] - 1

        if (lastDayWeek==0){
            lastDayWeek=7
        }

        Log.e("getDays", "星期$lastDayWeek")
        //添加下个月的部分
        for (i in lastDayWeek .. 7) {
            list.add(DayEntity("$i", tempCalendar.time))
            tempCalendar.add(Calendar.DATE, 1)
        }

        return list
    }

    private fun getTime(date: Date, ft: String): String {
        val format = SimpleDateFormat(ft)
        return format.format(date)
    }


}