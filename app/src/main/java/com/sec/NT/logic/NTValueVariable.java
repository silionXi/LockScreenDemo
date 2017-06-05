package com.sec.NT.logic;

import java.util.Calendar;

import com.sec.NT.UI.NTLockScreenManager;

public  class NTValueVariable extends NTValue {
	
	public NTValueVariable(String name)
	{
		type = VAL_TYPE.VAL_TYPE_VARIABLE;
		_name = name; 
	}
	private String 		_name;
	
	
	
	
	public String get_name() {
		return _name;
	}

	public NTValue getValue( )
	{
		Calendar now = Calendar.getInstance();  
		if(_name.equals("#year"))
		{
			return new NTValueInteger(now.get(Calendar.YEAR));
		}
		else if(_name.equals("#month"))
		{
			return new NTValueInteger(now.get(Calendar.MONTH));
		}
		else if(_name.equals("#day"))
		{
			return new NTValueInteger(now.get(Calendar.DAY_OF_MONTH));
		}
		else if(_name.equals("#day_of_week"))
		{
			return new NTValueInteger(now.get(Calendar.DAY_OF_WEEK));
		}		
		else if(_name.equals("#hour12"))
		{
			return new NTValueInteger(now.get(Calendar.HOUR));
		}
		else if(_name.equals("#hour24"))
		{
			return new NTValueInteger(now.get(Calendar.HOUR_OF_DAY));
		}
		else if(_name.equals("#minute"))
		{
			return new NTValueInteger(now.get(Calendar.MINUTE));
		}
		else if(_name.equals("#second"))
		{
			return new NTValueInteger(now.get(Calendar.SECOND));
		}
		else if(_name.equals("#second"))
		{
			return new NTValueInteger(now.get(Calendar.AM_PM));
		}	
		else if(_name.equals("#unlocker.move_x"))
		{
			return NTLockScreenManager.getInstance().getUnlockInfo("#unlocker.move_x");
		}	
		else if(_name.equals("#unlocker.move_y"))
		{
			return NTLockScreenManager.getInstance().getUnlockInfo("#unlocker.move_y");
		}	
		else
		{
			return null;			
		}
	}

}
