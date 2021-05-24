package com.lixinxinlove.all.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lixinxinlove.all.R


class DeepLinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deep_link)
        // 尝试获取WebApp页面上过来的URL
        val uri: Uri? = intent.data
        if (uri != null) {
            // scheme部分
            val scheme: String? = uri .scheme
            // host部分
            val host: String? = uri .host
            // 访问路径
            val path: String? = uri .path
            //参数
            val paramKeySet: Set<String> = uri .queryParameterNames
        }
    }

    private fun gotoLink(){
        val intent = Intent()
        intent.data = Uri.parse("pxwxstudent://app.puxinwangxiao.com/")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

}