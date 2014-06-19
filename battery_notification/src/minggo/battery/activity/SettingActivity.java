package minggo.battery.activity;

import com.baidu.mobstat.StatService;

import minggo.battery.BatteryService;
import minggo.battery.R;
import minggo.battery.util.MinggoDate;
import minggo.battery.util.PreferenceShareUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * 设置页面
 * @author minggo
 * @time 2014-6-19 S上午12:17:07
 */
public class SettingActivity extends Activity implements OnClickListener{
	
	private View backV;
	private Button feelingBt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initUI();
	}
	/**
	 * 初始化UI
	 */
	private void initUI(){
		backV = findViewById(R.id.lo_setting_back);
		feelingBt = (Button) findViewById(R.id.bt_feeling_setting);
		backV.setOnClickListener(this);
		feelingBt.setOnClickListener(this);
		
		feelingBt.setSelected(!PreferenceShareUtil.getUseFeeling(this));
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_feeling_setting:
			boolean flag = !feelingBt.isSelected();
			System.out.println("选择了什么东西--->"+flag);
			feelingBt.setSelected(flag);
			PreferenceShareUtil.saveUseFeeling(this, !feelingBt.isSelected());
			StatService.onEvent(SettingActivity.this, "feeling_set", flag+"");
			break;
		case R.id.lo_setting_back:
			onBackPressed();
			break;

		default:
			break;
		}
	}
}
