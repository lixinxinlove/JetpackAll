package com.lixinxinlove.all

import android.app.Application
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import com.lixinxinlove.all.util.IsForeBackGroundActivityCallback




class App : Application() {


    companion object{
        lateinit var dataStore: DataStore<Preferences>
    }



    override fun onCreate() {
        super.onCreate()
        dataStore = createDataStore(
            name = "settings"
        )

        registerActivityLifecycleCallbacks(IsForeBackGroundActivityCallback())
    }

}