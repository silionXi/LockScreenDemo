package com.example.lockscreendemo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class MainActivity extends Activity {
	
	private static String TAG = "LockService";
	private GridView gridView;
	private Button mApply;
	private Button mDiy;
	private String mTheme;
	
	private static final int MSG_SUCCEED = 0;
	private static final int MSG_FAIL    = 1;
	
	
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case MSG_SUCCEED:
				Intent i = new Intent(MainActivity.this, MyService.class);
				i.setAction(MyService.ACTION_LOCK);
				startService(i);  
				break;
			case MSG_FAIL:
				
				break;
			default:
				break;
			}
		}
		
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				   WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		
		
        setContentView(R.layout.activity_main);
        gridView = (GridView)findViewById(R.id.gridView);
        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.list_item,
        		new String[]{"image", "title", "checkBox"}, new int[] {R.id.image, R.id.title,R.id.checkBox});
        gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(mOnItemClickListener);
        
        mApply = (Button)findViewById(R.id.apply);
        
        mApply.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(TAG, "start Service 0");
				
				unZipRunnable unZip = new unZipRunnable();
				new Thread(unZip).start();
/*				
				Intent i = new Intent(MainActivity.this, MyService.class);
				i.setAction(MyService.ACTION_LOCK);
				startService(i);        
*/	
				finish();
			}
		});
        mApply.setClickable(false);
        
        mDiy = (Button) findViewById(R.id.diy);
        
        mDiy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myDiy = new Intent(MainActivity.this, MyDiyActivity.class);
				startActivity(myDiy);
			}
		});
        
    }


    private List<? extends Map<String, ?>> getData() {
		// TODO Auto-generated method stub
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("image", R.drawable.bumblebee);
    	map.put("title", this.getText(R.string.bumblebee));
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("image", R.drawable.snow);
    	map.put("title", this.getString(R.string.snow));
    	list.add(map);
		return list;
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    OnItemClickListener mOnItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			CheckBox checkBox = (CheckBox)view.findViewById(R.id.checkBox);
			if(checkBox.isChecked()) {
				mApply.setClickable(false);
				checkBox.setChecked(false);
			}
			else {
				for (int i = 0; i < parent.getCount(); i++) {
					CheckBox cb = (CheckBox)parent.getChildAt(i).findViewById(R.id.checkBox);
					cb.setChecked(false);
				}
				TextView title = (TextView)view.findViewById(R.id.title);
				mTheme = title.getText().toString() + ".zip";
				android.util.Log.i("slong.liang", "mTheme = " + mTheme);
				mApply.setClickable(true);
				checkBox.setChecked(true);
			}
		}
    	
    };

    /*
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }*/
    
    class unZipRunnable implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			android.util.Log.i("slong.liang", "run");
			AssetManager manager = getAssets();
			try {
				InputStream is = manager.open(mTheme);
				//String zipFile = Environment.getExternalStorageDirectory().getPath() + "/Download/" + mTheme;
				//deleteFilesByDirectory(filesDir);
				String targetDir = getFilesDir().getPath() + "/";
				unZip(is, targetDir);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void unZip(String zipFile, String targetDir) {
	    	int BUFFER = 1024 * 8; 
	    	String strEntry; 
	    	
	    	BufferedOutputStream dest = null;
	    	try {
				FileInputStream fis = new FileInputStream(zipFile);
				ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
				ZipEntry entry;
				try {
					while((entry = zis.getNextEntry()) != null) {
						android.util.Log.i("slong.liang", "Unzip = " + entry);
						int count;
						byte data[] = new byte[BUFFER];
						strEntry = entry.getName();
						
						File entryFile = new File(targetDir + strEntry);
						File entryDir = new File(entryFile.getParent());
						if (!entryDir.exists()) {
							entryDir.mkdirs();
						}
						FileOutputStream fos = new FileOutputStream(entryFile);
						dest = new BufferedOutputStream(fos, BUFFER);
						while ((count = zis.read(data, 0, BUFFER)) != -1) {
							dest.write(data, 0, count);
						}
						dest.flush();
						dest.close();
					}
					zis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
		public void unZip(InputStream fis, String targetDir) {
	    	int BUFFER = 1024 * 8; 
	    	String strEntry; 
	    	
	    	BufferedOutputStream dest = null;
	    	//FileInputStream fis = new FileInputStream(zipFile);
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
			ZipEntry entry;
			try {
				while((entry = zis.getNextEntry()) != null) {
					android.util.Log.i("slong.liang", "Unzip = " + entry);
					int count;
					byte data[] = new byte[BUFFER];
					strEntry = entry.getName();
					
					File entryFile = new File(targetDir + strEntry);
					File entryDir = new File(entryFile.getParent());
					if (!entryDir.exists()) {
						entryDir.mkdirs();
					}
					FileOutputStream fos = new FileOutputStream(entryFile);
					dest = new BufferedOutputStream(fos, BUFFER);
					while ((count = zis.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, count);
					}
					dest.flush();
					dest.close();
				}
				zis.close();
				
				mHandler.obtainMessage(MSG_SUCCEED).sendToTarget();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
    }

    
    
    
    
    // disable right key
/*    
	public boolean onKeyDown(int keyCode ,KeyEvent event){
		
		Log.i(TAG, "keyCode =" + event.getKeyCode() + "KeyCode :" + keyCode);
		
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK)
			return true ;
		else
			return super.onKeyDown(keyCode, event);
	}
*/
    
    


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.i(TAG, "keyCode =" + event.getKeyCode() + "KeyCode :" + keyCode);
		
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK || event.getKeyCode() == KeyEvent.KEYCODE_MENU)
			return false ;
		else
			return super.onKeyDown(keyCode, event);
	}



	
	
}
