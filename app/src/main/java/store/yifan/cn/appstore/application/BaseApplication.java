package store.yifan.cn.appstore.application;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

/**
 * Author: Jwf(feijia101@gmail.com) <br\>
 * Date: 2015-04-14 15:45<br\>
 * Version: 1.0<br\>
 * Desc:<br\>
 * Revise:<br\>
 */
public class BaseApplication extends Application {

    //获取全局的上下文
    private static Application mContext;
    //获取主线程handler
    private static Handler mMainThreadHandler;
    //获取主线程Looper
    private static Looper mMainThreadLooper;
    //获取主线程
    private static Thread mMainThread;
    //获取主线程Id
    private static int mMainThreadId;
    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext=this;
        this.mMainThread=Thread.currentThread();
        this.mMainThreadHandler=new Handler();
        this.mMainThreadId=android.os.Process.myTid();
        this.mMainThreadLooper=getMainLooper();
    }

    public static  Application getApplication() {
        return mContext;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    public static  Looper getMainThreadLooper() {
        return mMainThreadLooper;
    }

    public static  Thread getMainThread() {
        return mMainThread;
    }

    public  static int getMainThreadId() {
        return mMainThreadId;
    }
}
