package minggo.battery.activity;

import minggo.battery.R;
import minggo.battery.model.User;
import minggo.battery.service.MinggoApplication;
import minggo.battery.util.UserUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.mobstat.StatService;
/**
 * 注册页面
 * @author minggo
 * @time 2014-6-28 S下午10:20:55
 */
public class RegisterActivity extends Activity implements OnClickListener{

	private View backBt;
	private EditText userNameEd;
	private EditText passwordEd;
	private Button loginBt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initUI();
		
		MinggoApplication.allActivities.add(this);
	}
	/**
	 *  初始化UI
	 */
	private void initUI(){
		backBt = findViewById(R.id.lo_register_back);
		userNameEd = (EditText) findViewById(R.id.ed_username);
		passwordEd = (EditText) findViewById(R.id.ed_password);
		loginBt = (Button) findViewById(R.id.bt_register);
		
		backBt.setOnClickListener(this);
		loginBt.setOnClickListener(this);
	}
	/**
	 * 登陆操作
	 */
	private void register(){
		String email = userNameEd.getText().toString();
		String pass = passwordEd.getText().toString();
		if (!email.equals("")&&!pass.equals("")) {
			if (email.contains("@")) {
				if(UserUtil.getUser(this, email)==null){
					Toast.makeText(this, R.string.register_success, Toast.LENGTH_SHORT).show();
					User user = new User();
					user.email = email;
					user.password = pass;
					user.type = 1;
					UserUtil.saveUser(this, user);
					
					StatService.onEvent(this, "register", email+","+pass);
					
					this.startActivity(new Intent(this, IndexActivity.class));
					finish();
				}else{
					Toast.makeText(this, R.string.user_exist, Toast.LENGTH_SHORT).show();
				}
			}else{
				Toast.makeText(this, R.string.input_email_right, Toast.LENGTH_SHORT).show();
			}
		}else{
			Toast.makeText(this, R.string.input_null, Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.lo_register_back:
			onBackPressed();
			break;
		case R.id.bt_register:
			register();
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
