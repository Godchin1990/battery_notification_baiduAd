package minggo.battery.activity;

import minggo.battery.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.mobstat.StatService;
/**
 * 自定义闹钟
 * @author minggo
 * @date 2014-9-10 下午3:28:33
 */
public class DefineAlarmActivity extends Activity implements OnClickListener{
	
	private EditText titleEd;
	private View dateSelectV;
	private View timeSelectV;
	private View backV;
	private Button sacnBt;
	private Button saveBt;
	private String title;
	
	public static final int REQUEST_CODE_DATE = 10001;
	public static final int REQUEST_CODE_TIME = 10002;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_define_alarm);
		initUI();
	}
	/**
	 * 初始化UI
	 */
	private void initUI(){
		titleEd = (EditText) findViewById(R.id.ed_title);
		dateSelectV = findViewById(R.id.lo_alarm_date);
		timeSelectV = findViewById(R.id.lo_time_select);
		backV = findViewById(R.id.lo_define_alarm_back);
		sacnBt = (Button) findViewById(R.id.bt_scan);
		saveBt = (Button) findViewById(R.id.bt_save);
		
		dateSelectV.setOnClickListener(this);
		backV.setOnClickListener(this);
		timeSelectV.setOnClickListener(this);
		sacnBt.setOnClickListener(this);
		saveBt.setOnClickListener(this);
	}
	/**
	 * 保存数据
	 */
	private void save(){
		title = titleEd.getText().toString();
		if (title!=null&&!title.equals("")) {
			
		}else{
			Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
			Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
			titleEd.setAnimation(animation);
			titleEd.startAnimation(animation);
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.lo_alarm_date:
			Intent intent0 = new Intent(this, TimeSelectActivity.class);
			intent0.putExtra("title", "提醒日期选择");
			intent0.putExtra("type", "date");
			startActivity(intent0);
			//overridePendingTransition(R.anim.translate_up, R.anim.translate_down);
			break;
		case R.id.lo_time_select:
			Intent intent1 = new Intent(this, TimeSelectActivity.class);
			intent1.putExtra("title", "提醒时间选择");
			intent1.putExtra("type", "time");
			startActivity(intent1);
			//overridePendingTransition(R.anim.translate_up, R.anim.translate_down);
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
		
		if (requestCode==REQUEST_CODE_DATE) {
			
			
		}else if (requestCode==REQUEST_CODE_TIME) {
			
			
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
