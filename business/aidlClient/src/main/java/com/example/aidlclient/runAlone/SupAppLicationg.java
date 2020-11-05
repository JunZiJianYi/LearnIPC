package com.example.aidlclient.runAlone;

import com.eca.common.base.mvp.BaseApplication;


public class SupAppLicationg extends BaseApplication {

    private static SupAppLicationg supAppLicationg;

    //初始化
    private static SupAppLicationg getInstance(){
        return supAppLicationg;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        supAppLicationg = this ;
    }

}
