package store.yifan.cn.appstore;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Author: Jwf(feijia101@gmail.com) <br\>
 * Date: 2015-04-14 14:38<br\>
 * Version: 1.0<br\>
 * Desc:<br\>
 * Revise:<br\>
 */
public abstract class BaseActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initData();
        initActionbar();
    }

    public abstract void initActionbar() ;

    public abstract void initData() ;

    public abstract void init() ;
}
