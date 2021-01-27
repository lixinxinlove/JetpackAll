package com.lixinxinlove.all.activity

import android.os.Bundle
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilt)
    }
}