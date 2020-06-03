package com.odin.link.demo;

import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.odin.link.demo.ui.OneKeyWakeupActivity;
import com.odin.link.demo.ui.ScenarioReductionActivity;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ImmersionBar.with(this).barColor(R.color.colorWhite).statusBarDarkFont(true).init();
        checkPermission();
    }

    public void oneKeyWakeup(View view) {
        startToActivity(OneKeyWakeupActivity.class);
    }

    public void scenarioReduction(View view) {
        startToActivity(ScenarioReductionActivity.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
