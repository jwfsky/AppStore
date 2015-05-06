package store.yifan.cn.appstore.ui.widget;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import store.yifan.cn.appstore.R;
import store.yifan.cn.appstore.manager.ThreadManager;
import store.yifan.cn.appstore.utils.UIUtils;

public abstract class LoadingPage extends FrameLayout {

    private static final int STATE_UNLOAD = 0;//默认状态
    private static final int STATE_LOADING = 1;//加载状态
    private static final int STATE_ERROR = 2;//错误状态
    private static final int STATE_EMPTY = 3;//空状态
    private static final int STATE_SUCCESS = 4;//成功状态

    private View mLoadingView;//加载页面
    private View mEmptyView;//空页面
    private View mErrorView;//错误页面
    private View mSuccessView;//成功页面

    private int state;//默认状态

    public LoadingPage(Context context) {
        super(context);
        init();
    }

    //初始状态下，添加页面
    private void init() {
        setBackgroundColor(UIUtils.getColor(R.color.bg_page));
        state = STATE_UNLOAD;//设置默认状态

        mLoadingView = createLoadingView();
        if (mLoadingView != null) {
            addView(mLoadingView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        mEmptyView = createEmptyView();
        if (mEmptyView != null) {
            addView(mEmptyView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        mErrorView = createErrorView();
        if (mErrorView != null) {
            addView(mErrorView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        showSafeView();
    }

    /**
     * 确保view运行在主线程
     */
    private void showSafeView() {
        UIUtils.runInMainThread(new Runnable() {
            @Override
            public void run() {
                showView();
            }
        });
    }

    /**
     * 根据状态展示相应页面
     */
    private void showView() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(state == STATE_UNLOAD || state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if (mEmptyView != null) {
            mEmptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        }
        if (mErrorView != null) {
            mErrorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);

        }
        // 只有数据成功返回了，才知道成功的View该如何显示，因为该View的显示依赖加载完毕的数据
        if (state == STATE_SUCCESS && mSuccessView == null) {
            mSuccessView = createSuccessView();
        }
        if (mSuccessView != null) {
            mSuccessView.setVisibility(state == STATE_SUCCESS ? View.VISIBLE : View.INVISIBLE);
        }

    }
    /** 有外部调用，会根据状态判断是否引发load */
    public synchronized void show() {
        if (needReset()) {
            state = STATE_UNLOAD;
        }
        if (state == STATE_UNLOAD) {
            state = STATE_LOADING;
            LoadingTask task = new LoadingTask();
            ThreadManager.getLongPool().execute(task);
        }
        showSafeView();
    }

    public abstract View createSuccessView();

    public abstract LoadResult load();

    private View createErrorView() {
        mErrorView = UIUtils.inflate(R.layout.loading_page_error);
        Button page_bt = (Button) mErrorView.findViewById(R.id.page_bt);
        page_bt.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                show();
            }
        });
        return mErrorView;
    }

    /** 是否需要恢复状态 */
    private boolean needReset() {
        return state == STATE_ERROR || state == STATE_EMPTY;
    }

    private View createEmptyView() {
        return UIUtils.inflate(R.layout.loading_page_empty);
    }

    private View createLoadingView() {
        return UIUtils.inflate(R.layout.loading_page_loading);
    }

    class LoadingTask implements Runnable {

        @Override
        public void run() {
            final LoadResult result = load();
            //状态的改变和界面息息相关，所以需要放到主线程来赋值，保障同步性
            UIUtils.runInMainThread(new Runnable() {
                @Override
                public void run() {
                    state=result.getValue();//根据枚举对象的返回值来改变显示状态码
                    showView();
                }
            });
        }
    }


    public enum LoadResult {
        ERROR(2), EMPTY(3), SUCCESS(4);
        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
