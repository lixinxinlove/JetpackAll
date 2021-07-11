package com.lixinxinlove.all.butter;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * author ： lixinxin
 * time    ： 2021/7/10
 * email：895330766@qq.com
 */
public class ButterKnife {


    public static void bindView(Activity activity) {
        bindView(activity, activity.getWindow().getDecorView());
    }

    public static void bindView(Object object, View view) {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field f : fields) {
            BindView bindView = f.getAnnotation(BindView.class);
            if (bindView != null) {
                f.setAccessible(true);
                try {
                    f.set(object, view.findViewById(bindView.value()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
