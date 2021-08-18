package com.lixinxinlove.all.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lixinxinlove.all.R;

import java.util.ArrayList;
import java.util.List;

import xyz.mylib.creator.IProvider;

/**
 * author ： lixinxin
 * time    ： 2021/8/9
 * email：895330766@qq.com
 */
public class LeeProvider implements IProvider<Bitmap> {

    private Context mContext;
    private List<Bitmap> bitmapList;

    public LeeProvider(Context context) {
        mContext = context;
        bitmapList = new ArrayList<>();
        Bitmap bitmap0 = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.time);
        Bitmap bitmap1 = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
        Bitmap bitmap2 = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_cat);
        Bitmap bitmap3 = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.mm);

        bitmapList.add(bitmap0);
        bitmapList.add(bitmap1);
        bitmapList.add(bitmap2);
        bitmapList.add(bitmap3);
        bitmapList.add(bitmap0);
        bitmapList.add(bitmap1);
        bitmapList.add(bitmap2);
        bitmapList.add(bitmap3);
        bitmapList.add(bitmap0);
        bitmapList.add(bitmap1);
        bitmapList.add(bitmap2);
        bitmapList.add(bitmap3);
        bitmapList.add(bitmap0);
        bitmapList.add(bitmap1);
        bitmapList.add(bitmap2);
        bitmapList.add(bitmap3);
    }


    private int index = 0;

    @Override
    public boolean hasNext() {
        return index < bitmapList.size();
    }

    @Override
    public int size() {
        return bitmapList.size();
    }

    @Override
    public Bitmap next() {
        return bitmapList.get(index);
    }
}
