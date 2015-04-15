package mwqi.ui.widget;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import store.yifan.cn.appstore.R;
import store.yifan.cn.appstore.manager.ThreadManager;
import store.yifan.cn.appstore.utils.UIUtils;

public abstract class LoadingPage extends FrameLayout{
	
	private static final int STATE_UNLOADED = 1;// 表示默认状态
	private static final int STATE_LOADING = 2;//表示加载的状态
	private static final int STATE_ERROR = 3;//表示错误的状态
	private static final int STATE_EMPTY = 4;//表示空的状态
	private static final int STATE_SUCCEED = 5;//表示成功的状态

	private View mLoadingView;
	private View mErrorView;
	private View mEmptyView;
	private View mSucceedView;

	private int mState;

	public LoadingPage(Context context) {
		super(context);
		init();
	}

	private void init() {
		setBackgroundColor(UIUtils.getColor(R.color.bg_page));
		mState = STATE_UNLOADED;

		mLoadingView = createLoadingView();
		if (null != mLoadingView) {
			addView(mLoadingView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}

		mErrorView = createErrorView();
		if (null != mErrorView) {
			addView(mErrorView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}

		mEmptyView = createEmptyView();
		if (null != mEmptyView) {
			addView(mEmptyView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}
		showPageSafe();
	}

	private void showPageSafe() {
		UIUtils.runInMainThread(new Runnable() {
			@Override
			public void run() {
				showPage();
			}
		});
	}

	private void showPage() {
		if (null != mLoadingView) {
			mLoadingView.setVisibility(mState == STATE_UNLOADED || mState == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
		}
		if (null != mErrorView) {
			mErrorView.setVisibility(mState == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
		}
		if (null != mEmptyView) {
			mEmptyView.setVisibility(mState == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
		}

		if (mState == STATE_SUCCEED && mSucceedView == null) {
			mSucceedView = createLoadedView();
			addView(mSucceedView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}

		if (null != mSucceedView) {
			mSucceedView.setVisibility(mState == STATE_SUCCEED ? View.VISIBLE : View.INVISIBLE);
		}
	}



	public synchronized void show() {
		if (mState == STATE_ERROR || mState == STATE_EMPTY) {
			mState = STATE_UNLOADED;
		}
		if (mState == STATE_UNLOADED) {
			mState = STATE_LOADING;
			LoadingTask task = new LoadingTask();
			ThreadManager.getLongPool().execute(task);
		}
		showPageSafe();
	}

	protected View createLoadingView() {
		return UIUtils.inflate(R.layout.loading_page_loading);
	}

	protected View createEmptyView() {
		return UIUtils.inflate(R.layout.loading_page_empty);
	}

	protected View createErrorView() {
		View view = UIUtils.inflate(R.layout.loading_page_error);
		view.findViewById(R.id.page_bt).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				show();
			}
		});
		return view;
	}

	public abstract View createLoadedView();

	public abstract LoadResult load();

	class LoadingTask implements Runnable {
		@Override
		public void run() {
			final LoadResult loadResult = load();
			UIUtils.runInMainThread(new Runnable() {
				@Override
				public void run() {
					mState = loadResult.getValue();
					showPage();
				}
			});
		}
	}

	public enum LoadResult {
		ERROR(3), EMPTY(4), SUCCEED(5);
		int value;

		LoadResult(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}
