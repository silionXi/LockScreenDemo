package com.example.lockscreendemo.test;

import junit.framework.TestCase;

import com.sec.NT.expression.Parser;
import com.sec.NT.logic.NTOperation_OP;
import com.sec.NT.logic.NTValue;
import com.sec.NT.logic.NTValue.VAL_TYPE;
import com.sec.NT.logic.NTValueFloat;
import com.sec.NT.logic.NTValueInteger;
import com.sec.NT.logic.NTValueString;

public class ParserTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testParser001(){
		NTValue r1 = Parser.parseExpression("'中文' + 'ab\\'cd\\'' +  #year");
		assertEquals(r1.getType(), VAL_TYPE.VAL_TYPE_OPERATION);
		NTValue r2 = ((NTOperation_OP)r1).getValue();
		assertEquals(r2.getType(), VAL_TYPE.VAL_TYPE_STRING);
		String r3 = ((NTValueString)r2).getValue();
		assertEquals("parser case 001", r3, "中文ab'cd'2015");	
	}
	
	
	static public void testParser002()
	{
		NTValue r1 = Parser.parseExpression("#screen_width + 100");
		assertEquals(r1.getType(), VAL_TYPE.VAL_TYPE_INT);
		int r2 = ((NTValueInteger)r1).getValue();
		assertEquals("parser case 002", r2, 1180);	
	}

	static public void testParser003()
	{
		NTValue r1 = Parser.parseExpression("#screen_width + 100.3");
		assertEquals(r1.getType(), VAL_TYPE.VAL_TYPE_FLOAT);
		float r2 = ((NTValueFloat)r1).getValue();
		assertEquals("parser case 003", r2, 1180.3, 0.1);
	}
	
	static public void testParser004()
	{
		NTValue r1 = Parser.parseExpression("34+'abc'");
		assertEquals("parser case 004", r1, null);
	}
		
	static public void testCase005()
	{
		NTValue r1 = Parser.parseExpression("34%10");
		assertEquals("case 005", r1.getType(), VAL_TYPE.VAL_TYPE_INT);
		int r2 = ((NTValueInteger)r1).getValue();
		assertEquals("case 005", r2, 4);
	}
	
	static public void testCase006()
	{
		NTValue r1 = Parser.parseExpression("3 + #year%10");
		assertEquals("case 006", r1.getType(), VAL_TYPE.VAL_TYPE_OPERATION);
		NTValue r2 = ((NTOperation_OP)r1).getValue();
		assertEquals(r2.getType(), VAL_TYPE.VAL_TYPE_INT);
		int r3 = ((NTValueInteger)r2).getValue();
		assertEquals("case 006", r3, 8);	
	}
	
	static public void testParser007()
	{
		NTValue r1 = Parser.parseExpression("34.4");
		assertEquals(r1.getType(), VAL_TYPE.VAL_TYPE_FLOAT);
		float r2 = ((NTValueFloat)r1).getValue();
		assertEquals("parser case 007", r2, 34.4, 0.1);	
	}
		
	
}
