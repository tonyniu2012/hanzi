package com.xinxin.hanzi;


import com.xinxin.data.Data;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Display;
import android.view.Menu;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.content.SharedPreferences;

//import net.sourceforge.*;
//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
//import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


public class StudyActivity extends Activity {

	private int index = 0;
	private SharedPreferences prefs;
	private static final String prefFileName = "fPref";
	private static final String prefParamName = "Index";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		prefs = getSharedPreferences(prefFileName, MODE_PRIVATE);
		index = prefs.getInt(prefParamName, 0);
		//setContentView(R.layout.activity_main);
		WindowManager wm = getWindowManager();
		Display d = wm.getDefaultDisplay();
		if (d.getRotation() == Surface.ROTATION_0 || 
				d.getRotation() == Surface.ROTATION_180 )
			showPortrait();
		else
			showLandscape();
		
		Button btnStudy = (Button)findViewById(R.id.KnownBtn);
		btnStudy.setOnClickListener( lst );
	}

	@Override
	public void onDestroy()
	{
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt(prefParamName, index);
		editor.commit();
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private View.OnClickListener lst = new OnClickListener()
	{
		public void onClick(View arg0)
		{
           showNextContent();               
		}
	};
	
	private void showLandscape()
	{
		setContentView(R.layout.v_activity_study);
		showCurrContent();
	}
	
	private void showPortrait()
	{
		setContentView(R.layout.h_activity_study);
		showCurrContent();
	}
	
	private void showNextContent()
	{
		updateToNext();
		showCurrContent();
	}

	private void updateToNext() {
		index = Data.getDataInst().getNextIndex(index);
	}
	
	private void showCurrContent()
	{
		char ch = Data.getDataInst().get(index);
		showHanzi(ch);
		showPinyin(ch);
	}
	
	private void showHanzi(char cStr)
	{
		TextView ed = ((TextView)findViewById(R.id.hanzi));
		String str = String.format("%c", cStr);
		ed.setText(str);		
	}
	
	private void showPinyin(char cStr)
	{
     	HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	    format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
	    format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
	    String[] pinyin = new String[20];
		try {
			pinyin = PinyinHelper.toHanyuPinyinStringArray(cStr, format);
			String str = pinyin[0];
			TextView ed = ((TextView)findViewById(R.id.pinyin));
			ed.setText(str);

		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
	}

}