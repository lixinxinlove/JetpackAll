package com.lixinxinlove.all.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.deeplinkdispatch.DeepLink
import com.lixinxinlove.all.R

@DeepLink("https://dribbble.com/shots/{id}")
class GotoLinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goto_link)
    }
}