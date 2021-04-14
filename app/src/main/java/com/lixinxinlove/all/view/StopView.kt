package com.lixinxinlove.all.view

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.lixinxinlove.all.R
import com.lixinxinlove.all.dp

private const val UN_START = 0
private const val START = 1
private const val STOP = 2
private const val OK = 3

class StopView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var mContext: Context = context

    private val bitmap = BitmapFactory.decodeResource(mContext.resources, R.mipmap.icon_modify)

    private var progress = 0
        set(value) {
            field = value
            invalidate()
        }

    private var mW = 0
    private var mH = 0

    private lateinit var animator: ObjectAnimator

    private var bgPaint = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.STROKE
        strokeWidth = 20.dp
        isAntiAlias = true
        //设置画笔的画出的形状
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }

    private var bitmapPaint = Paint().apply {
        style = Paint.Style.STROKE
        isAntiAlias = true
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
        path.addCircle(mW / 2f, mW / 2f, mW / 2f - 20.dp, Path.Direction.CCW)
    }

    private val path = Path()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()
        canvas.clipPath(path)
        drawIcon(canvas)
        canvas.restore()
        drawProgress(canvas)
    }


    private fun drawIcon(canvas: Canvas) {

        val srcRect = Rect(0, 0, bitmap.width, bitmap.height)
        val dstRect = Rect(0, 0, mW, mH)
        canvas.drawBitmap(bitmap, srcRect, dstRect, bitmapPaint)

    }


    private fun drawProgress(canvas: Canvas) {
        canvas.drawArc(
            20.dp,
            20.dp,
            mW.toFloat() - 20.dp,
            mH.toFloat() - 20.dp,
            -90f,
            progress * 3.6f,
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
            -90f,
            350f,
            false,
            bgPaint
        )
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                //按下
                Log.e("StopView", "ACTION_DOWN")

                setValues(100)
            }
            MotionEvent.ACTION_MOVE -> {
                //移动
                Log.d("StopView", "ACTION_MOVE")
            }
            MotionEvent.ACTION_UP -> {
                Log.e("StopView", "ACTION_UP")
                animator.cancel()
            }
            MotionEvent.ACTION_CANCEL -> {
                Log.e("StopView", "ACTION_CANCEL")
            }
        }
        return true
    }


    fun setValues(mProgress: Int) {
        animator = ObjectAnimator.ofInt(this, "progress", mProgress)
        animator.duration = 3000
        // animator.interpolator = BounceInterpolator()
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {

            }

            override fun onAnimationCancel(animation: Animator?) {
                progress = 0
            }

            override fun onAnimationRepeat(animation: Animator?) {

            }
        })
        animator.start()
    }
}



