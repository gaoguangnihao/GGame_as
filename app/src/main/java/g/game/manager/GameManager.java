package g.game.manager;

import g.game.R;	
import g.game.UI.GameUI;		
import g.game.character.Effect;
import g.game.character.Hero;
import g.game.character.Character;
import g.game.character.Monster;
import g.game.map.GameMap;
import g.game.views.GameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameManager  {
	
	private static GameManager	_instance;
	
	public  GameMap gameMap;
	public 	Hero mainRole;
	public	GameUI gameUi;
	public 	List<Character> npcList = new ArrayList<Character>();
	public 	List<Character> monsterList = new ArrayList<Character>();
	
	public  Effect beAttackEffect;
	
	private Timer timer;
	private TimerTask  timerTask = new TimerTask() {
		@Override
		public void run() {
			if(monsterList.size() < 3){
				Bitmap monsterRes = BitmapFactory.decodeResource(GameView.instance.getResources(), R.drawable.monster_ninja);
				monsterList.add(new Monster(GameView.instance.getContext(), monsterRes));
			}
		}
	};
	
	public static GameManager instance(){
		if(_instance == null)
			_instance	=  new GameManager();
		return _instance;
	}

	public GameManager() {
		 timer = new Timer(true);
		 timer.schedule(timerTask,1000, 5000); 
	}
	
	
	public void fight(){
		for( int i = 0; i < monsterList.size() ; i ++){
			if(gameMap.hitTest( beAttackEffect.getShowPoint() , monsterList.get(i).getSite() , 1 )){
				monsterList.get(i).hp -= 10;
				if(monsterList.get(i).hp <= 0){
					monsterList.remove(i);
				}
				
			}
		}
	}
}
