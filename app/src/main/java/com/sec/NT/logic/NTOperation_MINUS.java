package com.sec.NT.logic;


public class NTOperation_MINUS extends NTOperation_OP  {

	public NTOperation_MINUS(NTValue param1)
	{
		super(param1, null);
	}	
	
	@Override
	public NTValue getValue() {
		// TODO Auto-generated method stub
		NTValue p1 = param1;
		// expression process
		if( param1.getType() == VAL_TYPE.VAL_TYPE_OPERATION)
		{
			p1 = ((NTOperation_OP)param1).getValue();
		}
		else if(param1.getType() == VAL_TYPE.VAL_TYPE_VARIABLE)
		{
			p1 = ((NTValueVariable)param1).getValue();
		}
		
		if( p1.getType() == VAL_TYPE.VAL_TYPE_INT)
		{
			return new NTValueInteger(((NTValueInteger)p1).getValue() * (-1));
		}
		else if( p1.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			return new NTValueFloat(((NTValueFloat)p1).getValue() * (-1));
		}
		
		return null;
	}

}
