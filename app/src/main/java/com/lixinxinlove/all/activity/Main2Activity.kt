package com.lixinxinlove.all.activity

import android.content.*
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Chronometer
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.lixinxinlove.all.R
import com.lixinxinlove.all.service.FloatWinfowServices


class Main2Activity : AppCompatActivity() {

    private val chronometer: Chronometer? = null

    private var hasBind = false

    private val rangeTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    fun zoom(v: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT)

                AlertDialog.Builder(this)
                    .setTitle("提示").setMessage("当前未获取悬浮窗权限")
                    .setNegativeButton(
                        "去开启"
                    ) { dialog, _ ->
                        dialog.dismiss()
                        startActivityForResult(
                            Intent(
                                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:$packageName")
                            ), 0
                        )
                    }
                    .create()
                    .show()

            } else {
                moveTaskToBack(true)
                val intent = Intent(this@Main2Activity, FloatWinfowServices::class.java)
                hasBind = bindService(intent, mVideoServiceConnection, Context.BIND_AUTO_CREATE)
            }

        }

    }

    internal var mVideoServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            // 获取服务的操作对象
            val binder = service as FloatWinfowServices.MyBinder
            binder.service
        }
        override fun onServiceDisconnected(name: ComponentName) {}

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show()
                } else {
                    Handler().postDelayed({
                        val intent = Intent(this@Main2Activity, FloatWinfowServices::class.java)
                        intent.putExtra("rangeTime", rangeTime)
                        hasBind =
                            bindService(intent, mVideoServiceConnection, Context.BIND_AUTO_CREATE)
                        moveTaskToBack(true)
                    }, 1000)
                }
            }
        }
    }


    override fun onRestart() {
        super.onRestart()
        Log.d("RemoteView", "重新显示了")
        //不显示悬浮框
        if (hasBind) {
            unbindService(mVideoServiceConnection)
            hasBind = false
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
    }
    override fun onDestroy() {
        super.onDestroy()

    }

}
