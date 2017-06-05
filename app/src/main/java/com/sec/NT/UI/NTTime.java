package com.sec.NT.UI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sec.NT.UI.NTDrawObj.ALIGN_TYPE;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class NTTime extends NTDrawObj {
	
	// TODO: add more time format. 
	
	protected List<Bitmap>	listObj;
	
	protected ALIGN_TYPE 	alignVType;
	protected ALIGN_TYPE 	alignHType;
	
    public enum TimeFormat { FORMAT_HH_MM, FORMAT_HH_MM_SS }
    
    private TimeFormat mTimeFormat = TimeFormat.FORMAT_HH_MM;

    public NTTime(String strPath, String x, String y) {
		setPos(x, y);
		setBmp(strPath);
	}

	private void setBmp(String strPath) {
		if(listObj == null) {
			listObj = new ArrayList<Bitmap>();
		}
		int end = strPath.indexOf(".png");
		strPath = strPath.substring(0, end);
		Bitmap dot = BitmapFactory.decodeFile(NTLockScreenGlobal.curBmpPath + strPath + "_dot" + ".png");
		for(int i = 0; i < 10; i++) {
			Bitmap bm = BitmapFactory.decodeFile(NTLockScreenGlobal.curBmpPath + strPath + "_" + i + ".png");
			listObj.add(bm);
		}
		listObj.add(dot);
	}
	
    public TimeFormat getmTimeFormat() {
        return mTimeFormat;
    }

    public void setmTimeFormat(String mTimeFormat) {
        if ("FORMAT_HH_MM_SS".equals(mTimeFormat))
            this.mTimeFormat = TimeFormat.FORMAT_HH_MM_SS;
        else if ("FORMAT_HH_MM".equals(mTimeFormat))
            this.mTimeFormat = TimeFormat.FORMAT_HH_MM;
    }	
    
    

	@Override
	public void setVAlignment(String align) {
		if( align.equals("top"))
			alignVType = ALIGN_TYPE.ALIGN_TYPE_TOP;
		else if(align.equals("center"))
			alignVType = ALIGN_TYPE.ALIGN_TYPE_CENTER;
		else if( align.equals("bottom"))
			alignVType =  ALIGN_TYPE.ALIGN_TYPE_BOTTOM;
	}

	@Override
	public void doDraw(Canvas canvas, long time, float x, float y) {
		float imageX = getPosX();
		float imageY = getPosY();
		float startX = imageX;
		NTGroupManager tGroup = new NTGroupManager();
		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		
		NTImage h0 = new NTImage(listObj.get(hour / 10 % 10));
		h0.setPos(String.valueOf(imageX), String.valueOf(imageY));
//		h0.doDraw(canvas, time, x, y);
		tGroup.addObject(h0);
		imageX = imageX + h0.getBmp().getWidth();
		
		NTImage h1 = new NTImage(listObj.get(hour % 10));
		h1.setPos(String.valueOf(imageX), String.valueOf(imageY));
//		h1.doDraw(canvas, time, x, y);
		tGroup.addObject(h1);
		imageX = imageX + h1.getBmp().getWidth();
		
		NTImage dot = new NTImage(listObj.get(10));
		dot.setPos(String.valueOf(imageX), String.valueOf(imageY));
//		dot.doDraw(canvas, time, x, y);
		tGroup.addObject(dot);
		imageX = imageX + dot.getBmp().getWidth();
		
		NTImage m0 = new NTImage(listObj.get(minute / 10 % 10));
		m0.setPos(String.valueOf(imageX), String.valueOf(imageY));
//		m0.doDraw(canvas, time, x, y);
		tGroup.addObject(m0);
		imageX = imageX + m0.getBmp().getWidth();
		
		NTImage m1 = new NTImage(listObj.get(minute % 10));
		m1.setPos(String.valueOf(imageX), String.valueOf(imageY));
//		m1.doDraw(canvas, time, x, y);
		tGroup.addObject(m1);
		imageX = imageX + m1.getBmp().getWidth();
        
        switch(mTimeFormat) {
        case FORMAT_HH_MM_SS :
//            imageX = imageX + m1.getBmp().getWidth();
            int second = now.get(Calendar.SECOND);
            
            NTImage dot2 = new NTImage(listObj.get(10));
            dot2.setPos(String.valueOf(imageX), String.valueOf(imageY));
            dot2.doDraw(canvas, time, x, y);
            imageX = imageX + dot2.getBmp().getWidth();
            
            NTImage s0 = new NTImage(listObj.get(second / 10 % 10));
            s0.setPos(String.valueOf(imageX), String.valueOf(imageY));
            s0.doDraw(canvas, time, x, y);
            imageX = imageX + s0.getBmp().getWidth();
            
            NTImage s1 = new NTImage(listObj.get(second % 10));
            s1.setPos(String.valueOf(imageX), String.valueOf(imageY));
            s1.doDraw(canvas, time, x, y);
            imageX = imageX + s1.getBmp().getWidth();
            if(alignVType == ALIGN_TYPE.ALIGN_TYPE_CENTER) {
            	x = x - (imageX - startX) / 2;
            }
            tGroup.doDraw(canvas, time, x, y);
            break;
        default :
        	if(alignVType == ALIGN_TYPE.ALIGN_TYPE_CENTER) {
            	x = x - (imageX - startX) / 2;
            }
        	tGroup.doDraw(canvas, time, x, y);
            break;
        }

	}

}
