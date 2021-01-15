package com.lixinxinlove.all.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.view.View
import android.view.Window
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
        startActivity(
            Intent(this, ExplodeActivity::class.java),
            ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        )
    }

    fun slide(view: View) {
        startActivity(
            Intent(this, SlideActivity::class.java),
            ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        )
    }
    fun fade(view: View) {
        startActivity(
            Intent(this, FadeActivity::class.java),
            ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        )
    }
    fun sharedElements1(view: View) {
        startActivity(
            Intent(this, SharedElementsActivity::class.java),
            ActivityOptions.makeSceneTransitionAnimation(this, view, "myButton1")
                .toBundle()
        )}
    fun sharedElements2(view: View) {}
    fun sharedElements4(view: View) {}


}