package com.xinxin.data;

//import java.util.ArrayList;
//import java.io.*;

public class Data {
	private int count;
	private char[] list;
	private static final Data data = new Data();
	public static Data getDataInst()
	{
		return data;
	}
	
	private Data()
	{
		count = 6;
		list = new char[20];
		
		list[0] = '人';
		list[1] = '口';
		list[2] = '手';
		list[3] = '上';
		list[4] = '中';
		list[5] = '下';
		
	}
	public char getFist()
	{
		return list[0];
	}
	
	public char getNext(int index)
	{
		if (index >= count)
			index = count -1;
		return list[index];
	}
	
	public int getLessonCount() {
		// TODO Auto-generated method stub
		return count;
	}

	public char get(int index) {
		// TODO Auto-generated method stub
		if (index >= count)
			index = count -1;

		return list[index];
		
	}

	public int getNextIndex(int index) {
		// TODO Auto-generated method stub
		if (++index >=count)
			index = 0;
		return index;
	}
}
