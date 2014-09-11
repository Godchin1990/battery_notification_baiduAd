package minggo.battery.model;

import minggo.battery.annotation.Primarykey;

/**
 * 闹钟
 * @author minggo
 * @date 2014-9-11 下午2:51:01
 */
public class Alarmer {
	@Primarykey
	public int alarmerId;
	public String title;
	public int repeat;
	public long alarmTime;
	public int type;//1:自定义提醒;2.生日提醒;3.喝水提醒
	public String soundPath;
}
