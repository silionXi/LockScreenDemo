package com.sec.NT.UI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;

import com.sec.NT.expression.Parser;
import com.sec.NT.logic.NTValue;
import com.sec.NT.logic.NTValue.VAL_TYPE;
import com.sec.NT.logic.NTValueFloat;
import com.sec.NT.logic.NTValueInteger;
import com.sec.NT.logic.NTValueString;

public class NTImage extends NTDrawObj{
	
	public NTImage( Bitmap bmp)
	{
		this.bmp = bmp;
	}
	
	public NTImage(String strPath)
	{
		//this.alpha = 255;
		//this.position = new PointF(0,0);
		//this.bmp = bmp;
		setBmp(strPath);
	}
	
	public void doDraw(Canvas canvas, long time)
	{
		doDraw(canvas, time, 0, 0);
	}
	
	@Override
	public void doDraw(Canvas canvas, long time, float x, float y)
	{
		int curAlpha;

		PointF pos = new PointF(0,0);
		if( animPos != null)
			pos =animPos.getPos(time);
		
		if( animAlpha != null )
			curAlpha = animAlpha.getAlpha(time);
		else
			curAlpha = getAlpha();
		
		Paint paint = new Paint();
		
		paint.setAlpha(curAlpha);
		
		Matrix matrix = new Matrix();
		
		if( animRotate != null )
		{
			float rotation= animRotate.getRotation(time);
			PointF center = animRotate.get_center();
			if (center != null) {
				matrix.postTranslate(-center.x, -center.y);
				matrix.postRotate(rotation);
				matrix.postTranslate(center.x, center.y);
			}
			else {
				matrix.postRotate(rotation);
			}
			
		}
		else
		{
			if(angle != null )
			{
				float rotation = getFloatValue(angle);
				if( centerX != null && centerY != null )
				{
					float center_x = 0;
					float center_y = 0;
					center_x = getFloatValue(centerX);
					center_y = getFloatValue(centerY);
					matrix.postTranslate(-center_x, -center_y);
					matrix.postRotate(rotation);
					matrix.postTranslate( center_x,  center_y);
				}
				else
				{
					matrix.postRotate(rotation);
				}	
			}
		}
		
		
		matrix.postTranslate(pos.x, pos.y);
		matrix.postTranslate(this.getPosX(), this.getPosY());
		matrix.postTranslate(x, y);

		doAlignTranlate(matrix);
		
		matrix.postScale(NTLockScreenGlobal.fScaleX, NTLockScreenGlobal.fScaleY);
		
		canvas.drawBitmap(bmp, matrix, paint);
	}
	
	
	protected void setBmp(String strPath)
	{
		if(!strPath.contains("'"))
		{
			this.bmp = BitmapFactory.decodeFile(NTLockScreenGlobal.curBmpPath + strPath);
		}
		else
		{
			NTValue r = Parser.parseExpression(strPath);
			if( r.getType() == VAL_TYPE.VAL_TYPE_STRING)
			{
				String strBmp = ((NTValueString)r).getValue();
				this.bmp = BitmapFactory.decodeFile(NTLockScreenGlobal.curBmpPath + strBmp);
			}
			else
			{
				bmpV = r;
			}
		}
	}
	
	protected void setBmp(Bitmap bmp) {
		this.bmp = bmp;
	}
	
	protected Bitmap getBmp()
	{
		if( bmpV != null)
		{
			if( bmpV.getType() == VAL_TYPE.VAL_TYPE_VARIABLE)
			{
				String strBmp = getStringValue(bmpV);
				return BitmapFactory.decodeFile(strBmp);			
			}
		}
		else
		{
			return this.bmp;
		}
		
		return null;
	}
	

	public void setAnimPos(NTAnimationPosition animPos) {
		this.animPos = animPos;
	}
	
	public void setAnimAlpha(NTAnimationAlpha animAlpha) {
		this.animAlpha = animAlpha;
	}
	
	public void setAnimRotate(NTAnimationRotation animRotate) {
		this.animRotate = animRotate;
	}
	
	

	@Deprecated
	public void setPosition(PointF position) {
		//this.position = position;
		posX = new NTValueFloat(position.x);
		posY = new NTValueFloat(position.y);
	}

	@Deprecated
	public void setAlpha(int alpha) {
		//this.alpha = alpha;
		this.alpha = new NTValueInteger(alpha);
	}
	
	public void doAlignTranlate(Matrix m)
	{
		int w = this.bmp.getWidth();
		int h = this.bmp.getHeight();
		
		float offsetX = 0;
		float offsetY = 0;;
		if(alignHType == ALIGN_TYPE.ALIGN_TYPE_LEFT)
			offsetX = 0;
		else if(alignHType == ALIGN_TYPE.ALIGN_TYPE_CENTER)
			offsetX = -w/2.0f;
		else if(alignHType == ALIGN_TYPE.ALIGN_TYPE_RIGHT)
			offsetX = -w;
		
		if(alignVType == ALIGN_TYPE.ALIGN_TYPE_TOP)
			offsetY = 0;
		else if(alignVType == ALIGN_TYPE.ALIGN_TYPE_CENTER)
			offsetY = -h/2.0f;
		else if(alignVType == ALIGN_TYPE.ALIGN_TYPE_BOTTOM)
			offsetY = -h;
		
		m.postTranslate(offsetX, offsetY);
		
	}



	private NTValue bmpV = null;
	private Bitmap bmp = null;
	private NTAnimationPosition  animPos = null;
	private NTAnimationAlpha  	 animAlpha= null;
	private NTAnimationRotation  animRotate= null;
	
	//private PointF     position;
	//private int        alpha;

}
