package store.yifan.cn.appstore;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends BaseActivity {

    private ActionBar mActionBar;

    @Override
    public void initActionbar() {
        mActionBar=getSupportActionBar();
    }

    @Override
    public void initData() {

    }

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
    }
}
