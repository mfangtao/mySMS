package com.mysms.fangtao.mysms;

import java.util.List;

/**
 * Created by fangtao on 2017/5/11.
 */

public class PublicMethod {
    /**
     * 直接调用短信接口发短信
     * @param phoneNumber
     * @param message
     */
    public static void sendSMS(String phoneNumber, String message){
//        PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
        //获取短信管理器
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
        //拆分短信内容（手机短信长度限制）
        List<String> divideContents = smsManager.divideMessage(message);
        for (String text : divideContents) {
            smsManager.sendTextMessage(phoneNumber, null, text, null, null);
        }
    }
}
