package store.yifan.cn.basework;

import android.os.*;
import android.support.v7.app.ActionBarActivity;

import com.lidroid.xutils.ViewUtils;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;

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
    private static final List<BaseActivity> activities = new LinkedList<BaseActivity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getForegroundActivity = this;
        init();//初始化view
        initData();
        initActionbar();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!activities.contains(getForegroundActivity)) {
            activities.add(getForegroundActivity);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        getForegroundActivity = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (activities.contains(getForegroundActivity)) {
            activities.remove(getForegroundActivity);
        }
    }

    public static BaseActivity getForegroundActivity() {
        return getForegroundActivity;
    }

    public void initActionbar() {
    }

    ;

    public void initData() {
    }

    ;

    public void init() {
    }

    ;

    /**
     * 清除所有的存在的activity
     */
    public static void clearAll() {
        LinkedList<BaseActivity> copy;
        synchronized (activities) {
            copy = new LinkedList<BaseActivity>(activities);
        }
        for (BaseActivity ba : copy) {
            ba.finish();
        }
    }

    /**
     * 清除所有的存在的activity ,除了一个被指定的activy外
     */
    public static void clearAll(BaseActivity exc) {
        LinkedList<BaseActivity> copy;
        synchronized (activities) {
            copy = new LinkedList<BaseActivity>(activities);
        }
        for (BaseActivity ba : copy) {
            if (ba != exc) ba.finish();

        }
    }
    /** 获取当前处于栈顶的activity，无论其是否处于前台 */
    public static BaseActivity getCurrentctivity(){
        LinkedList<BaseActivity> copy;
        synchronized (activities) {
            copy = new LinkedList<BaseActivity>(activities);
        }
        if(0<copy.size()){
            return copy.get(copy.size()-1);
        }
        return null;
    }
    /**
     * 退出应用
     */
    public static void exitApp(){
        clearAll();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
