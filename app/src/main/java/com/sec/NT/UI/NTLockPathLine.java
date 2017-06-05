package com.sec.NT.UI;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

public class NTLockPathLine implements NTLockPath {

	private static String TAG = "LockService";
	
	private long   startTime = 0;
	
	public NTLockPathLine()
	{
		_pressed = false;
		this.pressedObjs = null;
		this.normalObjs = null;
		
		_pressedX = 0;
		_pressedY = 0;
		_offsetX  = 0;
		_offsetY  = 0;
		startTime = 0;
	}
	
	private NTLockScreenListener _listener = null; 
	
	public void registerUnlockListner(NTLockScreenListener listener)
	{
		_listener = listener;		
	}


	@Override
	public boolean onTouchDown(float x, float y) {
		// TODO Auto-generated method stub
		if( _startRect.contains((int)x, (int)y))
		{
			_pressed = true;
			_pressedX = x;
			_pressedY = y;
			_offsetX = 0 ;
			_offsetY = 0 ;
			
			PlaySound(strAudioPressed);
			
			startTime = NTLockScreenGlobal.getCurrentTime(); 
		}
		return _pressed;
	}

	@Override
	public boolean onTouchMove(float x, float y) {
		// TODO Auto-generated method stub
		if( _pressed)
		{
			if( _direction)
			{
				_offsetX = x - _pressedX ;
				_offsetY = 0;
			}
			else
			{
				_offsetY = y - _pressedY ;
				_offsetX = 0;				
			}
		}
		return _pressed;
	}

	@Override
	public boolean onTouchUp(float x, float y) {
		// TODO Auto-generated method stub
		
		if( _endRect.contains((int)x, (int)y))
		{
			Log.i(TAG, "unlock succeed");
			if( _pressed)
			{
				_offsetX = x - _pressedX ;
				_offsetY = y - _pressedY ;
				if( _direction)
				{
					if( _offsetX > _tolerance)
					{
						_listener.onUnLock();
					}	
				}
				else
				{
					if( _offsetY > _tolerance)
					{
						_listener.onUnLock();
					}	
				}
				
				if( pressedObjs != null)
				{
					startTime = NTLockScreenGlobal.getCurrentTime(); 
				}
			}
			else
			{
				PlaySound(strAudioReleased);
			}
			
		}
		
		_pressed = false;
		_offsetX = 0;
		_offsetY = 0;
		return _pressed;
	}
	
	@Override
	public void doDraw(Canvas canvas, long time)
	{
		if( _pressed )
		{
			Log.i(TAG, "draw _pressed= true offsetX = "+ _offsetX + "offsetY =" + _offsetY);
			if( this.pressedObjs != null)
				this.pressedObjs.doDraw(canvas, time -startTime, _offsetX, _offsetY);
			else
				this.normalObjs.doDraw(canvas, time -startTime, _offsetX, _offsetY);
		}
		else
		{
			Log.i(TAG, "draw _pressed= false");
			if( this.normalObjs != null)
				this.normalObjs.doDraw(canvas, time -startTime,0,0);
		}
	}
	
	
	
	public void setPressedObjs(NTGroupManager pressedObjs) {
		this.pressedObjs = pressedObjs;
	}

	public void setNormalObjs(NTGroupManager normalObjs) {
		this.normalObjs = normalObjs;
	}
	
	public void addNormalObjs(NTImage image) {
		if(this.normalObjs == null) {
			this.normalObjs = new NTGroupManager();
		}
		this.normalObjs.addObject(image);
	}
	
	public void setAudio(String strPressed, String strReleased)
	{
		this.strAudioPressed = strPressed;
		this.strAudioReleased = strReleased;
	}
	
	private void PlaySound( String strFile)
	{
		try{
			MediaPlayer mPlayer = new MediaPlayer();
			mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mPlayer.setDataSource(strFile);
			mPlayer.prepare();
			mPlayer.start();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	


	public void set_startRect(Rect _startRect) {
		this._startRect = _startRect;
	}

	public void set_endRect(Rect _endRect) {
		this._endRect = _endRect;
	}

	@Override
	public float getInfo_MoveX()
	{
		return /*this._startRect.left + */_offsetX;
	}

	@Override
	public float getInfo_MoveY()
	{
		return /*this._startRect.top + */_offsetY;
	}

	public void set_tolerance(int _tolerance) {
		this._tolerance = _tolerance;
	}
	
    /**
     * currently, there has only two point path. 
     * both of the two points, has one direction is 0. 
     * for example(0, 20)--->(0, 300)   or (30, 0)---->(400, 0)
     */	
	public void setPath(int x0, int y0, int x1, int y1) throws Exception
	{
		if( x0 == 0 && x0 == x1)
			_direction = false;
		else if( y0 == 0 && y1 == 0)
			_direction = true;
	}

	private String          strAudioPressed;
	private String          strAudioReleased;


	private NTGroupManager	pressedObjs;
	private NTGroupManager    normalObjs;
	
	private boolean    		_pressed;
	private boolean         _direction = false;  // false -> vertical    true --> horizon.

	
	private float 	_pressedX;
	private float 	_pressedY;
	
	private float   _offsetX;
	private float   _offsetY;
	
	private int     _tolerance =200;
	
	Rect			_startRect;
	Rect			_endRect;
		

}
