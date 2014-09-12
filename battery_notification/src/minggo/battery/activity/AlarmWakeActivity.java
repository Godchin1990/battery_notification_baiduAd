package minggo.battery.activity;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.mobstat.StatService;
/**
 * 闹钟提醒页面
 * @author minggo
 * @date 2014-9-12 下午3:02:01
 */
public class AlarmWakeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	protected void onResume() {
		super.onResume();
		StatService.onResume(this);
	}
	@Override
	protected void onPause() {
		super.onPause();
		StatService.onPause(this);
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
