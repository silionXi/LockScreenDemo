package com.sec.NT.UI;

import android.graphics.PointF;


public class NTAnimationPositionLinear implements NTAnimationPosition{
	
	private PosTimeItem[]  aryPos;
	private int count = 0;
	
	public NTAnimationPositionLinear(int arraySize)
	{
		count = 0;
		aryPos = new PosTimeItem[arraySize];
	}
	
	public void addPosTime(float x, float y, long time)
	{
		aryPos[count] = new PosTimeItem(x, y, time);
		count++ ;
	}
	
	public void addPosTime(PointF pt, long time)
	{
		aryPos[count] = new PosTimeItem(pt.x, pt.y, time);
		
		count++ ;
	}	
	
	
	
	public long getMaxTime() {
		// TODO Auto-generated method stub
		return aryPos[count -1].get_time();
	}

	public PointF getPos(long time)
	{
		if( count == 0)
			return new PointF(0,0);
		
		time = time % this.getMaxTime();
		int i = 0; 
		while(i < count && aryPos[i].get_time() < time)
		{
			i++;		
		}
		
		if( i <= 0 || i >= count)
			return aryPos[0].get_Pt();
		
		PointF p0 = aryPos[i-1].get_Pt();
		PointF p1 = aryPos[i].get_Pt();
		long time0 = aryPos[i-1].get_time();
		long time1 = aryPos[i].get_time();
		
		return new PointF(p0.x + (p1.x - p0.x) * (time - time0)/(time1-time0), p0.y + (p1.y - p0.y) * (time - time0)/(time1-time0));
	}

	
	public class PosTimeItem{
		public PosTimeItem(float x, float y, long time)
		{
			_pt = new PointF(x, y);
			_time = time;
		}
		
		public PosTimeItem(PointF pt, long time)
		{
			_pt = new PointF(pt.x, pt.y);
			_time = time;
		}
		

		public PointF get_Pt() {
			return _pt;
		}


		public long get_time() {
			return _time;
		}
		
		private PointF _pt;

		private long   _time;
	}
}
