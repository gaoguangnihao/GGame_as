package g.game.db;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import g.game.vo.UserInfoVO;


public class BaseDBAdapter {
	
	public static DBSQLOpenHelper dbhelper;
	
	public static BaseDBAdapter _instance ;
	
	public static BaseDBAdapter instance(){
		if(_instance == null)
			_instance = new BaseDBAdapter(null);
		return _instance;
	}
	
	public BaseDBAdapter(Context context) {
		if(dbhelper == null)
			dbhelper = new DBSQLOpenHelper(context, "userInfo.db", null, DB.DB_VERSION);
	}
	
	public void closeDB(){
		if(dbhelper != null){
			dbhelper.close();
		}
	}
	
	

	public  void saveUserInfo(UserInfoVO userInfo){
		ContentValues cv = new ContentValues();
		cv.put(DB.UserInfo.COLUMN_ID, userInfo.getId());
		cv.put(DB.UserInfo.COLUMN_PASSW, userInfo.getPassword());
		cv.put(DB.UserInfo.COLUMN_NAME, userInfo.getName());
		cv.put(DB.UserInfo.COLUMN_TYPE, userInfo.getType());
		
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		db.delete(DB.UserInfo.TABLE_NAME, DB.UserInfo.COLUMN_ID + " = ?", new String[]{userInfo.getId()});
		db.insert(DB.UserInfo.TABLE_NAME, null, cv);
	}
	
	   public List<UserInfoVO> queryUserInfo(){
	    	List<UserInfoVO> userInfoList=new ArrayList<UserInfoVO>();
	    	String sql = "select * from " + DB.UserInfo.TABLE_NAME 
	    			+ " order by id";
	    	synchronized (DB._WRITELOCK) {
				SQLiteDatabase db = this.dbhelper.getReadableDatabase();
				Cursor cursor = null;
				try{
					cursor = db.rawQuery(sql, new String[]{});
					while (cursor.moveToNext()) {
						UserInfoVO userInfo=new UserInfoVO();
						userInfo.setId(cursor.getString(0));
						userInfo.setPassword(cursor.getString(1));
						userInfo.setName(cursor.getString(2));
						userInfo.setType(cursor.getInt(3));
						userInfoList.add(userInfo);
					}
				}catch(Exception e){     
					e.printStackTrace();
				}finally{
					if(db!=null){
						db.close();
					}
				}
			}
	    	return userInfoList;
	    }
	

}
