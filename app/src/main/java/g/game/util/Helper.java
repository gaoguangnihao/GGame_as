package g.game.util;

import java.util.Date;
import java.util.Random;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.graphics.Rect;
import android.graphics.RectF;

public class Helper {

	public static Boolean isInRectF(RectF rect, float x , float y){
		if( x < rect.right && x > rect.left && y < rect.bottom && y > rect.top)
			return true;
		return false;
	}
	
	public static Boolean isInRectF(Rect rect, float x , float y){
		if( x < rect.right && x > rect.left && y < rect.bottom && y > rect.top)
			return true;
		return false;
	}
	
	/*
	 *  get Timestamp
	 */
	public static long getCurrentTimestamp() throws ParseException {	
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());
		String str = formater.format(curDate);      
		Date date = formater.parse(str);
		return date.getTime();
	}
	
	/*
	 * random
	 */
	public static int random(int ranStart, int ranEnd){
		return ranStart + (int)Math.random()*(ranEnd - ranStart);
	}
	public static int random( int ranEnd){
		return (int)(Math.random()*ranEnd);
	}
}
