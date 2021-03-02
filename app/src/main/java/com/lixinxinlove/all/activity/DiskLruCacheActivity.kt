package com.lixinxinlove.all.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import com.lixinxinlove.all.cache.CacheManager

/**
 * 缓存
 */
class DiskLruCacheActivity : BaseActivity() {
    lateinit var textCache: AppCompatTextView
    lateinit var cacheManager: CacheManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disk_lru_cache)
        textCache = findViewById(R.id.text_cache)
        cacheManager = CacheManager.getInstance()
        cacheManager.putCache("key1", "lixinxin")
    }


    fun getData(view: View) {
        val data = cacheManager.getCache("key1")
        textCache.text = data

    }

    fun editData(view: View) {
        cacheManager.putCache("key1", "lixinxin111")
    }
}