package com.lixinxinlove.all.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lixinxinlove.all.R
import com.lixinxinlove.all.database.entity.DayEntity
import java.util.*

/**
 * 月
 */
class MonthAdapter(data: MutableList<DayEntity>) :

    BaseQuickAdapter<DayEntity, BaseViewHolder>(R.layout.item_month, data) {

    override fun convert(holder: BaseViewHolder, dayEntity: DayEntity) {
        holder.setText(R.id.day_text, dayEntity.day)
    }
}