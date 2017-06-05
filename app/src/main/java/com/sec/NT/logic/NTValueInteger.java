package com.sec.NT.logic;

public class NTValueInteger extends NTValue{
	private int _v ;
	
	public NTValueInteger(int v)
	{
		this.type = VAL_TYPE.VAL_TYPE_INT;
		this._v = v;
	}

	public int getValue() {
		// TODO Auto-generated method stub
		return _v;
	}

}
