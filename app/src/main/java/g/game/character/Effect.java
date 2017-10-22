package g.game.character;

import g.game.Game;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

public class Effect extends Animation {

	private Context ctx ;
	private Bitmap resBitmap;
	
	private RectF showRect;
	private Point showPoint;
	
	protected boolean isShow = false;
	
	public Effect(Context ctx , Bitmap bitmap) {
		super();
		this.ctx = ctx;
		this.resBitmap = bitmap;
		this.setCirculate(false);
		this._startFrame = 0;
		this._endFrame	= 6;
		
		Game.effect.offsetX = 0; 
		Game.effect.offsetY = Game.map.TILE_HEIGHT / 2;
	}
	
	public void show(Canvas	canvas , Paint gPaint){
		if(isShow){
			Rect srcR = new Rect(0, resBitmap.getHeight()/7 * _actionFrameIndex , resBitmap.getWidth(),resBitmap.getHeight()/7 * (_actionFrameIndex + 1));
			RectF desR = new RectF(showPoint.x - resBitmap.getWidth()/2 + Game.effect.offsetX, showPoint.y - resBitmap.getHeight()/7 + Game.effect.offsetY , showPoint.x + resBitmap.getWidth()/2+ Game.effect.offsetX , showPoint.y +Game.effect.offsetY );
			canvas.drawBitmap(resBitmap, srcR, desR, gPaint);
		}
	}
	public void step(){
		if(isShow)
			super.step();
		
		if(isEnd())
			this.stop();
	}
	
	public void start(){
		isShow = true;
		_actionFrameIndex = 0;
	}
	
	public void stop(){
		isShow = false;
	}


	public Point getShowPoint() {
		return showPoint;
	}

	public void setShowPoint(Point showPoint) {
		this.showPoint = showPoint;
	}
}
