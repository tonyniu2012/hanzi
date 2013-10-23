package com.xinxin.AndroidUtil;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.net.Uri;

public class AndroidUtil {
	private static final AndroidUtil inst = new AndroidUtil();
	public static AndroidUtil getInst()
	{
		return inst;
	}
	
	private AndroidUtil()
	{
		
	}
	
	public static boolean isPackageInstalled(Context context,String packName)
	{
		 final PackageManager packageManager = context.getPackageManager();//获取packagemanager 
         List< PackageInfo> pinfo = packageManager.getInstalledPackages(0);//获取所有已安装程序的包信息 
         List<String> pName = new ArrayList<String>();//用于存储所有已安装程序的包名 
          //从pinfo中将包名字逐一取出，压入pName list中 
         if(pinfo != null)
         { 
             for(int i = 0; i < pinfo.size(); i++)
             { 
                 String pn = pinfo.get(i).packageName; 
                 pName.add(pn); 
             } 
         } 
         return pName.contains(packName);//判断pName中是否有目标程序的包名，有TRUE，没有FALSE 		
	}
	
	public static void installPackage(Context context, String packName)
	{
		String market = "market://details?id=";
		
		String uriStr = market + packName;
        Uri uri = Uri.parse(uriStr);//id为包名 
        Intent it = new Intent(Intent.ACTION_VIEW, uri); 
        context.startActivity(it);
	}

}
