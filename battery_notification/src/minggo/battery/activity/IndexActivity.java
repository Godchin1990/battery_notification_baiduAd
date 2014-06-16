package minggo.battery.activity;

import java.util.ArrayList;
import java.util.List;

import minggo.battery.BatteryService;
import minggo.battery.R;
import minggo.battery.adapter.BatteryPagerAdpater;
import minggo.battery.fragment.FragmentFeelingSetting;
import minggo.battery.fragment.FragmentGame;
import minggo.battery.fragment.FragmentTimeSetting;
import minggo.battery.util.ImageUtils;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
/**
 * 首页
 * @author minggo
 * @time 2014-6-16 S下午9:15:33
 */
public class IndexActivity extends FragmentActivity implements OnClickListener {
	
	private List<Fragment> fragmentList = new ArrayList<Fragment>();
	private ImageView naviBottomIv;
	private int currIndex = 1;// 当前页卡编号
	private int defaultIndex = 1;// 默认的第一页面

	private int bmpW;// 动画图片宽度
	public ViewPager viewPager;
	private BatteryPagerAdpater pagerAdpater;

	private Button feelingBt;
	private Button seziBt;
	private Button alertBt;
	private FragmentFeelingSetting feelingSettingFgm;
	private FragmentGame gameFgm;
	private FragmentTimeSetting timeSettingFgm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		this.startService(new Intent(this, BatteryService.class));
		initView();
	}
	
	/**
	 * 初始化UI
	 */
	private void initView() {

		initImageView();

		viewPager = (ViewPager) findViewById(R.id.index_viewPager);
		alertBt = (Button) findViewById(R.id.bt_index_alert);
		seziBt = (Button) findViewById(R.id.bt_index_seii);
		feelingBt = (Button) findViewById(R.id.bt_index_feeling);

		alertBt.setOnClickListener(this);
		seziBt.setOnClickListener(this);
		feelingBt.setOnClickListener(this);
		
		timeSettingFgm = new FragmentTimeSetting();
		gameFgm = new FragmentGame();
		feelingSettingFgm = new FragmentFeelingSetting();
		
		
		fragmentList.add(timeSettingFgm);
		fragmentList.add(gameFgm);
		fragmentList.add(feelingSettingFgm);

		pagerAdpater = new BatteryPagerAdpater(getSupportFragmentManager(), fragmentList);
		viewPager.setAdapter(pagerAdpater);
		viewPager.setCurrentItem(currIndex);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		viewPager.setOffscreenPageLimit(2);
	}
	
	public class MyOnPageChangeListener implements OnPageChangeListener {
		public void onPageScrollStateChanged(int position) {
		}

		public void onPageScrolled(int position, float arg1, int arg2) {
			
		}

		public void onPageSelected(int position) {
			translate(position);
		}
	}

	/**
	 * 导航底层图片移动当当前选择页面
	 * 
	 * @param tab
	 */
	private void translate(final int tab) {

		float startX = bmpW * (currIndex - defaultIndex);
		float endX = bmpW * (tab - defaultIndex);

		alertBt.setSelected(tab == 0 ? true : false);
		seziBt.setSelected(tab == 1 ? true : false);
		feelingBt.setSelected(tab == 2 ? true : false);

		Animation animation = new TranslateAnimation(startX, endX, 0, 0);
		currIndex = tab;
		animation.setFillAfter(true);// True:图片停在动画结束位置
		animation.setDuration(300);
		naviBottomIv.startAnimation(animation);
	}
	
	/**
	 * 初始化导航条
	 */
	private void initImageView() {

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度

		naviBottomIv = (ImageView) findViewById(R.id.cursor);
		//Bitmap bm = ((BitmapDrawable)naviBottomIv.getDrawable()).getBitmap();
		Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.index_navi_bottom);
		bm = ImageUtils.zoomBitmap(bm, screenW / 3, bm.getHeight());
		bmpW = bm.getWidth();// 获取图片宽度
		Matrix matrix = new Matrix();
		matrix.postTranslate(defaultIndex * (screenW / 3), 0);
		naviBottomIv.setImageMatrix(matrix);// 设置动画初始位置
		naviBottomIv.setImageBitmap(bm);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.bt_index_alert:
			viewPager.setCurrentItem(0);
			break;
		case R.id.bt_index_seii:
			viewPager.setCurrentItem(1);
			break;
		case R.id.bt_index_feeling:
			viewPager.setCurrentItem(2);
			break;
		default:
			break;
		}
	}

}
