package com.datian.uppopupdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		EditText et = (EditText) this.findViewById(R.id.editText1);
		et.setKeyListener(null);
		
		this.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ExitPopup exitPopup = new ExitPopup(MainActivity.this, "你确定要退出吗？");
				exitPopup.setConfirmListener("确定", new ExitPopup.OnClickListener() {
					
					@Override
					public void onClick(ExitPopup exitPopup) {
						Toast.makeText(MainActivity.this, "退出", 1).show();
					}
				});
				exitPopup.setCancelListener("取消", new ExitPopup.OnClickListener() {
					
					@Override
					public void onClick(ExitPopup exitPopup) {
						Toast.makeText(MainActivity.this, "取消", 1).show();
						exitPopup.dismiss();
					}
				});
				exitPopup.show((View) v.getParent());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
