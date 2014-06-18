package minggo.battery.fragment;

import minggo.battery.R;
import minggo.battery.util.MinggoDate;
import minggo.battery.util.PreferenceShareUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 每天心情设置
 * 
 * @author minggo
 * @time 2014-6-16 S下午9:18:35
 */
public class FragmentFeelingSetting extends Fragment implements OnClickListener {

	private Activity activity;
	private View feelingSettingView;
	private LayoutInflater inflater;
	private MinggoDate date;

	private Button write1Bt;
	private Button write2Bt;
	private Button write3Bt;
	private Button write4Bt;
	private Button write5Bt;
	private Button write6Bt;
	private Button write7Bt;

	private TextView feeling1Tv;
	private TextView feeling2Tv;
	private TextView feeling3Tv;
	private TextView feeling4Tv;
	private TextView feeling5Tv;
	private TextView feeling6Tv;
	private TextView feeling7Tv;

	private EditText input1Ed;
	private EditText input2Ed;
	private EditText input3Ed;
	private EditText input4Ed;
	private EditText input5Ed;
	private EditText input6Ed;
	private EditText input7Ed;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
		date = new MinggoDate();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		feelingSettingView = inflater.inflate(R.layout.fragment_feeling, container, false);
		initUI();
		initData();
		return feelingSettingView;
	}

	/**
	 * 初始化UI
	 */
	private void initUI() {
		write1Bt = (Button) feelingSettingView.findViewById(R.id.bt_sunday_pen);
		write2Bt = (Button) feelingSettingView.findViewById(R.id.bt_monday_pen);
		write3Bt = (Button) feelingSettingView.findViewById(R.id.bt_tuestday_pen);
		write4Bt = (Button) feelingSettingView.findViewById(R.id.bt_wednesday_pen);
		write5Bt = (Button) feelingSettingView.findViewById(R.id.bt_thursday_pen);
		write6Bt = (Button) feelingSettingView.findViewById(R.id.bt_friday_pen);
		write7Bt = (Button) feelingSettingView.findViewById(R.id.bt_saturday_pen);

		feeling1Tv = (TextView) feelingSettingView.findViewById(R.id.tv_sunday_feeling);
		feeling2Tv = (TextView) feelingSettingView.findViewById(R.id.tv_monday_feeling);
		feeling3Tv = (TextView) feelingSettingView.findViewById(R.id.tv_tuestday_feeling);
		feeling4Tv = (TextView) feelingSettingView.findViewById(R.id.tv_wednesday_feeling);
		feeling5Tv = (TextView) feelingSettingView.findViewById(R.id.tv_thursday_feeling);
		feeling6Tv = (TextView) feelingSettingView.findViewById(R.id.tv_friday_feeling);
		feeling7Tv = (TextView) feelingSettingView.findViewById(R.id.tv_saturday_feeling);

		input1Ed = (EditText) feelingSettingView.findViewById(R.id.ed_sunday_feeling);
		input2Ed = (EditText) feelingSettingView.findViewById(R.id.ed_monday_feeling);
		input3Ed = (EditText) feelingSettingView.findViewById(R.id.ed_tuestday_feeling);
		input4Ed = (EditText) feelingSettingView.findViewById(R.id.ed_wednesday_feeling);
		input5Ed = (EditText) feelingSettingView.findViewById(R.id.ed_thursday_feeling);
		input6Ed = (EditText) feelingSettingView.findViewById(R.id.ed_friday_feeling);
		input7Ed = (EditText) feelingSettingView.findViewById(R.id.ed_saturday_feeling);

		write1Bt.setOnClickListener(this);
		write2Bt.setOnClickListener(this);
		write3Bt.setOnClickListener(this);
		write4Bt.setOnClickListener(this);
		write5Bt.setOnClickListener(this);
		write6Bt.setOnClickListener(this);
		write7Bt.setOnClickListener(this);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {

		String sundayFeeling = PreferenceShareUtil.getFeeling(activity, PreferenceShareUtil.SUNDAY_FELLING);
		String mondayFeeling = PreferenceShareUtil.getFeeling(activity, PreferenceShareUtil.MONDAY_FELLING);
		String tuestdayFeeling = PreferenceShareUtil.getFeeling(activity, PreferenceShareUtil.TUESDAY_FELLING);
		String wendnesdayFeeling = PreferenceShareUtil.getFeeling(activity, PreferenceShareUtil.WEDNESDAY_FELLING);
		String thursdayFeeling = PreferenceShareUtil.getFeeling(activity, PreferenceShareUtil.THURSDAY_FELLING);
		String fridayFeeling = PreferenceShareUtil.getFeeling(activity, PreferenceShareUtil.FRIDAY_FELLING);
		String saturdayFeeling = PreferenceShareUtil.getFeeling(activity, PreferenceShareUtil.SATURDAY_FELLING);

		feeling1Tv.setText(sundayFeeling.equals("") == true ? activity.getString(R.string.sunday) : sundayFeeling);
		feeling2Tv.setText(mondayFeeling.equals("") == true ? activity.getString(R.string.monday) : mondayFeeling);
		feeling3Tv
				.setText(tuestdayFeeling.equals("") == true ? activity.getString(R.string.tuestday) : tuestdayFeeling);
		feeling4Tv.setText(wendnesdayFeeling.equals("") == true ? activity.getString(R.string.wednesday)
				: wendnesdayFeeling);
		feeling5Tv
				.setText(thursdayFeeling.equals("") == true ? activity.getString(R.string.thursday) : thursdayFeeling);
		feeling6Tv.setText(fridayFeeling.equals("") == true ? activity.getString(R.string.friday) : fridayFeeling);
		feeling7Tv
				.setText(saturdayFeeling.equals("") == true ? activity.getString(R.string.saturday) : saturdayFeeling);

		input1Ed.setText(sundayFeeling.equals("") == true ? activity.getString(R.string.sunday) : sundayFeeling);
		input2Ed.setText(mondayFeeling.equals("") == true ? activity.getString(R.string.monday) : mondayFeeling);
		input3Ed.setText(tuestdayFeeling.equals("") == true ? activity.getString(R.string.tuestday) : tuestdayFeeling);
		input4Ed.setText(wendnesdayFeeling.equals("") == true ? activity.getString(R.string.wednesday)
				: wendnesdayFeeling);
		input5Ed.setText(thursdayFeeling.equals("") == true ? activity.getString(R.string.thursday) : thursdayFeeling);
		input6Ed.setText(fridayFeeling.equals("") == true ? activity.getString(R.string.friday) : fridayFeeling);
		input7Ed.setText(saturdayFeeling.equals("") == true ? activity.getString(R.string.saturday) : saturdayFeeling);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_sunday_pen:

			if (feeling1Tv.getVisibility() == View.GONE) {
				feeling1Tv.setVisibility(View.VISIBLE);
				input1Ed.setVisibility(View.GONE);
				String feeling = input1Ed.getText().toString();
				if (!feeling.equals("")) {
					PreferenceShareUtil.saveFeeling(activity, PreferenceShareUtil.SUNDAY_FELLING, feeling);
					feeling1Tv.setText(feeling);
					sendFeelingBroadcast(1,feeling);
				}
			} else {
				feeling1Tv.setVisibility(View.GONE);
				input1Ed.setVisibility(View.VISIBLE);
			}

			break;
		case R.id.bt_monday_pen:
			if (feeling2Tv.getVisibility() == View.GONE) {
				feeling2Tv.setVisibility(View.VISIBLE);
				input2Ed.setVisibility(View.GONE);
				String feeling = input2Ed.getText().toString();
				if (!feeling.equals("")) {
					PreferenceShareUtil.saveFeeling(activity, PreferenceShareUtil.MONDAY_FELLING, feeling);
					feeling2Tv.setText(feeling);
					sendFeelingBroadcast(2,feeling);
				}

			} else {

				feeling2Tv.setVisibility(View.GONE);
				input2Ed.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.bt_tuestday_pen:
			if (feeling3Tv.getVisibility() == View.GONE) {
				feeling3Tv.setVisibility(View.VISIBLE);
				input3Ed.setVisibility(View.GONE);
				String feeling = input3Ed.getText().toString();
				if (!feeling.equals("")) {
					PreferenceShareUtil.saveFeeling(activity, PreferenceShareUtil.TUESDAY_FELLING, feeling);
					feeling3Tv.setText(feeling);
					sendFeelingBroadcast(3,feeling);
				}
			} else {

				feeling3Tv.setVisibility(View.GONE);
				input3Ed.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.bt_wednesday_pen:
			if (feeling4Tv.getVisibility() == View.GONE) {
				feeling4Tv.setVisibility(View.VISIBLE);
				input4Ed.setVisibility(View.GONE);
				String feeling = input4Ed.getText().toString();
				if (!feeling.equals("")) {
					PreferenceShareUtil.saveFeeling(activity, PreferenceShareUtil.WEDNESDAY_FELLING, feeling);
					feeling4Tv.setText(feeling);
					sendFeelingBroadcast(4,feeling);
				}
			} else {

				feeling4Tv.setVisibility(View.GONE);
				input4Ed.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.bt_thursday_pen:
			if (feeling5Tv.getVisibility() == View.GONE) {
				feeling5Tv.setVisibility(View.VISIBLE);
				input5Ed.setVisibility(View.GONE);
				String feeling = input5Ed.getText().toString();
				if (!feeling.equals("")) {
					PreferenceShareUtil.saveFeeling(activity, PreferenceShareUtil.THURSDAY_FELLING, feeling);
					feeling5Tv.setText(feeling);
					sendFeelingBroadcast(5,feeling);
				}
			} else {

				feeling5Tv.setVisibility(View.GONE);
				input5Ed.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.bt_friday_pen:
			if (feeling6Tv.getVisibility() == View.GONE) {
				feeling6Tv.setVisibility(View.VISIBLE);
				input6Ed.setVisibility(View.GONE);
				String feeling = input6Ed.getText().toString();
				if (!feeling.equals("")) {
					PreferenceShareUtil.saveFeeling(activity, PreferenceShareUtil.FRIDAY_FELLING, feeling);
					feeling6Tv.setText(feeling);
					sendFeelingBroadcast(6,feeling);
				}
			} else {

				feeling6Tv.setVisibility(View.GONE);
				input6Ed.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.bt_saturday_pen:
			if (feeling7Tv.getVisibility() == View.GONE) {
				feeling7Tv.setVisibility(View.VISIBLE);
				input7Ed.setVisibility(View.GONE);
				String feeling = input7Ed.getText().toString();
				if (!feeling.equals("")) {
					PreferenceShareUtil.saveFeeling(activity, PreferenceShareUtil.SATURDAY_FELLING, feeling);
					feeling7Tv.setText(feeling);

					sendFeelingBroadcast(7,feeling);
				}
			} else {

				feeling7Tv.setVisibility(View.GONE);
				input7Ed.setVisibility(View.VISIBLE);

				// activity.s
			}
			break;

		default:
			break;
		}
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
			activity.sendBroadcast(intent);
		}
	}

}