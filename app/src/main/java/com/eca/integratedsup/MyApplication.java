package com.eca.integratedsup;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.eca.common.base.mvp.BaseApplication;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;

import org.android.agoo.huawei.HuaWeiRegister;
import org.android.agoo.mezu.MeizuRegister;
import org.android.agoo.xiaomi.MiPushRegistar;

/**
 * @Created by TOME .
 * @时间 2018/5/14 17:40
 * @描述 ${应用的application}
 */

public class MyApplication extends BaseApplication {

    private static MyApplication myApplication;

    //初始化
    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this ;

        //推送
        push();

    }

    //推送平台
    private void push() {
        UMConfigure.init(this, "5c10866ff1f556f958000202", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "79d3f3adbd13c998bc5b7da914033d31");
        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {

                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                Log.e("测试","注册成功：deviceToken：-------->  " + deviceToken);
            }
            @Override
            public void onFailure(String s, String s1) {

                Log.e("测试","注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });
        //该方法是【友盟+】Push后台进行日活统计及多维度推送的必调用方法，请务必调用！
        PushAgent.getInstance(myApplication).onAppStart();


        //华为厂商
        HuaWeiRegister.register(myApplication);
        //小米
        MiPushRegistar.register(myApplication, "2882303761518246249", "5601824637249");
        //魅族
        MeizuRegister.register(myApplication, "125696", "544ce3849267430ab1fb7694c12380fa");

        //自定义消息
        UmengMessageHandler messageHandler = new UmengMessageHandler(){
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        // 对于自定义消息，PushSDK默认只统计送达。若开发者需要统计点击和忽略，则需手动调用统计方法。
                        boolean isClickOrDismissed = true;
                        if(isClickOrDismissed) {
                            //自定义消息的点击统计
                            UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
                        } else {
                            //自定义消息的忽略统计
                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
                        }
                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                    }
                });
            }
        };
        mPushAgent.setMessageHandler(messageHandler);

    }

}
