package com.example.messenger.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.example.messenger.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "客户端";

    private Messenger mService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            mService = new Messenger(service);
            Message msg = Message.obtain(null,0);
            Bundle data = new Bundle();
            data.putString("msg", "你好，这是客户端");
            msg.setData(data);
            try{
                mService.send(msg);
            }catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent("com.ryg.MessengerService.launch");
        intent.setPackage("com.example.messengerserver");
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
    }

    protected void onDestroy(){
        unbindService(mConnection);
        super.onDestroy();
    }
}
