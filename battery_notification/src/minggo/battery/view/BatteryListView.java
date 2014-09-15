package minggo.battery.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 处理Scrollview冲突的listview
 * @author minggo
 * @date 2014-9-15 下午3:04:20
 */
public class BatteryListView extends ListView {
	public BatteryListView(Context context) {
		super(context);
	}

	public BatteryListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BatteryListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}