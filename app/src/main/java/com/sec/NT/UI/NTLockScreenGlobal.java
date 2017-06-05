package com.sec.NT.UI;

public class NTLockScreenGlobal {

	static public float fScaleX = 1.0f;
	static public float fScaleY = 1.0f;
	
	static public float fWallScaleX = 1.0f;
	static public float fWallScaleY = 1.0f;
	
	static public long  startTime = 0;
	
	static public String curBmpPath = "data/data/com.example.lockscreendemo/files/";
	
	static public long  getCurrentTime()
	{
		return System.currentTimeMillis() - startTime;
	}
}
