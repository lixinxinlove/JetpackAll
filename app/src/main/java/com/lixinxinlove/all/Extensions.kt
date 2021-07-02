package com.lixinxinlove.all

import android.content.res.Resources
import android.util.TypedValue
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.BaseRequestOptions
import com.bumptech.glide.request.RequestOptions

val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )


val Int.dp
    get() = this.toFloat().dp


fun RequestBuilder<*>.miniThumb(size: Int): RequestBuilder<*> {
    return this.fitCenter()
        //.circleCrop()
        .override(size)
}