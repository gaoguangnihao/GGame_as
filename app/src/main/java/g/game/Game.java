package g.game;

import android.graphics.Point;

public class Game {

	public Game() {
		// TODO Auto-generated constructor stub
	}
	
    public static final String GAMENAME = "gGame";
    
    public static final class mainView{
    	public static int screenW;
    	public static int screenH;
    }
    
    public static final class map{
    	public static  int TILE_WIDTH ;
    	public static  int TILE_HEIGHT ;
    	public static final int TILE_NUM_X = 10;
    	public static final int TILE_NUM_Y = 17;
    	
    	
    	public static final int resClume = 10;
    }
    
    public static final class ui{
    	public static  float baseX ;
    	public static  float baseY ;
    	
    	public static   int dirResW;
    	public static   int dirResH;
    	
    	public static   int attackResW;
    	public static   int attackResH;
    	
    }
    
    public static final class character{
    	//direction 
    	public static final int DIRECTION_DOWN = 0;
    	public static final int DIRECTION_LEFT = 1;
    	public static final int DIRECTION_RIGHT = 2;
    	public static final int DIRECTION_UP = 3;

    	//action
    	public static final int ACTION_STAND = 0;
    	public static final int ACTION_WALK = 1;
    	public static final int ACTION_ATTACK = 2;
    	public static final int ACTION_ATTACKED = 3;
    }
    
    public static final class hero{

    	//res offset
    	public static int offsetX;
    	public static int offsetY;
    	
    	// born point
    	public static Point originPoint = new Point(3, 3);
    	

    }

    public static final class monster{
    	// born point
    	public static Point originPoint = new Point(6, 5);
    }
    public static final class effect{
    	//res offset
    	public static int offsetX ;
    	public static int offsetY ;
    }
    public static final class skill{
    	// skill
    	public static int skillGap = 2;
    }
	public interface HTTP{
		
		public static final String BASERUL = "";
		public static final String UPDATEURL = "";
	    public static final int TIMEOUT = 30000;
	}
}
