package com.sec.NT.UI;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

import com.sec.NT.expression.Parser;
import com.sec.NT.logic.NTValue;
import com.sec.NT.logic.NTValue.VAL_TYPE;
import com.sec.NT.logic.NTValueFloat;
import com.sec.NT.logic.NTValueString;

public class NTLockScreenManager extends NTDrawObj {
	
	//private NTGroupManager  backgroundObj;
	
	//private NTLockPath      lockpath;
	
	protected List<NTDrawObj>	backgroundObjs;
	protected List<NTLockPath>  lockpathObjs;
	protected List<NTDrawObj>	foregroundObjs;
	public NTLockPathLine curLockPath;
	
	private Bitmap wallpaperBmp = null;
	
	private static NTLockScreenManager _instance ;
	public static synchronized NTLockScreenManager getInstance()
	{
		if( _instance == null)
		{
			synchronized (NTLockScreenManager.class) {
				_instance = new NTLockScreenManager();
			}
		}
		
		return _instance;
	}	
	
	public NTLockScreenManager()
	{
		restart();
	}
	
	public void restart()
	{
		backgroundObjs = new ArrayList<NTDrawObj>();
		lockpathObjs   = new ArrayList<NTLockPath>();
		foregroundObjs = new ArrayList<NTDrawObj>();
		curLockPath = new NTLockPathLine();
	}
	
	public void addBackgroundObj(NTDrawObj obj)
	{
		this.backgroundObjs.add(obj);
	}
	
	public void addLockPath(NTLockPath path)
	{
		this.lockpathObjs.add(path);
	}
	
	public void addForegroundObj(NTDrawObj obj)
	{
		this.foregroundObjs.add(obj);
	}	
	
	@Deprecated
	public void setWallPaper(Bitmap bmp)
	{
		wallpaperBmp = bmp;
	}
	
	public void setWallPaper(String wallpaper)
	{
		if(!wallpaper.contains("'"))
		{
			this.wallpaperBmp = BitmapFactory.decodeFile(NTLockScreenGlobal.curBmpPath + wallpaper);
		}
		else
		{
			NTValue r = Parser.parseExpression(wallpaper);
			if( r.getType() == VAL_TYPE.VAL_TYPE_STRING)
			{
				String strBmp = ((NTValueString)r).getValue();
				this.wallpaperBmp = BitmapFactory.decodeFile(NTLockScreenGlobal.curBmpPath + strBmp);
			}
			
		}
	}
	
	public void doDraw(Canvas canvas, long time ) {
		this.doDraw(canvas, time, 0, 0);
	}

	@Override
	public void doDraw(Canvas canvas, long time, float x, float y) {
		// TODO Auto-generated method stub
		
		// draw wallpaper
		if( wallpaperBmp != null)
		{
			Matrix m = new Matrix();
			m.postScale(NTLockScreenGlobal.fWallScaleX, NTLockScreenGlobal.fWallScaleY);
			canvas.drawBitmap(wallpaperBmp, m, null);
		}
				
		
		for(NTDrawObj obj : backgroundObjs)
		{
			obj.doDraw(canvas, time, x, y);
		}
		
		for(NTLockPath path : lockpathObjs)
		{
			path.doDraw(canvas, time);
		}
		
		for(NTDrawObj obj : foregroundObjs)
		{
			obj.doDraw(canvas, time, x, y);
		}		
		
		//backgroundObj.doDraw(canvas, time, x, y);
		
		//lockpath.doDraw(canvas, time);
	}
	
	public boolean onTouchDown(float x, float y)
	{
		for(NTLockPath path : lockpathObjs)
		{
			path.onTouchDown(x, y);
		}
		
		return true;
	}
	
	public boolean onTouchMove(float x, float y)
	{
		for(NTLockPath path : lockpathObjs)
		{
			path.onTouchMove(x, y);
		}
		
		return true;
	}
	
	public boolean onTouchUp(float x, float y)
	{
		for(NTLockPath path : lockpathObjs)
		{
			path.onTouchUp(x, y);
		}
		
		return true;
	}
	
	public NTValue getUnlockInfo(String info)
	{
		if( lockpathObjs != null && lockpathObjs.size() > 0)
		{
			NTLockPath t = lockpathObjs.get(0);
			if( info.equals("#unlocker.move_x"))
			{
				return new NTValueFloat(t.getInfo_MoveX());
			}
			else if(info.equals("#unlocker.move_y"))
			{
				return new NTValueFloat(t.getInfo_MoveY());
			}
			
		}
		
		return null;
	}
	

}
