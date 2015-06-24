/**
 * SpeechSynthesisUtil.java [V 1..0.0]
 * classes : com.hb56.DriverReservation.android.utils.speech.SpeechSynthesisUtil
 * zhangyx Create at 2014-12-18 下午3:01:11
 */
package store.yifan.cn.basework.utils;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

import store.yifan.cn.basework.manager.Constants;


/**
 * 语音合成工具类 com.hb56.DriverReservation.android.utils.speech.SpeechSynthesisUtil
 * 
 * @author Admin-zhangyx
 * 
 *         create at 2014-12-18 下午3:01:11
 */
public class SpeechSynthesisUtil {
	private SpeechSynthesizer mTts;// 语音合成对象
//	private int mPercentForBuffering = 0;// 缓冲进度
//	private int mPercentForPlaying = 0;// 播放进度
	private String mEngineType = SpeechConstant.TYPE_CLOUD;// 引擎类型
	private Context c;

	public SpeechSynthesisUtil(Context c) {
		this.c = c;
		mTts = SpeechSynthesizer.createSynthesizer(c, mTtsInitListener);
	}

	/**
	 * 初期化监听。
	 */
	private InitListener mTtsInitListener = new InitListener() {
		@Override
		public void onInit(int code) {
			System.out.println("InitListener init() code = " + code);
			if (code != ErrorCode.SUCCESS) {
				// showToast("初始化失败,错误码：" + code);
			}
		}
	};

	/**
	 * 合成回调监听。
	 */
	private SynthesizerListener mTtsListener = new SynthesizerListener() {
		@Override
		public void onSpeakBegin() {
			// showToast("开始播放");
		}

		@Override
		public void onSpeakPaused() {
			// showToast("暂停播放");
		}

		@Override
		public void onSpeakResumed() {
			// showToast("继续播放");
		}

		@Override
		public void onBufferProgress(int percent, int beginPos, int endPos,
				String info) {
//			mPercentForBuffering = percent;
//			ToastHelper.show(c, String.format(
//					c.getString(R.string.tts_toast_format),
//					mPercentForBuffering, mPercentForPlaying));
		}

		@Override
		public void onSpeakProgress(int percent, int beginPos, int endPos) {
//			mPercentForPlaying = percent;
//			ToastHelper.show(c, String.format(
//					c.getString(R.string.tts_toast_format),
//					mPercentForBuffering, mPercentForPlaying));
		}

		@Override
		public void onCompleted(SpeechError error) {
			if (error == null) {
				// showToast("播放完成");
			} else if (error != null) {
				// showToast(error.getPlainDescription(true));
			}
		}

		@Override
		public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {

		}
	};

	/***
	 * 开始合成
	 * 
	 * @param speechStr
	 */
	public void startSpeech(String speechStr) {
//		mPercentForBuffering = 0;// 缓冲进度
//		mPercentForPlaying = 0;
		// 设置参数
		setParam();
		int code = mTts.startSpeaking(speechStr, mTtsListener);
		if (code != ErrorCode.SUCCESS) {
			if (code == ErrorCode.ERROR_COMPONENT_NOT_INSTALLED) {
				// 未安装则跳转到提示安装页面
				// mInstaller.install();
			} else {
				// showToast("语音合成失败,错误码: " + code);
				//ToastHelper.show(c, "语音合成失败,错误码: " + code);
                Toast.makeText(c,"语音合成失败,错误码: " + code,Toast.LENGTH_LONG).show();
			}
		}
	}

	/***
	 * 销毁
	 */
	public void stopSpeech() {
		mTts.stopSpeaking();
		mTts.destroy();
	}

	/**
	 * 参数设置
	 * 
	 * @param param
	 * @return
	 */
	private void setParam() {
		// 设置合成
		if (mEngineType.equals(SpeechConstant.TYPE_CLOUD)) {
			mTts.setParameter(SpeechConstant.ENGINE_TYPE,
					SpeechConstant.TYPE_CLOUD);
			// 设置发音人
			mTts.setParameter(SpeechConstant.VOICE_NAME, Constants.VOICER);
		} else {
			mTts.setParameter(SpeechConstant.ENGINE_TYPE,
					SpeechConstant.TYPE_LOCAL);
			// 设置发音人 voicer为空默认通过语音+界面指定发音人。
			mTts.setParameter(SpeechConstant.VOICE_NAME, Constants.VOICER);
		}
	}

}
