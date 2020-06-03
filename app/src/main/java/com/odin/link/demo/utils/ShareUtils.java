package com.odin.link.demo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.odin.link.demo.R;

import cn.odinshare.odinonekeyshare.OneKeyShare;

public class ShareUtils {

    private static Context mContext;
    private static ShareUtils instance;

    private ShareUtils(Context context) {
        mContext = context;
    }

    public static ShareUtils getInstance(Context context) {
        if (instance == null) {
            instance = new ShareUtils(context);
        }
        return instance;
    }

    public void oneKeyShare(String url) {
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
        new OneKeyShare().setTitle(mContext.getString(R.string.app_name))
                .setText(mContext.getString(R.string.str_share_text))
                .setUrl(url)
                .setImageData(bitmap)
                .show(mContext);
    }
}
