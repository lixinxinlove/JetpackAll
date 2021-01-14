package com.lixinxinlove.all.activity

import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.view.View
import android.view.Window
import androidx.core.app.ActivityOptionsCompat
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity


class TransitionActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.enterTransition = Explode().setDuration(2000)
        window.exitTransition = Explode().setDuration(2000)
        setContentView(R.layout.activity_transition)
    }

    fun explode(view: View) {
        val intent = Intent(this, Transition2Activity::class.java)
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
    }


}