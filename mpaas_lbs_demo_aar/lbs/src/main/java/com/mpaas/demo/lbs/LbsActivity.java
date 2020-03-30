package com.mpaas.demo.lbs;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.alipay.mobile.common.lbs.LBSLocation;
import com.alipay.mobile.common.lbs.LBSLocationManagerProxy;
import com.mpaas.demo.CaseApi;
import com.mpaas.demo.R;

import java.util.concurrent.TimeUnit;

public class LbsActivity extends Activity {

    private static final int PERMISSION_REQUEST_CODE = 0X01;
    private static final long DEFAULT_LOCATION_INTERVAL = TimeUnit.SECONDS.toMillis(30L);
    private static final long LOCATION_TIME_OUT = TimeUnit.SECONDS.toMillis(31L);
    private Button mRequestLbsBtn;
    private LocationListener mLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lbs);
        checkPermission();
        mLocationListener = new LocationListener(this);
        findView();
        initView();
    }

    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_CODE);
        }
    }

    private void findView() {
        mRequestLbsBtn = (Button) findViewById(R.id.lbs_request_lbs_btn);
        findViewById(R.id.btn_go_test_case).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoTest();
            }
        });
    }

    private void onClickGoTest() {
        CaseApi.goTest(getBaseContext());
    }

    private void initView() {
        setTitle(getString(R.string.lbs));
        mRequestLbsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LBSLocationManagerProxy.getInstance().doRequestLocationUpdates(
                        // context上下文
                        LbsActivity.this,
                        // 是否需要gps
                        false,
                        // 定位回调
                        mLocationListener,
                        // 定位时间间隔
                        DEFAULT_LOCATION_INTERVAL,
                        // 定位超时时间
                        LOCATION_TIME_OUT,
                        // 是否需要逆地理
                        true,
                        // biz标识
                        "test",
                        // 是否需要速度
                        false,
                        // 是否是H5
                        "F");
            }
        });
    }

    public void showLocation(LBSLocation location) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.lbs_success)
                .setMessage(getLocationString(location))
                .create()
                .show();
    }

    /**
     * 格式化定位信息
     * @param location 定位信息
     * @return
     */
    private String getLocationString(LBSLocation location) {
        StringBuilder sb = new StringBuilder();
        // 获取经度
        sb.append(getString(R.string.longitude));
        sb.append(location.getLongitude());
        sb.append("\n");
        // 获取纬度
        sb.append(getString(R.string.latitude));
        sb.append(location.getLatitude());
        sb.append("\n");
        // 获取城市编码
        sb.append(getString(R.string.ad_code));
        sb.append(location.getAdCode());
        sb.append("\n");
        // 获取国家
        sb.append(getString(R.string.country));
        sb.append(location.getCountry());
        sb.append("\n");
        // 获取省
        sb.append(getString(R.string.province));
        sb.append(location.getProvince());
        sb.append("\n");
        // 获取市
        sb.append(getString(R.string.city));
        sb.append(location.getCity());
        sb.append("\n");
        // 获取区
        sb.append(getString(R.string.district));
        sb.append(location.getDistrict());
        sb.append("\n");
        // 获取街道
        sb.append(getString(R.string.street));
        sb.append(location.getStreet());
        sb.append("\n");
        // 获取地址
        sb.append(getString(R.string.address));
        sb.append(location.getAddress());
        return sb.toString();
    }
}
