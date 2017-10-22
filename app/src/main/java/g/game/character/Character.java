package g.game.character;

import g.game.Game;
import g.game.map.GameMap;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class Character extends Animation {
	
	private Context ctx;

	private int x;
	private int y;
	private int desX;
	private int desY;
	
	protected int _id;
	protected int _type; // 0 hero 1 npc 2 monster
	protected int _acton; // 0 stand 1 walk
	protected int speed = 4;
	private int _direction = 0;    
	private Boolean _isMove = false;
	protected Bitmap resBitmap;
	
	public int hp = 50;
	protected int mp;

	public Character(Context context,Bitmap res) {
		ctx = context;
		resBitmap = res;
		Game.hero.offsetX = res.getWidth()/8  - 53;
		Game.hero.offsetY = res.getHeight()/4 - 132;
		init();
	}
	
	final void init(){
	}
	
	protected void setActionFrame(int action){
		switch (action) {
		case Game.character.ACTION_STAND:
			_startFrame = 0;
			_endFrame	= 0;
			break;
		case Game.character.ACTION_WALK:
			_startFrame	= 0;
			_endFrame	= 3;
			break;
		case Game.character.ACTION_ATTACK:
			_startFrame		= 0;
			_endFrame		= 1;
			break;
		case Game.character.ACTION_ATTACKED:
			_startFrame	 = 3;
			_endFrame	 = 4;
		default:
			break;
		}
	}
	
	@Override
	public void step(){
		super.step();
		switch (getActon()) {
		case Game.character.ACTION_STAND: 
			break;
		case Game.character.ACTION_WALK: 
			switch(getDirection())
			{
				case Game.character.DIRECTION_DOWN: 
					if(getDesY() > getY())
						setY(getY() + Game.map.TILE_HEIGHT / speed);
					else
						moveEnd();
					break;
				case Game.character.DIRECTION_LEFT: 
					if(getDesX() < getX())
						setX(getX() - Game.map.TILE_WIDTH / speed);
					else
						moveEnd();
					break;
				case Game.character.DIRECTION_RIGHT: 
					if(getDesX() > getX())
						setX(getX() + Game.map.TILE_WIDTH / speed);
					else
						moveEnd();
					break;
				case Game.character.DIRECTION_UP: 
					if(getDesY() < getY())
						setY(getY() - Game.map.TILE_HEIGHT / speed);
					else
						moveEnd();
					break;
			}
			break;
		case Game.character.ACTION_ATTACK:
			if(isEnd())
			{
				setCirculate(true);
				setActon(Game.character.ACTION_STAND);
			}
			break;
		case Game.character.ACTION_ATTACKED:
			if(isEnd())
			{
			}
			break;
		default:
			break;
		}
	}
	
	protected void	moveTo(Point des){
		if(!_isMove)
		{
			setDesX(des.x);
			setDesY(des.y);
		}
	}
	private void moveEnd(){
		_isMove = false;
		setActon(Game.character.ACTION_STAND);
		setX(desX);
		setY(desY);
//		Log.v("Hero move to", GameMap.getTileBytPixel(desX, desY).toString());
	}
	
	public void show(Canvas	canvas , Paint gPaint){
		drawBitmap(canvas , gPaint);
	}

	
	private void drawBitmap(Canvas canvas , Paint gPaint){

	//	canvas.clipRect(sceneR);  
		Rect srcR = new Rect(resBitmap.getWidth()/4 * _actionFrameIndex, resBitmap.getHeight()/4*getDirection() , resBitmap.getWidth()/4 * (_actionFrameIndex + 1), resBitmap.getHeight()/4*(getDirection() +1));
		RectF desR = new RectF(getX() + Game.hero.offsetX , getY() + Game.hero.offsetY , getX()+ Game.hero.offsetX + resBitmap.getWidth()/4, getY() + + Game.hero.offsetY + resBitmap.getHeight()/4);
		canvas.drawBitmap(resBitmap, srcR,desR , gPaint);
	// hp
		RectF hpR = new RectF(desR.left, desR.top, desR.left + hp , desR.top - 6);
		canvas.drawRect(hpR, gPaint);
	}


	public int getActon() {
		return _acton;
	}

	public void setActon(int _acton) {
		this._acton = _acton;
		setActionFrame(_acton);
		setCirculate(true);
	}
	public void setActon(int _acton , Boolean bCircle) {
		this._acton = _acton;
		setActionFrame(_acton);
		setCirculate(bCircle);
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public float getDesX() {
		return desX;
	}

	public void setDesX(int desX) {
		this.desX = desX;
	}

	public float getDesY() {
		return desY;
	}

	public void setDesY(int desY) {
		this.desY = desY;
	}

	public int getDirection() {
		return _direction;
	}

	public void setDirection(int _direction) {
		this._direction = _direction;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		if(getActon() == Game.character.ACTION_STAND)
			setDesX(x);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		if(getActon() == Game.character.ACTION_STAND)
			setDesY(y);
	}
	public Point getSite(){
		return new Point(this.getX() , this.getY());
	}
}
