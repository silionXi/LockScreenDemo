package com.sec.NT.logic;

import java.util.HashMap;

public class NTVariableGlobal {
	
	private static NTVariableGlobal _instance ;
	public static synchronized NTVariableGlobal getInstance()
	{
		if( _instance == null)
		{
			synchronized (NTVariableGlobal.class) {
				_instance = new NTVariableGlobal();
			}
		}
		
		return _instance;
	}
	
	
	public NTValue getValue(String name)
	{
		if( _map == null)
		{
			_map = new HashMap<String, Object>();
		}
		
		if( _map.containsKey(name) )
			return (NTValue)_map.get(name);
		else
		{
			// const value, just return the value. 
			// others will return NTVariable.
			if( name.equals("#screen_width"))
			{
				NTValue ret = new NTValueInteger(720);
				_map.put(name,ret);
				return ret;
			}
			else if( name.equals("#screen_height"))
			{
				NTValue ret = new NTValueInteger(1280);
				_map.put(name, ret);
				return ret;
			}
			else if(name.equals("#year") || 
					name.equals("#hour12") ||
					name.equals("#hour24") ||
					name.equals("#minute") ||
					name.equals("#second") ||
					name.equals("#year") ||
					name.equals("#unlocker.move_x") ||
					name.equals("#unlocker.move_y")
					)
			{
				NTValue ret = new NTValueVariable(name);
				_map.put(name, ret);
				return ret;
			}
		}
		
		
		return null;
	}

	private HashMap<String, Object>  _map = null;
}
