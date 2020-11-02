package com.lixinxinlove.all.activity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.datastore.DataStore
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import androidx.lifecycle.lifecycleScope
import com.lixinxinlove.all.App
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.prefs.Preferences

class DataStoreActivity : BaseActivity() {


    private val EXAMPLE_COUNTER = preferencesKey<Int>("example_counter")


    private lateinit var button: Button
    private lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_store)
        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)


        val exampleCounterFlow: Flow<Int> = App.dataStore.data.map { preferences ->
            preferences[EXAMPLE_COUNTER] ?: 0
        }

        button.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                incrementCounter()
            }
        }

        textView.setOnClickListener {

            lifecycleScope.launch(Dispatchers.IO) {
                exampleCounterFlow.collectLatest {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@DataStoreActivity, "$it", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


    }


    //写数据
    suspend fun incrementCounter() {
        App.dataStore.edit { settings ->
            val currentCounterValue = settings[EXAMPLE_COUNTER] ?: 0
            settings[EXAMPLE_COUNTER] = currentCounterValue + 1
        }
    }


}