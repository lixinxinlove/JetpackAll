package com.lixinxinlove.all.http.data

/**
 *   author ： lixinxin
 *   time    ： 2021/7/15
 *   email：895330766@qq.com
 */
data class RespData<T>(
    val code: String,
    /**
     * 返回错误信息
     */
    val message: String,
    /**
     * 返回的数据
     */
    val data: T
)
