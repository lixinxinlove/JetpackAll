package com.lixinxinlove.all.util

import java.util.*

/**
 *   author ： lixinxin
 *   time    ： 2021/6/24
 *   email：895330766@qq.com
 */
object DateTimeUtils {


    /**
     * 根据日期获得星期
     *
     * @param date
     * @return
     */
    fun getWeekOfDate(date: Long): String? {
        val weekDaysName = arrayOf("周日", "周一", "周二", "周三", "周四", "周五", "周六")
        val calendar = Calendar.getInstance()
        calendar.time = Date(date)
        val intWeek = calendar[Calendar.DAY_OF_WEEK] - 1
        return weekDaysName[intWeek]
    }

}