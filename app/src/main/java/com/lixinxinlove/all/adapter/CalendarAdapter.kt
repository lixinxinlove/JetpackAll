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

        holder.setText(R.id.date_text, getTime(mCalendar.time, "yyyy-MM-dd"))
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

        val list: MutableList<DayEntity> = mutableListOf()
        val dayOfWeek = calendar[Calendar.DAY_OF_WEEK] - 1
        for (i in 2..dayOfWeek) {
            list.add(DayEntity(""))
        }
        for (i in 1..calendar.getActualMaximum(Calendar.DATE)) {
            list.add(DayEntity("$i"))
        }
        return list
    }

    private fun getTime(date: Date, ft: String): String {
        val format = SimpleDateFormat(ft)
        return format.format(date)
    }


}