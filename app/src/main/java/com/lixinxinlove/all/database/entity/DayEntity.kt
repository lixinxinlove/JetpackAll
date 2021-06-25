package com.lixinxinlove.all.database.entity

import java.util.*

data class DayEntity(var day: String,
                     var Date: Date,
                     var isCurrentMonth:Boolean=false,
                     var isWeekSelected: Boolean = false,
                     var isHighlight: Boolean = false
)
