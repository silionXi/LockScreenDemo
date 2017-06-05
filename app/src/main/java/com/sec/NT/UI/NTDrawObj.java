package com.sec.NT.UI;

import android.graphics.Canvas;
import android.util.Log;

import com.sec.NT.expression.Parser;
import com.sec.NT.logic.NTOperation_OP;
import com.sec.NT.logic.NTValue;
import com.sec.NT.logic.NTValue.VAL_TYPE;
import com.sec.NT.logic.NTValueFloat;
import com.sec.NT.logic.NTValueInteger;
import com.sec.NT.logic.NTValueString;
import com.sec.NT.logic.NTValueVariable;

public abstract class NTDrawObj {
	
	public enum ALIGN_TYPE{
		ALIGN_TYPE_LEFT,
		ALIGN_TYPE_CENTER,
		ALIGN_TYPE_RIGHT,
		ALIGN_TYPE_TOP,
		ALIGN_TYPE_BOTTOM
		};
	
	private static String TAG = "LockService";
	
	public abstract void doDraw(Canvas canvas, long time, float x, float y);
	
	public void setPos(String x, String y)
	{
		posX = parseExpression(x);
		posY = parseExpression(y);		
	}
	
	public void setCenter(String x, String y)
	{
		centerX = parseExpression(x);
		centerY = parseExpression(y);
	}
	
	public void setAngle(String a)
	{
		angle = parseExpression(a);
	}
	
	public void setAlpha(String a)
	{
		alpha = parseExpression(a);
	}
	
	public void setVAlignment(String align)
	{
		if( align.equals("top"))
			alignVType = ALIGN_TYPE.ALIGN_TYPE_TOP;
		else if(align.equals("center"))
			alignVType = ALIGN_TYPE.ALIGN_TYPE_CENTER;
		else if( align.equals("bottom"))
			alignVType =  ALIGN_TYPE.ALIGN_TYPE_BOTTOM;		
	}

	public void setHAlignment(String align)
	{
		if( align.equals("left"))
			alignHType = ALIGN_TYPE.ALIGN_TYPE_LEFT;
		else if(align.equals("center"))
			alignHType = ALIGN_TYPE.ALIGN_TYPE_CENTER;
		else if( align.equals("right"))
			alignHType =  ALIGN_TYPE.ALIGN_TYPE_RIGHT;		
	}
	
	protected NTValue parseExpression(String str)
	{
		return Parser.parseExpression(str);
	}
	
	
	protected int getAlpha()
	{
		if( alpha != null )
			return getIntValue(alpha);
		else
			return 255;
	}
	
	protected float getPosX()
	{
		return getFloatValue(posX);
	}
	
	protected float getPosY()
	{
		return getFloatValue(posY);
	}
	
	
	protected float getFloatValue(NTValue v)
	{
		if( v == null)
			return 0;
		
		if( v.getType() == VAL_TYPE.VAL_TYPE_INT)
		{
			return ((NTValueInteger)v).getValue() * 1.0f;
		}
		else if( v.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			return ((NTValueFloat)v).getValue();
		}
		else 
		{
			NTValue r = null;
			if( v.getType() == VAL_TYPE.VAL_TYPE_VARIABLE)
			{
				r = ((NTValueVariable)v).getValue();
			}
			else if( v.getType() == VAL_TYPE.VAL_TYPE_OPERATION)
			{
				r = ((NTOperation_OP)v).getValue();
			}
			
			
			if(r != null)
			{
				if( r.getType() == VAL_TYPE.VAL_TYPE_INT)
				{
					return ((NTValueInteger)r).getValue() * 1.0f;
				}
				else if( r.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
				{
					return ((NTValueFloat)r).getValue();
				}
			}

			Log.e(TAG, "getFloatValue parse error.");
		}
		
		return 0;
	}
	
	protected int getIntValue(NTValue v)
	{
		if( v == null)
			return 0;
		
		if( v.getType() == VAL_TYPE.VAL_TYPE_INT)
		{
			return ((NTValueInteger)v).getValue();
		}
		else if( v.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
		{
			return (int)((NTValueFloat)v).getValue();
		}
		else
		{
			NTValue r = null;
			if( v.getType() == VAL_TYPE.VAL_TYPE_VARIABLE)
			{
				r = ((NTValueVariable)v).getValue();
			}
			else if( v.getType() == VAL_TYPE.VAL_TYPE_OPERATION)
			{
				r = ((NTOperation_OP)v).getValue();
			}
			
			if( r!= null)
			{
				if( r.getType() == VAL_TYPE.VAL_TYPE_INT)
				{
					return ((NTValueInteger)r).getValue();
				}
				else if( r.getType() == VAL_TYPE.VAL_TYPE_FLOAT)
				{
					return (int)((NTValueFloat)r).getValue();
				}
			}

			Log.e(TAG, "getIntValue parse error.");
		}
		
		return 0;
	}
	
	protected String getStringValue(NTValue v)
	{
		if( v == null)
			return null;
		
		if( v.getType() == VAL_TYPE.VAL_TYPE_STRING)
		{
			return ((NTValueString)v).getValue();
		}
		else
		{
			NTValue r = null;
			if( v.getType() == VAL_TYPE.VAL_TYPE_VARIABLE)
			{
				r = ((NTValueVariable)v).getValue();
			}
			else if( v.getType() == VAL_TYPE.VAL_TYPE_OPERATION)
			{
				r = ((NTOperation_OP)v).getValue();
			}
			
			if( r!= null)
			{
				if( v.getType() == VAL_TYPE.VAL_TYPE_STRING)
				{
					return ((NTValueString)v).getValue();
				}
			}

			Log.e(TAG, "getStringValue parse error.");
		}
		
		return null;
	}
	
	
	protected NTValue posX =null;
	protected NTValue posY =null;
	
	protected NTValue centerX = null;
	protected NTValue centerY = null;
	
	protected NTValue angle = null;
	protected NTValue alpha = null;
	
	protected ALIGN_TYPE 	alignVType;
	protected ALIGN_TYPE 	alignHType;
	
	
}
