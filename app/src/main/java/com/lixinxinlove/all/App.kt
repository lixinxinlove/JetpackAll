package com.lixinxinlove.all

import android.app.Application
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import com.lixinxinlove.all.util.IsForeBackGroundActivityCallback
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        Log.e("App", "onCreate")
        dataStore = createDataStore(name = "settings")
        registerActivityLifecycleCallbacks(IsForeBackGroundActivityCallback())
    }

    companion object {
        lateinit var dataStore: DataStore<Preferences>
        var instance: App? = null
    }

}