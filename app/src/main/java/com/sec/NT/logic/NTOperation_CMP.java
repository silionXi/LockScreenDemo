package com.sec.NT.logic;


public class NTOperation_CMP extends NTOperation_OP {
	
	public enum CMP_TYPE{
		CMP_TYPE_GT,
		CMP_TYPE_GE,
		CMP_TYPE_LT,
		CMP_TYPE_LE,
		CMP_TYPE_EQ,
		CMP_TYPE_NE
	};
	
	protected CMP_TYPE cmp_type ;
	
	public NTOperation_CMP(CMP_TYPE ctype, NTValue param1, NTValue param2)
	{
		super(param1, param2);
		cmp_type = ctype;		
	}

	
	
	@Override
	public NTValue getValue() {
		// TODO Auto-generated method stub
		// expression process
		checkParam();
		
		if( p1 == null || p2 == null)
			return null;
		
		boolean ret= false ;
		// value process
		if( p1.getType() == VAL_TYPE.VAL_TYPE_INT)
		{
			if( p2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				if( cmp_type == CMP_TYPE.CMP_TYPE_GT)
				{
					ret = ((NTValueInteger)p1).getValue() > ((NTValueInteger)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_GE)
				{
					ret = ((NTValueInteger)p1).getValue() >= ((NTValueInteger)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_LT)
				{
					ret = ((NTValueInteger)p1).getValue() < ((NTValueInteger)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_LE)
				{
					ret = ((NTValueInteger)p1).getValue() <= ((NTValueInteger)p2).getValue();
				}			
				else if( cmp_type == CMP_TYPE.CMP_TYPE_EQ)
				{
					ret = ((NTValueInteger)p1).getValue() == ((NTValueInteger)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_NE)
				{
					ret = ((NTValueInteger)p1).getValue() != ((NTValueInteger)p2).getValue();
				}			
				
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				if( cmp_type == CMP_TYPE.CMP_TYPE_GT)
				{
					ret = ((NTValueInteger)p1).getValue() > ((NTValueFloat)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_GE)
				{
					ret = ((NTValueInteger)p1).getValue() >= ((NTValueFloat)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_LT)
				{
					ret = ((NTValueInteger)p1).getValue() < ((NTValueFloat)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_LE)
				{
					ret = ((NTValueInteger)p1).getValue() <= ((NTValueFloat)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_EQ)
				{
					ret = ((NTValueInteger)p1).getValue() == ((NTValueFloat)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_NE)
				{
					ret = ((NTValueInteger)p1).getValue() != ((NTValueFloat)p2).getValue();
				}					
			}
		}
		else if( p1.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			if( p2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				if( cmp_type == CMP_TYPE.CMP_TYPE_GT)
				{
					ret = ((NTValueFloat)p1).getValue() > ((NTValueInteger)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_GE)
				{
					ret = ((NTValueFloat)p1).getValue() >= ((NTValueInteger)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_LT)
				{
					ret = ((NTValueFloat)p1).getValue() < ((NTValueInteger)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_LE)
				{
					ret = ((NTValueFloat)p1).getValue() <= ((NTValueInteger)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_EQ)
				{
					ret = ((NTValueFloat)p1).getValue() == ((NTValueInteger)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_NE)
				{
					ret = ((NTValueFloat)p1).getValue() != ((NTValueInteger)p2).getValue();
				}				
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				if( cmp_type == CMP_TYPE.CMP_TYPE_GT)
				{
					ret = ((NTValueFloat)p1).getValue() > ((NTValueFloat)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_GE)
				{
					ret = ((NTValueFloat)p1).getValue() >= ((NTValueFloat)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_LT)
				{
					ret = ((NTValueFloat)p1).getValue() < ((NTValueFloat)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_LE)
				{
					ret = ((NTValueFloat)p1).getValue() <= ((NTValueFloat)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_EQ)
				{
					ret = ((NTValueFloat)p1).getValue() == ((NTValueFloat)p2).getValue();
				}
				else if( cmp_type == CMP_TYPE.CMP_TYPE_NE)
				{
					ret = ((NTValueFloat)p1).getValue() != ((NTValueFloat)p2).getValue();
				}				
			}
		}
		else if(p1.getType() == VAL_TYPE.VAL_TYPE_STRING)
		{
			if(p2.getType() == VAL_TYPE.VAL_TYPE_STRING)
			{
				if((((NTValueString)p1).getValue()).equals(((NTValueString)p2).getValue()))
				{
					if( cmp_type == CMP_TYPE.CMP_TYPE_EQ)
					{
						ret = true;
					}
					else
					{
						ret = false;
					}
				}
				else
				{
					if( cmp_type == CMP_TYPE.CMP_TYPE_EQ)
					{
						ret = false;
					}
					else
					{
						ret = true;
					}
				}
			}			
		}
		
		if(ret)
			return new NTValueInteger(1);
		else
			return new NTValueInteger(0);
		
	}

}
