package g.game.vo;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;

public class ButtonVo {
	
	private String id;
	private Rect srcR;
	private RectF desR;
	private Bitmap bitmap;
	

	public ButtonVo() {
	}
	public ButtonVo(String id, Rect srcR, RectF desR, Bitmap bitmap ) {
		this.id = id;
		this.srcR = srcR;
		this.desR = desR;
		this.bitmap = bitmap;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}





	public Bitmap getBitmap() {
		return bitmap;
	}


	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public Rect getSrcR() {
		return srcR;
	}
	public void setSrcR(Rect srcR) {
		this.srcR = srcR;
	}
	public RectF getDesR() {
		return desR;
	}
	public void setDesR(RectF desR) {
		this.desR = desR;
	}

}
