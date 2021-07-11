package com.lixinxinlove.all.glide

import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import com.lixinxinlove.all.glide.options.GlideApp
import com.lixinxinlove.all.miniThumb

class GlideDemoActivity : BaseActivity() {

    private lateinit var imageView: AppCompatImageView

    private val url = "http://pic27.nipic.com/20130312/12058334_175946381000_2.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide_demo)

        imageView = findViewById(R.id.image_view)

        Glide.with(this)
            .load(R.mipmap.ic_launcher)
            .miniThumb(130)
            .into(imageView)

    }
}


