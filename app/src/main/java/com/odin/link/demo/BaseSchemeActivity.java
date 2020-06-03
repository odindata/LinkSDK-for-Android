package com.odin.link.demo;

import android.view.View;
import android.widget.TextView;

public abstract class BaseSchemeActivity extends BaseActivity {

    public abstract String getTitleText();

    public abstract void onClickHeaderBack();

    @Override
    public void initView() {
        TextView mTvTitle = findViewById(R.id.tv_header_title);
        String title = getTitleText();
        mTvTitle.setText(title == null ? "" : title);
        findViewById(R.id.img_header_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHeaderBack();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onClickHeaderBack();
    }
}
