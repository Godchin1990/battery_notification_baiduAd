package minggo.battery.reciever;

import minggo.battery.activity.AlarmWakeActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 闹钟广播接收器
 * @author minggo
 * @date 2014-9-11 下午2:58:07
 */
public class AlarmerReciever extends BroadcastReceiver {
	
	public static final String MINGGO_ALARM_ACTION = "minggo.alarm.action";
	public static final int DEFINE_ALARM = 1;
	public static final int BIRTHDAY_ALARM = 2;
	public static final int DRINK_ALARM = 3;
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("闹钟进来了");
		Intent intent2 = new Intent(context, AlarmWakeActivity.class);
		intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		intent.putExtra("alarm", intent.getSerializableExtra("alarm"));
		context.startActivity(intent2);
	}

}
