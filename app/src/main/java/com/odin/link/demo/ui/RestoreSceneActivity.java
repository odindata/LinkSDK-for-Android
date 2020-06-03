package com.odin.link.demo.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.odin.link.OdinLink;
import com.odin.link.demo.BaseSchemeActivity;
import com.odin.link.demo.R;
import com.odin.link.demo.utils.ShareUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * 被还原的场景的Activity
 */
public class RestoreSceneActivity extends BaseSchemeActivity {

    private TextView mTvTitle;
    private TextView mTvTime;
    private TextView mTvContent;
    private boolean isFirstDemo = true;

    private static final String EXTRA_TITLE = "EXTRA_TITLE";
    private static final String EXTRA_CONTENT = "EXTRA_CONTENT";
    private static final String EXTRA_IS_FIRST_DEMO = "EXTRA_IS_FIRST_DEMO";

    private static final String LINK_KEY_DATE = "date";
    private static final String LINK_KEY_PAGE = "page";

    private static final String LINK_VALUE_DATE_DEMO1 = "demo1";
    private static final String LINK_VALUE_PAGE_DEMO1 = "detail1";

    private static final String LINK_VALUE_DATE_DEMO2 = "demo2";
    private static final String LINK_VALUE_PAGE_DEMO2 = "detail2";

    public static void newInstance(Context context, String title, String content, boolean isFirstDemo) {
        Intent intent = new Intent(context, RestoreSceneActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_CONTENT, content);
        intent.putExtra(EXTRA_IS_FIRST_DEMO, isFirstDemo);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_restore_scene;
    }

    @Override
    public String getTitleText() {
        return getString(R.string.str_restore_scene);
    }

    @Override
    public void onClickHeaderBack() {
        startToActivity(ScenarioReductionActivity.class);
        finish();
    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle = findViewById(R.id.tv_restore_title);
        mTvTime = findViewById(R.id.tv_restore_time);
        mTvContent = findViewById(R.id.tv_restore_content);

        Intent intent = getIntent();
        String title = intent.getStringExtra(EXTRA_TITLE);
        String content = intent.getStringExtra(EXTRA_CONTENT);
        isFirstDemo = intent.getBooleanExtra(EXTRA_IS_FIRST_DEMO, true);
        mTvTitle.setText(title == null ? "" : title);
        mTvContent.setText(content == null ? "" : content);
        mTvTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));

        openByLink(intent);
        ImageView mImgShare = findViewById(R.id.img_header_restore_share);
        mImgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareUrl = getString(R.string.str_share_url_news_detail);
                if (!isFirstDemo) {
                    shareUrl = getString(R.string.str_share_url_news_detail2);
                }
                ShareUtils.getInstance(RestoreSceneActivity.this).oneKeyShare(shareUrl);
            }
        });
    }

    /**
     * 防止APP在后台，然后点击link跳转
     *
     * @param intent intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        openByLink(intent);
    }

    private void openByLink(Intent intent) {
        if (intent.getData() == null) {
            return;
        }
        //处理点击link跳转后打开APP携带的数据
        HashMap<String, Object> linkParams = OdinLink.getLinkParamsByUri(intent.getData());
        //然后根据key来获取对应的数据，还原相应的场景
        Object objDate = linkParams.get(LINK_KEY_DATE);
        Object objPage = linkParams.get(LINK_KEY_PAGE);
        String date = objDate == null ? "" : objDate.toString();
        String page = objPage == null ? "" : objPage.toString();
        if (LINK_VALUE_DATE_DEMO1.equals(date) && LINK_VALUE_PAGE_DEMO1.equals(page)) {
            isFirstDemo = true;
            mTvTitle.setText(getString(R.string.str_news_title1));
            mTvContent.setText(getString(R.string.str_news_content1));
            mTvTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
        } else if (LINK_VALUE_DATE_DEMO2.equals(date) && LINK_VALUE_PAGE_DEMO2.equals(page)) {
            isFirstDemo = false;
            mTvTitle.setText(getString(R.string.str_news_title2));
            mTvContent.setText(getString(R.string.str_news_content2));
        }
    }
}