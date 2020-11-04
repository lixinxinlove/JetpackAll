package com.lixinxinlove.all.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity

/**
 * SpringAnimation动画
 */
class SpringAnimationActivity : BaseActivity() {

    var offsetX: Float = 0f
    var offsetY: Float = 0f

    private lateinit var test: TextView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spring_animation)
        test = findViewById(R.id.test)

        test.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    offsetX = event.rawX
                    offsetY = event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    test.translationX = event.rawX - offsetX
                    test.translationY = event.rawY - offsetY
                }
                MotionEvent.ACTION_UP -> {
                    SpringAnimation(test, DynamicAnimation.TRANSLATION_Y).apply {
                        spring = SpringForce().apply {
//                            dampingRatio = DAMPING_RATIO_NO_BOUNCY
//                            stiffness = SpringForce.STIFFNESS_VERY_LOW
                        }
                        animateToFinalPosition(0f)
                    }
                    SpringAnimation(test, DynamicAnimation.TRANSLATION_X).apply {
                        spring = SpringForce().apply {
//                            dampingRatio = DAMPING_RATIO_NO_BOUNCY
//                            stiffness = SpringForce.STIFFNESS_VERY_LOW
                        }
                        addUpdateListener { animation, value, velocity ->
                            Log.e("SpringAnimation", "动画更新$value")
                        }
                        addEndListener { animation, canceled, value, velocity ->
                            Log.e("SpringAnimation","动画结束")
                        }

                        animateToFinalPosition(0f)
                    }


                }
            }
            true
        }


    }
}