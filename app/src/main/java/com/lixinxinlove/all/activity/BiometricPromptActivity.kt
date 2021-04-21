package com.lixinxinlove.all.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity

/**
 * 指纹识别
 */
class BiometricPromptActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biometric_prompt)
    }
}