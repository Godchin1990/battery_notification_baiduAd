package minggo.battery.dao;

import java.util.List;

import minggo.battery.model.Alarmer;
import minggo.battery.model.SoundRecord;
import minggo.battery.model.User;
import minggo.battery.util.UserUtil;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DB连接工具类
 * @author minggo
 * @time 2014-6-23 S下午9:42:46
 */
public class DbOpenHelper extends SQLiteOpenHelper {
	
	
	public DbOpenHelper(Context context) {
		super(context,DBConfig.NAME, null, DBConfig.VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//创建录音表
		db.execSQL(SQLHelper.getCreateTable(DBConfig.TABLE_SOUND_RECORD,SoundRecord.class));
		db.execSQL(SQLHelper.getCreateTable(DBConfig.TABLE_USER,User.class));
		db.execSQL(SQLHelper.getCreateTable(DBConfig.TABLE_ALARM,Alarmer.class));
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion==1&&newVersion==2) {
			db.execSQL(SQLHelper.getCreateTable(DBConfig.TABLE_ALARM,Alarmer.class));
		}
		
	}

}
