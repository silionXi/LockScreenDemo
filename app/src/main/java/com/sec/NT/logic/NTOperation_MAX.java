package com.sec.NT.logic;


public class NTOperation_MAX extends NTOperation_OP {

	public NTOperation_MAX(NTValue param1, NTValue param2 )
	{
		super(param1, param2);
	}
	
	@Override
	public NTValue getValue() {
		// TODO Auto-generated method stub
		checkParam();
		
		if( p1 == null || p2 == null)
			return null;

		// value process
		if( p1.getType() == VAL_TYPE.VAL_TYPE_INT)
		{
			int t1 = ((NTValueInteger)p1).getValue();
			if( p2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				int t2 = ((NTValueInteger)p2).getValue();
				if( t1 > t2)
					return p1;
				else
					return p2;
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				float t2 = ((NTValueFloat)p2).getValue();
				if( t1 > t2)
					return p1;
				else
					return p2;
			}
		}
		else if( p1.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			float t1 = ((NTValueFloat)p1).getValue();
			if( p2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				int t2 = ((NTValueInteger)p2).getValue();
				if( t1 > t2)
					return p1;
				else
					return p2;
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				float t2 = ((NTValueFloat)p2).getValue();
				if( t1 > t2)
					return p1;
				else
					return p2;				
			}			
		}
		else //if(p1.getType() == VAL_TYPE.VAL_TYPE_STRING)
		{

		}
		
		return null;
	}

}
