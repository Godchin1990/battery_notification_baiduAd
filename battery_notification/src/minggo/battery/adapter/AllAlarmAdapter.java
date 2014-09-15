package minggo.battery.adapter;

import java.util.Calendar;
import java.util.List;

import minggo.battery.R;
import minggo.battery.model.Alarmer;
import minggo.battery.reciever.AlarmerReciever;
import minggo.battery.util.MinggoDate;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 所有闹钟的适配器
 * @author minggo
 * @date 2014-9-15 下午4:38:22
 */
public class AllAlarmAdapter extends BaseAdapter {

	private List<Alarmer> alarmerList;
	private Context context;
	private String drinkTime;
	
	public AllAlarmAdapter(Context context,List<Alarmer> alarmerList,String drinkTime){
		this.context = context;
		this.alarmerList = alarmerList;
		this.drinkTime = drinkTime;
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
		ViewHolder viewHolder  = null;
		if (convertView==null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_all_alarm, null);
			viewHolder.typeIv = (ImageView) convertView.findViewById(R.id.iv_alarm_type);
			viewHolder.titleTv = (TextView) convertView.findViewById(R.id.tv_alarm_title);
			viewHolder.repeatTimeTv = (TextView) convertView.findViewById(R.id.tv_repeat_time);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		if (alarmerList.get(position).type == AlarmerReciever.DEFINE_ALARM) {
			viewHolder.typeIv.setImageResource(R.drawable.emoji_298);
			viewHolder.repeatTimeTv.setText("一次 "+MinggoDate.toString(alarmerList.get(position).alarmTime));
		}else if (alarmerList.get(position).type == AlarmerReciever.BIRTHDAY_ALARM) {
			viewHolder.typeIv.setImageResource(R.drawable.emoji_103);
			viewHolder.repeatTimeTv.setText("一次 "+MinggoDate.toString(alarmerList.get(position).alarmTime));
			
		}else if (alarmerList.get(position).type == AlarmerReciever.DRINK_ALARM) {
			viewHolder.typeIv.setImageResource(R.drawable.smiley_60);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis((alarmerList.get(position).alarmTime));
			viewHolder.repeatTimeTv.setText("每天 "+drinkTime);
		}
		viewHolder.titleTv.setText(alarmerList.get(position).title);
		return convertView;
	}
	
	private class ViewHolder{
		TextView titleTv;
		ImageView typeIv;
		TextView repeatTimeTv;
	}
}
