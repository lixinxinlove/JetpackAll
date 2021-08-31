package com.lixinxinlove.all.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.lixinxinlove.all.R

class LottieAnimationViewActivity : AppCompatActivity() {

    lateinit var lottieAnimationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie_animation_view)

        lottieAnimationView = findViewById(R.id.animation_view)

        val ZIPURL = "https://cqz-1256838880.cos.ap-shanghai.myqcloud.com/bird1.json"

        lottieAnimationView.setAnimationFromUrl(ZIPURL)

        lottieAnimationView.setOnClickListener { lottieAnimationView.playAnimation() }


    }
}