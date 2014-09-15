package minggo.battery.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 处理冲突Scrollview
 * 
 * @author minggo
 * @date 2014-9-15 下午12:04:08
 */
public class BatteryScrollView extends ScrollView {

	public BatteryScrollView(Context context) {
		super(context);
	}

	public BatteryScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BatteryScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		final int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			super.onTouchEvent(ev);
			break;

		case MotionEvent.ACTION_MOVE:
			return false; // redirect MotionEvents to ourself

		case MotionEvent.ACTION_CANCEL:
			super.onTouchEvent(ev);
			break;

		case MotionEvent.ACTION_UP:
			return false;

		default:
			break;
		}

		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		super.onTouchEvent(ev);
		return true;
	}
}