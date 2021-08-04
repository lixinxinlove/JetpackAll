package com.lixinxinlove.all.activity

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import com.permissionx.guolindev.PermissionX

class PermissionXActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission_xactivity)
    }


    fun onCall(view: View) {
        PermissionX.init(this)
            .permissions(Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(deniedList, "核心基础都是基于这些权限", "OK", "Cancel")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    Toast.makeText(this, "授予所有权限", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "这些权限被拒绝: $deniedList", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun onCamera(view: View) {

    }


    private fun call() {

    }
}