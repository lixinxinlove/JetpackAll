package com.lixinxinlove.all.activity

import android.os.Bundle
import android.transition.Explode
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.lixinxinlove.all.R

class Transition2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.enterTransition = Explode().setDuration(2000)
        window.exitTransition = Explode().setDuration(2000)
        setContentView(R.layout.activity_transition2)
    }
}