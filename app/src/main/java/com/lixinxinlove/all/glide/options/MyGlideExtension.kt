package com.lixinxinlove.all.glide.options

import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.request.BaseRequestOptions
import com.bumptech.glide.request.RequestOptions
import com.lixinxinlove.all.R

/**
 *   author ： lixinxin
 *   time    ： 2021/7/2
 *   email：895330766@qq.com
 */

object MyGlideExtension {



    fun applyAvatar(options: BaseRequestOptions<*>, size: Int): BaseRequestOptions<*>? {
        return options.placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher_round)
            .override(size)
    }


}


