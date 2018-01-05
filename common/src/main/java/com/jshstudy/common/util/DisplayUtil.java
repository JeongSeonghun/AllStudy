package com.jshstudy.common.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class DisplayUtil {

	/**
	 * 단말 화면 밝기 설정 (시스템)
	 * @param ctx
	 * @param brightness 0~255
	 */
	public static void setManualSystemBrightness(@NonNull Context ctx, int brightness){
		ContentResolver cr = ctx.getContentResolver();
		try {
			int mode = Settings.System.getInt(cr, Settings.System.SCREEN_BRIGHTNESS_MODE);

			LogUtil.dLog(DisplayUtil.class.getSimpleName(), "Current Settings.System.SCREEN_BRIGHTNESS_MODE = " + mode);

			if(mode != Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL){
				boolean setMode = Settings.System.putInt(cr, Settings.System.SCREEN_BRIGHTNESS_MODE,
						Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);

				LogUtil.dLog(DisplayUtil.class.getSimpleName(), "Set Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL "+setMode);
			}

			int currBrightness = Settings.System.getInt(cr, Settings.System.SCREEN_BRIGHTNESS);

			LogUtil.dLog(DisplayUtil.class.getSimpleName(), "Current Settings.System.SCREEN_BRIGHTNESS = " + currBrightness);

			if(currBrightness != brightness){
				if(brightness < 0 ) {
					brightness = 0;
				}else if(brightness > 255){
					brightness = 255;
				}

				boolean setBrightness = Settings.System.putInt(cr, Settings.System.SCREEN_BRIGHTNESS, brightness);

				LogUtil.dLog(DisplayUtil.class.getSimpleName(), "Set Settings.System.SCREEN_BRIGHTNESS = "+setBrightness + "/"+ brightness);
			}
		} catch (Settings.SettingNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static int getStatusBarHeight(Context ctx) {
		int result = 0;
		int resourceId = ctx.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = ctx.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	/**
	 * 단말 LCD의 width를 반환한다.
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		Display display = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		return display.getWidth();
	}

	/**
	 * 단말 LCD의 height를 반환한다.
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		Display display = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		return display.getHeight();
	}

	/**
	 * 소프트 키패드 표시하기
	 * <p>
	 * CHECK 테스트 필요.
	 * 
	 * @since 1.0.0
	 * @param context
	 * @param view
	 */
	public static void showSoftInput(Context context, EditText view) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(view, 0);
	}

	/**
	 * 소프트 키패드 숨기기
	 * <p>
	 * CHECK 테스트 필요.
	 * 
	 * @since 1.0.0
	 * @param context
	 * @param view
	 */
	public static void hideSoftInput(Context context, EditText view) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	/**
	 * 화면꺼짐 방지 설정 (액티비티단위)
	 * 
	 * @since 1.0.0
	 * @param context
	 */
	public static void setKeepScreenOn(Context context) {
		((Activity) context).getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	/**
	 * 화면꺼짐 방지 설정해제 (액티비티단위)
	 * 
	 * @since 1.0.0
	 * @param context
	 */
	public static void unsetKeepScreenOn(Context context) {
		((Activity) context).getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	/**
	 * 픽셀을 DP 로 바꿔준다.
	 * @param px
	 * @param context
	 * @return
	 */
	public static float convertPixelsToDp(float px, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return dp;
	}
}