package com.lixinxinlove.all.util;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author ： lixinxin
 * time    ： 2021/8/26
 * email：895330766@qq.com
 */
public class LottieUtils {


//    private void loadUrl(String url) {
//        Request request = new Request.Builder().url(url).build();
//        OkHttpClient client = new OkHttpClient();
//        client.newCall(request).enqueue(new Callback() {
//            @Override public void onFailure(Call call, IOException e) {}
//            @Override public void onResponse(Call call, Response response) throws IOException {
//                try {
//                    JSONObject json = new JSONObject(response.body().string());
//                    LottieComposition.Factory
//                            .fromJson(getResources(), json, new OnCompositionLoadedListener() {
//                                @Override
//                                public void onCompositionLoaded(LottieComposition composition) {
//                                    lottieAnimationView.setComposition(composition);
//                                    lottieAnimationView.playAnimation();
//                                }
//                            });
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }


}
