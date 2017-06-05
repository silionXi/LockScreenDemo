package com.sec.NT.UI;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.graphics.Canvas;
import android.graphics.Color;

public class NTDate extends NTDrawObj {
	
	private int mColor = 0xFFFFFFFF;
	
	private int mSize = 30;
	
    public enum DateFormat { YYYY_MM_DD  }
    
    private DateFormat mDateFormat = DateFormat.YYYY_MM_DD;

    public NTDate(String x, String y) {
		setPos(x, y);
	}
	
    public DateFormat getmDateFormat() {
        return mDateFormat;
    }

    public void setmDateFormat(String mDateFormat) {
        if ("YYYY_MM_DD".equals(mDateFormat))
            this.mDateFormat = DateFormat.YYYY_MM_DD;
    }

	@Override
	public void doDraw(Canvas canvas, long time, float x, float y) {
		float textX = getPosX();
		float textY = getPosY();
		Date now = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = null;
		
        switch(mDateFormat) {
        case YYYY_MM_DD :
            formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            break;
        default :
            break;
        }

        String dataString = formatter.format(now);
        NTText data = new NTText(dataString);
        data.setColor(mColor);
        data.setSize(mSize);
        data.doDraw(canvas, getAlpha(), textX, textY);
	}

	public void setColor(String colorStr) {
		this.mColor = Color.parseColor(colorStr);
	}

	public void setSize(String sizeStr) {
		this.mSize = Integer.parseInt(sizeStr);
	}

}
