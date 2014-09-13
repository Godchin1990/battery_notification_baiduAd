package minggo.battery.activity;

import java.io.IOException;
import java.util.Calendar;

import minggo.battery.R;
import minggo.battery.model.Alarmer;
import minggo.battery.reciever.AlarmerReciever;
import minggo.battery.util.PlaySound;
import minggo.battery.util.PreferenceShareUtil;
import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
/**
 * 闹钟提醒页面
 * @author minggo
 * @date 2014-9-12 下午3:02:01
 */
public class AlarmWakeActivity extends Activity implements OnClickListener{
	
	private ImageView themeIv;
	private Button confirmBt;
	private TextView titleTv;
	private TextView timeTv;
	private TextView dayTv;
	private Alarmer alarmer;
	
	private Vibrator vibrator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); // 全屏
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_awake);
		
		alarmer = (Alarmer) getIntent().getSerializableExtra("alarm");
		initUI();
		initData();
	}
	
	private void initUI(){
		themeIv = (ImageView) findViewById(R.id.iv_theme);
		timeTv = (TextView) findViewById(R.id.tv_alarm_time);
		dayTv = (TextView) findViewById(R.id.tv_alarm_date);
		titleTv = (TextView) findViewById(R.id.tv_alarm_title);
		confirmBt = (Button) findViewById(R.id.bt_confirm);
		
		confirmBt.setOnClickListener(this);
		
		Animation animation = AnimationUtils.loadAnimation(this,R.anim.shake);
		themeIv.setAnimation(animation);
		themeIv.startAnimation(animation); 
		animation.setRepeatCount(1000);
	}
	
	private void initData(){
		if (alarmer!=null) {
			
			switch (alarmer.type) {
			case AlarmerReciever.DEFINE_ALARM:
				themeIv.setImageResource(R.drawable.emoji_298);
				break;
			case  AlarmerReciever.DRINK_ALARM:
				themeIv.setImageResource(R.drawable.smiley_60);
				break;
			case AlarmerReciever.BIRTHDAY_ALARM:
				themeIv.setImageResource(R.drawable.emoji_103);
				break;
			default:
				break;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(alarmer.alarmTime);
			if (calendar.get(Calendar.MINUTE)<10) {
				timeTv.setText(calendar.get(Calendar.HOUR_OF_DAY)+":0"+calendar.get(Calendar.MINUTE));
			}else{
				timeTv.setText(calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));
			}
			dayTv.setText(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+(calendar.get(Calendar.DAY_OF_MONTH)));	
			titleTv.setText(alarmer.title);
			
			if (alarmer.shock==1) {
				if (vibrator == null) {
					vibrator = (Vibrator) this.getSystemService(Service.VIBRATOR_SERVICE);
				}
				//vibrator.vibrate(500);
				vibrator.vibrate(new long[]{650,650}, 0);
			}
			if (alarmer.sound==1) {
				try {
					PlaySound.playDefineAlarmVoice2(alarmer.soundPath, this.getAssets(), 1, true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
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
		vibrator.cancel();
		PlaySound.stopVoice();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_confirm:
			onBackPressed();
			break;

		default:
			break;
		}
	}
}
