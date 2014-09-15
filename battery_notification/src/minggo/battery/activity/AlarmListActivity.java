package minggo.battery.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import minggo.battery.R;
import minggo.battery.adapter.AllAlarmAdapter;
import minggo.battery.model.Alarmer;
import minggo.battery.reciever.AlarmerReciever;
import minggo.battery.util.AlarmUtil;
import minggo.battery.util.SoundRecordUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mobstat.StatService;

/**
 * 所有闹钟的列表
 * 
 * @author minggo
 * @date 2014-9-15 下午5:11:40
 */
public class AlarmListActivity extends Activity implements OnItemLongClickListener, OnClickListener,OnItemClickListener {

	private View backV;
	private ListView alarmLv;
	private List<Alarmer> alarmList;
	private List<Alarmer> brthList;
	private List<Alarmer> defineList;
	private List<Alarmer> drinkList;
	private AllAlarmAdapter adapter;

	private TextView emptyListTv;
	private String drinkTimeStr = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_list);
		initUI();
		initData();
	}

	private void initUI() {
		backV = findViewById(R.id.lo_alarm_list_back);
		emptyListTv = (TextView) findViewById(R.id.tv_empty);
		alarmLv = (ListView) findViewById(R.id.lv_all_alarm);
		backV.setOnClickListener(this);
	}

	private void initData() {
		alarmList = new ArrayList<Alarmer>();
		brthList = AlarmUtil.queryAlarmerByType(this, AlarmerReciever.BIRTHDAY_ALARM);
		defineList = AlarmUtil.queryAlarmerByType(this, AlarmerReciever.DEFINE_ALARM);
		drinkList = AlarmUtil.queryAlarmerByType(this, AlarmerReciever.DRINK_ALARM);
		if (defineList != null && !defineList.isEmpty()) {
			alarmList.addAll(defineList);
		}
		if (brthList != null && !brthList.isEmpty()) {
			alarmList.addAll(brthList);
		}
		if (drinkList != null && !drinkList.isEmpty()) {
			alarmList.add(drinkList.get(0));
			for (int i = 0; i < drinkList.size(); i++) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(drinkList.get(i).alarmTime);

				calendar.get(Calendar.HOUR_OF_DAY);

				drinkTimeStr += calendar.get(Calendar.HOUR_OF_DAY)
						+ ":"
						+ (calendar.get(Calendar.MINUTE) < 10 ? "0" + calendar.get(Calendar.MINUTE) : calendar
								.get(Calendar.MINUTE)) + ",";
			}
		}

		if (!alarmList.isEmpty()) {
			if (adapter == null) {
				adapter = new AllAlarmAdapter(this, alarmList, drinkTimeStr);
				alarmLv.setAdapter(adapter);
				alarmLv.setOnItemLongClickListener(this);
				alarmLv.setOnItemClickListener(this);
			} else {
				adapter.notifyDataSetChanged();
			}
		} else {
			emptyListTv.setVisibility(View.VISIBLE);
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

	}

	private void deleteAlarm(final int position) {

		AlertDialog.Builder builder = new Builder(this);
		String message = alarmList.get(position).title;
		builder.setMessage(message);
		builder.setTitle(R.string.tips_1);
		builder.setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (alarmList.get(position).type == AlarmerReciever.DRINK_ALARM) {
					for (Alarmer alarm : drinkList) {
						AlarmUtil.deleteAlarm(AlarmListActivity.this, alarm.alarmerId);
					}
				} else {
					AlarmUtil.deleteAlarm(AlarmListActivity.this, alarmList.get(position).alarmerId);
				}
				alarmList.remove(position);
				adapter.notifyDataSetChanged();
			}
		});
		builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				dialog.cancel();
			}
		});
		builder.show();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

		deleteAlarm(position);

		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.lo_alarm_list_back:
			onBackPressed();
			break;

		default:
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		/*if (alarmList.get(position).type == AlarmerReciever.DRINK_ALARM) {
			startActivityForResult(new Intent(this, DrinkActivity.class),1001);
		}*/
	}
	/*@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1001:
			initData();
			break;

		default:
			break;
		}
		
	}*/
}
