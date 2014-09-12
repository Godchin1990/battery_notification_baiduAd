package minggo.battery.activity;

import java.util.Calendar;

import minggo.battery.R;
import minggo.battery.model.Alarmer;
import minggo.battery.reciever.AlarmerReciever;
import minggo.battery.service.BatteryService;
import minggo.battery.util.AlarmUtil;
import android.app.Activity;
import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mobstat.StatService;

/**
 * 自定义闹钟
 * 
 * @author minggo
 * @date 2014-9-10 下午3:28:33
 */
public class DefineAlarmActivity extends Activity implements OnClickListener {

	private EditText titleEd;
	private View dateSelectV;
	private View timeSelectV;
	private View backV;
	private Button sacnBt;
	private Button saveBt;
	private String title;

	private TextView dateTv;
	private TextView timeTv;
	private TextView themeTv;

	public static final int REQUEST_CODE_DATE = 10001;
	public static final int REQUEST_CODE_TIME = 10002;

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int alarmType;
	private Alarmer alarmer = new Alarmer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_define_alarm);
		initUI();
		initData();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		long timelong = getIntent().getLongExtra("timelong", Calendar.getInstance().getTimeInMillis());
		alarmType = getIntent().getIntExtra("alarmType", AlarmerReciever.DEFINE_ALARM);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timelong);

		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);

		dateTv.setText(year + "-" + month + "-" + day);
		if (minute<10) {
			timeTv.setText(hour + ":0" + minute);
		}else{
			timeTv.setText(hour + ":" + minute);
		}

		if (alarmType == AlarmerReciever.BIRTHDAY_ALARM) {
			themeTv.setText("生日闹钟");
		} else if (alarmType == AlarmerReciever.DRINK_ALARM) {
			themeTv.setText("喝水闹钟");
		} else if (alarmType == AlarmerReciever.DEFINE_ALARM) {
			themeTv.setText("自定义闹钟");
		}
	}

	/**
	 * 初始化UI
	 */
	private void initUI() {

		titleEd = (EditText) findViewById(R.id.ed_title);
		dateSelectV = findViewById(R.id.lo_alarm_date);
		timeSelectV = findViewById(R.id.lo_time_select);
		backV = findViewById(R.id.lo_define_alarm_back);
		sacnBt = (Button) findViewById(R.id.bt_scan);
		saveBt = (Button) findViewById(R.id.bt_save);
		dateTv = (TextView) findViewById(R.id.tv_date);
		timeTv = (TextView) findViewById(R.id.tv_alarm_time);
		themeTv = (TextView) findViewById(R.id.tv_theme);

		dateSelectV.setOnClickListener(this);
		backV.setOnClickListener(this);
		timeSelectV.setOnClickListener(this);
		sacnBt.setOnClickListener(this);
		saveBt.setOnClickListener(this);
	}

	/**
	 * 保存数据
	 */
	private void save() {
		title = titleEd.getText().toString();
		if (title == null ||title.equals("")) {
			title = themeTv.getText().toString();
		}
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, day, hour, minute,0);
			long time = calendar.getTimeInMillis();

			alarmer.soundPath = "";
			alarmer.alarmTime = time;
			alarmer.repeat = 0;
			alarmer.title = title;
			alarmer.type = alarmType;
			if (!AlarmUtil.isExistAlarm(this, alarmer)&&!AlarmUtil.isTheSameAlarm(this, alarmer)) {
				if (AlarmUtil.saveAlarm(this, alarmer)) {
					System.out.println("alarmer.alarmTime--"+alarmer.alarmTime+","+System.currentTimeMillis());
					if (alarmer.alarmTime - System.currentTimeMillis() >= 0) {
						
						alarmer = AlarmUtil.getAlarmByTimeAndType(this, alarmer.alarmTime,alarmer.type);
						
						Intent intent = new Intent(this, BatteryService.class);
						intent.putExtra("alarm", alarmer);
						this.startService(intent);
						Toast.makeText(this, "保存闹钟成功！", Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(this, "保存闹钟失败！", Toast.LENGTH_LONG).show();
				}
				themeTv.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						onBackPressed();
						
					}
				}, 1000);
				
			} else {
				Toast.makeText(this, "闹钟已经存在！", Toast.LENGTH_LONG).show();
			}

		/*} else {
			Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
			Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
			titleEd.setAnimation(animation);
			titleEd.startAnimation(animation);
		}*/
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.lo_alarm_date:
			Intent intent0 = new Intent(this, TimeSelectActivity.class);
			intent0.putExtra("title", "提醒日期选择");
			intent0.putExtra("type", "date");
			startActivityForResult(intent0, REQUEST_CODE_DATE);
			// overridePendingTransition(R.anim.translate_up,
			// R.anim.translate_down);
			break;
		case R.id.lo_time_select:
			Intent intent1 = new Intent(this, TimeSelectActivity.class);
			intent1.putExtra("title", "提醒时间选择");
			intent1.putExtra("type", "time");
			startActivityForResult(intent1, REQUEST_CODE_TIME);
			// overridePendingTransition(R.anim.translate_up,
			// R.anim.translate_down);
			break;
		case R.id.bt_scan:

			break;
		case R.id.lo_define_alarm_back:
			onBackPressed();
			break;
		case R.id.bt_save:
			save();
			break;

		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_CODE_DATE) {
			if (data != null) {
				year = data.getIntExtra("year", 2014);
				month = data.getIntExtra("month", 8);
				day = data.getIntExtra("day", 12);
				Calendar calendar = Calendar.getInstance();
				calendar.set(year, month, day);
				dateTv.setText(year + "-" + month + "-" + day);
			}

		} else if (requestCode == REQUEST_CODE_TIME) {

			if (data != null) {
				hour = data.getIntExtra("hour", 8);
				minute = data.getIntExtra("minute", 28);
				if (minute < 10) {
					timeTv.setText(hour + ":0" + minute);
				} else {
					timeTv.setText(hour + ":" + minute);
				}
			}

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

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
}
