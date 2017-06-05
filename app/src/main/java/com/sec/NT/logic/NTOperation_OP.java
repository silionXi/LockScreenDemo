package com.sec.NT.logic;




public abstract class NTOperation_OP extends NTValue {
	protected NTValue param1;
	protected NTValue param2;
	
	public NTOperation_OP(NTValue param1, NTValue param2 )
	{
		this.param1 =param1;
		this.param2 = param2;
		this.type = VAL_TYPE.VAL_TYPE_OPERATION;		
	}
	
	public abstract NTValue getValue();
	
	protected NTValue p1 = null;
	protected NTValue p2 = null;
	
	protected void checkParam()
	{
		p1 = param1;
		p2 = param2;
		if( param1 != null)
		{
			if( param1.getType() == VAL_TYPE.VAL_TYPE_OPERATION)
			{
				p1 = ((NTOperation_OP)param1).getValue();
			}
			else if(param1.getType() == VAL_TYPE.VAL_TYPE_VARIABLE)
			{
				p1 = ((NTValueVariable)param1).getValue();
			}
		}
		
		if( param2 != null)
		{
			if( param2.getType() == VAL_TYPE.VAL_TYPE_OPERATION)
			{
				p2 = ((NTOperation_OP)param2).getValue();
			}
			else if(param2.getType() == VAL_TYPE.VAL_TYPE_VARIABLE)
			{
				p2 = ((NTValueVariable)param2).getValue();
			}
		}
		
		
	}
}
