package com.lixinxinlove.all.activity

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AnticipateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.Interpolator
import android.view.animation.OvershootInterpolator
import com.lixinxinlove.all.base.BaseActivity
import com.lixinxinlove.all.databinding.ActivityLeeViewBinding

class LeeViewActivity : BaseActivity() {
    private lateinit var binding: ActivityLeeViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeeViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val animator = ObjectAnimator.ofInt(binding.leeView, "progress", 50)
        animator.duration = 2000
        animator.startDelay = 1000
        animator.interpolator = BounceInterpolator()
        animator.start()

    }
}