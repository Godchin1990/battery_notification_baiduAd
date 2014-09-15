package minggo.battery.activity;

import minggo.battery.R;
import minggo.battery.service.MinggoApplication;
import minggo.battery.util.MinggoDate;
import minggo.battery.util.PreferenceShareUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.baidu.mobstat.StatService;
/**
 * 设置页面
 * @author minggo
 * @time 2014-6-19 S上午12:17:07
 */
public class SettingActivity extends Activity implements OnClickListener{
	
	private View backV;
	private Button feelingBt;
	private Button timeSoundbt;
	private Button lowPowerbt;
	private Button exitbt;
	
	private Button shockBt;
	private Button defineSoundBt;
	private MinggoDate date = new MinggoDate();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_new);
		initUI();
		initData();
		MinggoApplication.allActivities.add(this);
	}
	/**
	 * 初始化UI
	 */
	private void initUI(){
		backV = findViewById(R.id.lo_setting_back);
		timeSoundbt = (Button) findViewById(R.id.zheng_sound_bt);
		feelingBt = (Button) findViewById(R.id.bt_feeling_use);
		lowPowerbt = (Button) findViewById(R.id.low_power_bt);
		exitbt = (Button) findViewById(R.id.exitapp);
		shockBt = (Button) findViewById(R.id.bt_shock);
		defineSoundBt = (Button) findViewById(R.id.bt_alert_define);
		
		lowPowerbt.setOnClickListener(this);
		backV.setOnClickListener(this);
		feelingBt.setOnClickListener(this);
		timeSoundbt.setOnClickListener(this);
		exitbt.setOnClickListener(this);
		defineSoundBt.setOnClickListener(this);
		shockBt.setOnClickListener(this);
		
	}
	/**
	 * 初始化数据
	 */
	private void initData(){
		lowPowerbt.setSelected(PreferenceShareUtil.getLowPowerFlag(this));
		defineSoundBt.setSelected(PreferenceShareUtil.getDefineSoundFlag(this));
		timeSoundbt.setSelected(PreferenceShareUtil.getZhengTimeFlag(this));
		feelingBt.setSelected(PreferenceShareUtil.getUseFeeling(this));
		shockBt.setSelected(PreferenceShareUtil.getShockFlag(this));
	}
	
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.exitapp:
			//统计用户点击退出应用
			StatService.onEvent(SettingActivity.this, "exit_app", "exit");
			((MinggoApplication)this.getApplication()).stopAPP();
			break;
		case R.id.low_power_bt:
			boolean flag0 = !lowPowerbt.isSelected();
			lowPowerbt.setSelected(flag0);
			PreferenceShareUtil.saveLowPowerFlag(this, !PreferenceShareUtil.getLowPowerFlag(this));
			//统计用户设置低电量提醒
			StatService.onEvent(SettingActivity.this, "battery_alert", flag0+"");
			break;
		case R.id.bt_feeling_use:
			boolean flag1 = !feelingBt.isSelected();
			System.out.println("选择了什么东西--->"+flag1);
			feelingBt.setSelected(flag1);
			PreferenceShareUtil.saveUseFeeling(this, !PreferenceShareUtil.getUseFeeling(this));
			StatService.onEvent(SettingActivity.this, "feeling_set", flag1+"");
			refreshNotify();
			break;
		case R.id.zheng_sound_bt:
			boolean flag2 = !timeSoundbt.isSelected();
			timeSoundbt.setSelected(flag2);
			PreferenceShareUtil.saveZhengTimeFlag(this, !PreferenceShareUtil.getZhengTimeFlag(this));
			//统计用户设置整点报时提醒
			StatService.onEvent(SettingActivity.this, "time_alert", flag2+"");
			break;
		case R.id.lo_setting_back:
			onBackPressed();
			break;
		case R.id.bt_shock:
			boolean flag3 = !shockBt.isSelected();
			shockBt.setSelected(flag3);
			PreferenceShareUtil.saveShockFlag(this, !PreferenceShareUtil.getShockFlag(this));
			//统计用户设置整点报时提醒
			StatService.onEvent(SettingActivity.this, "use_shack", flag3+"");
			break;
		case R.id.bt_alert_define:
			boolean flag4 = !defineSoundBt.isSelected();
			defineSoundBt.setSelected(flag4);
			PreferenceShareUtil.saveDefineSoundFlag(this, !PreferenceShareUtil.getDefineSoundFlag(this));
			//统计用户设置整点报时提醒
			StatService.onEvent(SettingActivity.this, "define_sound", flag4+"");
			break;
		default:
			break;
		}
	}
	@Override
	protected void onPause() {
		super.onPause();
		StatService.onPause(this);
	}
	@Override
	protected void onResume() {
		super.onResume();
		StatService.onResume(this);
	}
	
	/**
	 * 发出广播
	 * 
	 * @param feeling
	 */
	private void sendFeelingBroadcast(int dayOfWeek, String feeling) {
		if (date.getDayOfWeek() == dayOfWeek) {
			Intent intent = new Intent("minggo.bettery.feeling");
			intent.putExtra("feeling", feeling);
			this.sendBroadcast(intent);
		}
	}
	/**
	 * 刷新通知栏
	 */
	private void refreshNotify(){
		
		if (PreferenceShareUtil.getUseFeeling(this)) {
			sendFeelingBroadcast(date.getDayOfWeek(),PreferenceShareUtil.getFeeling(this, date.getWeek2ENstr()));
		}else{
			sendFeelingBroadcast(date.getDayOfWeek(),getDayPrompt());
		}
	}
	
	
	/**
	 * 每天的提示语
	 * @return
	 */
	private String getDayPrompt(){
		String feeling = "";
		switch (date.getDayOfWeek()) {
		case 1:
			feeling = this.getString(R.string.sunday);
			break;
		case 2:
			feeling = this.getString(R.string.monday);
			break;
		case 3:
			feeling = this.getString(R.string.tuestday);
			break;
		case 4:
			feeling = this.getString(R.string.wednesday);
			break;
		case 5:
			feeling = this.getString(R.string.thursday);
			break;
		case 6:
			feeling = this.getString(R.string.friday);
			break;
		case 7:
			feeling = this.getString(R.string.saturday);
			break;
		default:
			break;
		}
		return feeling;
	}
}
