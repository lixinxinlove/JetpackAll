package com.lixinxinlove.all.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaRecorder;
import android.os.Bundle;

import com.lixinxinlove.all.R;

public class MediaRecorderActivity extends AppCompatActivity {

    private MediaRecorder mediarecorder;// 录制视频的类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_recorder);


        mediarecorder = new MediaRecorder();// 创建mediarecorder对象
        // 设置录制视频源为Camera(相机)
        mediarecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
        // 设置录制完成后视频的封装格式THREE_GPP为3gp.MPEG_4为mp4
        mediarecorder
                .setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        // 设置录制的视频编码h263 h264
        mediarecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        // 设置视频录制的分辨率。必须放在设置编码和格式的后面，否则报错
        mediarecorder.setVideoSize(176, 144);
        // 设置录制的视频帧率。必须放在设置编码和格式的后面，否则报错
        mediarecorder.setVideoFrameRate(20);
      //  mediarecorder.setPreviewDisplay(surfaceHolder.getSurface());
        // 设置视频文件输出的路径
        mediarecorder.setOutputFile("/sdcard/love.3gp");

    }
}