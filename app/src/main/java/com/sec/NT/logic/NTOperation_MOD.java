package com.sec.NT.logic;


public class NTOperation_MOD extends NTOperation_OP  {

	public NTOperation_MOD(NTValue param1, NTValue param2 )
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
			if( p2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				return new NTValueInteger(((NTValueInteger)p1).getValue() % ((NTValueInteger)p2).getValue());
			}
		}
		else //if(p1.getType() == VAL_TYPE.VAL_TYPE_STRING)
		{

		}
		
		return null;
	}	
}
