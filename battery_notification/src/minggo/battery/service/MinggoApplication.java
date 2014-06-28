package minggo.battery.service;

import java.util.ArrayList;
import java.util.List;

import minggo.battery.model.SoundRecord;
import minggo.battery.model.User;
import minggo.battery.util.UserUtil;
import android.app.Application;
import android.content.Context;
/**
 * 应用全局
 * @author minggo
 * @time 2014-6-23 S下午10:49:54
 */
public class MinggoApplication extends Application {
	
	public List<SoundRecord> defaultSoundList;
	public static final String EMAIL = "minggo8en@gmail.com";
	@Override
	public void onCreate() {
		super.onCreate();
		firstInit();
		initSoundList();
	}
	/**
	 * 初始化默认孙燕姿播放列表
	 */
	private void initSoundList(){
		if (defaultSoundList==null) {
			defaultSoundList = new ArrayList<SoundRecord>();
			for (int i = 6; i < 24; i++) {
				SoundRecord soundRecord = new SoundRecord();
				soundRecord.path = "sound/"+i+".mp3";
				soundRecord.type = 1;
				soundRecord.whichHour = i;
				defaultSoundList.add(soundRecord);
			}
		}
	}
	/**
	 * 第一次安装时的初始化
	 */
	private void firstInit(){
		boolean preferences = getSharedPreferences("first", Context.MODE_PRIVATE).getBoolean("isfrist", true);
		if (preferences) {
			User user = new User();
			user.name = "sys_minggo";
			user.email = EMAIL;
			user.password = "12345678";
			user.type = 1;
			user.useDefineSound = 0;
			user.useDefinFeeling = 0;
			UserUtil.saveUser(this, user);
		}
	}
	
}