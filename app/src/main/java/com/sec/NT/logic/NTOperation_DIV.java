package com.sec.NT.logic;


public class NTOperation_DIV extends NTOperation_OP {

	public NTOperation_DIV(NTValue param1, NTValue param2 )
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
				return new NTValueInteger(((NTValueInteger)p1).getValue() / ((NTValueInteger)p2).getValue());
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				return new NTValueFloat(((NTValueInteger)p1).getValue() / ((NTValueFloat)p2).getValue());
			}
		}
		else if( p1.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			if( p2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				return new NTValueFloat(((NTValueFloat)p1).getValue() / ((NTValueInteger)p2).getValue());
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				return new NTValueFloat(((NTValueFloat)p1).getValue() / ((NTValueFloat)p2).getValue());
			}			
		}
		else //if(p1.getType() == VAL_TYPE.VAL_TYPE_STRING)
		{

		}
		
		return null;
	}

}
