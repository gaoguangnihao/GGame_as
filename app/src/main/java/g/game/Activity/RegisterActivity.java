package g.game.Activity;

import g.game.R;	
import g.game.db.BaseDBAdapter;
import g.game.vo.UserInfoVO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {
	
	private Button	btnSure;
	private EditText usr,pasW;
	private Activity rs;
	
	private BaseDBAdapter dbAdapter;

	public RegisterActivity() {
		// TODO Auto-generated constructor stub
		super();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avtivity_register);
		rs = this;
		dbAdapter = new BaseDBAdapter(getApplicationContext());
		init();
	}
	
	private void init(){
		btnSure = (Button) findViewById(R.id.button_sure);
		usr	= (EditText) findViewById(R.id.editText_User);
		pasW = (EditText) findViewById(R.id.editText_PassW);
		
		btnSure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					UserInfoVO info = new UserInfoVO();
					info.setId(usr.getText().toString());
					info.setPassword(pasW.getText().toString());
					info.setName("test");
					info.setType(1);
					dbAdapter.saveUserInfo(info);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
				Intent intent = new Intent();
				intent.putExtra("flag", 1);
				intent.setClass(rs, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
	}

}
