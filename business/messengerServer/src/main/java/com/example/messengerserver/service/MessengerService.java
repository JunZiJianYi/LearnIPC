package com.example.messengerserver.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author 孙伟
 * @description: 服务端的Service
 * @date :2020/10/17 15:04
 */
public class MessengerService extends Service {

    private static final String TAG = "服务端";

    private static class MessengerHandler extends Handler {

       public void handleMessage(Message msg){
           switch(msg.what){
               case 0:
                   Log.e(TAG, "receive msg from Client:" + msg.getData().getString("msg") );
                   break;
               default:
                   super.handleMessage(msg);
           }
       }

    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
