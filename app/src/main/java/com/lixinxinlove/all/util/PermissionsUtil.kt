package com.lixinxinlove.all.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import java.util.*

object PermissionsUtil {
    const val REQUEST_STATUS_CODE = 0x001
    const val REQUEST_PERMISSION_SETTING = 0x002
    var PERMISSIONS_GROUP_SORT = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.CAMERA
    )

    private var callbacks: PermissionCallbacks? = null

    fun checkAndRequestPermissions(activity: Activity?, callback: PermissionCallbacks?) {
        if (Build.VERSION.SDK_INT >= 23) {
            callbacks = callback
            val denidArray = ArrayList<String>()
            for (permission in PERMISSIONS_GROUP_SORT) {
                val grantCode = ActivityCompat.checkSelfPermission(activity!!, permission)
                if (grantCode == PackageManager.PERMISSION_DENIED) {
                    denidArray.add(permission)
                }
            }
            if (denidArray.size > 0) {
                val denidArrayNew = ArrayList<String>()
                denidArrayNew.add(denidArray[0])
                val denidPermissions = denidArrayNew.toTypedArray()
                requestPermissions(activity, denidPermissions)
            } else {
                callbacks!!.onPermissionsGranted()
            }
        }
    }

    /**
     * 关于shouldShowRequestPermissionRationale函数的一点儿注意事项：
     * ***1).应用安装后第一次访问，则直接返回false；
     * ***2).第一次请求权限时，用户Deny了，再次调用shouldShowRequestPermissionRationale()，
     * 则返回true； ***3).第二次请求权限时，用户Deny了，并选择了“dont ask me
     * again”的选项时，再次调用shouldShowRequestPermissionRationale()时，返回false；
     * ***4).设备的系统设置中，禁止了应用获取这个权限的授权，则调用shouldShowRequestPermissionRationale()，
     * 返回false。
     */
    fun showRationaleUI(activity: Activity?, permission: String?): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity!!, permission!!)
    }

    /**
     * 对权限字符串数组中的所有权限进行申请授权，如果用户选择了“dont ask me again”，则不会弹出系统的Permission申请授权对话框
     */
    fun requestPermissions(activity: Activity?, permissions: Array<String>?) {
        ActivityCompat.requestPermissions(activity!!, permissions!!, REQUEST_STATUS_CODE)
    }

    fun checkSinglePermissions(activity: Activity?, permission: String): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            val denidArray = ArrayList<String>()
            val grantCode = ActivityCompat.checkSelfPermission(activity!!, permission)
            if (grantCode == PackageManager.PERMISSION_DENIED) {
                denidArray.add(permission)
            }
            if (denidArray.size > 0) {
                return false
            }
        }
        return true
    }

    /**
     * 用来判断，App是否是首次启动：
     * ***由于每次调用shouldShowRequestPermissionRationale得到的结果因情况而变，因此必须判断一下App是否首次启动
     * ，才能控制好出现Dialog和SnackBar的时机
     */
    fun isAppFirstRun(activity: Activity): Boolean {
        val sp = activity.getSharedPreferences("config", Context.MODE_PRIVATE)
        val editor = sp.edit()
        return if (sp.getBoolean("first_run", true)) {
            editor.putBoolean("first_run", false)
            editor.commit()
            true
        } else {
            editor.putBoolean("first_run", false)
            editor.commit()
            false
        }
    }

    fun checkNotifySetting(activity: Activity): Boolean {
        val manager = NotificationManagerCompat.from(activity)
        // areNotificationsEnabled方法的有效性官方只最低支持到API 19，低于19的仍可调用此方法不过只会返回true，即默认为用户已经开启了通知。
        val isOpened = manager.areNotificationsEnabled()
        if (isOpened) {
            Log.e(
                "checkNotifySetting", """
     通知权限已经被打开
     手机型号:${Build.MODEL}
     SDK版本:${Build.VERSION.SDK}
     系统版本:${Build.VERSION.RELEASE}
     软件包名:${activity.packageName}
     """.trimIndent()
            )
        } else {
            Log.e("checkNotifySetting", "还没有开启通知权限，点击去开启")
        }
        return isOpened
    }

    fun gotoSetting(activity: Activity) {
        //跳转到通知设置界面
        try {
            // 根据isOpened结果，判断是否需要提醒用户跳转AppInfo页面，去打开App通知权限
            val intent = Intent()
            intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            //这种方案适用于 API 26, 即8.0（含8.0）以上可以用
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, activity.packageName)
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, activity.applicationInfo.uid)

            //这种方案适用于 API21——25，即 5.0——7.1 之间的版本可以使用
            intent.putExtra("app_package", activity.packageName)
            intent.putExtra("app_uid", activity.applicationInfo.uid)

            // 小米6 -MIUI9.6-8.0.0系统，是个特例，通知设置界面只能控制"允许使用通知圆点"——然而这个玩意并没有卵用，我想对雷布斯说：I'm not ok!!!
            //  if ("MI 6".equals(Build.MODEL)) {
            //      intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            //      Uri uri = Uri.fromParts("package", getPackageName(), null);
            //      intent.setData(uri);
            //      // intent.setAction("com.android.settings/.SubSettings");
            //  }
            activity.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            // 出现异常则跳转到应用设置界面：锤子坚果3——OC105 API25
            val intent = Intent()
            //下面这种方案是直接跳转到当前应用的设置界面。
            //https://blog.csdn.net/ysy950803/article/details/71910806
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", activity.packageName, null)
            intent.data = uri
            activity.startActivity(intent)
        }
    }

    interface PermissionCallbacks {
        fun onPermissionsGranted()
        fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>)
    }
}