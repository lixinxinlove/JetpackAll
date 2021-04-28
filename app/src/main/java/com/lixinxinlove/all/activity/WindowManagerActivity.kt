package com.lixinxinlove.all.activity

import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import com.lixinxinlove.all.dp


class WindowManagerActivity : BaseActivity(), View.OnTouchListener {

    private lateinit var mWindowManager: WindowManager
    private lateinit var mImageView: ImageView
    private lateinit var mLayoutParams: WindowManager.LayoutParams


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window_manager)


        mWindowManager =
            applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mImageView = ImageView(this)
        mImageView.setImageResource(R.mipmap.ic_launcher)
        mLayoutParams = WindowManager.LayoutParams()

        mLayoutParams.width = 120.dp.toInt()
        mLayoutParams.height = 120.dp.toInt()

        mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY

        mLayoutParams.flags =
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED


        mLayoutParams.gravity = Gravity.TOP or Gravity.LEFT
        mLayoutParams.x = 0
        mLayoutParams.y = 0
        //如果不加,背景会是一片黑色。
        mLayoutParams.format = PixelFormat.RGBA_8888
        mImageView.setOnTouchListener(this)


    }


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val rawX = event!!.rawX.toInt()
        val rawY = event.rawY.toInt()

        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                mLayoutParams.x = rawX
                mLayoutParams.y = rawY
                mWindowManager.updateViewLayout(mImageView, mLayoutParams)
            }
            else -> {
            }
        }
        return false

    }


    override fun onStop() {
        super.onStop()
        mWindowManager.removeView(mImageView)
    }

    override fun onDestroy() {
        mWindowManager.removeView(mImageView)
        super.onDestroy()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun onShow(view: View) {
        if (Settings.canDrawOverlays(this)) {
            mWindowManager.addView(mImageView, mLayoutParams)
        } else {
            val intent = Intent()
            intent.action = Settings.ACTION_MANAGE_OVERLAY_PERMISSION
            intent.data = Uri.parse("package:$packageName")
            startActivity(intent)
        }

    }


}