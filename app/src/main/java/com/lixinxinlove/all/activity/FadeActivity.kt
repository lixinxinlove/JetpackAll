package com.lixinxinlove.all.activity

import android.os.Bundle
import android.transition.Fade
import androidx.appcompat.app.AppCompatActivity
import com.lixinxinlove.all.R

class FadeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fade)
        //进入退出效果 注意这里 创建的效果对象是 Fade()
        window.enterTransition = Fade().setDuration(2000)
        window.exitTransition = Fade().setDuration(2000)
    }
}