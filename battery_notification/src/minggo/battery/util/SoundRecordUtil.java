package minggo.battery.util;

import java.util.List;

import minggo.battery.dao.DBConfig;
import minggo.battery.dao.DaoUtils;
import minggo.battery.dao.DbOpenHelper;
import minggo.battery.model.SoundRecord;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 录音数据库操作类
 * @author minggo
 * @time 2014-6-23 S下午9:48:30
 */
public class SoundRecordUtil {
	
	/**
	 * 保存录音
	 * @param context
	 * @param soundRecord
	 */
	public static void saveSubscriptionGiftType(Context context,SoundRecord soundRecord){
		SQLiteDatabase db = new DbOpenHelper(context).getWritableDatabase();
		db.replace(DBConfig.TABLE_SOUND_RECORD, null, DaoUtils.object2ContentValues(soundRecord));
		db.close();
	}
	/**
	 * 获取录音列表
	 * @param context
	 * @return
	 */
	public static List<SoundRecord> getSoundRecordList(Context context){
		List<SoundRecord> giftTypeList = null;
		SQLiteDatabase db = new DbOpenHelper(context).getReadableDatabase();
		Cursor query = db.query(DBConfig.TABLE_SOUND_RECORD,null,null,null,null, null, null);
		giftTypeList = DaoUtils.cursor2ObjectList(query, SoundRecord.class);
		db.close();
		return giftTypeList;
	}
	
	/**
	 * 获取某一时间的录音
	 * @param whichHour
	 * @return
	 */
	public static SoundRecord getSoundRecord(Context context,int whichHour){
		List<SoundRecord> giftTypeList = null;
		SQLiteDatabase db = new DbOpenHelper(context).getReadableDatabase();
		Cursor query = db.query(DBConfig.TABLE_SOUND_RECORD,null,"whichHour=?",new String[]{String.valueOf(whichHour)},null, null, null);
		giftTypeList = DaoUtils.cursor2ObjectList(query, SoundRecord.class);
		db.close();
		if (giftTypeList!=null&&!giftTypeList.isEmpty()) {
			return giftTypeList.get(0);
		}
		return null;
	}
}
