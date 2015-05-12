package store.yifan.cn.appstore.fragment;

import android.view.View;
import android.widget.TextView;

import store.yifan.cn.appstore.ui.widget.LoadingPage;
import store.yifan.cn.appstore.utils.UIUtils;

/**
 * Author: Jwf(feijia101@gmail.com) <br\>
 * Date: 2015-04-15 14:52<br\>
 * Version: 1.0<br\>
 * Desc:<br\>
 * Revise:<br\>
 */
public class AppFragment extends BaseFragment {
    @Override
    protected LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.SUCCESS;
    }


    @Override
    protected View createSuccessView() {
        TextView view=new TextView(UIUtils.getContext());
        view.setText("测试");
        return view;
    }
}
