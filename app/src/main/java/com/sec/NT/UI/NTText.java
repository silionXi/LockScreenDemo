package com.sec.NT.UI;

import com.sec.NT.logic.NTValueInteger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class NTText extends NTDrawObj{
	
    public NTText( String text)
    {
        this.text = text;
    }
	
	public void doDraw(Canvas canvas, long time)
	{
		doDraw(canvas, time, 0, 0);
	}
	
	@Override
	public void doDraw(Canvas canvas, long time, float x, float y)
	{
		int curAlpha;
		
		if( animAlpha != null )
			curAlpha = animAlpha.getAlpha(time);
		else
			curAlpha = getAlpha();
		
		Paint paint = new Paint();
		
		paint.setAlpha(curAlpha);
		paint.setColor(mColor);
		paint.setTextSize(mSize * NTLockScreenGlobal.fScaleX);
		
		canvas.drawText(text, x, y, paint);
	}
	
	public void setAnimAlpha(NTAnimationAlpha animAlpha) {
		this.animAlpha = animAlpha;
	}
	
	@Deprecated
    public void setPos(String x, String y)
    {
        posX = parseExpression(x);
        posY = parseExpression(y);      
    }

	@Deprecated
	public void setAlpha(int alpha) {
		//this.alpha = alpha;
		this.alpha = new NTValueInteger(alpha);
	}
	
    public Paint getmPaint() {
        return mPaint;
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

	private String text = null;
    private Paint mPaint = null;
	private NTAnimationAlpha  	 animAlpha= null;
	private int mColor;
	private int mSize;
	public void setColor(int color) {
		mColor = color;
	}

	public void setSize(int size) {
		mSize = size;
	}
}
