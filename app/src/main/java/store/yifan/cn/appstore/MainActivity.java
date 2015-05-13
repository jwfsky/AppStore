package store.yifan.cn.appstore;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;


import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import store.yifan.cn.appstore.fragment.BaseFragment;
import store.yifan.cn.appstore.fragment.FragmentFactory;
import store.yifan.cn.appstore.ui.anim.DepthPageTransformer;
import store.yifan.cn.appstore.ui.anim.RotateDownPageTransformer;
import store.yifan.cn.appstore.ui.anim.ZoomOutPageTransformer;
import store.yifan.cn.appstore.ui.widget.PagerTab;
import store.yifan.cn.appstore.utils.UIUtils;


public class MainActivity extends BaseActivity {


    @ViewInject(R.id.drawer_layout)
    private DrawerLayout drawer_layout;
    @ViewInject(R.id.tabs)
    private PagerTab mTabs;
    @ViewInject(R.id.pager)
    private ViewPager mPager;
    private MainPageAdapter mainPageAdapter;

    private ActionBar mActionBar;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initActionbar() {
        mActionBar = getSupportActionBar();
        mActionBar.setTitle(getString(R.string.app_name));
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);

        toggle = new ActionBarDrawerToggle(this, drawer_layout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close);
        toggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void initData() {

    }

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        drawer_layout.setDrawerListener(new DemoDrawerListener());
        drawer_layout.setDrawerShadow(R.drawable.ic_drawer_shadow, GravityCompat.START);

        mainPageAdapter=new MainPageAdapter(getSupportFragmentManager());
        mPager.setAdapter(mainPageAdapter);
//        mPager.setPageTransformer(false,new ZoomOutPageTransformer());

        mTabs.setViewPager(mPager);
        mTabs.setOnPageChangeListener(new MyOnPageChangeListener());

    }

    /**
     * drawer的监听
     */
    private class DemoDrawerListener implements DrawerLayout.DrawerListener {
        @Override
        public void onDrawerOpened(View drawerView) {// 打开drawer
            toggle.onDrawerOpened(drawerView);//需要把开关也变为打开

            /*UIUtils.showToastSafe("我测试一下,打开了");
            mActionBar.setTitle("打开了");
            invalidateOptionsMenu();*/
        }

        @Override
        public void onDrawerClosed(View drawerView) {// 关闭drawer
            toggle.onDrawerClosed(drawerView);//需要把开关也变为关闭
            /*UIUtils.showToastSafe("我测试一下,关闭了");
            mActionBar.setTitle("关闭了");
            invalidateOptionsMenu();*/
        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {// drawer滑动的回调
            toggle.onDrawerSlide(drawerView, slideOffset);
        }

        @Override
        public void onDrawerStateChanged(int newState) {// drawer状态改变的回调
            toggle.onDrawerStateChanged(newState);
        }
    }

    class MainPageAdapter extends FragmentPagerAdapter{

        private String[] mTabTitle;
        public MainPageAdapter(FragmentManager fm) {
            super(fm);
            mTabTitle=UIUtils.getStringArray(R.array.tab_names);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitle[position];
        }

        @Override
        public Fragment getItem(int i) {
            return FragmentFactory.createFragment(i);
        }

        @Override
        public int getCount() {
            return mTabTitle.length;
        }
    }
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int i, float v, int i2) {

        }

        @Override
        public void onPageSelected(int i) {
            BaseFragment fragment=FragmentFactory.createFragment(i);
            fragment.show();

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }
}
