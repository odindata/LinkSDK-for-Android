package com.odin.link.demo.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.odin.link.demo.BaseSchemeActivity;
import com.odin.link.demo.MainActivity;
import com.odin.link.demo.R;
import com.odin.link.demo.utils.ShareUtils;

/**
 * 一键唤醒
 */
public class OneKeyWakeupActivity extends BaseSchemeActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_one_key_wakeup;
    }

    @Override
    public String getTitleText() {
        return getString(R.string.str_one_key_wakeup);
    }

    @Override
    public void onClickHeaderBack() {
        startToActivity(MainActivity.class);
        finish();
    }

    @Override
    public void initView() {
        super.initView();
    }

    /**
     * 当App处于后台，该Activity启动模式为SingleTask或者SingleTop，通过Web端点击打开App时，可能会走onNewIntent方法。
     *
     * @param intent 意图
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    /**
     * 一键分享：分享一个链接，实现Web与App的无缝链接
     */
    public void immediateUse(View view) {
        ShareUtils.getInstance(this).oneKeyShare(getString(R.string.str_share_url));
    }
}
