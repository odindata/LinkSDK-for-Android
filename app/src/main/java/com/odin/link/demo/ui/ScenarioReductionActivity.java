package com.odin.link.demo.ui;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.odin.link.demo.BaseSchemeActivity;
import com.odin.link.demo.MainActivity;
import com.odin.link.demo.R;
import com.odin.link.demo.adapter.RestoreSceneAdapter;
import com.odin.link.demo.data.RestoreBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 场景还原
 */
public class ScenarioReductionActivity extends BaseSchemeActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_scenario_reduction;
    }

    @Override
    public String getTitleText() {
        return getString(R.string.str_scenario_reduction);
    }

    @Override
    public void onClickHeaderBack() {
        startToActivity(MainActivity.class);
        finish();
    }

    @Override
    public void initView() {
        super.initView();
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 1;
                outRect.bottom = 1;
            }
        });

        List<RestoreBean> restoreBeanList = new ArrayList<>();

        RestoreBean restoreBean1 = new RestoreBean();
        restoreBean1.setTitle("尽己所能、团结合作 四个关键词读懂习近平最新讲话");
        restoreBean1.setCount("置顶 专题 1183人浏览");
        restoreBean1.setUrl("file:///android_asset/news_detail.html");
        restoreBean1.setIcon(R.mipmap.new_1);
        restoreBean1.setContent(getString(R.string.str_news_content1));
        restoreBeanList.add(restoreBean1);

        RestoreBean restoreBean2 = new RestoreBean();
        restoreBean2.setTitle("中共中央、国务院：探索农村宅基地“三权分置”！究竟怎么改革？快来看");
        restoreBean2.setCount("1142人浏览");
        restoreBean2.setUrl("file:///android_asset/news_detail2.html");
        restoreBean2.setIcon(R.mipmap.new_2);
        restoreBean2.setContent(getString(R.string.str_news_content2));
        restoreBeanList.add(restoreBean2);

        mRecyclerView.setAdapter(new RestoreSceneAdapter(restoreBeanList));
    }
}