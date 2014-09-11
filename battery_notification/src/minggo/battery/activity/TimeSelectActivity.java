package minggo.battery.activity;

import minggo.battery.R;
import android.app.Activity;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.baidu.mobstat.StatService;

/**
 * 时间选择
 * 
 * @author minggo
 * @date 2014-9-10 下午3:15:40
 */
public class TimeSelectActivity extends Activity implements OnClickListener, OnTimeSetListener, OnTimeChangedListener {

	private TextView titleTv;
	private ImageView cancelBt;
	private ImageView saveBt;
	private TimePicker timePicker;
	private DatePicker datePicker;
	private String titleStr;
	private String type;
	private int timeHour;
	private int timeMinute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_time);
		getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		titleStr = getIntent().getStringExtra("title");
		type = getIntent().getStringExtra("type");
		initUI();
	}

	/**
	 * 初始化UI
	 */
	private void initUI() {
		titleTv = (TextView) findViewById(R.id.tv_tltle);
		cancelBt = (ImageView) findViewById(R.id.iv_cancel);
		saveBt = (ImageView) findViewById(R.id.iv_save);
		timePicker = (TimePicker) findViewById(R.id.tp_select_time);
		datePicker = (DatePicker) findViewById(R.id.dp_select_date);

		timePicker.setIs24HourView(true);
		timePicker.setOnTimeChangedListener(this);

		
		cancelBt.setOnClickListener(this);
		saveBt.setOnClickListener(this);

		if (type != null && !type.equals("")) {
			if (type.equals("date")) {
				datePicker.setVisibility(View.VISIBLE);
				timePicker.setVisibility(View.GONE);
			} else {
				datePicker.setVisibility(View.GONE);
				timePicker.setVisibility(View.VISIBLE);
			}
		}

		if (titleStr != null && !titleStr.equals("")) {
			titleTv.setText(titleStr);
		}
	}

	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		timeHour = hourOfDay;
		timeMinute = minute;
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

	}

	private void saveAlarm() {

		if (type != null) {
			if (type.equals("date")) {
				int year = datePicker.getYear();
				int month = datePicker.getMonth();
				int day = datePicker.getDayOfMonth();
				System.out.println(year+","+month+","+day);
				Intent intent = new Intent();
				intent.putExtra("year", year);
				intent.putExtra("month", month);
				intent.putExtra("day", day);
						
				setResult(DefineAlarmActivity.REQUEST_CODE_DATE, intent);
			}else{
				int hour = timePicker.getCurrentHour();
				int minute = timePicker.getCurrentMinute();
				
				Intent intent = new Intent();
				intent.putExtra("hour", hour);
				intent.putExtra("minute", minute);
						
				setResult(DefineAlarmActivity.REQUEST_CODE_TIME, intent);
				System.out.println(hour+","+minute);
			}
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_cancel:
			onBackPressed();
			break;
		case R.id.iv_save:
			saveAlarm();
			onBackPressed();
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

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.alpha_in, R.anim.translate_down);
	}

}
