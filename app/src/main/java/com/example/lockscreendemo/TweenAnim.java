package com.example.lockscreendemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;

public class TweenAnim extends View {
	
	private static String TAG = "LockService";
	
	public TweenAnim(Context context)
	{
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		Log.e(TAG, "onDraw");
		canvas.drawBitmap(((BitmapDrawable)getResources().getDrawable(R.drawable.layer0)).getBitmap(), 0, 0, null);
		
	}
	
	public void doTranslate()
	{
		TranslateAnimation trans = new TranslateAnimation(0.1f, 500.0f, 0.1f, 1000.0f);
		trans.setDuration(10000);
		
		
		this.startAnimation(trans);		
	}

}
