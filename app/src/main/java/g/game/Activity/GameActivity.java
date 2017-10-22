package g.game.Activity;

import g.game.views.GameView;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity {
	
	public static GameActivity instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(new GameView(this));
		
		IntentFilter filter = new IntentFilter("com.aurora.grow.android.mainTabReceiver");
		registerReceiver(receiver, filter);
	}
	
    private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {             
			if (intent.hasExtra("roleType")) {
			}
		}
	};
}
