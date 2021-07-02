package com.lixinxinlove.all.location

import android.content.Intent
import android.view.View
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity


import android.os.Bundle

class LocationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
    }

    fun startService(view: View) {
        startService(Intent(this, MyLocationService::class.java))
    }

    fun stopService(view: View) {
        stopService(Intent(this, MyLocationService::class.java))
    }
}