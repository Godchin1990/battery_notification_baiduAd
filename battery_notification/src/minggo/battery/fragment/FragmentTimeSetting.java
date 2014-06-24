package minggo.battery.fragment;

import java.io.IOException;
import java.util.List;

import minggo.battery.R;
import minggo.battery.adapter.SoundAdapter;
import minggo.battery.adapter.SoundAdapter.TryListener;
import minggo.battery.model.SoundRecord;
import minggo.battery.model.User;
import minggo.battery.service.MinggoApplication;
import minggo.battery.util.PlaySound;
import minggo.battery.util.PlaySound.FinishListen;
import minggo.battery.util.UserUtil;
import minggo.battery.util.VibratorUtil;
import minggo.battery.view.RecordButton;
import minggo.battery.view.RecordButton.OnEventListener;
import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 整点报时设置页面
 * @author minggo
 * @time 2014-6-16 S下午9:18:35
 */
public class FragmentTimeSetting extends Fragment implements TryListener,OnClickListener{

	private Activity activity;
	private View timeSettinView;
	private LayoutInflater inflater;
	public static AssetManager assetManager;
	private RecordButton recordButton;
	private ImageButton tryButton;
	
	private List<SoundRecord> soundRecordList;
	private ListView soundLv;
	private SoundAdapter soundAdapter;
	private TextView userTryTv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
		assetManager = activity.getResources().getAssets();
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		timeSettinView = inflater.inflate(R.layout.fragment_alert, container, false);
		recordButton = (RecordButton)timeSettinView.findViewById(R.id.bt_record_sound);
		tryButton = (ImageButton)timeSettinView.findViewById(R.id.ib_try_top);
		soundLv = (ListView)timeSettinView.findViewById(R.id.lv_alert_sounds);
		userTryTv = (TextView)timeSettinView.findViewById(R.id.tv_user_try);
		tryButton.setOnClickListener(this);
		
		return timeSettinView;
	}
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			getSoundList();
			refreshSoundListUI();
		}
	}
	@Override
	public View getView() {
		return super.getView();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_try_top:
			
			break;

		default:
			break;
		}
	}
	
	/**
	 * 获取播放录音列表
	 */
	private void getSoundList(){
		User user = UserUtil.getUser(activity, MinggoApplication.EMAIL);
		if (user.useDefineSound==0) {
			soundRecordList = ((MinggoApplication)activity.getApplication()).defaultSoundList;
			recordButton.setEnabled(false);
			tryButton.setEnabled(false);
			recordButton.setOnEventListener(new VoiceListener(),false);
			userTryTv.setTextColor(activity.getResources().getColor(R.color.white));
		}else{
			recordButton.setOnEventListener(new VoiceListener(),true);
			userTryTv.setTextColor(activity.getResources().getColor(R.color.index_navi_bt_normal));
			
		}
	}
	/**
	 * 刷新声音列表
	 */
	private void refreshSoundListUI(){
		if (soundAdapter==null) {
			soundAdapter = new SoundAdapter(activity, soundRecordList,this);
			soundLv.setAdapter(soundAdapter);
		}else{
			soundAdapter.notifyDataSetChanged();
		}
	}
	
	
	/**
	 * 实现自定义的录音按钮的开始录音和结束录音
	 * @author minggo
	 * @created 2013-2-23下午12:39:56
	 */
	public class VoiceListener implements OnEventListener{

		@Override
		public void onFinishedRecord(String audioPath, int time) {
			try {
				PlaySound.play("sound/qrcode_completed.mp3", assetManager);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Log.i("record", "录音完成");
			
		}

		@Override
		public void onStartRecord() {
			try {
				VibratorUtil.Vibrate(activity, (long) 100);
				PlaySound.play("sound/qrcode_completed.mp3", assetManager);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	private Handler handler = new Handler(new Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			return false;
		}
	});
	
	private boolean isRepeat0 = true;
	private int currIndex = -1;
	private View v;
	@Override
	public void onTryClick(final View v,int position) {
		
		if (currIndex!=-1&&currIndex!=position) {
			PlaySound.stopVoice();
			handler.removeCallbacks(runnable);
			if (this.v!=null) {
				((ImageButton)this.v).setImageResource(R.drawable.chatfrom_voice_playing_f3);
			}
			
		}
		Log.i("battery", "currIndex-->"+currIndex+",position-->"+position+",isRepeat-->"+isRepeat0);
		if (currIndex!=position||(currIndex==position&&!isRepeat0)) {
			this.v = v;
			currIndex = position;
			isRepeat0 = true;
			
			try {
				handler.post(runnable);
				PlaySound.playVoice(soundRecordList.get(position).path,activity.getResources().getAssets(),new FinishListen() {
					
					@Override
					public void onFinish() {
						isRepeat0 = false;
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			if (isRepeat0) {
				PlaySound.stopVoice();
				handler.removeCallbacks(runnable);
				if (this.v!=null) {
					((ImageButton)this.v).setImageResource(R.drawable.chatfrom_voice_playing_f3);
				}
				isRepeat0 = false;
			}
		}
	}
	
	private Runnable runnable = new Runnable() {
		int i = 0;
		@Override
		public void run() {
			if (isRepeat0==true) {
				if (i==0) {
					((ImageButton)v).setImageResource(R.drawable.chatfrom_voice_playing_f1);
				}else if(i==1){
					((ImageButton)v).setImageResource(R.drawable.chatfrom_voice_playing_f2);
				}else if(i==3){
					((ImageButton)v).setImageResource(R.drawable.chatfrom_voice_playing_f3);
					i=-1;
				}
				i++;
				handler.postDelayed(this,250);
			}else{
				isRepeat0 = false;
				handler.removeCallbacks(this);
				((ImageButton)v).setImageResource(R.drawable.chatfrom_voice_playing_f3);
			}
		}
	};

	
	
}
