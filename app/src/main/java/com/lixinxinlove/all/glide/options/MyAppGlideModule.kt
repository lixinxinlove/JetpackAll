package com.lixinxinlove.all.glide.options

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule

/**
 *   author ： lixinxin
 *   time    ： 2021/7/2
 *   email：895330766@qq.com
 */
@GlideModule
class MyAppGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)

        val memoryCacheSizeBytes: Long = 1024 * 1024 * 20 //20mb
        builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes))

    }

}