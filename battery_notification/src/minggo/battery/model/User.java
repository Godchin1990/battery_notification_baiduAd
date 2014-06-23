package minggo.battery.model;

import minggo.battery.annotation.Primarykey;

/**
 * 用户表
 * @author minggo
 * @time 2014-6-23 S下午10:10:48
 */
public class User {
	
	@Primarykey
	public String email;
	public String name;
	public String telephone;
	public String password;
	/**
	 * 使用自定义录音1：是 0：不是
	 */
	public int useDefineSound;
	/**
	 * 使用自定义心情提醒1:是0:否
	 */
	public int useDefinFeeling;
	/**
	 * 用户类型1：注册用户 0：系统用户
	 */
	public int type;
	
	
}
