package g.game.Activity;


import java.util.ArrayList;
import java.util.List;

import g.game.R;
import g.game.db.BaseDBAdapter;
import g.game.vo.UserInfoVO;
import android.app.Activity;	
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class LoginActivity extends Activity {
	private LinearLayout layout;
	private Button	btnOk, btnCancel , btnRegister;
	private EditText usr, pasW ;
	private int count = 0;
	private Activity rs;
	
	private BaseDBAdapter dbAdapter ;

	@Override 
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		rs = this ;
		dbAdapter = new BaseDBAdapter(getApplicationContext());
		
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
//		initView();
		this.setContentView(R.layout.activity_login);
		btnOk = (Button) findViewById(R.id.button_start);
		btnCancel = (Button) findViewById(R.id.button_cancel);
		btnRegister = (Button) findViewById(R.id.button_sure);
		usr	= (EditText) findViewById(R.id.editText_User);
		pasW = (EditText) findViewById(R.id.editText_PassW);
		
		btnOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent();
				intent.setClass(rs, GameActivity.class);
				startActivity(intent);
				finish();
				
				List<UserInfoVO> userList = dbAdapter.queryUserInfo();
				for(int i = 0 ; i < userList.size() ; i++)
				{
					String usrId = userList.get(i).getId();
					String password = userList.get(i).getPassword();
					if(usr.getText().toString() == usrId || pasW.getText().toString() == password)
					{
						Intent intentGa = new Intent();
						intentGa.setClass(rs, GameActivity.class);
						startActivity(intentGa);
						finish();
					}
				}
				
			}
		});
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("flag", 1);
				intent.setClass(rs, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		btnRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("flag", 1);
				intent.setClass(rs, RegisterActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
	}
	private void initView()
	{	
		
		Intent intent = rs.getIntent();
		int param1 = intent.getIntExtra("param1", 0);
		String param2 = intent.getStringExtra("param2");
		
		layout = new LinearLayout(rs);
		btnOk = new Button(rs);
		btnOk.setWidth(100);
		btnOk.setText(param2);
		btnOk.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(usr.getText().toString() == "admin")
					rs.finish();
			}
		});
		layout.addView(btnOk);
		
		usr 	= new EditText(rs);
		
		layout.addView(usr);
		setContentView(layout);
	}

}
