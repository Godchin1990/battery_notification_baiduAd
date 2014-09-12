package minggo.battery.fragment;

import minggo.battery.R;
import minggo.battery.activity.DefineAlarmActivity;
import minggo.battery.activity.FeelingSettingActivity;
import minggo.battery.reciever.AlarmerReciever;
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
		drinkBt.setOnClickListener(this);
		feelSetBt.setOnClickListener(this);
		birthBt.setOnClickListener(this);
		anyBt.setOnClickListener(this);
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
			Intent intent2= new Intent(activity, DefineAlarmActivity.class);
			intent2.putExtra("alarmType", AlarmerReciever.BIRTHDAY_ALARM);
			activity.startActivity(intent2);
			break;
		case R.id.bt_any:
			Intent intent1= new Intent(activity, DefineAlarmActivity.class);
			intent1.putExtra("alarmType", AlarmerReciever.DEFINE_ALARM);
			activity.startActivity(intent1);
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
