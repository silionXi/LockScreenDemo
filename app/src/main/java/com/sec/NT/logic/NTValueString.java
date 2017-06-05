package com.sec.NT.logic;


public class NTValueString extends NTValue {
	private String _v ;
	
	public NTValueString(String v)
	{
		this.type = VAL_TYPE.VAL_TYPE_STRING;
		this._v = v;
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return _v;
	}
}
