package store.yifan.cn.appstore;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Author: Jwf(feijia101@gmail.com) <br\>
 * Date: 2015-04-14 14:38<br\>
 * Version: 1.0<br\>
 * Desc:activity的基础类<br\>
 * Revise:<br\>
 */
public abstract class BaseActivity extends ActionBarActivity {
    //获取到前台的Activity
    public static BaseActivity getForegroundActivity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initData();
        initActionbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getForegroundActivity=this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        getForegroundActivity=null;
    }

    public static BaseActivity getForegroundActivity() {
        return getForegroundActivity;
    }

    public abstract void initActionbar();

    public abstract void initData();

    public abstract void init();
}
