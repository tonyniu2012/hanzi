package com.xinxin.hanzi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Display;
import android.view.Menu;
import android.view.Surface;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		WindowManager wm = getWindowManager();
		Display d = wm.getDefaultDisplay();
		if (d.getRotation() == Surface.ROTATION_0 || 
				d.getRotation() == Surface.ROTATION_180 )
			showPortrait();
		else
			showLandscape();
		
		Button btnStudy = (Button)findViewById(R.id.StudyBtn);
		btnStudy.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0)
			{
				Intent i = new 
						Intent("com.xinxin.hanzi.STUDY");
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void showLandscape()
	{
		setContentView(R.layout.v_activity_main);
	}
	
	private void showPortrait()
	{
		setContentView(R.layout.h_activity_main);
	}
	
	
}
