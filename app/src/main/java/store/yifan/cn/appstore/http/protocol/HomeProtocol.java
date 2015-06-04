package store.yifan.cn.appstore.http.protocol;

import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import store.yifan.cn.appstore.bean.AppInfo;
import store.yifan.cn.appstore.utils.LogUtils;

public class HomeProtocol extends BaseProtocol<List<AppInfo>> {
	private List<String> mPictureUrl;

	@Override
	protected String getKey() {
		return "home";
	}

	public List<String> getPictureUrl() {
		return mPictureUrl;
	}

	@Override
	protected List<AppInfo> parseFromJson(String json) {
		try {
			JSONObject jsonObject = new JSONObject(json);

			mPictureUrl = new ArrayList<String>();
			JSONArray array = jsonObject.optJSONArray("picture");
			if(array != null){
				for (int i = 0; i < array.length(); i++) {
					mPictureUrl.add(array.getString(i));
				}
			}
			List<AppInfo> list = new ArrayList<AppInfo>();
            list.addAll((List<AppInfo>)getGson().fromJson(jsonObject.getString("list"), new TypeToken<List<AppInfo>>(){}.getType()));
			return list;
		} catch (Exception e) {
			LogUtils.e(e);
			return null;
		}
	}

}
