package com.lixinxinlove.all.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lixinxinlove.all.R
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class FlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)
    }

    
    private suspend fun flowFun() {
        val nums = (1..3).asFlow().onEach { delay(300) } // 发射数字 1..3，间隔 300 毫秒
        val strs = flowOf("one", "two", "three").onEach { delay(400) } // 每 400 毫秒发射一次字符串
        val startTime = System.currentTimeMillis() // 记录开始的时间
        nums.combine(strs) { a, b -> "$a -> $b" }.collect {
            println("$it at ${System.currentTimeMillis() - startTime} ms from start")
        }


        // 使用“combine”组合单个字符串
//            .collect { value -> // 收集并打印
//                println("$value at ${System.currentTimeMillis() - startTime} ms from start")
//            }
    }


}