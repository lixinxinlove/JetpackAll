package com.lixinxinlove.all.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.lixinxinlove.all.R;

import xyz.mylib.creator.task.AvcExecuteAsyncTask;

public class ImageToVideo2Activity extends AppCompatActivity {


    private final Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_to_video2);

        String name =  System.currentTimeMillis() + "test.mp4";
        String dpath = getFileStreamPath(name).getPath();


        AvcExecuteAsyncTask.execute(provider, 16, mHandler, dpath);

    }
}