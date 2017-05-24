package com.cnpc.hyxt.zys.simpleview;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private List<String> imgUrls;
    private Banner mBanner;
    private DachshundTabLayout dachshundTabLayout;
    private ViewPager mViewPager;
    private String[] titles;
    private CommonViewPagerAdapter infoPagerAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //设置推移过程中 状态栏颜色的变化
        StatusBarUtil.setTranslucent(this);
        init();
    }

    private void init() {
        initData();
        initWidget();
        initView();
    }

    private void initView() {
        //初始化ToolBar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //如果系统版本大于4.4
            ViewGroup.LayoutParams layoutParams = mToolbar.getLayoutParams();
            layoutParams.height = ScreenUtil.dip2px(this, 80);
            mToolbar.setLayoutParams(layoutParams);
        }

    }

    private void initWidget() {
        //轮播图控件
        mBanner = (Banner) findViewById(R.id.main_banner);
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.isAutoPlay(true);
        mBanner.setImages(imgUrls).setImageLoader(new GlideImageLoader()).start();

        mViewPager = (ViewPager) findViewById(R.id.main_vp);
        mViewPager.setAdapter(infoPagerAdapter);
        mViewPager.setCurrentItem(1);
        mViewPager.setOffscreenPageLimit(6);

        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);

        //侧滑菜单 切换界面
        dachshundTabLayout = (DachshundTabLayout) findViewById(R.id.main_tab);
        dachshundTabLayout.setupWithViewPager(mViewPager);
    }

    private void initData() {
        //创建集合 保存轮播图的地址
        imgUrls = new ArrayList<>();
        imgUrls.add(Constact.BANNER_IMG_01);
        imgUrls.add(Constact.BANNER_IMG_02);
        imgUrls.add(Constact.BANNER_IMG_03);
        imgUrls.add(Constact.BANNER_IMG_04);
        imgUrls.add(Constact.BANNER_IMG_05);
        //tab主题
                titles = new String[]{
                "APP",
                "Android",
                "IOS",
                "Java",
                "PHP",
                "JavaEE"};
        List<String> itemList = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            itemList.add("我是数据" + i);
        }
        infoPagerAdapter = new CommonViewPagerAdapter(getSupportFragmentManager(), titles);
        // App
        CategoryFragment appFragment = new CategoryFragment(this, itemList);
        // Android
        CategoryFragment androidFragment = new CategoryFragment(this, itemList);
        // iOS
        CategoryFragment iOSFragment = new CategoryFragment(this, itemList);
        // 前端
        CategoryFragment frontFragment = new CategoryFragment(this, itemList);
        // 瞎推荐
        CategoryFragment referenceFragment = new CategoryFragment(this, itemList);
        // 拓展资源s
        CategoryFragment resFragment = new CategoryFragment(this, itemList);

        infoPagerAdapter.addFragment(appFragment);
        infoPagerAdapter.addFragment(androidFragment);
        infoPagerAdapter.addFragment(iOSFragment);
        infoPagerAdapter.addFragment(frontFragment);
        infoPagerAdapter.addFragment(referenceFragment);
        infoPagerAdapter.addFragment(resFragment);

    }

}
