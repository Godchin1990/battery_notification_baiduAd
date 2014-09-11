package minggo.battery.dao;

/**
 * 数据库配置
 * 
 * @author minggo
 * @time 2014-6-23 S下午9:32:35
 */
public class DBConfig {
	// 数据库名称
	public static final String NAME = "minggo.battery";
	// 数据库版本
	public static final int VERSION = 2;
	/**
	 * 录音保存数据库
	 */
	public static final String TABLE_SOUND_RECORD = "t_sound";
	/**
	 * 用户数据库
	 */
	public static final String TABLE_USER = "t_user";
	/**
	 * 闹钟数据表
	 */
	public static final String TABLE_ALARM = "t_alarmer";
}
