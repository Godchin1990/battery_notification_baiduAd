package minggo.battery.fragment;

import minggo.battery.R;
import minggo.battery.activity.FeelingSettingActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
/**
 * 个人设定
 * @author minggo
 * @time 2014-9-9 S下午10:33:04
 */
public class FragmentPerson extends Fragment implements OnClickListener{

	private Activity activity;
	private View mainView;
	private Button feelSetBt;
	private Button drinkBt;
	private Button birthBt;
	private Button anyBt;
	private Button zhengdianBt;
	private Button defineSoundBt;
	private Button lowpowerBt;
	private Button useFeelBt;
	private Button shcokBt;
	private Button defaultBt;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mainView = inflater.inflate(R.layout.fragment_person, container,false);
		initUI();
		return mainView;
	}
	
	private void initUI(){
		feelSetBt = (Button) mainView.findViewById(R.id.bt_feeling);
		drinkBt = (Button) mainView.findViewById(R.id.bt_drink);
		birthBt = (Button) mainView.findViewById(R.id.bt_birthday);
		anyBt = (Button) mainView.findViewById(R.id.bt_any);
		zhengdianBt = (Button) mainView.findViewById(R.id.bt_alert);
		defineSoundBt = (Button) mainView.findViewById(R.id.bt_alert_define);
		lowpowerBt = (Button) mainView.findViewById(R.id.bt_low_power);
		useFeelBt = (Button) mainView.findViewById(R.id.bt_feeling_use);
		shcokBt = (Button) mainView.findViewById(R.id.bt_shock);
		defaultBt = (Button) mainView.findViewById(R.id.bt_use_default);
		
		defaultBt.setOnClickListener(this);
		drinkBt.setOnClickListener(this);
		feelSetBt.setOnClickListener(this);
		birthBt.setOnClickListener(this);
		anyBt.setOnClickListener(this);
		zhengdianBt.setOnClickListener(this);
		defineSoundBt.setOnClickListener(this);
		lowpowerBt.setOnClickListener(this);
		shcokBt.setOnClickListener(this);
		useFeelBt.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_feeling:
			activity.startActivity(new Intent(activity, FeelingSettingActivity.class));
			break;
		case R.id.bt_drink:
			
			break;
		case R.id.bt_birthday:
			
			break;
		case R.id.bt_any:
			
			break;
		case R.id.bt_alert:
			
			break;
		case R.id.bt_alert_define:
			
			break;
		case R.id.bt_low_power:
			
			break;
		case R.id.bt_feeling_use:
			
			break;
		case R.id.bt_shock:
			
			break;
		case R.id.bt_use_default:
			
			break;
		default:
			break;
		}
	}
	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity =activity;
	}
	@Override
	public void onDetach() {
		super.onDetach();
		activity = null;
	}
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
	}
	@Override
	public void onResume() {
		super.onResume();
	}

	
}
