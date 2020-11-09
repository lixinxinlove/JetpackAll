package com.lixinxinlove.all.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 地图轨迹记录
 */
public class MapService extends Service {
    public MapService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}