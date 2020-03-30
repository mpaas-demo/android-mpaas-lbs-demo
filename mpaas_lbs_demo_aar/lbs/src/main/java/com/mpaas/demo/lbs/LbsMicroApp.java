package com.mpaas.demo.lbs;

import android.os.Bundle;

import com.alipay.mobile.framework.app.ActivityApplication;

public class LbsMicroApp extends ActivityApplication {

    private Bundle mParam;

    @Override
    public String getEntryClassName() {
        return null;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        mParam = bundle;
    }

    @Override
    protected void onStart() {
        doStart(mParam);
    }

    @Override
    protected void onRestart(Bundle bundle) {
        mParam = bundle;
        doStart(mParam);
    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onDestroy(Bundle bundle) {

    }

    private void doStart(Bundle bundle) {
        String className = LbsActivity.class.getName();
        getMicroApplicationContext().startActivity(this, className);
    }
}
