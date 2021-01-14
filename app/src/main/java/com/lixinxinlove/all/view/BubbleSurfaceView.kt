package com.lixinxinlove.all.view

import android.content.Context
import android.graphics.Color
import android.graphics.DiscretePathEffect
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceView

class BubbleSurfaceView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    SurfaceView(context, attrs, defStyleAttr) {


    private val colors = arrayListOf(Color.BLUE, Color.GRAY, Color.LTGRAY)

    private val paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 6f
        pathEffect = DiscretePathEffect(30f, 30f)
    }


    private data class Bubble(val x: Float, val y: Float, val color: Int, var r: Float)


    private val bubblesList = mutableListOf<Bubble>()


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x ?: 0f
        val y = event?.y ?: 0f
        val color = colors.random()
        val bubble=Bubble(x,y,color,1f)
        if (bubblesList.size>30){
            bubblesList.removeAt(0)
        }
        return super.onTouchEvent(event)
    }

}