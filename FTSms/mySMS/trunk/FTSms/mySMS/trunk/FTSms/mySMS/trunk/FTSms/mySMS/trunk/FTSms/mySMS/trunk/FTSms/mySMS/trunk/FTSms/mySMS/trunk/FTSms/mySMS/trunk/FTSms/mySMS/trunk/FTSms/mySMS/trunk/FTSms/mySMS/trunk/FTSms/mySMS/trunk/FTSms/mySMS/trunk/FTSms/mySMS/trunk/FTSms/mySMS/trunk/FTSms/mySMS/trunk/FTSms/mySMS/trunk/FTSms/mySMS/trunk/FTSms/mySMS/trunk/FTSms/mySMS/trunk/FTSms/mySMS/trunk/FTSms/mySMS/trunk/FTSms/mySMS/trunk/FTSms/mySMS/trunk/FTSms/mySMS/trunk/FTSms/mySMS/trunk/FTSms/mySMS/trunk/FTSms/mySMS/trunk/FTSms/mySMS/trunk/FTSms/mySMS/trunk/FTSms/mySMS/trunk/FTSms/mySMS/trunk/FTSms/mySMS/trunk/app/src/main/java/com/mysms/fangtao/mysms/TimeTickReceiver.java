package com.mysms.fangtao.mysms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by fangtao on 2017/5/11.
 */

public class TimeTickReceiver extends BroadcastReceiver {
    private boolean flag;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
            //每过一分钟 触发
            Log.i("ft", "onReceive: 11");
            context.startService(new Intent(context,MyService.class));
            Intent in = new Intent("com.mysms.fangtao.mysms");
            context.sendBroadcast(in);



    } else

        {
            /*
             * 系统bug??
             * android.intent.action.TIME_SET  当调整系统时间后 这个action会收到两次
             */
            if (flag) {
                try {
                    /*  do some thing */
                } catch (Exception e) {
                    e.printStackTrace();
                }
                flag = false; //第二次置false
            } else {
                flag = true; //第一次置true
            }
        }


    }
}
