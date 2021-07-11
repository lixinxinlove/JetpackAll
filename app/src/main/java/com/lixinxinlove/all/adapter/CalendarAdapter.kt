package com.lixinxinlove.all.adapter

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lixinxinlove.all.R
import com.lixinxinlove.all.database.entity.DayEntity
import com.lixinxinlove.all.database.entity.MonthEntity
import com.lixinxinlove.all.util.DateTimeUtilsKt
import java.util.*

/**
 * 月
 */
class CalendarAdapter(data: MutableList<MonthEntity>) :
    BaseQuickAdapter<MonthEntity, BaseViewHolder>(R.layout.item_calendar, data) {

    private val spanCount = 7

    private var highlightPosition = -1

    override fun convert(holder: BaseViewHolder, month: MonthEntity) {
        holder.setText(R.id.date_text, month.yearMonth)
        val monthView: RecyclerView = holder.getView(R.id.item_month_recycler_view)
        monthView.layoutManager = GridLayoutManager(context, spanCount)
        val mAdapter = MonthAdapter(month.days)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            //${position / 7}  计算点击的行
            //  Toast.makeText(context, "点击的是第${position}----${position / 7}", Toast.LENGTH_LONG).show()
            Log.e("CalendarAdapter", "点击的是第${position}----${position / 7}")

            val dayEntity = mAdapter.getItem(position)
            //TODO 点击一个 选择一周
            //判断上次点击的位置 如果是本周 点击的是同一天
            if (dayEntity.isHighlight) {
                //跳转到活动页面
                Toast.makeText(context, "跳转到活动页面", Toast.LENGTH_SHORT).show()
                return@setOnItemClickListener
            }

            if (highlightPosition >= 1) {
                mAdapter.getItem(highlightPosition).isHighlight = false
            }

            //判断上次点击的位置 如果是本周 点击的不是同一天
            if (!dayEntity.isHighlight && dayEntity.isWeekSelected) {
                dayEntity.isHighlight = true
                mAdapter.getItem(highlightPosition).isHighlight = false
                mAdapter.notifyItemChanged(position)
                mAdapter.notifyItemChanged(highlightPosition)
                highlightPosition = position
                return@setOnItemClickListener
            }

            //判断上次点击的位置 如果是不是本周
            mAdapter.data.forEach {
                it.isWeekSelected = false
            }
            val week = DateTimeUtilsKt.getWeekOfDate(dayEntity.Date)
            for (i in 1 until week) {
                mAdapter.getItem(position - i).isWeekSelected = true
            }
            for (j in 0..(7 - week)) {
                mAdapter.getItem(position + j).isWeekSelected = true
            }
            dayEntity.isHighlight = true
            highlightPosition = position
            mAdapter.notifyDataSetChanged()

            // TODD 更新viewpager

            Log.e("holder.adapterPosition",holder.adapterPosition.toString())

            this.data.forEachIndexed { index, monthEntity ->
                if (index!= holder.adapterPosition){
                    monthEntity.days.forEach {
                        it.isHighlight=false
                        it.isWeekSelected=false
                    }
                }
            }
            this.notifyDataSetChanged()


        }
        monthView.adapter = mAdapter
    }

    private fun getDays(calendar: Calendar): MutableList<DayEntity> {

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
        return list
    }


}