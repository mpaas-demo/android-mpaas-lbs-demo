package com.mpaas.demo.lbs;

import android.widget.Toast;

import com.alipay.mobile.common.lbs.LBSLocation;
import com.alipay.mobile.common.lbs.LBSLocationListener;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.mpaas.demo.R;

import java.lang.ref.WeakReference;

public class LocationListener implements LBSLocationListener {

    private static final String TAG = "LocationListener";

    private WeakReference<LbsActivity> mContext;

    public LocationListener(LbsActivity context) {
        mContext = new WeakReference<>(context);
    }

    /**
     * 定位成功回调
     *
     * @param lbsLocation 定位信息
     */
    @Override
    public void onLocationUpdate(final LBSLocation lbsLocation) {
        LoggerFactory.getTraceLogger().debug(TAG, "onLocationUpdate: " + lbsLocation.toString());
        final LbsActivity activity = mContext.get();
        if (null != activity) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    activity.showLocation(lbsLocation);
                }
            });
        }
    }

    /**
     * 定位失败回调
     *
     * @param errorCode 异常信息
     */
    @Override
    public void onLocationFailed(final int errorCode) {
        LoggerFactory.getTraceLogger().debug(TAG, "onLocationFailed: " + errorCode);
        final LbsActivity activity = mContext.get();
        if (null != activity) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, activity.getString(R.string.lbs_error) + "\nErrorCode = " + errorCode, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
