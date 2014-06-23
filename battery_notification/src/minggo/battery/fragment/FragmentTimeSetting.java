package minggo.battery.fragment;

import java.io.IOException;
import java.util.List;

import minggo.battery.R;
import minggo.battery.adapter.SoundAdapter;
import minggo.battery.model.SoundRecord;
import minggo.battery.model.User;
import minggo.battery.service.MinggoApplication;
import minggo.battery.util.PlaySound;
import minggo.battery.util.UserUtil;
import minggo.battery.util.VibratorUtil;
import minggo.battery.view.RecordButton;
import minggo.battery.view.RecordButton.OnEventListener;
import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ListView;
/**
 * 整点报时设置页面
 * @author minggo
 * @time 2014-6-16 S下午9:18:35
 */
public class FragmentTimeSetting extends Fragment {

	private Activity activity;
	private View timeSettinView;
	private LayoutInflater inflater;
	public static AssetManager assetManager;
	private RecordButton recordButton;
	
	private List<SoundRecord> soundRecordList;
	private ListView soundLv;
	private SoundAdapter soundAdapter;
	
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
		soundLv = (ListView)timeSettinView.findViewById(R.id.lv_alert_sounds);
		recordButton.setOnEventListener(new VoiceListener());
		
		return timeSettinView;
	}
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			getSoundList();
			refreshSoundListUI();
		}
		System.out.println("时间XXXXX");
	}
	@Override
	public View getView() {
		return super.getView();
	}
	
	/**
	 * 获取播放录音列表
	 */
	private void getSoundList(){
		User user = UserUtil.getUser(activity, MinggoApplication.EMAIL);
		if (user.useDefineSound==0) {
			Log.i("battery", "用户--->"+user.email);
			soundRecordList = ((MinggoApplication)activity.getApplication()).defaultSoundList;
			Log.i("battery", "声音列表--->"+soundRecordList.size());

			
		}
	}
	/**
	 * 刷新声音列表
	 */
	private void refreshSoundListUI(){
		if (soundAdapter==null) {
			soundAdapter = new SoundAdapter(activity, soundRecordList);
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
}
