package minggo.battery.activity;

import java.util.List;

import minggo.battery.adapter.DrinkAdapter;
import minggo.battery.model.Alarmer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.baidu.mobstat.StatService;

/**
 * 喝水提醒
 * @author minggo
 * @time 2014-9-14 S上午8:51:05
 */
public class DrinkActivity extends Activity implements OnClickListener,OnItemClickListener{

	private View backV;
	private Button addBt;
	private Button scanBt;
	private Button saveBt;
	private ImageView themeIv;
	private EditText titleTv;
	private ListView drinkLv;
	private List<Alarmer> alarmList;
	private DrinkAdapter drinkAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initUI();
	}
	
	private void initUI(){
		
	}
	
	private void initData(){
		
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case 1:
			
			break;

		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
	}
}
