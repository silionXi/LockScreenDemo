package com.sec.NT.logic;

public class NTValueFloat extends NTValue{
	private float _v;
	
	public NTValueFloat(float v)
	{
		this.type = VAL_TYPE.VAL_TYPE_FLOAT;
		this._v = v;
	}

	public NTValueFloat(double v)
	{
		this.type = VAL_TYPE.VAL_TYPE_FLOAT;
		this._v = (float)v;
	}
	
	public float getValue() {
		// TODO Auto-generated method stub
		return _v;
	}

}
