package minggo.battery.util;

import java.util.List;

import minggo.battery.dao.DBConfig;
import minggo.battery.dao.DaoUtils;
import minggo.battery.dao.DbOpenHelper;
import minggo.battery.model.User;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 录音数据库操作类
 * @author minggo
 * @time 2014-6-23 S下午9:48:30
 */
public class UserUtil {
	
	/**
	 * 保存录音
	 * @param context
	 * @param user
	 */
	public static void saveUser(Context context,User user){
		
		SQLiteDatabase db = new DbOpenHelper(context).getWritableDatabase();
		db.replace(DBConfig.TABLE_USER, null, DaoUtils.object2ContentValues(user));
		db.close();
	}
	/**
	 * 获取录音列表
	 * @param context
	 * @return
	 */
	public static List<User> getUserList(Context context){
		List<User> userList = null;
		SQLiteDatabase db = new DbOpenHelper(context).getReadableDatabase();
		Cursor query = db.query(DBConfig.TABLE_USER,null,null,null,null, null, null);
		userList = DaoUtils.cursor2ObjectList(query, User.class);
		db.close();
		return userList;
	}
	/**
	 * 判断是否注册过
	 * @param context
	 * @return
	 */
	public static boolean isExistUser(Context context){
		List<User> userList = null;
		SQLiteDatabase db = new DbOpenHelper(context).getReadableDatabase();
		Cursor query = db.query(DBConfig.TABLE_USER,null,"type=?",new String[]{"1"},null, null, null);
		userList = DaoUtils.cursor2ObjectList(query, User.class);
		db.close();
		if (userList!=null&&!userList.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取某一时间的录音
	 * @param email
	 * @return
	 */
	public static User getUser(Context context,String email){
		List<User> userList = null;
		SQLiteDatabase db = new DbOpenHelper(context).getReadableDatabase();
		Cursor query = db.query(DBConfig.TABLE_USER,null,"email=?",new String[]{email},null, null, null);
		userList = DaoUtils.cursor2ObjectList(query, User.class);
		db.close();
		if (userList!=null&&!userList.isEmpty()) {
			return userList.get(0);
		}
		return null;
	}
}
