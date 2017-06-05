package com.example.lockscreendemo;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.sec.NT.UI.NTAnimationAlphaLinear;
import com.sec.NT.UI.NTAnimationPositionLinear;
import com.sec.NT.UI.NTAnimationRotationLinear;
import com.sec.NT.UI.NTGroupManager;
import com.sec.NT.UI.NTImage;
import com.sec.NT.UI.NTLockPath;
import com.sec.NT.UI.NTLockPathLine;
import com.sec.NT.UI.NTLockScreenGlobal;
import com.sec.NT.UI.NTLockScreenListener;
import com.sec.NT.UI.NTLockScreenManager;
import com.sec.elements.DomParser;

public class MyUIView extends SurfaceView implements SurfaceHolder.Callback, NTLockScreenListener
{
	private static String TAG = "LockService";
	
	private SurfaceHolder holder=null; 
	
	private Context mContext;

	private MyUILoop mLoop = null ;
	
	public MyUIView(Context context, AttributeSet attrs)
	{
		super(context,attrs);
		mContext = context;
		holder = getHolder();
		holder.addCallback(this);		
		
		
	}

	
	
	
	@Override
	public void onUnLock() {
		// TODO Auto-generated method stub
		NTLockScreenManager.getInstance().restart();
		DoUnlock();
	}




	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
		init();
		mLoop =new MyUILoop();
		new Thread(mLoop).start();
		
		doStart();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	
	public void doExit()
	{
		mLoop.exit();
	}
	
	public void doRestart()
	{
		doStart();
	}
	
	public void doStop()
	{
		if( mLoop != null)
		{
			mLoop.stop();
		}
	}	
	
	private NTImage image0 ;

	private NTGroupManager objMananger = null;
	
	private NTLockPath   lockPath = null;
	
	private NTLockScreenManager   lockscreen = null;
	
	
	
	public void setLockPath(NTLockPath lockPath) {
		this.lockPath = lockPath;
	}

	private long    start_time;
	
	public void init()
	{
		// TODO: test

		//this.test001();
		//this.test003();
		this.test004();
	}
	
	public void test003()
	{
		this.lockscreen = new NTLockScreenManager();
		
		NTAnimationPositionLinear animPos;
		NTAnimationAlphaLinear animAlpha;
		
		NTGroupManager objPressed = new NTGroupManager();
		
		
		
		
		image0 = new NTImage(((BitmapDrawable)getResources().getDrawable(R.drawable.layer0)).getBitmap());
		//image0.setPosition(new PointF(500, 1000));
		image0.setPos("500", "1000");
		
		animAlpha = new NTAnimationAlphaLinear(6);
		animAlpha.addAlphaTime(120, 0);
		animAlpha.addAlphaTime(255, 10000);
		animAlpha.addAlphaTime(120, 20000);
		animAlpha.addAlphaTime(120, 1000000000);
				
		image0.setAnimAlpha(animAlpha);
		objPressed.addObject(image0);
		
		NTGroupManager objNormal = new NTGroupManager();
		
		NTImage image1 = new NTImage(((BitmapDrawable)getResources().getDrawable(R.drawable.layer0)).getBitmap());
		//image1.setPosition(new PointF(500, 900));
		image1.setPos("500", "800 + #hour12");
		animAlpha = new NTAnimationAlphaLinear(6);
		animAlpha.addAlphaTime(255, 0);
		animAlpha.addAlphaTime(0, 10000);
		animAlpha.addAlphaTime(255, 20000);
		animAlpha.addAlphaTime(120, 1000000000);
		image1.setAnimAlpha(animAlpha);
		objNormal.addObject(image1);
		
		if(lockPath == null)
		{
			NTLockPathLine curLockPath = new NTLockPathLine();
			curLockPath.setNormalObjs(objNormal);
			curLockPath.setPressedObjs(objPressed);
			
			curLockPath.set_startRect(new Rect(0, 800, 1080, 1000));
			curLockPath.set_endRect(new Rect(0, 1500, 1080, 1700));
			
		
			curLockPath.setAudio(mContext.getFilesDir().getPath() +"/sound_normal.mp3",mContext.getFilesDir().getPath() +"/sound_normal.mp3" );
			
//			this.setLockPath(curLockPath);
			
			this.lockscreen.addLockPath(curLockPath);
		}
		
		
		NTImage image3 = new NTImage(((BitmapDrawable)getResources().getDrawable(R.drawable.layer0)).getBitmap());
		//image3.setPosition(new PointF(100, 100));
		image3.setPos("300", "#second * 20");
		image3.setVAlignment("bottom");
		image3.setHAlignment("center");
		animPos = new NTAnimationPositionLinear(5);
		animPos.addPosTime(100, 100, 0);
		animPos.addPosTime(200, 100, 2000);
		animPos.addPosTime(200, 600, 4000);
		animPos.addPosTime(-100, -200, 8000);
		animPos.addPosTime(-100, -200, 1000000000);
		//image3.setAnimPos(animPos);		
		
		animAlpha = new NTAnimationAlphaLinear(6);
		animAlpha.addAlphaTime(255, 0);
		animAlpha.addAlphaTime(100, 2000);
		animAlpha.addAlphaTime(255, 3000);
		animAlpha.addAlphaTime(0, 6000);
		animAlpha.addAlphaTime(120, 9000);
		animAlpha.addAlphaTime(120, 1000000000);
				
		image3.setAnimAlpha(animAlpha);
		
		
		this.lockscreen.addBackgroundObj(image3);
		
	
		this.lockscreen.setWallPaper(((BitmapDrawable)getResources().getDrawable(R.drawable.iphone5s)).getBitmap());

		
	}
	
	public void test004() {
		File f = new File (mContext.getFilesDir().getPath() + "/manifest.xml");
		DomParser domParser = new DomParser(mContext);
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(f);
            lockscreen = domParser.parserXml(document);
            lockscreen.curLockPath.registerUnlockListner(this);
            lockscreen.addLockPath(lockscreen.curLockPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void test001()
	{

		image0 = new NTImage(((BitmapDrawable)getResources().getDrawable(R.drawable.layer0)).getBitmap());
		image0.setPosition(new PointF(500, 1000));
		
		NTAnimationPositionLinear animPos = new NTAnimationPositionLinear(5);
		animPos.addPosTime(100, 100, 0);
		animPos.addPosTime(200, 100, 2000);
		animPos.addPosTime(200, 600, 4000);
		animPos.addPosTime(-500, -1000, 8000);
		animPos.addPosTime(-500, -1000, 1000000000);
		
		image0.setAnimPos(animPos);
		
		NTAnimationAlphaLinear animAlpha = new NTAnimationAlphaLinear(6);
		animAlpha.addAlphaTime(255, 0);
		animAlpha.addAlphaTime(100, 2000);
		animAlpha.addAlphaTime(255, 3000);
		animAlpha.addAlphaTime(0, 6000);
		animAlpha.addAlphaTime(255, 9000);
		animAlpha.addAlphaTime(255, 1000000000);
		
		
		image0.setAnimAlpha(animAlpha);
		
		NTAnimationRotationLinear animRotate = new NTAnimationRotationLinear(7);
		animRotate.set_center(new PointF(10,10));
		animRotate.addRotationTime(0, 0);
		animRotate.addRotationTime(45, 2000);
		animRotate.addRotationTime(90, 4000);
		animRotate.addRotationTime(180, 5000);
		animRotate.addRotationTime(360, 8000);
		animRotate.addRotationTime(0, 8000);
		animRotate.addRotationTime(0, 1000000000);
		
		image0.setAnimRotate(animRotate);
		
		objMananger.addObject(image0);
		
		NTImage image1 = new NTImage(((BitmapDrawable)getResources().getDrawable(R.drawable.layer7_left)).getBitmap());
		image1.setPosition(new PointF(600, 1200));
		animPos = new NTAnimationPositionLinear(5);
		animPos.addPosTime(100, 100, 0);
		animPos.addPosTime(200, 100, 2000);
		animPos.addPosTime(200, 600, 4000);
		animPos.addPosTime(-100, -200, 8000);
		animPos.addPosTime(-100, -200, 1000000000);
		image1.setAnimPos(animPos);
		
		objMananger.addObject(image1);
		
		
	}
	
	public void test002()
	{
		if(objMananger == null) {
			objMananger = new NTGroupManager();
		}/*
		try {
			is = manager.open("manifest.xml");
			List<NTImage> list = new DomParser(mContext).getNTImageListsByXml(is);
			for(NTImage i: list) {
				objMananger.addObject(i);
			}
		} catch (Exception e){
			e.printStackTrace();
		}*/
	}

	public void doStart()
	{
		start_time =  System.currentTimeMillis();
		NTLockScreenGlobal.startTime = start_time;
		mLoop.start();
	}

	public void doDraw(Canvas canvas, long time)
	{
		/*
		Matrix m = new Matrix();
		m.postScale(1.2f, 1.2f);
		canvas.drawBitmap(((BitmapDrawable)getResources().getDrawable(R.drawable.iphone5s)).getBitmap(), m, null);
		*/
		//image0.doDraw(canvas, time);
		//objMananger.doDraw(canvas, time);
		if( lockPath != null)
		{
			lockPath.doDraw(canvas, time);
		}
		
		if( lockscreen != null)
		{
			lockscreen.doDraw(canvas, time);
		}
	}
		
	
	private boolean _pressed = false;
	
	// TODO: just for 720p to 1080p
	private float   _fscale = 1.5f;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			{
				//Log.i(TAG, "touch down x:" + event.getX() + "  y: " + event.getY());
				if( event.getX() < 200 && event.getY() < 200)
				{
					DoUnlock();
				}
				
				if( lockPath != null)
				{
					_pressed = lockPath.onTouchDown(event.getX()/_fscale, event.getY()/_fscale);
					if(_pressed )
					{
						//doStart();
					}
				}
				
				if( lockscreen != null)
				{
					lockscreen.onTouchDown(event.getX()/_fscale, event.getY()/_fscale);
				}
				return true;
			}
		case MotionEvent.ACTION_MOVE:
			{
				//Log.i(TAG, "touch move x:" + event.getX() + "  y: " + event.getY());
				if( lockPath != null)
				{
					lockPath.onTouchMove(event.getX()/_fscale, event.getY()/_fscale);
				}
				if( lockscreen != null)
				{
					lockscreen.onTouchMove(event.getX()/_fscale, event.getY()/_fscale);
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			{
				//Log.i(TAG, "touch up x:" + event.getX() + "  y: " + event.getY());
				if( lockPath != null)
				{
					lockPath.onTouchUp(event.getX()/_fscale, event.getY()/_fscale);
					if(_pressed )
					{
						//doRestart();
						_pressed = false;
					}
				}
				if( lockscreen != null)
				{
					lockscreen.onTouchUp(event.getX()/_fscale, event.getY()/_fscale);
				}				
				this.performClick();
			}
			break;
		}
		
		return super.onTouchEvent(event);
	}
	

	@Override
	public boolean performClick() {
		// TODO Auto-generated method stub
		return super.performClick();
	}


	class MyUILoop implements Runnable
	{
		private boolean isRunning = false;
		private boolean isExit = false;
		
		public void start()
		{
			isRunning = true;
		}
		
		public void stop()
		{
			isRunning = false;
		}
		
		public void exit()
		{
			isExit = true;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true)
			{
				try{
					if( isExit )
						return;
					if( !isRunning)
					{
						Thread.sleep(20);
					}
					else
					{
						long start_timex = System.currentTimeMillis();
						Canvas c = holder.lockCanvas();
						doDraw(c, start_timex - start_time);
						holder.unlockCanvasAndPost(c);
						//long end_timex = System.currentTimeMillis();
						
						//long spend_time = end_timex - start_timex;
						//Log.i(TAG,"My Loop: spend_time = " + spend_time);
					}
					//Thread.sleep(20);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	
	public void DoUnlock()
	{
		Log.i(TAG, "start Service 4");
		Intent i = new Intent(mContext, MyService.class);
		i.setAction(MyService.ACTION_UNLOCK);
		mContext.startService(i);		
	}
	
}
