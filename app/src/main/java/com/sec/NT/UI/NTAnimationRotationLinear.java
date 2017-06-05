package com.sec.NT.UI;

import android.graphics.PointF;


public class NTAnimationRotationLinear  implements NTAnimationRotation{
	private RotationTimeItem[]  aryRotation;
	private PointF				_center;
	private int count = 0;
	
	public NTAnimationRotationLinear(int arraySize)
	{
		count = 0;
		aryRotation = new RotationTimeItem[arraySize];
	}
	
	public void addRotationTime(int rotation, long time)
	{
		aryRotation[count] = new RotationTimeItem(rotation, time);
		count++ ;
	}
	
	

	public long getMaxTime() {
		// TODO Auto-generated method stub
		return aryRotation[count -1].get_time();
	}

	public float getRotation(long time)
	{
		if( count == 0)
			return 255;
		
		time = time % this.getMaxTime();
		int i = 0; 
		while(i < count && aryRotation[i].get_time() < time)
		{
			i++;		
		}

		if( i <= 0 || i >= count)
			return aryRotation[0].get_rotation();
		
		float p0 = aryRotation[i-1].get_rotation();
		float p1 = aryRotation[i].get_rotation();
		long time0 = aryRotation[i-1].get_time();
		long time1 = aryRotation[i].get_time();
		
		return p0 + (p1 - p0) * (time - time0)/(time1-time0);
	}
	
	public class RotationTimeItem{
		public RotationTimeItem(float rotation, long time)
		{
			_rotation = rotation;
			_time = time;
		}

		
		public float get_rotation() {
			return _rotation;
		}


		public long get_time() {
			return _time;
		}
		
		private float _rotation;

		private long   _time;
	}

	public PointF get_center() {
		return _center;
	}

	public void set_center(PointF _center) {
		this._center = _center;
	}	
	
	
}
