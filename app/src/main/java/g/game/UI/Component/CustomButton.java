package g.game.UI.Component;

import g.game.util.Helper;	
import g.game.vo.ButtonVo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.preference.PreferenceActivity.Header;
import android.view.MotionEvent;


public class CustomButton {

	protected ButtonVo vo ;
	
	private int motionType;  
	
	public CustomButton(ButtonVo vo) {
		this.vo = vo;
	}
	
	public void show(Canvas canvas , Paint gPaint){
		canvas.drawBitmap(vo.getBitmap(), vo.getSrcR()	, vo.getDesR(), gPaint);
	}
	
	public Boolean onTouchEvent(MotionEvent event){
		int eventaction = event.getAction();  
		float rawX, rawY;
		rawX = (int) event.getX();  
		rawY = (int) event.getY();  
		
		if(Helper.isInRectF( vo.getDesR(),rawX, rawY)){
			setMotionType(eventaction);
			return true;
		}
		return false;
	}

	public int getMotionType() {
		return motionType;
	}

	public void setMotionType(int motionType) {
		this.motionType = motionType;
	}
}
