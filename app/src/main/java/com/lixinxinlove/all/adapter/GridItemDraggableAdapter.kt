package com.lixinxinlove.all.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.dragswipe.DragAndSwipeCallback
import com.chad.library.adapter.base.module.BaseDraggableModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lixinxinlove.all.R

//TODO draggableModule: BaseDraggableModule?

class GridItemDraggableAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_grid_view) {


    override fun convert(holder: BaseViewHolder, item: String) {

        holder.setText(R.id.tv, item)

    }
}