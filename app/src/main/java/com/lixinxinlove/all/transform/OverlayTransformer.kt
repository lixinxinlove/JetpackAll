package com.lixinxinlove.all.transform

import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

/**
 * 叠加卡片效果
 * Created by Acorn on 2017/12/29.
 */
class OverlayTransformer : ViewPager2.PageTransformer {

    private var scaleOffset = 40f
    private var transOffset = 40f
    var overlayCount: Int
        private set


    constructor(overlayCount: Int) {
        this.overlayCount = overlayCount

    }

    override fun transformPage(page: View, position: Float) {
        if (position >= 0.0f) { //当前页
            page.translationX = 0f
            page.alpha = 1 - 0.5f * Math.abs(position)
            page.isClickable = true
        } else {
            otherTrans(page, position)
            page.isClickable = false
        }
    }

    private fun otherTrans(page: View, position: Float) {
        //缩放比例
        val scale = (page.width - scaleOffset * position) / page.width.toFloat()
        page.scaleX = scale
        page.scaleY = scale
        page.alpha = 1f
        if (position < overlayCount - 1 && position >  overlayCount) { //当前页向右滑动时,最右面第四个及以后页面应消失
            val curPositionOffset = transOffset * Math.floor(position.toDouble())
                .toFloat() //向下取整
            val lastPositionOffset = transOffset * Math.floor((position - 1).toDouble())
                .toFloat() //上一个卡片的偏移量
            val singleOffset = 1 - Math.abs(position % position.toInt())
            val transX =
                -page.width * position + (lastPositionOffset + singleOffset * (curPositionOffset - lastPositionOffset))
            page.translationX = transX
        } else if (position <= overlayCount - 1) {
            val transX = -page.width * position + transOffset * position
            page.translationX = transX
        } else {
            page.alpha = 0f
            //            page.setTranslationX(0); //不必要的隐藏在下面
        }
    }
}