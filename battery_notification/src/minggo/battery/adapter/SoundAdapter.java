package minggo.battery.adapter;

import java.util.List;

import com.baidu.mobstat.c;

import minggo.battery.R;
import minggo.battery.model.SoundRecord;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
/**
 * 声音列表
 * @author minggo
 * @time 2014-6-23 S下午11:17:01
 */
public class SoundAdapter extends BaseAdapter {

	private Context context;
	private List<SoundRecord> list;
	
	
	public SoundAdapter(Context context,List<SoundRecord> list){
		this.context = context;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView==null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_sound_list, null);
			viewHolder.timeTv = (TextView) convertView.findViewById(R.id.tv_user_and_time);
			viewHolder.tryListenIb = (ImageButton) convertView.findViewById(R.id.ib_try_listen);
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.timeTv.setText(list.get(position).whichHour+":00");
		return convertView;
	}
	
	private class ViewHolder{
		TextView timeTv;
		ImageButton tryListenIb;
	}
	
}
