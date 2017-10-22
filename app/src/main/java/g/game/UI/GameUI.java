package g.game.UI;

import java.util.ArrayList;	
import java.util.List;

import g.game.Game;
import g.game.R;
import g.game.UI.Component.CustomButton;
import g.game.manager.GameManager;
import g.game.vo.ButtonVo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

public class GameUI {
	
	public static GameUI _instance ;
	private Resources res;
	
	private Bitmap dirRes;  // direction button res
	private Bitmap attackRes; // attack button res
	
	private List<ButtonVo> btnInfoList = new ArrayList<ButtonVo>();
	private List<CustomButton> btnList = new ArrayList<CustomButton>();

	public static GameUI instance(){
		if(_instance == null)
			_instance = new GameUI();
		return _instance;
	}
	public GameUI(){
		init();
	}
	public GameUI(Resources _res) {
		// TODO Auto-generated constructor stub
		
		res = _res;
		Game.ui.baseX = Game.mainView.screenW * 0.1f;
		Game.ui.baseY = Game.mainView.screenH * 0.6f;
		Log.v("GameUI base", String.valueOf(Game.ui.baseX) + "  " + String.valueOf(Game.ui.baseY));
		
		dirRes = BitmapFactory.decodeResource(res, R.drawable.e1);
		Log.v("GameUI direction res:", String.valueOf(dirRes.getWidth()) + "  " + String.valueOf(dirRes.getHeight()));
		Game.ui.dirResH	= dirRes.getHeight()/3;
		Game.ui.dirResW	= dirRes.getWidth()/3;
		
		attackRes = BitmapFactory.decodeResource(_res, R.drawable.e2);
		Game.ui.attackResH = attackRes.getHeight();
		Game.ui.attackResW = attackRes.getWidth()/2;
		init();
	}
	private void init(){
		//direction button
		btnInfoList.add(new ButtonVo("left",new Rect(0, Game.ui.dirResH, Game.ui.dirResW,  Game.ui.dirResH*2), 
				new RectF(Game.ui.baseX , Game.ui.baseY + Game.ui.dirResH,Game.ui.baseX + Game.ui.dirResW, Game.ui.baseY + Game.ui.dirResH * 2 ), dirRes));
		btnInfoList.add(new ButtonVo("right",new Rect(Game.ui.dirResW * 2, Game.ui.dirResH, Game.ui.dirResW * 3,  Game.ui.dirResH*2),
				new RectF(Game.ui.baseX + Game.ui.dirResW * 2, Game.ui.baseY + Game.ui.dirResH,Game.ui.baseX + Game.ui.dirResW * 3, Game.ui.baseY + Game.ui.dirResH * 2 ), dirRes));
		btnInfoList.add(new ButtonVo("up",	new Rect(Game.ui.dirResW, 0, Game.ui.dirResW * 2,  Game.ui.dirResH), 
				new RectF(Game.ui.baseX + Game.ui.dirResW , Game.ui.baseY ,Game.ui.baseX + Game.ui.dirResW * 2, Game.ui.baseY + Game.ui.dirResH ), dirRes));
		btnInfoList.add(new ButtonVo("down",new Rect(Game.ui.dirResW, Game.ui.dirResH *2 , Game.ui.dirResW * 2,  Game.ui.dirResH*3), 
				new RectF(Game.ui.baseX + Game.ui.dirResW , Game.ui.baseY + Game.ui.dirResH * 2,Game.ui.baseX + Game.ui.dirResW * 2, Game.ui.baseY + Game.ui.dirResH * 3 ), dirRes));
		
		//attack button
		float att_y = Game.ui.baseY + 50;
		btnInfoList.add(new ButtonVo("attack1",new Rect(0,0,Game.ui.attackResW,Game.ui.attackResH), 
				new RectF(Game.mainView.screenW*0.7f, att_y  ,Game.mainView.screenW*0.7f + Game.ui.attackResW , att_y + Game.ui.attackResH), attackRes));
		btnInfoList.add(new ButtonVo("attack2",new Rect(Game.ui.attackResW,0,Game.ui.attackResW * 2,Game.ui.attackResH), 
				new RectF(Game.mainView.screenW*0.7f + Game.ui.attackResW, att_y  ,Game.mainView.screenW*0.7f + Game.ui.attackResW * 2 , att_y + Game.ui.attackResH), BitmapFactory.decodeResource(res, R.drawable.e2)));
		
		for(int i = 0; i < btnInfoList.size(); i ++){
			btnList.add(new CustomButton(btnInfoList.get(i)));	
		}
	}
	
	public void show(Canvas canvas , Paint gPaint){
		for(int i = 0; i < btnList.size(); i ++){
			btnList.get(i).show(canvas, gPaint);
		}
	}
	
	public boolean onTouchEvent(MotionEvent event){
		for(int i = 0; i < btnList.size(); i ++){
			if(btnList.get(i).onTouchEvent(event)){
				onTouchEventHandler(btnInfoList.get(i) ,btnList.get(i).getMotionType());
				return true;
			}
		}
		return false;
	}
	private void onTouchEventHandler(ButtonVo btnVo, int motionType){
		switch (motionType) {
		case MotionEvent.ACTION_DOWN:	
			btnDownHandler(btnVo);
			break;
		case MotionEvent.ACTION_UP:
			btnUpHandler(btnVo);
			break;
		case MotionEvent.ACTION_MOVE:
			btnMoveHandler(btnVo);
			break;
		default:
			break;
		}
	}
	private void btnMoveHandler(ButtonVo btnVo){
		String id = btnVo.getId();
	//	Log.v("btnMoveHandler", id);
		if(id == "left"){   // left s
			GameManager.instance().mainRole.setDirection(Game.character.DIRECTION_LEFT);
			GameManager.instance().mainRole.walk();
		}
		else if(id  == "right" ){
			GameManager.instance().mainRole.setDirection(Game.character.DIRECTION_RIGHT);
			GameManager.instance().mainRole.walk();
		}
		else if(id == "up"){
			GameManager.instance().mainRole.setDirection(Game.character.DIRECTION_UP);
			GameManager.instance().mainRole.walk();
		}
		else if(id == "down"){
			GameManager.instance().mainRole.setDirection(Game.character.DIRECTION_DOWN);
			GameManager.instance().mainRole.walk();
		}
	}
	private void btnUpHandler(ButtonVo btnVo){
	}
	
	private void btnDownHandler(ButtonVo btnVo){
		String id = btnVo.getId();
//		Log.v("btnDownHandler", id);
		if(id == "left"){   // left 
			GameManager.instance().mainRole.setDirection(Game.character.DIRECTION_LEFT);
			GameManager.instance().mainRole.walk();
		}
		else if(id  == "right" ){
			GameManager.instance().mainRole.setDirection(Game.character.DIRECTION_RIGHT);
			GameManager.instance().mainRole.walk();
		}
		else if(id == "up"){
			GameManager.instance().mainRole.setDirection(Game.character.DIRECTION_UP);
			GameManager.instance().mainRole.walk();
		}
		else if(id == "down"){
			GameManager.instance().mainRole.setDirection(Game.character.DIRECTION_DOWN);
			GameManager.instance().mainRole.walk();
		}
		else if(id == "attack1"){
			GameManager.instance().mainRole.jump();
		}
		else if(id == "attack2"){
			GameManager.instance().mainRole.attack();
		}
	}

}
