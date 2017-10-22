package g.game.character;

import g.game.Game;
import g.game.manager.GameManager;
import g.game.map.GameMap;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Hero extends Character {
	public Hero(Context context, Bitmap res) {
		super(context, res);
		// TODO Auto-generated constructor stub
		setX(GameMap.getPixelByTile(Game.hero.originPoint).x);
		setY(GameMap.getPixelByTile(Game.hero.originPoint).y);
	}
	
	@Override 
	public void show(Canvas	canvas , Paint gPaint){
		super.show(canvas,gPaint);
	}
	
	@Override
	public void step(){
		super.step();

	}
	
	public void walk(){
		setActon(Game.character.ACTION_WALK);
		moveToNextTile();
	}
	public void stand(){ 
		setActon(Game.character.ACTION_STAND);
	}
	
	public void attack(){
		if(getActon() != Game.character.ACTION_STAND) return;
		setActon(Game.character.ACTION_ATTACK , false);
		
		GameManager.instance().beAttackEffect.setShowPoint(GameMap.getNextTilePixel(getX(), getY(), getDirection(), Game.skill.skillGap));
		GameManager.instance().beAttackEffect.start();
		GameManager.instance().fight();
	}
	public void jump(){
		
	}
	private void moveToNextTile(){
		Point nextTilePixel = GameMap.getNextTilePixel(getX(), getY(), getDirection());
		if(GameManager.instance().gameMap.checkRoad(GameMap.getTileBytPixel(nextTilePixel)))
			moveTo(new Point(nextTilePixel.x, nextTilePixel.y));
	}
}
