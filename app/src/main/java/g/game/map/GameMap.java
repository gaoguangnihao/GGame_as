package g.game.map;

import g.game.Game;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

public class GameMap {
	private Context ctx;
	private Bitmap res;
	
	private int mapInfo[][] = {
			{18,18,18,18,18,18,18,18,18,18,18,18,55,55,55,17,18}, 
			{18,18,18,17,17,17,17,17,17,17,17,17,55,55,17,17,18},
			{18,18,17,17,17,17,18,18,17,17,17,17,55,55,17,17,18},
			{18,17,17,17,18,18,18,18,18,17,17,55,55,17,17,17,18},
			{18,17,17,18,22,23,23,23,24,18,17,55,55,17,17,17,18},
			{18,17,17,18,25,28,26,79,27,18,55,55,17,17,17,17,18},
			{18,17,17,17,17,10,11,12,18,18,55,55,17,17,17,17,18},
			{18,18,17,17,10,16,16,16,11,55,55,17,17,17,17,17,18},
			{18,18,17,17,77,16,16,16,16,21,21,17,17,17,17,17,18},
			{18,18,18,18,18,18,18,18,18,55,55,18,18,18,18,18,18}
			};
	private int roadInfo[] = {10,11,12,16,17,21};
	public GameMap(Context context ,Bitmap bitmap) {
		// TODO Auto-generated constructor stub
		ctx = context;
		res = bitmap;
		Game.map.TILE_WIDTH = bitmap.getWidth()/Game.map.resClume;
		Game.map.TILE_HEIGHT = bitmap.getHeight()/Game.map.resClume;
	}
	public static Point getTileBytPixel(int x, int y){
		Point point = new Point();
		point.x 	= x/Game.map.TILE_WIDTH;
		point.y 	= y/Game.map.TILE_HEIGHT;
		return point;
	}
	public static Point getTileBytPixel(Point pPoint){
		Point point = new Point();
		point.x 	= pPoint.x/Game.map.TILE_WIDTH;
		point.y 	= pPoint.y/Game.map.TILE_HEIGHT;
		return point;
	}
	public static Point getPixelByTile(int tile_x , int tile_y ){
		Point point = new Point();
		point.x 	= tile_x * Game.map.TILE_WIDTH + Game.map.TILE_WIDTH/2;
		point.y		= tile_y * Game.map.TILE_HEIGHT + Game.map.TILE_HEIGHT/2;
		return point;
	}
	public static Point getPixelByTile(Point pTile ){
		Point point = new Point();
		point.x 	= pTile.x * Game.map.TILE_WIDTH + Game.map.TILE_WIDTH/2;
		point.y		= pTile.y * Game.map.TILE_HEIGHT + Game.map.TILE_HEIGHT/2;
		return point;
	}
	public static Point getNextTile(int tileX , int tileY, int direction){
		Point tilePoint = new Point();
		switch (direction) {
		case Game.character.DIRECTION_DOWN: 
			tilePoint.x = tileX;
			tilePoint.y = tileY + 1;
			break;
		case Game.character.DIRECTION_LEFT: 
			tilePoint.x = tileX - 1;
			tilePoint.y = tileY;
			break;
		case Game.character.DIRECTION_RIGHT:
			tilePoint.x = tileX + 1;
			tilePoint.y = tileY;
			break;
		case Game.character.DIRECTION_UP: 
			tilePoint.x = tileX ;
			tilePoint.y = tileY - 1;
			break;
		default:
			break;
		}
		return tilePoint;
	}
	
	public static Point getNextTilePixel(int x , int y, int direction){
		Point pixelPoint = new Point();
		switch (direction) {
		case Game.character.DIRECTION_DOWN: 
			pixelPoint.x = x;
			pixelPoint.y = y + Game.map.TILE_HEIGHT ;
			break;
		case Game.character.DIRECTION_LEFT: 
			pixelPoint.x = x - Game.map.TILE_WIDTH ;
			pixelPoint.y = y;
			break;
		case Game.character.DIRECTION_RIGHT:
			pixelPoint.x = x + Game.map.TILE_WIDTH ;
			pixelPoint.y = y;
			break;
		case Game.character.DIRECTION_UP: 
			pixelPoint.x = x ;
			pixelPoint.y = y - Game.map.TILE_HEIGHT ;
			break;
		default:
			break;
		}
		return pixelPoint;
	}
	
	public static Point getNextTilePixel(int x , int y, int direction , int gap){
		Point pixelPoint = new Point();
		switch (direction) {
		case Game.character.DIRECTION_DOWN: 
			pixelPoint.x = x;
			pixelPoint.y = y + Game.map.TILE_HEIGHT * gap ;
			break;
		case Game.character.DIRECTION_LEFT: 
			pixelPoint.x = x - Game.map.TILE_WIDTH * gap;
			pixelPoint.y = y;
			break;
		case Game.character.DIRECTION_RIGHT:
			pixelPoint.x = x + Game.map.TILE_WIDTH * gap;
			pixelPoint.y = y;
			break;
		case Game.character.DIRECTION_UP: 
			pixelPoint.x = x ;
			pixelPoint.y = y - Game.map.TILE_HEIGHT * gap;
			break;
		default:
			break;
		}
		return pixelPoint;
	}
	
	public boolean checkRoad(int tileX , int tileY){
		for(int i = 0; i < roadInfo.length ; i ++){
			if(mapInfo[tileY][tileX] == roadInfo[i])
				return true;
		}
		return false;
	}
	public boolean checkRoad(Point pTile){
		for(int i = 0; i < roadInfo.length ; i ++){
			if(mapInfo[pTile.y][pTile.x] == roadInfo[i])
				return true;
		}
		return false;
	}
	public void show(Canvas canvas , Paint gPaint){
		Rect sceneR = new Rect(0, 0, Game.mainView.screenW, Game.mainView.screenH);
		canvas.drawRect(sceneR , gPaint);
		for(int i=0; i < Game.map.TILE_NUM_X ; i ++){
			for(int j=0; j < Game.map.TILE_NUM_Y ; j++){
				int resIndex_w =  mapInfo[i][j]%Game.map.resClume;
				int resIndex_h =  mapInfo[i][j]/Game.map.resClume;
				Rect srcR = new Rect(Game.map.TILE_WIDTH* resIndex_w, Game.map.TILE_HEIGHT * resIndex_h ,
						res.getWidth()/Game.map.resClume * (resIndex_w + 1), Game.map.TILE_HEIGHT * (resIndex_h + 1));
				RectF desR = new RectF(j*Game.map.TILE_WIDTH, i*Game.map.TILE_HEIGHT, 
						(j+1)*Game.map.TILE_WIDTH, (i+1) * Game.map.TILE_HEIGHT);
				canvas.drawBitmap(res, srcR, desR, gPaint);
			}
		}
	}
	
	public int calDirection(float srcX,float srcY, float desX, float desY)
	{
		int direction;
		float disX, disY;
		disX = srcX - desX;
		disY = srcY - desY;
		
		if(disX >0 && disY > 0  )
		{
			if(disX > disY)
				direction = 2;
			else
				direction = 0;
		}
		else
		{
			if(disX > disY)
				direction = 3;
			else
				direction = 1;
		}
		return direction ;
	}
	
	public boolean hitTest(Point original , Point target ,int gap  ){
		if(target.x <  original.x + gap * Game.map.TILE_WIDTH && target.x > original.x - gap * Game.map.TILE_WIDTH ){
			if(target.y < original.y + gap* Game.map.TILE_HEIGHT && target.y > original.y - gap * Game.map.TILE_HEIGHT)
				return true;
		}
		return false;
	}
}
