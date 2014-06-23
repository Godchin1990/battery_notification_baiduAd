package minggo.battery.model;

import minggo.battery.annotation.Primarykey;

/**
 * 录音model
 * @author minggo
 * @time 2014-6-23 S下午9:38:50
 */
public class SoundRecord {
	
	@Primarykey
	public int whichHour;
	/**
	 * 保存路劲
	 */
	public String path;
	/**
	 * 录音时间
	 */
	public String longTime;
	/**
	 * 录音类型1：系统,2:用户
	 */
	public int type;
}
