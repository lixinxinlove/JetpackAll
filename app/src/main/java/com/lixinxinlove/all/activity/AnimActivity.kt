package com.lixinxinlove.all.activity

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity



class AnimActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim)

//        imageView_hand.setOnClickListener {
//            val animation: Animation =
//                AnimationUtils.loadAnimation(this, R.anim.translate_anim).apply {
//                    this.fillBefore = true
//                }
//            imageView_hand.startAnimation(animation)
    //}

    }
}