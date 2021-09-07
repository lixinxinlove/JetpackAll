package com.lixinxinlove.all.activity

import android.hardware.Sensor
import com.lixinxinlove.all.base.BaseActivity
import android.hardware.SensorEventListener
import android.widget.TextView
import android.hardware.SensorManager
import android.os.Bundle
import com.lixinxinlove.all.R
import android.hardware.SensorEvent
import androidx.appcompat.widget.AppCompatImageView
import com.lixinxinlove.all.activity.SensorManagerActivity
import android.animation.ObjectAnimator


class SensorManagerActivity : BaseActivity(), SensorEventListener {

    private lateinit var hp_bg_img: AppCompatImageView

    private var tv_light: TextView? = null
    private var mSensorMgr // 声明一个传感管理器对象
            : SensorManager? = null
    private var mTimestamp // 记录上次的时间戳
            = 0f
    private val mAngle = FloatArray(3) // 记录xyz三个方向上的旋转角度


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor_manager)
        hp_bg_img = findViewById(R.id.hp_bg_img)

        hp_bg_img.scaleX = 1.2F
        hp_bg_img.scaleY = 1.2F


        tv_light = findViewById(R.id.tv_light)
        // 从系统服务中获取传感管理器对象
        mSensorMgr = getSystemService(SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        //注册陀螺仪
        mSensorMgr!!.registerListener(
            this,
            mSensorMgr!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        super.onPause()
        // 注销当前活动的传感监听器
        mSensorMgr!!.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_GYROSCOPE) { // 陀螺仪角度变更事件
            if (mTimestamp != 0f) {
                val dT = (event.timestamp - mTimestamp) * NS2S
                mAngle[0] += event.values[0] * dT
                mAngle[1] += event.values[1] * dT
                mAngle[2] += event.values[2] * dT
                // x轴的旋转角度，手机平放桌上，然后绕侧边转动
                val angleX = Math.toDegrees(mAngle[0].toDouble()).toFloat()
                // y轴的旋转角度，手机平放桌上，然后绕底边转动
                val angleY = Math.toDegrees(mAngle[1].toDouble()).toFloat()
                // z轴的旋转角度，手机平放桌上，然后水平旋转
                val angleZ = Math.toDegrees(mAngle[2].toDouble()).toFloat()
                val desc = String.format(
                    "陀螺仪检测到当前\nx轴方向的转动角度为%f\ny轴方向的转动角度为%f\nz轴方向的转动角度为%f",
                    angleX, angleY, angleZ
                )
                tv_light!!.text = desc



                upTrX(angleX)
                upTrY(angleY)


            }
            mTimestamp = event.timestamp.toFloat()
        }
    }


    private fun upTrX(angleX: Float) {
        var angle = angleX
        if (angle > 90) {
            angle = 90F
        }

        if (angle < -90) {
            angle = -90F
        }


        val animator1 = ObjectAnimator.ofFloat(hp_bg_img, "translationY", hp_bg_img.y, angle * 2.5f)
        animator1.start()

    }


    private fun upTrY(angleY: Float) {
        var angle = angleY
        if (angle > 90) {
            angle = 90F
        }

        if (angle < -90) {
            angle = -90F
        }


        val animator1 = ObjectAnimator.ofFloat(hp_bg_img, "translationX", hp_bg_img.x, angle * 2.5f)
        animator1.start()
    }

    //当传感器精度改变时回调该方法，一般无需处理
    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

    companion object {
        private const val NS2S = 1.0f / 1000000000.0f // 将纳秒转化为秒
    }
}