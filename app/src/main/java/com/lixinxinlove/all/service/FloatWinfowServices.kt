package com.lixinxinlove.all.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.view.*
import android.widget.Chronometer
import android.widget.LinearLayout
import com.lixinxinlove.all.R
import com.lixinxinlove.all.activity.Main2Activity

class FloatWinfowServices : Service() {

    private var winManager: WindowManager? = null

    private var wmParams: WindowManager.LayoutParams? = null

    private var inflater: LayoutInflater? = null
    //浮动布局
    private var mFloatingLayout: View? = null

    private var linearLayout: LinearLayout? = null

    private var chronometer: Chronometer? = null

    override fun onBind(intent: Intent): IBinder? {
        initWindow()

//悬浮框点击事件的处理

        initFloating()

        return MyBinder()

    }

    inner class MyBinder : Binder() {
        val service: FloatWinfowServices
            get() = this@FloatWinfowServices

    }

    override fun onCreate() {
        super.onCreate()

    }

    /**

     * 悬浮窗点击事件

     */

    private fun initFloating() {
        linearLayout = mFloatingLayout!!.findViewById(R.id.line1)

        linearLayout!!.setOnClickListener {
            startActivity(
                Intent(
                    this@FloatWinfowServices,
                    Main2Activity::class.java
                )
            )
        }

//悬浮框触摸事件，设置悬浮框可拖动

        linearLayout!!.setOnTouchListener(FloatingListener())

    }

//开始触控的坐标，移动时的坐标(相对于屏幕左上角的坐标)

    private var mTouchStartX: Int = 0

    private var mTouchStartY: Int = 0

    private var mTouchCurrentX: Int = 0

    private var mTouchCurrentY: Int = 0

//开始时的坐标和结束时的坐标(相对于自身控件的坐标)

    private var mStartX: Int = 0

    private var mStartY: Int = 0

    private var mStopX: Int = 0

    private var mStopY: Int = 0

//判断悬浮窗口是否移动，这里做个标记，防止移动后松手触发了点击事件

    private var isMove: Boolean = false

    private inner class FloatingListener : View.OnTouchListener {
        override fun onTouch(v: View, event: MotionEvent): Boolean {
            val action = event.action

            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    isMove = false

                    mTouchStartX = event.rawX.toInt()

                    mTouchStartY = event.rawY.toInt()

                    mStartX = event.x.toInt()

                    mStartY = event.y.toInt()

                }

                MotionEvent.ACTION_MOVE -> {
                    mTouchCurrentX = event.rawX.toInt()

                    mTouchCurrentY = event.rawY.toInt()

                    wmParams!!.x += mTouchCurrentX - mTouchStartX

                    wmParams!!.y += mTouchCurrentY - mTouchStartY

                    winManager!!.updateViewLayout(mFloatingLayout, wmParams)

                    mTouchStartX = mTouchCurrentX

                    mTouchStartY = mTouchCurrentY

                }

                MotionEvent.ACTION_UP -> {
                    mStopX = event.x.toInt()

                    mStopY = event.y.toInt()

                    if (Math.abs(mStartX - mStopX) >= 1 || Math.abs(mStartY - mStopY) >= 1) {
                        isMove = true

                    }

                }

                else -> {
                }

            }

//如果是移动事件不触发OnClick事件，防止移动的时候一放手形成点击事件

            return isMove

        }

    }

    /**

     * 初始化窗口

     */

    private fun initWindow() {
        winManager = application.getSystemService(Context.WINDOW_SERVICE) as WindowManager

//设置好悬浮窗的参数

        wmParams = params

// 悬浮窗默认显示以左上角为起始坐标

        wmParams!!.gravity = Gravity.LEFT or Gravity.TOP

//悬浮窗的开始位置，因为设置的是从左上角开始，所以屏幕左上角是x=0;y=0

        wmParams!!.x = winManager!!.defaultDisplay.width

        wmParams!!.y = 210

//得到容器，通过这个inflater来获得悬浮窗控件

        inflater = LayoutInflater.from(applicationContext)

// 获取浮动窗口视图所在布局

        mFloatingLayout = inflater!!.inflate(R.layout.remoteview, null)

        chronometer = mFloatingLayout!!.findViewById(R.id.chronometer)

        chronometer!!.start()

       // 添加悬浮窗的视图
        winManager!!.addView(mFloatingLayout, wmParams)

    }

    private //设置window type 下面变量2002是在屏幕区域显示，2003则可以显示在状态栏之上

//设置可以显示在状态栏上

//设置悬浮窗口长宽数据

    val params: WindowManager.LayoutParams
        get() {
            wmParams = WindowManager.LayoutParams()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                wmParams!!.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY

            } else {
                wmParams!!.type = WindowManager.LayoutParams.TYPE_PHONE

            }

            wmParams!!.flags =
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or

                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR or

                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH

            wmParams!!.width = WindowManager.LayoutParams.WRAP_CONTENT

            wmParams!!.height = WindowManager.LayoutParams.WRAP_CONTENT

            return wmParams!!

        }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)

    }

    override fun onDestroy() {
        super.onDestroy()

        winManager!!.removeView(mFloatingLayout)

    }

}
