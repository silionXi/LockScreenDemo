package com.sec.NT.UI;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;

public class NTGroupManager  extends NTDrawObj{
	
	//private float _x;
	//private float _y;
	
	public NTGroupManager()
	{
		listObj = new ArrayList<NTDrawObj>();
	}
	
	@Deprecated
	public NTGroupManager(float x, float y)
	{
		listObj = new ArrayList<NTDrawObj>();
	}
	
	private List<NTDrawObj> listObj;
//	private List<NTImage> listImage;
//	private List<NTGroupManager> listGroup;
	
	
	public void addObject(NTDrawObj obj)
	{
		listObj.add(obj);
	}
	
	public void doDraw(Canvas canvas, long time)
	{
		doDraw(canvas, time, 0,0);
	}
	
	@Override
	public void doDraw(Canvas canvas, long time, float x, float y)
	{
		float _x = getPosX();
		float _y = getPosY();
		for(int i = 0 ; i < listObj.size(); i++ )
		{
			listObj.get(i).doDraw(canvas, time, x + _x, y + _y);
		}		
	}	

}
