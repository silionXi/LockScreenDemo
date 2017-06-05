package com.sec.NT.UI;

import android.graphics.Canvas;

public interface NTLockPath {
	public boolean onTouchDown(float x, float y);
	public boolean onTouchMove(float x, float y);
	public boolean onTouchUp(float x, float y);
	
	public void doDraw(Canvas canvas, long time);
	public void setAudio(String strPressed, String strReleased);	
	public void registerUnlockListner(NTLockScreenListener listener);
	
	public float getInfo_MoveX();
	public float getInfo_MoveY();
}
