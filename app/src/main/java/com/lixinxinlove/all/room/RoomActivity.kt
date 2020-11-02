package com.lixinxinlove.all.room

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import com.lixinxinlove.all.vm.UserViewModel

class RoomActivity : BaseActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        // var userViewModel: UserViewModel by viewModels<UserViewModel>
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.allUserList().observe(this, Observer {

        })
    }
}