package minggo.battery.fragment;

import java.util.zip.Inflater;

import minggo.battery.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 每天心情设置
 * @author minggo
 * @time 2014-6-16 S下午9:18:35
 */
public class FragmentFeelingSetting extends Fragment {

	private Activity activity;
	private View feelingSettingView;
	private LayoutInflater inflater;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		feelingSettingView = inflater.inflate(R.layout.fragment_feeling, container, false);
		return feelingSettingView;
	}
}
