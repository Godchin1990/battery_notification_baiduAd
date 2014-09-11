package minggo.battery.util;

import java.util.List;

import minggo.battery.dao.DBConfig;
import minggo.battery.dao.DaoUtils;
import minggo.battery.dao.DbOpenHelper;
import minggo.battery.model.Alarmer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 闹钟数据库操作器
 * @author minggo
 * @date 2014-9-11 下午3:13:57
 */
public class AlarmUtil {
	/**
	 * 保存闹钟
	 * @param context
	 * @param alarmer
	 * @return
	 */
	public static boolean saveOrUpdateAlarm(Context context,Alarmer alarmer){
		SQLiteDatabase db = new DbOpenHelper(context).getWritableDatabase();
		try {
			db.beginTransaction();
			if (alarmer!=null) {
				db.replace(DBConfig.TABLE_ALARM, null, DaoUtils.object2ContentValues(alarmer));
				db.setTransactionSuccessful();
				db.endTransaction();
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			if (db!=null&&db.isOpen()) {
				db.endTransaction();
				db.close();
				db = null;
			}
		}
		return true;
	}
	/**
	 * 修改闹钟
	 * @param context
	 * @param alarmer
	 * @return
	 */
	public static boolean deleteAlarm(Context context,int alarmerId){
		
		SQLiteDatabase db = new DbOpenHelper(context).getReadableDatabase();
		try {
			db.beginTransaction();
			int count = db.delete(DBConfig.TABLE_ALARM, "alarmerId=?", new String[] { alarmerId + "" });
			db.setTransactionSuccessful();
			db.endTransaction();
			if (count > 0) {
				// Log.i("database", "删除很多----->"+count);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (db != null) {
				db.endTransaction();
				db.close();
				db = null;
			}
		}
		return true;
		
	}
	
	/**
	 * 获取前20条快速提示搜索词
	 * @return
	 */
	public static List<Alarmer> queryAllAlarmerList(Context context){
		List<Alarmer> keyList = null;
		SQLiteDatabase db = new DbOpenHelper(context).getReadableDatabase();
		try {
			db.beginTransaction();
			Cursor query = db.query(DBConfig.TABLE_ALARM, null, null, null, null, null, null);
			keyList = DaoUtils.cursor2ObjectList(query, Alarmer.class);
			db.setTransactionSuccessful();
			db.endTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if (db!=null) {
				db.endTransaction();
				db.close();
				db = null;
			}
		}
		return keyList;
	}
}
