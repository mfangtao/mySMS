package com.mysms.fangtao.mysms;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by fangtao on 2017/5/11.
 */

public class MyService extends Service {

    //监听时间变化的 这个receiver只能动态创建
    private TimeTickReceiver mTickReceiver;
    private IntentFilter mFilter;
    public static final String ACTION = "com.mysms.fangtao.mysms";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onReceive: 服务启动2");
        mFilter = new IntentFilter();
        mFilter.addAction(Intent.ACTION_TIME_TICK); //每分钟变化的action
        mFilter.addAction(Intent.ACTION_TIME_CHANGED); //设置了系统时间的action
        mTickReceiver = new TimeTickReceiver();
        registerReceiver(mTickReceiver, mFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("启动了服务");
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        startService(new Intent(getApplicationContext(),MyService.class));
        mFilter = new IntentFilter();
        mFilter.addAction(Intent.ACTION_TIME_TICK); //每分钟变化的action
        mFilter.addAction(Intent.ACTION_TIME_CHANGED); //设置了系统时间的action
        mTickReceiver = new TimeTickReceiver();
        registerReceiver(mTickReceiver, mFilter);
        super.onDestroy();

    }
}
