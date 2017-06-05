package com.example.lockscreendemo;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

public class LockView extends RelativeLayout {
	
	private static String TAG = "LockService";
	
	private Context mContext;
	private View rootView;
	
	
	private MyUIView lock_ui_view = null;
	
	public LockView(Context context)
	{
		super(context);
		Log.i(TAG, "LockView");
		mContext = context;
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);		
		rootView = inflater.inflate(R.layout.lockview, this);
		
		lock_ui_view = (MyUIView)rootView.findViewById(R.id.lock_ui_view);
/*		
		btnUnlock = (Button) rootView.findViewById(R.id.btn_unlock);
		
		btnUnlock.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				

			}
		});
		
		Button btnCheck = (Button)findViewById(R.id.btn_check);
		btnCheck.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ani.doTranslate();
				
			}
		});
		*/
				
		//ani = new TweenAnim(mContext);
		//this.addView(ani);
			
		
		
	}
	
	public void doExit()
	{
		if(lock_ui_view != null)
		{
			lock_ui_view.doExit();
		}
	}
	
	public void doRestart()
	{
		if(lock_ui_view != null)
		{
			lock_ui_view.doRestart();
		}
	}
	
	public void doStop()
	{
		if(lock_ui_view != null)
		{
			lock_ui_view.doStop();
		}
	}
	
}
