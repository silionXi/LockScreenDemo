package com.sec.NT.logic;

import com.sec.NT.logic.NTOperation_CMP.CMP_TYPE;
import com.sec.NT.logic.NTValue.VAL_TYPE;

public class NTOperation {
	static public NTValue CreateOP_MINUS(NTValue param1)
	{
		if( param1 == null)
			return null;
		
		if( param1.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param1.getType() == VAL_TYPE.VAL_TYPE_VARIABLE)
		{
			return new NTOperation_MUL(param1, new NTValueInteger(-1));
		}
		else if(param1.getType() == VAL_TYPE.VAL_TYPE_INT)
		{
			return new NTValueInteger((-1) * ((NTValueInteger)param1).getValue());
		}
		else if(param1.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			return new NTValueFloat((-1) * ((NTValueFloat)param1).getValue());
		}
		
		return null;
	}
	
	static public NTValue CreateOP_ADD(NTValue param1, NTValue param2)
	{
		if( param1.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param1.getType() == VAL_TYPE.VAL_TYPE_VARIABLE 
		  ||param2.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param2.getType() == VAL_TYPE.VAL_TYPE_VARIABLE	)
		{
			return new NTOperation_ADD(param1, param2);
		}
		
		NTValue p1 = param1;
		NTValue p2 = param2;
		
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
				return new NTValueString(((NTValueString)p1).getValue() + ((NTValueString)p2).getValue());
			}
		}
		
		return null;
	}
	
	static public NTValue CreateOP_SUB(NTValue param1, NTValue param2)
	{
		if( param1.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param1.getType() == VAL_TYPE.VAL_TYPE_VARIABLE 
				  ||param2.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param2.getType() == VAL_TYPE.VAL_TYPE_VARIABLE	){
			return new NTOperation_SUB(param1, param2);
			}
				
		NTValue p1 = param1;
		NTValue p2 = param2;
		
		if( p1.getType() == VAL_TYPE.VAL_TYPE_INT)
		{
			if( p2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				return new NTValueInteger(((NTValueInteger)p1).getValue() - ((NTValueInteger)p2).getValue());
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				return new NTValueFloat(((NTValueInteger)p1).getValue() - ((NTValueFloat)p2).getValue());
			}

		}
		else if( p1.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			if( p2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				return new NTValueFloat(((NTValueFloat)p1).getValue() - ((NTValueInteger)p2).getValue());
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				return new NTValueFloat(((NTValueFloat)p1).getValue() - ((NTValueFloat)p2).getValue());
			}
		}
		return null;
	}
	
	static public NTValue CreateOP_MUL(NTValue param1, NTValue param2)
	{
		if( param1.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param1.getType() == VAL_TYPE.VAL_TYPE_VARIABLE 
				  ||param2.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param2.getType() == VAL_TYPE.VAL_TYPE_VARIABLE	){
			return new NTOperation_MUL(param1, param2);
			}
				
		NTValue p1 = param1;
		NTValue p2 = param2;
		
		if( p1.getType() == VAL_TYPE.VAL_TYPE_INT)
		{
			if( p2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				return new NTValueInteger(((NTValueInteger)p1).getValue() * ((NTValueInteger)p2).getValue());
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				return new NTValueFloat(((NTValueInteger)p1).getValue() * ((NTValueFloat)p2).getValue());
			}

		}
		else if( p1.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			if( p2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				return new NTValueFloat(((NTValueFloat)p1).getValue() * ((NTValueInteger)p2).getValue());
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				return new NTValueFloat(((NTValueFloat)p1).getValue() * ((NTValueFloat)p2).getValue());
			}
		}
		return null;
	}
	
	static public NTValue CreateOP_DIV(NTValue param1, NTValue param2)
	{
		if( param1.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param1.getType() == VAL_TYPE.VAL_TYPE_VARIABLE 
				  ||param2.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param2.getType() == VAL_TYPE.VAL_TYPE_VARIABLE	){
			return new NTOperation_DIV(param1, param2);
			}
				
		NTValue p1 = param1;
		NTValue p2 = param2;
		
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
		return null;
	}
	
	static public NTValue CreateOP_MOD(NTValue param1, NTValue param2)
	{
		if( param1.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param1.getType() == VAL_TYPE.VAL_TYPE_VARIABLE 
				  ||param2.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param2.getType() == VAL_TYPE.VAL_TYPE_VARIABLE	){
			return new NTOperation_MOD(param1, param2);
			}
				
		NTValue p1 = param1;
		NTValue p2 = param2;
		
		if( p1.getType() == VAL_TYPE.VAL_TYPE_INT)
		{
			if( p2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				return new NTValueInteger(((NTValueInteger)p1).getValue() % ((NTValueInteger)p2).getValue());
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				return new NTValueInteger(((NTValueInteger)p1).getValue() % ((int) ((NTValueFloat)p2).getValue()));
			}

		}
		else if( p1.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			if( p2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				return new NTValueFloat(((NTValueFloat)p1).getValue() % ((NTValueInteger)p2).getValue());
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				return new NTValueFloat(((int)((NTValueFloat)p1).getValue()) % ((int) ((NTValueFloat)p2).getValue()));
			}
		}
		return null;
	}
	
	static public NTValue CreateOP_CMPGE(NTValue param1, NTValue param2)
	{
		if( param1.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param1.getType() == VAL_TYPE.VAL_TYPE_VARIABLE 
				  ||param2.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param2.getType() == VAL_TYPE.VAL_TYPE_VARIABLE	){
			return new NTOperation_CMP(CMP_TYPE.CMP_TYPE_GE, param1, param2);
			}
		
		boolean ret = false ;
		if( param1.getType() == VAL_TYPE.VAL_TYPE_INT)
		{
			if( param2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				ret = ((NTValueInteger)param1).getValue() >= ((NTValueInteger)param2).getValue();
			}
			else if(param2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				ret = ((NTValueInteger)param1).getValue() >= ((NTValueFloat)param2).getValue();
			}
		}
		else if( param1.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			if( param2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				ret = ((NTValueFloat)param1).getValue() >= ((NTValueInteger)param2).getValue();
			}
			else if(param2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				ret = ((NTValueFloat)param1).getValue() >= ((NTValueFloat)param2).getValue();
			}
		}
		
		if(ret)
			return new NTValueInteger(1);
		else
			return new NTValueInteger(0);
				
	}

	static public NTValue CreateOP_CMPGT(NTValue param1, NTValue param2)
	{
		if( param1.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param1.getType() == VAL_TYPE.VAL_TYPE_VARIABLE 
				  ||param2.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param2.getType() == VAL_TYPE.VAL_TYPE_VARIABLE	){
			return new NTOperation_CMP(CMP_TYPE.CMP_TYPE_GT, param1, param2);
			}
		
		boolean ret = false ;
		if( param1.getType() == VAL_TYPE.VAL_TYPE_INT)
		{
			if( param2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				ret = ((NTValueInteger)param1).getValue() > ((NTValueInteger)param2).getValue();
			}
			else if(param2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				ret = ((NTValueInteger)param1).getValue() > ((NTValueFloat)param2).getValue();
			}
		}
		else if( param1.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			if( param2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				ret = ((NTValueFloat)param1).getValue() > ((NTValueInteger)param2).getValue();
			}
			else if(param2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				ret = ((NTValueFloat)param1).getValue() > ((NTValueFloat)param2).getValue();
			}
		}
		
		if(ret)
			return new NTValueInteger(1);
		else
			return new NTValueInteger(0);
	}

	static public NTValue CreateOP_CMPLE(NTValue param1, NTValue param2)
	{
		if( param1.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param1.getType() == VAL_TYPE.VAL_TYPE_VARIABLE 
				  ||param2.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param2.getType() == VAL_TYPE.VAL_TYPE_VARIABLE	){
			return new NTOperation_CMP(CMP_TYPE.CMP_TYPE_LE, param1, param2);
			}
		
		boolean ret = false ;
		if( param1.getType() == VAL_TYPE.VAL_TYPE_INT)
		{
			if( param2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				ret = ((NTValueInteger)param1).getValue() <= ((NTValueInteger)param2).getValue();
			}
			else if(param2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				ret = ((NTValueInteger)param1).getValue() <= ((NTValueFloat)param2).getValue();
			}
		}
		else if( param1.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			if( param2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				ret = ((NTValueFloat)param1).getValue() <= ((NTValueInteger)param2).getValue();
			}
			else if(param2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				ret = ((NTValueFloat)param1).getValue() <= ((NTValueFloat)param2).getValue();
			}
		}
		
		if(ret)
			return new NTValueInteger(1);
		else
			return new NTValueInteger(0);
	}
	
	static public NTValue CreateOP_CMPLT(NTValue param1, NTValue param2)
	{
		if( param1.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param1.getType() == VAL_TYPE.VAL_TYPE_VARIABLE 
				  ||param2.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param2.getType() == VAL_TYPE.VAL_TYPE_VARIABLE	){
			return new NTOperation_CMP(CMP_TYPE.CMP_TYPE_LT, param1, param2);
			}
		
		boolean ret = false ;
		if( param1.getType() == VAL_TYPE.VAL_TYPE_INT)
		{
			if( param2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				ret = ((NTValueInteger)param1).getValue() < ((NTValueInteger)param2).getValue();
			}
			else if(param2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				ret = ((NTValueInteger)param1).getValue() < ((NTValueFloat)param2).getValue();
			}
		}
		else if( param1.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			if( param2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				ret = ((NTValueFloat)param1).getValue() < ((NTValueInteger)param2).getValue();
			}
			else if(param2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				ret = ((NTValueFloat)param1).getValue() < ((NTValueFloat)param2).getValue();
			}
		}
		
		if(ret)
			return new NTValueInteger(1);
		else
			return new NTValueInteger(0);
	}
	
	static public NTValue CreateOP_CMPEQ(NTValue param1, NTValue param2)
	{
		if( param1.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param1.getType() == VAL_TYPE.VAL_TYPE_VARIABLE 
				  ||param2.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param2.getType() == VAL_TYPE.VAL_TYPE_VARIABLE	){
			return new NTOperation_CMP(CMP_TYPE.CMP_TYPE_EQ, param1, param2);
			}
		
		boolean ret = false ;
		if( param1.getType() == VAL_TYPE.VAL_TYPE_INT)
		{
			if( param2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				ret = ((NTValueInteger)param1).getValue() == ((NTValueInteger)param2).getValue();
			}
			else if(param2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				ret = ((NTValueInteger)param1).getValue() == ((NTValueFloat)param2).getValue();
			}
		}
		else if( param1.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			if( param2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				ret = ((NTValueFloat)param1).getValue() == ((NTValueInteger)param2).getValue();
			}
			else if(param2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				ret = ((NTValueFloat)param1).getValue() == ((NTValueFloat)param2).getValue();
			}
		}
		else if(param1.getType() == VAL_TYPE.VAL_TYPE_STRING)
		{
			if(param2.getType() == VAL_TYPE.VAL_TYPE_STRING)
			{
				if((((NTValueString)param1).getValue()).equals(((NTValueString)param2).getValue()))
				{
					ret = true;
				}
				else
				{
					ret = false;
				}
			}			
		}	
		
		if(ret)
			return new NTValueInteger(1);
		else
			return new NTValueInteger(0);
	}
	
	
	static public NTValue CreateOP_CMPNE(NTValue param1, NTValue param2)
	{
		if( param1.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param1.getType() == VAL_TYPE.VAL_TYPE_VARIABLE 
				  ||param2.getType() == VAL_TYPE.VAL_TYPE_OPERATION || param2.getType() == VAL_TYPE.VAL_TYPE_VARIABLE	){
			return new NTOperation_CMP(CMP_TYPE.CMP_TYPE_LT, param1, param2);
			}
		
		boolean ret = false ;
		if( param1.getType() == VAL_TYPE.VAL_TYPE_INT)
		{
			if( param2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				ret = ((NTValueInteger)param1).getValue() < ((NTValueInteger)param2).getValue();
			}
			else if(param2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				ret = ((NTValueInteger)param1).getValue() < ((NTValueFloat)param2).getValue();
			}
		}
		else if( param1.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			if( param2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				ret = ((NTValueFloat)param1).getValue() < ((NTValueInteger)param2).getValue();
			}
			else if(param2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				ret = ((NTValueFloat)param1).getValue() < ((NTValueFloat)param2).getValue();
			}
		}
		else if(param1.getType() == VAL_TYPE.VAL_TYPE_STRING)
		{
			if(param2.getType() == VAL_TYPE.VAL_TYPE_STRING)
			{
				if((((NTValueString)param1).getValue()).equals(((NTValueString)param2).getValue()))
				{
					ret = false;
				}
				else
				{
					ret = true;
				}
			}			
		}			
		
		if(ret)
			return new NTValueInteger(1);
		else
			return new NTValueInteger(0);
	}
		
	
	static public NTValue CreateOP_MAX(NTValue param1, NTValue param2)
	{
		NTValue p1 = param1;
		NTValue p2 = param2;
		
		
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
		return null;
	}
	
	static public NTValue CreateOP_MIN(NTValue param1, NTValue param2)
	{
		NTValue p1 = param1;
		NTValue p2 = param2;
		
		
		if( p1.getType() == VAL_TYPE.VAL_TYPE_INT)
		{
			int t1 = ((NTValueInteger)p1).getValue();
			if( p2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				int t2 = ((NTValueInteger)p2).getValue();
				if( t1 > t2)
					return p2;
				else
					return p1;
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				float t2 = ((NTValueFloat)p2).getValue();
				if( t1 > t2)
					return p2;
				else
					return p1;
			}
		}
		else if( p1.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			float t1 = ((NTValueFloat)p1).getValue();
			if( p2.getType() == VAL_TYPE.VAL_TYPE_INT)
			{
				int t2 = ((NTValueInteger)p2).getValue();
				if( t1 > t2)
					return p2;
				else
					return p1;
			}
			else if(p2.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
			{
				float t2 = ((NTValueFloat)p2).getValue();
				if( t1 > t2)
					return p2;
				else
					return p1;
			}			
		}		
		return null;
	}
	
	
}
