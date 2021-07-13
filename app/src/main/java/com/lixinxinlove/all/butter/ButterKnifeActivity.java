package com.lixinxinlove.all.butter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.lixinxinlove.all.R;

/**
 * 面试题
 */
public class ButterKnifeActivity extends AppCompatActivity {

    @BindView(R.id.tv1)
    private TextView tv1;
    @BindView(R.id.tv2)
    private TextView tv2;

<<<<<<< HEAD


    //synchronized

=======
>>>>>>> d2fdb6ac5f5d05af3a57ffae52cef173ae8179a2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        ButterKnife.bindView(this);


        tv1.setText("tv1");
        tv1.setTextSize(50);

        tv2.setText("tv2");
        tv2.setTextSize(100);

    }
}