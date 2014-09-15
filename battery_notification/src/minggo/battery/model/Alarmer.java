package minggo.battery.model;

import java.io.Serializable;

import minggo.battery.annotation.AutoIncrement;
import minggo.battery.annotation.Exclude;
import minggo.battery.annotation.Primarykey;

/**
 * 闹钟
 * @author minggo
 * @date 2014-9-11 下午2:51:01
 */
public class Alarmer implements Serializable {
	/**
	 * 
	 */
	@Exclude
	private static final long serialVersionUID = -4491419004339408810L;
	@AutoIncrement
	@Primarykey
	public int alarmerId;
	public String title;
	public int repeat;
	public long alarmTime;
	public int type;//1:自定义提醒;2.生日提醒;3.喝水提醒
	public String soundPath;
	
	public int sound;//1：声音提醒 2:不声音提醒
	public int shock;//1:震动提醒 2:不震动提醒
}
