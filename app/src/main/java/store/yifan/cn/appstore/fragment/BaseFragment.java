package store.yifan.cn.appstore.fragment;

import android.support.v4.app.Fragment;

import store.yifan.cn.appstore.ui.widget.LoadingPage;

/**
 * Author: Jwf(feijia101@gmail.com) <br\>
 * Date: 2015-04-15 14:26<br\>
 * Version: 1.0<br\>
 * Desc:<br\>
 * Revise:<br\>
 */
public class BaseFragment extends Fragment {
    protected LoadingPage mContentView;

    public void show() {
        if(mContentView!=null){
            mContentView.show();
        }
    }
}
