package com.sec.NT.logic;


public class NTOperation_ADD extends NTOperation_OP  {
	
	public NTOperation_ADD(NTValue param1, NTValue param2 )
	{
		super(param1, param2);
	}
	
	public NTValue getValue() 
	{

		// expression process
		checkParam();
		
		if( p1 == null || p2 == null)
			return null;
		
		// value process
		if( p1.getType() == VAL_TYPE.VAL_TYPE_INT)
		{
			if( p2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				return new NTValueInteger(((NTValueInteger)p1).getValue() + ((NTValueInteger)p2).getValue());
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				return new NTValueFloat(((NTValueInteger)p1).getValue() + ((NTValueFloat)p2).getValue());
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_STRING)
			{
				return null;
			}
		}
		else if( p1.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			if( p2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				return new NTValueFloat(((NTValueFloat)p1).getValue() + ((NTValueInteger)p2).getValue());
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				return new NTValueFloat(((NTValueFloat)p1).getValue() + ((NTValueFloat)p2).getValue());
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_STRING)
			{
				return null;
			}			
		}
		else if(p1.getType() == VAL_TYPE.VAL_TYPE_STRING)
		{
			
			if( p2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				return new NTValueString(((NTValueString)p1).getValue() + ((NTValueInteger)p2).getValue());
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				return new NTValueString(((NTValueString)p1).getValue() + ((NTValueFloat)p2).getValue());
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_STRING)
			{
				return new NTValueString(((NTValueString)p1).getValue() + ((NTValueFloat)p2).getValue());
			}
		}
			

		return null; // unsupport.
		
	}
}
