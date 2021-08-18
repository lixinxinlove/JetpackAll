package com.lixinxinlove.all.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.lixinxinlove.all.R;
import com.lixinxinlove.all.adapter.LeeProvider;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import java.util.List;

import xyz.mylib.creator.handler.CreatorExecuteResponseHander;
import xyz.mylib.creator.task.AvcExecuteAsyncTask;

public class ImageToVideo2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_to_video2);


        PermissionX.init(this)
                .permissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                        if (allGranted) {
                            mTov();
                        } else {

                        }
                    }
                });
    }


    void mTov() {

        String name = System.currentTimeMillis() + "test.mp4";
        // String dpath = getFileStreamPath(name).getPath();


        String dpath = Environment.getExternalStorageDirectory() + "/" + name;

        Log.e("ImageToVideo2Activity", "dpath=" + dpath);


        LeeProvider provider = new LeeProvider(this);

        AvcExecuteAsyncTask.execute(provider, 16, new CreatorExecuteResponseHander<String>() {
            @Override
            public void onSuccess(String message) {
                Log.e("ImageToVideo2Activity", "onSuccess");
            }

            @Override
            public void onProgress(String message) {
                Log.e("ImageToVideo2Activity", "onSuccess" + message);
            }

            @Override
            public void onFailure(String message) {
                Log.e("ImageToVideo2Activity", "onFailure");
            }

            @Override
            public void onStart() {
                Log.e("ImageToVideo2Activity", "onStart");
            }

            @Override
            public void onFinish() {
                Log.e("ImageToVideo2Activity", "onFinish");
            }
        }, dpath);
    }
}