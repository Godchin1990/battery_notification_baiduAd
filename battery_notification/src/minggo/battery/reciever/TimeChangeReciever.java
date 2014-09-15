package minggo.battery.reciever;

import java.io.IOException;

import minggo.battery.model.SoundRecord;
import minggo.battery.model.User;
import minggo.battery.util.MinggoDate;
import minggo.battery.util.PlaySound;
import minggo.battery.util.PreferenceShareUtil;
import minggo.battery.util.SoundRecordUtil;
import minggo.battery.util.UserUtil;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;

/**
 * 时间广播监听器
 * 
 * @author minggo
 * @date 2013-8-7下午01:01:13
 */
public class TimeChangeReciever extends BroadcastReceiver {

	private MinggoDate date;
	private User user;
	private Vibrator vibrator;

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();

		if (action.equals(Intent.ACTION_TIME_TICK)) {
			// System.out.println("整点报时--->"+date.get24Hour()+"点");
			if (PreferenceShareUtil.getZhengTimeFlag(context) && !PreferenceShareUtil.getDefineSoundFlag(context)) {
				if (vibrator == null) {
					vibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
				}
				date = new MinggoDate();
				if (date.getMinutes() == 00 && date.get24Hour() > 5) {
					if (PreferenceShareUtil.getShockFlag(context)) {
						vibrator.vibrate(500);
					}
					// System.out.println("整点报时--->"+date.get24Hour()+"点");
					try {
						PlaySound.play("sound/" + date.get24Hour() + ".mp3", context.getAssets());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} else if (PreferenceShareUtil.getZhengTimeFlag(context) && PreferenceShareUtil.getDefineSoundFlag(context)) {
				//System.out.println("使用自己的声音");
				if (vibrator == null) {
					vibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
				}

				date = new MinggoDate();

				if (date.getMinutes() == 00) {
					SoundRecord soundRecord = SoundRecordUtil.getSoundRecord(context, date.get24Hour());

					if (soundRecord != null) {
						//System.out.println("已录音自己的声音");
						if (PreferenceShareUtil.getShockFlag(context)) {
							vibrator.vibrate(500);
						}
						try {
							PlaySound.playVoice2(soundRecord.path, context.getResources().getAssets(), 2);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						if (date.getMinutes() == 00 && date.get24Hour() > 5) {
							if (PreferenceShareUtil.getShockFlag(context)) {
								vibrator.vibrate(500);
							}
							//System.out.println("没有录音整点报时--->"+date.get24Hour()+"点");
							try {
								PlaySound.play("sound/" + date.get24Hour() + ".mp3", context.getAssets());
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}

		}
	}

}
