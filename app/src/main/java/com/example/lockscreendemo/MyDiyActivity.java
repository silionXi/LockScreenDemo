package com.example.lockscreendemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MyDiyActivity extends Activity {

	GridView mEditTags;
	GridView mEditRes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android.util.Log.e("slong.liang", "MyDiyActivity");
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.my_diy);
		
		mEditTags = (GridView) findViewById(R.id.edit_tags_gridView_res);
		SimpleAdapter adapterTags = new SimpleAdapter(this, getTagsData(), R.layout.editor_item, 
				new String[]{"title"}, new int[] {R.id.title});
		mEditTags.setAdapter(adapterTags);
		mEditTags.setOnItemClickListener(mEditTagsListener);
		
		
		mEditRes = (GridView) findViewById(R.id.edit_res_gridView_res);
		SimpleAdapter adapterRes = new SimpleAdapter(this, getResData(), R.layout.editor_res_item, 
				new String[]{"image"}, new int[] {R.id.image});
		mEditRes.setAdapter(adapterRes);
	}
	
	OnItemClickListener mEditTagsListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			TextView tv = (TextView) view.findViewById(R.id.title);
			String title = tv.getText().toString();
//			Map<String, Object> tagMap = (Map<String, Object>) gv.getItemAtPosition(position);
//			String title = (String) tagMap.get("title");
//			android.util.Log.v("slong.liang", title);
		}
	};

	private List<? extends Map<String, ?>> getResData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("image", R.drawable.test);
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("image", R.drawable.test);
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("image", R.drawable.test);
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("image", R.drawable.test);
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("image", R.drawable.test2);
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("image", R.drawable.test2);
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("image", R.drawable.test2);
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("image", R.drawable.test2);
    	list.add(map);
		return list;
	}

	private List<? extends Map<String, ?>> getTagsData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("title", this.getText(R.string.background));
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("title", this.getString(R.string.time));
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("title", this.getString(R.string.unlocker));
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("title", this.getString(R.string.animation));
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("title", this.getString(R.string.weather));
    	list.add(map);
		return list;
	}

}
