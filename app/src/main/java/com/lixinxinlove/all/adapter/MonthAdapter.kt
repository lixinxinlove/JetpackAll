package com.lixinxinlove.all.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lixinxinlove.all.R
import com.lixinxinlove.all.database.entity.DayEntity
import java.text.SimpleDateFormat
import java.util.*

/**
 * 月
 */
class MonthAdapter(data: MutableList<DayEntity>) :

    BaseQuickAdapter<DayEntity, BaseViewHolder>(R.layout.item_month, data) {

    override fun convert(holder: BaseViewHolder, dayEntity: DayEntity) {
        holder.setText(R.id.day_text, getTime(dayEntity.Date, "d"))


        if (dayEntity.isCurrentMonth) {
            holder.setTextColor(R.id.day_text, Color.parseColor("#000000"))
        } else {
            holder.setTextColor(R.id.day_text, Color.parseColor("#999999"))
        }

        if (dayEntity.isWeekSelected) {
            holder.setTextColor(R.id.day_text, Color.parseColor("#FF0000"))
        }

        if (dayEntity.isHighlight) {
            holder.setTextColor(R.id.day_text, Color.parseColor("#FFFF00"))
        }

    }


    @SuppressLint("SimpleDateFormat")
    private fun getTime(date: Date, ft: String): String {
        val format = SimpleDateFormat(ft)
        return format.format(date)
    }


}