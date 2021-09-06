package com.lixinxinlove.all.activity;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lixinxinlove.all.R;
import com.lixinxinlove.all.base.BaseActivity;
import com.lixinxinlove.all.util.MoneyUtil;

import java.util.Locale;

public class TTSActivity extends BaseActivity implements OnInitListener {


    //定义控件
    private Button speechButton;
    private TextView speechText;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttsactivity);
        //初始化TTS
        tts = new TextToSpeech(this, this);
        //获取控件
        speechText = (TextView) findViewById(R.id.speechTextView);
        speechButton = (Button) findViewById(R.id.speechButton);
        //为button添加监听
        speechButton.setOnClickListener(v -> {


            String chineseMoney = MoneyUtil.toChinese("98.45");
            speechText.setText(chineseMoney);
            String order = "收到" + chineseMoney;

            tts.speak(order, TextToSpeech.QUEUE_FLUSH, null);
        });

    }

    @Override
    public void onInit(int status) {
        // 判断是否转化成功
        if (status == TextToSpeech.SUCCESS) {
            //默认设定语言为中文，原生的android貌似不支持中文。
            int result = tts.setLanguage(Locale.CHINESE);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(TTSActivity.this, "Language is not available", Toast.LENGTH_SHORT).show();
            } else {
                //不支持中文就将语言设置为英文
                tts.setLanguage(Locale.US);
            }
        }
    }
}