package com.lixinxinlove.all

import android.app.Application
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import com.lixinxinlove.all.util.IsForeBackGroundActivityCallback
import dagger.hilt.android.HiltAndroidApp
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor


@HiltAndroidApp
class App : Application() {

    private var flutterEngine: FlutterEngine? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        Log.e("App", "onCreate")
        dataStore = createDataStore(name = "settings")
        registerActivityLifecycleCallbacks(IsForeBackGroundActivityCallback())



        flutterEngine = FlutterEngine(this)
        flutterEngine!!.getDartExecutor().executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        FlutterEngineCache
            .getInstance()
            .put("my_engine_id", flutterEngine)
    }

    companion object {
        lateinit var dataStore: DataStore<Preferences>
        var instance: App? = null
    }

}