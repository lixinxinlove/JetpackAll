package com.lixinxinlove.all.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 *   author ： lixinxin
 *   time    ： 2021/7/2
 *   email：895330766@qq.com
 */
class MyLocationObserver(content: Context) : LifecycleObserver {

    private var content: Context = content

    private lateinit var locationManager: LocationManager
    private lateinit var myLocationListener: MyLocationListener

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
     fun startGetLocation() {
        Log.e("MyLocationObserver","startGetLocation")
        locationManager = content.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        myLocationListener = MyLocationListener()
        if (ActivityCompat.checkSelfPermission(content, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                content,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000,
            0f,
            myLocationListener
        )


    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
     fun stopGetLocation() {
        Log.e("MyLocationObserver","stopGetLocation")
        locationManager.removeUpdates(myLocationListener)
    }


    private inner class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            Log.e("MyLocationListener", "latitude=${location.latitude} ")
            Log.e("MyLocationListener", "longitude=${location.longitude}")
            Log.e("MyLocationListener", "altitude=${location.altitude}")
            Log.e("MyLocationListener", "accuracy=${location.accuracy}")
            Log.e("MyLocationListener", "bearing=${location.bearing}")
            Log.e("MyLocationListener", "provider=${location.provider}")
            Log.e("MyLocationListener", "=================================")


        }

    }

}