package minggo.battery.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import minggo.battery.R;
import minggo.battery.adapter.DrinkAdapter;
import minggo.battery.model.Alarmer;
import minggo.battery.service.BatteryService;
import minggo.battery.util.AlarmUtil;
import minggo.battery.view.BatteryListView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.mobstat.StatService;

/**
 * 喝水提醒
 * 
 * @author minggo
 * @time 2014-9-14 S上午8:51:05
 */
public class DrinkActivity extends Activity implements OnClickListener, OnItemClickListener {

	private View backV;
	private Button scanBt;
	private Button saveBt;
	private Button shockBt;
	private Button soundBt;
	private EditText titleEd;
	private BatteryListView drinkLv;
	private List<Alarmer> alarmList;
	private DrinkAdapter drinkAdapter;
	public static final int REQUEST_CODE_TIME = 10002;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drink_alarm);
		initUI();
		initData();
	}

	private void initUI() {
		backV = findViewById(R.id.lo_drink_alarm_back);
		shockBt = (Button) findViewById(R.id.bt_alarm_shock);
		soundBt = (Button) findViewById(R.id.bt_alarm_sound);
		scanBt = (Button) findViewById(R.id.bt_drink_scan);
		saveBt = (Button) findViewById(R.id.bt_save);
		titleEd = (EditText) findViewById(R.id.ed_title);
		drinkLv = (BatteryListView) findViewById(R.id.lv_drink);

		scanBt.setOnClickListener(this);
		saveBt.setOnClickListener(this);
		backV.setOnClickListener(this);
	}

	private void initData() {

		alarmList = AlarmUtil.queryAlarmerByType(this, 3);
		if (alarmList != null && !alarmList.isEmpty()) {
			if (drinkAdapter == null) {
				drinkAdapter = new DrinkAdapter(this, alarmList);
				drinkLv.setAdapter(drinkAdapter);
			} else {
				drinkAdapter.notifyDataSetChanged();
			}
			drinkLv.setOnItemClickListener(this);
			soundBt.setSelected(alarmList.get(0).sound == 1 ? true : false);
			shockBt.setSelected(alarmList.get(0).shock == 1 ? true : false);
		} else {
			alarmList = new ArrayList<Alarmer>();

			System.out.println("进行了啊");

			for (int i = 0; i < 8; i++) {
				Alarmer alarmer = new Alarmer();
				alarmer.repeat = 1;
				alarmer.shock = 1;
				alarmer.sound = 1;
				alarmer.soundPath = "/sound/zdclock_strike.mp3";
				alarmer.title = "喝水提醒";
				alarmer.type = 3;
				Calendar calendar = Calendar.getInstance();

				switch (i) {
				case 0:
					calendar.set(Calendar.HOUR_OF_DAY, 8);
					calendar.set(Calendar.MINUTE, 30);
					calendar.set(Calendar.SECOND, 0);
					
					alarmer.alarmTime = calendar.getTimeInMillis();
					break;
				case 1:
					calendar.set(Calendar.HOUR_OF_DAY, 9);
					calendar.set(Calendar.MINUTE, 30);
					calendar.set(Calendar.SECOND, 0);
					alarmer.alarmTime = calendar.getTimeInMillis();
					break;
				case 2:
					calendar.set(Calendar.HOUR_OF_DAY, 11);
					calendar.set(Calendar.MINUTE, 00);
					calendar.set(Calendar.SECOND, 0);
					alarmer.alarmTime = calendar.getTimeInMillis();
					break;
				case 3:
					calendar.set(Calendar.HOUR_OF_DAY, 12);
					calendar.set(Calendar.MINUTE, 30);
					calendar.set(Calendar.SECOND, 0);
					alarmer.alarmTime = calendar.getTimeInMillis();
					break;
				case 4:
					calendar.set(Calendar.HOUR_OF_DAY, 14);
					calendar.set(Calendar.MINUTE, 30);
					calendar.set(Calendar.SECOND, 0);
					alarmer.alarmTime = calendar.getTimeInMillis();
					break;
				case 5:
					calendar.set(Calendar.HOUR_OF_DAY, 15);
					calendar.set(Calendar.MINUTE, 00);
					calendar.set(Calendar.SECOND, 0);
					alarmer.alarmTime = calendar.getTimeInMillis();
					break;
				case 6:
					calendar.set(Calendar.HOUR_OF_DAY, 18);
					calendar.set(Calendar.MINUTE, 30);
					calendar.set(Calendar.SECOND, 0);
					alarmer.alarmTime = calendar.getTimeInMillis();
					break;
				case 7:
					calendar.set(Calendar.HOUR_OF_DAY, 21);
					calendar.set(Calendar.MINUTE, 00);
					calendar.set(Calendar.SECOND, 0);
					alarmer.alarmTime = calendar.getTimeInMillis();
					break;

				default:
					break;
				}
				
				alarmList.add(alarmer);
			}

			if (drinkAdapter == null) {
				drinkAdapter = new DrinkAdapter(this, alarmList);
				drinkLv.setAdapter(drinkAdapter);
			} else {
				drinkAdapter.notifyDataSetChanged();
			}
			drinkLv.setOnItemClickListener(this);
		}
	}

	private void notifyData() {

		for (Alarmer alarm : alarmList) {
			alarm.shock = shockBt.isSelected() ? 1 : 2;
			alarm.sound = soundBt.isSelected() ? 1 : 2;
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
		//setResult(1001);
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.lo_drink_alarm_back:
			onBackPressed();
			break;
		case R.id.bt_alarm_shock:
			shockBt.setSelected(shockBt.isSelected() ? false : true);
			notifyData();
			break;
		case R.id.bt_alarm_sound:
			soundBt.setSelected(soundBt.isSelected() ? false : true);
			notifyData();
			break;
		case R.id.bt_save:
			
			String titleStr =titleEd.getText().toString();
			if (titleStr==null||titleStr.equals("")) {
				titleStr = "喝水提醒";
			}
			for (Alarmer alarmer : alarmList) {
				alarmer.title = titleStr;
				Intent intent = new Intent(this, BatteryService.class);
				if (AlarmUtil.isExistDrinkAlarm(this, alarmer)) {
					AlarmUtil.updateAlarm(this, alarmer);
					intent.putExtra("update", true);
					System.out.println("已经存在了闹钟--->"+alarmer.alarmerId);
				}else{
					AlarmUtil.saveAlarm(this, alarmer);
					intent.putExtra("update", false);
				}
				
				intent.putExtra("alarm", alarmer);
				this.startService(intent);
			}
			Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
			break;
		case R.id.bt_drink_scan:
			if (alarmList.get(0).alarmerId != 0) {
				Intent intent2 = new Intent(this, AlarmWakeActivity.class);
				intent2.putExtra("alarm", alarmList.get(0));
				startActivity(intent2);
			} else {
				Toast.makeText(this, "先保存闹钟", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}

	private int position = -1;
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent intent1 = new Intent(this, TimeSelectActivity.class);
		intent1.putExtra("title", "提醒时间选择");
		intent1.putExtra("type", "time");
		startActivityForResult(intent1, REQUEST_CODE_TIME);
		this.position = position;

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_TIME) {
			if (data != null) {
				int hour = data.getIntExtra("hour", 8);
				int minute = data.getIntExtra("minute", 28);
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(alarmList.get(position).alarmTime);
				calendar.set(Calendar.HOUR_OF_DAY, hour);
				calendar.set(Calendar.MINUTE, minute);
				alarmList.get(position).alarmTime = calendar.getTimeInMillis();
				drinkAdapter.notifyDataSetChanged();
				position = -1;
			}

		}
	}
}
