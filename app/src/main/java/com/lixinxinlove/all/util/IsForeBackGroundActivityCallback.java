package com.lixinxinlove.all.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class IsForeBackGroundActivityCallback implements Application.ActivityLifecycleCallbacks {

    private int foregroundActivities = 0;
    private boolean isbackGroudAcitvity;

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
//        if (bundle != null) { // 若bundle不为空则程序异常结束
//            // 重启整个程序
//            Intent intent = new Intent(activity, SplashActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        Log.e("栈顶的activity======", activity.getLocalClassName());
        foregroundActivities++;
        if (foregroundActivities == 1) {
            // 应用切到前台
            Log.e("Callback", "应用切换到前台");
            isbackGroudAcitvity = false;
        }
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        foregroundActivities--;
        if (foregroundActivities == 0) {
            // 应用切到后台
            Log.e("Callback", "应用切换到后台");
            isbackGroudAcitvity = activity.isChangingConfigurations();
        }
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
