package com.example.aidlclient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aidlserver.IAdd;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNumber1, etNumber2;
    private Button btAdd;
    private TextView tvNumberShow;

    protected IAdd addService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber1 = findViewById(R.id.et_number1);
        etNumber2 = findViewById(R.id.et_number2);
        btAdd = findViewById(R.id.bt_add);
        tvNumberShow = findViewById(R.id.tv_number_show);

        btAdd.setOnClickListener(this);

    }

    private void initServer(){

        if(addService == null){

            Intent intent = new Intent(IAdd.class.getName());

            intent.setAction("service.calc");

            intent.setPackage("com.example.aidlserverfunction");

            bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            addService = IAdd.Stub.asInterface(iBinder);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            addService = null;

        }
    };


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.bt_add) {
            if (etNumber1.length() > 0 && etNumber2.length() > 0 && addService != null) {
                try {
                    tvNumberShow.setText("Add Number Show(两数之和展示)：" + addService.addNumbers(Integer.parseInt(etNumber1.getText().toString()), Integer.parseInt(etNumber2.getText().toString())));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    protected void onResume() {
        super.onResume();
        if(addService == null){
            initServer();
        }
    }


    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }


}
