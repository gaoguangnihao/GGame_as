package g.game.db;

public class DB {
	
	public static final int DB_VERSION = 7;
    public static final String DB_DROP = "DROP TABLE IF EXISTS ";
    
    public static final byte[] _WRITELOCK = new byte[0];   
    
    public static final class UserInfo{
    	public static final String TABLE_NAME = "UserInfo";
    	public static final String COLUMN_ID = "id";	// id
        public static final String COLUMN_PASSW = "password"; // password
        public static final String COLUMN_NAME = "name"; // name
        public static final String COLUMN_TYPE = "type";  //type
        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + COLUMN_ID + " TEXT NOT NULL," 
        		+ COLUMN_NAME + " TEXT NOT NULL," 
        		+ COLUMN_TYPE + " INTEGER)";
    }
    

    
}
