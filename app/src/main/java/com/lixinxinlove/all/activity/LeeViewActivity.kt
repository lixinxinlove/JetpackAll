package com.lixinxinlove.all.activity

import android.os.Bundle
import com.lixinxinlove.all.base.BaseActivity
import com.lixinxinlove.all.databinding.ActivityLeeViewBinding

class LeeViewActivity : BaseActivity() {
    private lateinit var binding: ActivityLeeViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeeViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.leeView.setOnClickListener {
            binding.leeView.setValues(100)
        }

    }
}