package com.sec.NT.logic;


/**
 * 
 * @author wesley
 *  value type:  float/ int / string
 */
public class NTValue {
	
	public enum VAL_TYPE{
		VAL_TYPE_OPERATION,
		VAL_TYPE_VARIABLE,
		VAL_TYPE_INT,
		VAL_TYPE_FLOAT,
		VAL_TYPE_STRING
	};


	protected VAL_TYPE type;


	public VAL_TYPE getType() {
		return type;
	}
	
	
	
}
