package com.lixinxinlove.all.glide.options

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.LibraryGlideModule
import java.io.InputStream

/**
 *   author ： lixinxin
 *   time    ： 2021/7/2
 *   email：895330766@qq.com
 */
@GlideModule
class OkHttpLibraryGlideModel :LibraryGlideModule(){

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        //super.registerComponents(context, glide, registry)
        //替换Glide 加载引擎 默认 是 httpclaurl
        registry.replace(GlideUrl::class.java,InputStream::class.java,OkHttpUrlLoader.Factory())
    }
}