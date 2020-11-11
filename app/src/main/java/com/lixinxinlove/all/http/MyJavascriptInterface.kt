package com.lixinxinlove.all.http

import android.util.Log
import android.webkit.JavascriptInterface




class MyJavascriptInterface {

    /**
     * 前端代码嵌入js：
     * imageClick 名应和js函数方法名一致
     *
     * @param src 图片的链接
     */
    @JavascriptInterface
    fun imageClick(src: String) {
        Log.e("imageClick", "----点击了图片")
        Log.e("src", src)
    }

}