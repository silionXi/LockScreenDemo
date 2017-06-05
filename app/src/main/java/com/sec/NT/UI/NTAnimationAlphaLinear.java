package com.sec.NT.UI;



public class NTAnimationAlphaLinear implements NTAnimationAlpha{

	private AlphaTimeItem[]  aryAlpha;
	private int count = 0;
	
	public NTAnimationAlphaLinear(int arraySize)
	{
		count = 0;
		aryAlpha = new AlphaTimeItem[arraySize];
	}
	
	public long getMaxTime() {
		// TODO Auto-generated method stub
		return aryAlpha[count -1].get_time();
	}

	public void addAlphaTime(int alpha, long time)
	{
		aryAlpha[count] = new AlphaTimeItem(alpha, time);
		count++ ;
	}
	

	public int getAlpha(long time)
	{
		if( count == 0)
			return 255;
		
		time = time % this.getMaxTime();
		int i = 0; 
		while(i < count && aryAlpha[i].get_time() < time)
		{
			i++;		
		}

		if( i <= 0 || i >= count)
			return aryAlpha[0].get_alpha();
		
		int p0 = aryAlpha[i-1].get_alpha();
		int p1 = aryAlpha[i].get_alpha();
		long time0 = aryAlpha[i-1].get_time();
		long time1 = aryAlpha[i].get_time();
		
		return p0 + (int)((p1 - p0) * (time - time0)/(time1-time0));
	}

	
	

	
	public class AlphaTimeItem{
		public AlphaTimeItem(int alpha, long time)
		{
			_alpha = alpha;
			_time = time;
		}

		public long get_time() {
			return _time;
		}
		
		public int get_alpha() {
			return _alpha;
		}

		private int _alpha;

		private long   _time;
	}	
}
