package com.odin.link.demo;

import android.app.Application;

import com.odin.link.OdinLink;

import cn.odinshare.core.OdinShareSDK;

public class OdinLinkApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化OdinLinkSDK，填入申请的奥丁数据产品的AppKey和AppSecret
        OdinLink.init(this, "985459861c2c4e7b8f4f2c7245e56448", "b250f2ba017048e88275154a62ed1bce");
        OdinLink.setLogEnabled(true);

        //初始化OdinShareSDK，用于分享APP对应的H5页面到各个社交平台
        OdinShareSDK.init(this);
    }
}
