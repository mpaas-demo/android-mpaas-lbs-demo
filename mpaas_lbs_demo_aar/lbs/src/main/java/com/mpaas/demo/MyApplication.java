package com.mpaas.demo;

import android.app.Application;
import android.content.Context;
//import android.support.multidex.MultiDex;

import com.alipay.mobile.framework.quinoxless.IInitCallback;
import com.alipay.mobile.framework.quinoxless.QuinoxlessFramework;


public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        QuinoxlessFramework.setup(this, new IInitCallback() {
            @Override
            public void onPostInit() {

            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        MultiDex.install(this);
        QuinoxlessFramework.init();
        CaseApi.healthCase(getApplicationContext());

    }
}
