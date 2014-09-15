package minggo.battery.adapter;

import java.util.Calendar;
import java.util.List;

import minggo.battery.R;
import minggo.battery.model.Alarmer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 喝水列表适配器
 * @author minggo
 * @date 2014-9-15 下午12:25:21
 */
public class DrinkAdapter extends BaseAdapter {

	private Context context;
	private List<Alarmer> alarmerList;
	
	public DrinkAdapter(Context context,List<Alarmer> alarmerList){
		this.alarmerList = alarmerList;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return alarmerList.size();
	}

	@Override
	public Object getItem(int position) {
		return alarmerList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder ;
		if (convertView==null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_drink, null);
			viewHolder.drinkTv = (TextView) convertView.findViewById(R.id.tv_drink_time);
			viewHolder.consequenceTv = (TextView) convertView.findViewById(R.id.tv_consequence);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(alarmerList.get(position).alarmTime);
		calendar.get(Calendar.HOUR_OF_DAY);
		calendar.get(Calendar.MINUTE);
		viewHolder.drinkTv.setText(calendar.get(Calendar.HOUR_OF_DAY)+":"+(calendar.get(Calendar.MINUTE)<10?"0"+calendar.get(Calendar.MINUTE):calendar.get(Calendar.MINUTE)));
		viewHolder.consequenceTv.setText("第"+position+1+"次");
		return convertView;
	}
	
	private class ViewHolder{
		TextView drinkTv;
		TextView consequenceTv;
	}

}
