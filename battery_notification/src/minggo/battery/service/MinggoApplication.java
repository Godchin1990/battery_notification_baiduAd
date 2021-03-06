package minggo.battery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import minggo.battery.model.SoundRecord;
import minggo.battery.model.User;
import minggo.battery.util.UserUtil;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

/**
 * 应用全局
 * 
 * @author minggo
 * @time 2014-6-23 S下午10:49:54
 */
public class MinggoApplication extends Application {

	public List<SoundRecord> defaultSoundList;
	public static final String EMAIL = "minggo8en@gmail.com";
	public static Stack<Activity> allActivities;
	//http://item.taobao.com/item.htm?spm=a1z10.1.w4004-7871465182.2.k49qDs&id=40301430368
	public static final String TAOBAO_URL = "http://item.taobao.com/item.htm?spm=a1z10.1.w4004-7871465182.2.k49qDs&id=40301430368";

	@Override
	public void onCreate() {
		super.onCreate();
		allActivities = new Stack<Activity>();
		firstInit();
		initSoundList();
	}

	/**
	 * 结束所有Activity
	 */
	public static void finishAllActivity() {
		for (int i = 0, size = allActivities.size(); i < size; i++) {
			if (null != allActivities.get(i)) {
				allActivities.get(i).finish();
			}
		}
		allActivities.clear();
	}

	/**
	 * 推出应用
	 */
	public void stopAPP() {
		finishAllActivity();
		this.stopService(new Intent("minggo.battery.alarm.service"));
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}

	/**
	 * 初始化默认孙燕姿播放列表
	 */
	private void initSoundList() {
		if (defaultSoundList == null) {
			defaultSoundList = new ArrayList<SoundRecord>();
			for (int i = 6; i < 24; i++) {
				SoundRecord soundRecord = new SoundRecord();
				soundRecord.path = "sound/" + i + ".mp3";
				soundRecord.type = 1;
				soundRecord.whichHour = i;
				defaultSoundList.add(soundRecord);
			}
		}
	}

	/**
	 * 第一次安装时的初始化
	 */
	private void firstInit() {
		boolean preferences = getSharedPreferences("first", Context.MODE_PRIVATE).getBoolean("isfrist", true);
		if (preferences) {
			User user = new User();
			user.name = "sys_minggo";
			user.email = EMAIL;
			user.password = "12345678";
			user.type = 0;
			user.useDefineSound = 0;
			user.useDefinFeeling = 0;
			UserUtil.saveUser(this, user);
		}
	}

	/**
	 * 获取手机唯一识别码
	 * 
	 * @return
	 */
	public String GetAndroidId() {
		String android_id = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);

		if (android_id == null || android_id.equals("")) {
			android_id = "no_android_id";;
		}
		return android_id;
	}

	/**
	 * 获取手机的IMEI
	 * 
	 * @return
	 */
	public String getAndroidIMEI() {
		TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId();
		if (imei != null && !imei.equals("")) {
			return imei;
		}
		return "no_imei";
	}

	/**
	 * 获取手机型号
	 * 
	 * @return
	 */
	public String GetMobileVersion() {
		return android.os.Build.MODEL;
	}

	/**
	 * 获取系统的版本号
	 * 
	 * @return
	 */
	public String GetAndroidVersion() {
		return android.os.Build.VERSION.RELEASE;
	}
}
