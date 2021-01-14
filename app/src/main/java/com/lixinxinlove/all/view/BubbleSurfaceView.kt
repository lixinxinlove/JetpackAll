package com.lixinxinlove.all.view

import android.content.Context
import android.graphics.Color
import android.graphics.DiscretePathEffect
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BubbleSurfaceView(context: Context?, attrs: AttributeSet?) :
    SurfaceView(context, attrs, 0) {


    private val colors = arrayListOf(Color.CYAN, Color.LTGRAY,Color.RED,Color.YELLOW,Color.GREEN)

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
        val bubble = Bubble(x, y, color, 1f)
        bubblesList.add(bubble)
        if (bubblesList.size > 30) {
            bubblesList.removeAt(0)
        }
        return super.onTouchEvent(event)
    }


    init {
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                if (holder.surface.isValid) {
                    val canvas = holder.lockCanvas()
                    canvas.drawColor(Color.BLACK)
                    bubblesList.toList().filter { it.r < 2000 }.forEach {
                        paint.color = it.color
                        canvas.drawCircle(it.x, it.y, it.r, paint)
                        it.r += 10f
                    }
                    holder.unlockCanvasAndPost(canvas)
                }
            }
        }
    }

}