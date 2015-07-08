package store.yifan.cn.basework.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;


import store.yifan.cn.appstore.R;
import store.yifan.cn.basework.utils.UIUtils;

public class BaseListView extends ListView {
	public BaseListView(Context context) {
		this(context, null);
	}

	public BaseListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public BaseListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		setDivider(UIUtils.getResources().getDrawable(R.drawable.nothing));
		setCacheColorHint(UIUtils.getColor(R.color.bg_page));
		setSelector(UIUtils.getResources().getDrawable(R.drawable.nothing));
	}
}
