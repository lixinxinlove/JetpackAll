package com.lixinxinlove.all.activity

import android.os.Bundle
import android.transition.Slide
import androidx.appcompat.app.AppCompatActivity
import com.lixinxinlove.all.R

class SlideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)
        //进入退出效果 注意这里 创建的效果对象是 Slide()
        //进入退出效果 注意这里 创建的效果对象是 Slide()
        window.enterTransition = Slide().setDuration(2000)
        window.exitTransition = Slide().setDuration(2000)
    }
}