package com.lixinxinlove.all.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import org.opencv.android.OpenCVLoader

class CppActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cpp)
        findViewById<TextView>(R.id.sample_text).text = stringFromJNI()


        findViewById<TextView>(R.id.sample_text).setOnClickListener {
            startActivity(Intent(this, RetrofitActivity::class.java))
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
        }

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
           if (OpenCVLoader.initDebug()) {
               Log.e("CppActivity","initDebug成功")
           }else{
               Log.e("CppActivity","initDebug失败")
           }
        }
    }
}