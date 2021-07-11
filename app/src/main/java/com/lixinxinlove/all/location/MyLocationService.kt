package com.lixinxinlove.all.location

import androidx.lifecycle.LifecycleService


/**
 * 后台定位
 */
class MyLocationService : LifecycleService() {


    init {
        val myLocationObserver = MyLocationObserver(this)
        lifecycle.addObserver(myLocationObserver)
    }


}