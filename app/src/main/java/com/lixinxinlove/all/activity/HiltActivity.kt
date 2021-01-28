package com.lixinxinlove.all.activity

import android.os.Bundle
import com.lixinxinlove.all.App_HiltComponents
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import com.lixinxinlove.all.databinding.ActivityHiltBinding
import com.lixinxinlove.all.entity.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiltActivity : BaseActivity() {

    @Inject
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHiltBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.textUser.text = user.toString()

    }
}