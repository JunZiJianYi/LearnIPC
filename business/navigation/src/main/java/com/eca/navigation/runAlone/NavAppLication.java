package com.eca.navigation.runAlone;

import com.eca.common.base.mvp.BaseApplication;


public class NavAppLication extends BaseApplication {

    private static NavAppLication navAppLication;

    //初始化
    public static NavAppLication getInstance(){
        return navAppLication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        navAppLication = this ;
    }
}
