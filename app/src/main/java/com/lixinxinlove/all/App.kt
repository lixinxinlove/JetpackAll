package com.lixinxinlove.all

import android.app.Application
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore

class App : Application() {


    companion object{
        lateinit var dataStore: DataStore<Preferences>
    }



    override fun onCreate() {
        super.onCreate()
        dataStore = createDataStore(
            name = "settings"
        )
    }

}