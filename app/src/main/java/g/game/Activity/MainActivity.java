package g.game.Activity;

import g.game.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	private String TAG = "MainActivity";
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg){
			switch (msg.what) {
			case 1:
				toWelcome();
				break;
			default:
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.v(TAG,"onStart");
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	//	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		init();
	}
	
	
	private void init(){
		Thread th = new Thread(){
			public void run(){
				try{
					Thread.sleep(2000);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
				
				Message msg = new Message();
				msg.what	= 1;
				handler.sendMessage(msg);
			}
		};
		th.start();
	}
	
	private void toWelcome(){
		Intent mIntent = new Intent();
		mIntent.setClass(this, LoginActivity.class);
		startActivity(mIntent);
		finish();
	}
	
	public void onStart() {
	    super.onStart();
	    Log.v(TAG,"onStart");
	 }
	
	@Override
	protected void onResume() {
		super.onResume();
	
		Log.v(TAG,"onResume");
		
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		if(intent.getIntExtra("flag", 0) == 1 )
			finish();
		super.onNewIntent(intent);
	}
	

	public void onStop(){
	    super.onStop();
	    Log.v(TAG,"onStop");
	}

	public void onRestart(){
	    super.onRestart();
	    Log.v(TAG,"onReStart");
	}
	public void onPause(){
	    super.onPause();
	    Log.v(TAG,"onPause");
	}
	public void onDestroy(){
	    super.onDestroy();
	    Log.v(TAG,"onDestroy");
	}

}
