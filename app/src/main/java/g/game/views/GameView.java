package g.game.views;

import java.text.ParseException;

import g.game.Game;
import g.game.R;	
import g.game.Activity.GameActivity;
import g.game.Activity.LoginActivity;
import g.game.UI.GameUI;
import g.game.character.Effect;
import g.game.character.Hero;
import g.game.manager.GameManager;
import g.game.map.GameMap;
import g.game.util.Helper;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;

public class GameView extends  SurfaceView implements Callback , Runnable {
	
	public static GameView instance;
	private GameManager gameManager = GameManager.instance();
	private Paint gPaint;
	private Thread th;
	private SurfaceHolder sfh;
	private Canvas	canvas;
	private Resources res;
	private Bitmap bmp;
	private boolean isKeyDown = false;
	
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		instance = this;
		this.setKeepScreenOn(true);
		
		gPaint = new Paint();
		gPaint.setAntiAlias(true);
		gPaint.setColor(Color.RED);
		gPaint.setTextSize(20);
		
		th = new Thread(this);
		sfh = this.getHolder();
		sfh.addCallback(this);
		
		res = this.getResources();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}
	

	@Override
	public void surfaceCreated(SurfaceHolder holder)	
	{
		Game.mainView.screenH = this.getHeight();
		Game.mainView.screenW = this.getWidth();
		
		initGame();
		
		th.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try{
			drawFrame();
			logicFrame();
			Thread.sleep(60);
			}catch(InterruptedException ex)
			{
				ex.printStackTrace();
			}
		}
	}

	
	private void initGame(){
		Bitmap mapRes		 = BitmapFactory.decodeResource(res, R.drawable.map);
		gameManager.gameMap  = new GameMap(this.getContext(),mapRes);
		
		Bitmap mainRoleBitmap = BitmapFactory.decodeResource(res, R.drawable.mingren);
		gameManager.mainRole = new Hero(this.getContext(),mainRoleBitmap);
		
		gameManager.gameUi 	 = new GameUI(res);
		 
		 Bitmap effectRes = BitmapFactory.decodeResource(GameView.instance.getResources(), R.drawable.effect01);
		 gameManager.beAttackEffect = new Effect(GameView.instance.getContext(), effectRes) ;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		gameManager.gameUi.onTouchEvent(event);
		int eventaction = event.getAction();  
		float rawX, rawY;
		rawX = (int) event.getX();  
		rawY = (int) event.getY();  
		switch (eventaction) {  
		case MotionEvent.ACTION_DOWN:  
			Log.v("onTouchEvent", "down");
			break;  
		case MotionEvent.ACTION_MOVE:
			Log.v("onTouchEvent", "move");
			break;
		case MotionEvent.ACTION_UP:
			Log.v("onTouchEvent", "up");
			break;
		}  
		return true;
	}

	private void logicFrame()
	{
		stepRole();
		stepMonster();
		stepEffect();
	}
	
	private void drawFrame()
	{
		try{
			canvas = sfh.lockCanvas();
			canvas.drawColor(Color.WHITE);
			canvas.save();
			drawMap();
			drawRole();
			drawMonster();
			drawEffect();
			drawUI();
			showFPS(canvas , gPaint);
			canvas.restore();
		}
		catch(Exception ex)
		{}
		finally
		{
			if(canvas != null)
				sfh.unlockCanvasAndPost(canvas);
		}
	}
	
	private void drawMap(){
		gameManager.gameMap.show(canvas, gPaint);
	}
	private void drawRole(){
		gameManager.mainRole.show(canvas ,gPaint);
	}
	private void drawEffect(){
		gameManager.beAttackEffect.show(canvas, gPaint);
	}
	private void stepEffect(){
		gameManager.beAttackEffect.step();
	}
	private void stepRole(){
		gameManager.mainRole.step();
	}
	private void drawMonster(){
		for(int i = 0; i < gameManager.monsterList.size() ; i ++){	
			gameManager.monsterList.get(i).show(canvas ,gPaint);
		}
	}
	private void stepMonster(){
		for(int i = 0; i < gameManager.monsterList.size() ; i ++){	
			gameManager.monsterList.get(i).step();
		}
	}
	private void drawUI(){
		gameManager.gameUi.show(canvas, gPaint);
	}
	private long fpsOld = 0;
	private int framsInsdex = 0;
	private String strFps = "";
	private void showFPS(Canvas canvas , Paint gPaint){
		long current;
		try {
			current = Helper.getCurrentTimestamp();
			framsInsdex ++;
			if(current - fpsOld > 1000){
				fpsOld = current;
				strFps = "FPS: " + framsInsdex + " f/s";
				framsInsdex = 0;
			}
			canvas.drawText(strFps , 30, 30, gPaint);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
