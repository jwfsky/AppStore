package store.yifan.cn.appstore;


import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;


import store.yifan.cn.appstore.utils.UIUtils;


public class MainActivity extends BaseActivity {

    private ActionBar mActionBar;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer_layout;

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
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_layout.setDrawerListener(new DemoDrawerListener());
    }

    /** drawer的监听 */
    private class DemoDrawerListener implements DrawerLayout.DrawerListener {
        @Override
        public void onDrawerOpened(View drawerView) {// 打开drawer
            toggle.onDrawerOpened(drawerView);//需要把开关也变为打开

            UIUtils.showToastSafe("我测试一下,打开了");
            mActionBar.setTitle("打开了");
            invalidateOptionsMenu();
        }

        @Override
        public void onDrawerClosed(View drawerView) {// 关闭drawer
            toggle.onDrawerClosed(drawerView);//需要把开关也变为关闭
            UIUtils.showToastSafe("我测试一下,关闭了");
            mActionBar.setTitle("关闭了");
            invalidateOptionsMenu();
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

}
