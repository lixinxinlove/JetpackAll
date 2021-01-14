package com.lixinxinlove.all.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lixinxinlove.all.R

class SmoothScrollAdapter(data: MutableList<String>?) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_view_layout, data) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tv, item)

    }
}