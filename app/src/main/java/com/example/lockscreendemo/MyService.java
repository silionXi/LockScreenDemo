package com.example.lockscreendemo;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class MyService extends Service {
	
	public static final String ACTION_LOCK = "lock";
	public static final String ACTION_UNLOCK = "unlock";
	public static final String ACTION_SCREENON = "screenon";
	public static final String ACTION_SCREENOFF= "screenoff";
	public static final String ACTION_NONE = "none";
	
	private static String TAG = "LockService";
	private Intent mLockIntent = null ;
	
	private Context mContext;
	private WindowManager mWinMng;	
	private LockView lockView = null;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
		mLockIntent = new Intent(MyService.this , MainActivity.class);
		mLockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		IntentFilter mScreenOnFilter = new IntentFilter("android.intent.action.SCREEN_ON");
		MyService.this.registerReceiver(mScreenOnReciever, mScreenOnFilter);
		
		IntentFilter mScreenOffFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
		MyService.this.registerReceiver(mScreenOffReciever, mScreenOffFilter);
		
		mContext = getApplicationContext();
		mWinMng = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		MyService.this.unregisterReceiver(mScreenOffReciever);
		MyService.this.unregisterReceiver(mScreenOnReciever);
		
		//startService(new Intent(MyService.this, MyService.class));
		Log.i(TAG, "start Service 1");
		Intent i = new Intent(mContext, MyService.class);
		i.setAction(MyService.ACTION_NONE);
		mContext.startService(i);		
		
	}

	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		
		if( action == null)
			return super.onStartCommand(intent, flags, startId);
		
		if(action.equals(ACTION_LOCK))
		{
			addView();
		}
		else if(action.equals(ACTION_UNLOCK))
		{
			removeView();
			//stopSelf();
		}
		else if(action.equals(ACTION_SCREENON))
		{
			viewScreenOn();
		}
		else if( action.equals(ACTION_SCREENOFF))
		{
			addView();
			if( lockView != null)
			{
				lockView.doStop();
			}
		}
		
		
		return super.onStartCommand(intent, flags, startId);
	}

	
	public void addView()
	{
		if(lockView == null)
		{
			lockView = new LockView(mContext);
			
			LayoutParams param = new LayoutParams();
			param.type = LayoutParams.TYPE_SYSTEM_ALERT;
			param.format = PixelFormat.RGBA_8888;
			// mParam.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
			// | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
			param.width = LayoutParams.MATCH_PARENT;
			param.height = LayoutParams.MATCH_PARENT;
//			param.flags = LayoutParams.FLAG_FULLSCREEN;

			mWinMng.addView(lockView, param);
		}
		
	}
	
	public void removeView()
	{
		if(lockView != null)
		{
//			mWinMng.removeView(lockView);
			
			lockView.doExit();
			mWinMng.removeViewImmediate(lockView);
			lockView = null;
		}
	}
	
	public void viewScreenOn()
	{
		if(lockView != null)
		{
			lockView.doRestart();
		}
		else
		{
			addView();
		}
	}
	

	private BroadcastReceiver mScreenOnReciever = new BroadcastReceiver(){

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			Log.i(TAG, "screen on");
			viewScreenOn();
		}
		
		
	};
	
	private BroadcastReceiver mScreenOffReciever = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Log.i(TAG, "screen off");
			String action = intent.getAction();
			
			Log.i(TAG, intent.toString());
			if( action.equals("android.intent.action.SCREEN_OFF") || action.equals("android.intent.action.SCREEN_ON")){
//				KeyguardManager m = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
//				KeyguardManager.KeyguardLock mLock = m.newKeyguardLock("My Service");
//				mLock.disableKeyguard();
				
				Log.i(TAG, "start Service 2");
				Intent i = new Intent(mContext, MyService.class);
				i.setAction(MyService.ACTION_SCREENOFF);
				mContext.startService(i);
			}
		}		
	};

}
