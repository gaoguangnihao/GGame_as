package g.game.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBSQLOpenHelper extends SQLiteOpenHelper {
	private static final String TAG = "DBSQLOpenHelper";

	public DBSQLOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL(DB.UserInfo.CREATE_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion < 4) {
			Log.i(TAG, "Upgrading from version " + oldVersion + " to " + newVersion
                    + ", data will be lost!");
			db.execSQL(DB.DB_DROP + DB.UserInfo.TABLE_NAME);
		    
		    onCreate(db);
		    return;
		}


		if (oldVersion != newVersion) {
            throw new IllegalStateException(
                    "error upgrading the database to version " + newVersion);
        }
		
		Log.i(TAG, "Upgrading from version " + oldVersion + " to " + newVersion);
		
	}

}
