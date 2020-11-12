package com.lixinxinlove.all.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.BounceInterpolator
import com.lixinxinlove.all.dp

class LeeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var progress = 0
        set(value) {
            field = value
            invalidate()
        }

    private var mW = 0
    private var mH = 0

    private var bgPaint = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.STROKE
        strokeWidth = 20.dp
        isAntiAlias = true
        //设置画笔的画出的形状
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }


    private var progressPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 20.dp
        isAntiAlias = true
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mW = w
        mH = w

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawBg(canvas)
        drawProgress(canvas)
    }


    private fun drawProgress(canvas: Canvas) {
        canvas.drawArc(
            20.dp,
            20.dp,
            mW.toFloat() - 20.dp,
            mH.toFloat() - 20.dp,
            150f,
            progress * 2.4f,
            false,
            progressPaint
        )
    }


    private fun drawBg(canvas: Canvas) {
        canvas.drawArc(
            20.dp,
            20.dp,
            mW.toFloat() - 20.dp,
            mH.toFloat() - 20.dp,
            150f,
            240f,
            false,
            bgPaint
        )
    }

    fun setValues(mProgress: Int) {
        val animator = ObjectAnimator.ofInt(this, "progress", mProgress)
        animator.duration = 1000
        animator.interpolator = BounceInterpolator()
        animator.start()
    }


}