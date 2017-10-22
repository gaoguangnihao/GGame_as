package g.game.character;

import java.text.ParseException;

import g.game.Game;
import g.game.manager.GameManager;
import g.game.map.GameMap;
import g.game.util.Helper;
import g.game.views.GameView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;

public class Monster extends Character implements Runnable {
	
	protected Thread th;

	public Monster(Context context, Bitmap res) {
		super(context, res);
		setX(GameMap.getPixelByTile(Game.monster.originPoint).x);
		setY(GameMap.getPixelByTile(Game.monster.originPoint).y);
		
		th = new Thread(this);
		th.start();
	}
	
	private long oldTime;
	@Override
	public void run() {
		try {
			while(true){
				long current = Helper.getCurrentTimestamp();
				if(current - oldTime > 1000){
					monsterAI();
					oldTime = current;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	public void monsterAI(){
		setDirection(Helper.random(4));
		walk();
	}
	public void walk(){
		setActon(Game.character.ACTION_WALK);
		moveToNextTile();
	}
	public void stand(){ 
		setActon(Game.character.ACTION_STAND);
	}
	private void moveToNextTile(){
		Point nextTilePixel = GameMap.getNextTilePixel(getX(), getY(), getDirection());
		if(GameManager.instance().gameMap.checkRoad(GameMap.getTileBytPixel(nextTilePixel)))
			moveTo(new Point(nextTilePixel.x, nextTilePixel.y));
	}
	public void beAttacked(){
		setActon(Game.character.ACTION_ATTACKED);
	}
	
	@SuppressWarnings("deprecation")
	public void dispose(){
		th.stop();
	}
}
