package minggo.battery.fragment;

import minggo.battery.R;
import android.app.Activity;
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
 * @author minggo
 * @time 2014-6-16 S下午9:18:35
 */
public class FragmentFeelingSetting extends Fragment implements OnClickListener{

	private Activity activity;
	private View feelingSettingView;
	private LayoutInflater inflater;
	
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
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		feelingSettingView = inflater.inflate(R.layout.fragment_feeling, container, false);
		initUI();
		return feelingSettingView;
	}
	/**
	 * 初始化UI
	 */
	private void initUI(){
		write1Bt = (Button) feelingSettingView.findViewById(R.id.bt_sunday_pen);
		write2Bt = (Button) feelingSettingView.findViewById(R.id.bt_monday_pen);
		write3Bt = (Button) feelingSettingView.findViewById(R.id.bt_tuestday_pen);
		write4Bt = (Button) feelingSettingView.findViewById(R.id.bt_wednesday_pen);
		write5Bt = (Button) feelingSettingView.findViewById(R.id.bt_thursday_pen);
		write6Bt = (Button) feelingSettingView.findViewById(R.id.bt_friday_pen);
		write7Bt = (Button) feelingSettingView.findViewById(R.id.bt_saturday_pen);
		
		feeling1Tv = (TextView)feelingSettingView.findViewById(R.id.tv_sunday_feeling);
		feeling2Tv = (TextView)feelingSettingView.findViewById(R.id.tv_monday_feeling);
		feeling3Tv = (TextView)feelingSettingView.findViewById(R.id.tv_tuestday_feeling);
		feeling4Tv = (TextView)feelingSettingView.findViewById(R.id.tv_wednesday_feeling);
		feeling5Tv = (TextView)feelingSettingView.findViewById(R.id.tv_thursday_feeling);
		feeling6Tv = (TextView)feelingSettingView.findViewById(R.id.tv_friday_feeling);
		feeling7Tv = (TextView)feelingSettingView.findViewById(R.id.tv_saturday_feeling);
		
		input1Ed = (EditText)feelingSettingView.findViewById(R.id.ed_sunday_feeling);
		input2Ed = (EditText)feelingSettingView.findViewById(R.id.ed_monday_feeling);
		input3Ed = (EditText)feelingSettingView.findViewById(R.id.ed_tuestday_feeling);
		input4Ed = (EditText)feelingSettingView.findViewById(R.id.ed_wednesday_feeling);
		input5Ed = (EditText)feelingSettingView.findViewById(R.id.ed_thursday_feeling);
		input6Ed = (EditText)feelingSettingView.findViewById(R.id.ed_friday_feeling);
		input7Ed = (EditText)feelingSettingView.findViewById(R.id.ed_saturday_feeling);
		
		write1Bt.setOnClickListener(this);
		write2Bt.setOnClickListener(this);
		write3Bt.setOnClickListener(this);
		write4Bt.setOnClickListener(this);
		write5Bt.setOnClickListener(this);
		write6Bt.setOnClickListener(this);
		write7Bt.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_sunday_pen:
			
			if (feeling1Tv.getVisibility()==View.GONE) {
				feeling1Tv.setVisibility(View.VISIBLE);
				input1Ed.setVisibility(View.GONE);
				
			}else{
				feeling1Tv.setVisibility(View.GONE);
				input1Ed.setVisibility(View.VISIBLE);
			}
			
			break;
		case R.id.bt_monday_pen:
			if (feeling2Tv.getVisibility()==View.GONE) {
				feeling2Tv.setVisibility(View.VISIBLE);
				input2Ed.setVisibility(View.GONE);
				
			}else{
				feeling2Tv.setVisibility(View.GONE);
				input2Ed.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.bt_tuestday_pen:
			if (feeling3Tv.getVisibility()==View.GONE) {
				feeling3Tv.setVisibility(View.VISIBLE);
				input3Ed.setVisibility(View.GONE);
				
			}else{
				feeling3Tv.setVisibility(View.GONE);
				input3Ed.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.bt_wednesday_pen:
			if (feeling4Tv.getVisibility()==View.GONE) {
				feeling4Tv.setVisibility(View.VISIBLE);
				input4Ed.setVisibility(View.GONE);
				
			}else{
				feeling4Tv.setVisibility(View.GONE);
				input4Ed.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.bt_thursday_pen:
			if (feeling5Tv.getVisibility()==View.GONE) {
				feeling5Tv.setVisibility(View.VISIBLE);
				input5Ed.setVisibility(View.GONE);
				
			}else{
				feeling5Tv.setVisibility(View.GONE);
				input5Ed.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.bt_friday_pen:
			if (feeling6Tv.getVisibility()==View.GONE) {
				feeling6Tv.setVisibility(View.VISIBLE);
				input6Ed.setVisibility(View.GONE);
				
			}else{
				feeling6Tv.setVisibility(View.GONE);
				input6Ed.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.bt_saturday_pen:
			if (feeling7Tv.getVisibility()==View.GONE) {
				feeling7Tv.setVisibility(View.VISIBLE);
				input7Ed.setVisibility(View.GONE);
				
			}else{
				feeling7Tv.setVisibility(View.GONE);
				input7Ed.setVisibility(View.VISIBLE);
			}
			break;

		default:
			break;
		}
	}
	
}
