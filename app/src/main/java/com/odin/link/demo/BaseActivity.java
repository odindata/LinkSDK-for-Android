package com.odin.link.demo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;

public abstract class BaseActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private static final String TAG = "BaseActivity";

    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        ImmersionBar.with(this).barColor(R.color.colorWhite).statusBarDarkFont(true).transparentNavigationBar().init();
        Log.i(TAG, "onCreate: " + this.getClass().getName());
    }

    public abstract void initView();

    public void startToActivity(Class<? extends BaseActivity> toActivity) {
        startActivity(new Intent(this, toActivity));
    }

    public void checkPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int result = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (result != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
