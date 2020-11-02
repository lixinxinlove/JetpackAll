package com.lixinxinlove.all.transform

import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

class CardTransformer2 : ViewPager2.PageTransformer {


    override fun transformPage(page: View, position: Float) {
//        var tempPosition = position
//
//        if (tempPosition < -1f) {
//            tempPosition = -1f
//        }
//        if (tempPosition > 1) {
//            tempPosition = 1f
//        }
//
//        val tempScale = if (tempPosition < 0) {
//            1 + tempPosition
//        } else {
//            1 - tempPosition
//        }
//
//        val scaleValue = 0.7f + tempScale * 0.1f
//        page.scaleX = scaleValue
//        page.scaleY = scaleValue


        if (position < 0) {
            page.translationX = -position * page.width

            val scale = (page.width + 40 * position) / page.width
            page.scaleX = scale
            page.scaleY = scale

            page.translationY = -position * 40
            page.translationZ = position
        }


    }
}