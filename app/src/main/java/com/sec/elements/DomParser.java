package com.sec.elements;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.Display;
import android.view.WindowManager;

import com.sec.NT.UI.NTAnimationAlphaLinear;
import com.sec.NT.UI.NTAnimationPositionLinear;
import com.sec.NT.UI.NTAnimationRotationLinear;
import com.sec.NT.UI.NTDate;
import com.sec.NT.UI.NTGroupManager;
import com.sec.NT.UI.NTImage;
import com.sec.NT.UI.NTLockScreenGlobal;
import com.sec.NT.UI.NTLockScreenManager;
import com.sec.NT.UI.NTText;
import com.sec.NT.UI.NTTime;

/**
 * DOM解析器在解析XML文档时，会把文档中的所有元素，按照其出现的层次关系，解析成一个个Node对象(节点)。Node对象提供了一系列常量来代表结点的类型
 * ，当开发人员获得某个Node类型后，就可以把Node节点转换成相应节点对象(Node的子类对象)，以便于调用其特有的方法。
 * Node对象提供了相应的方法去获得它的父结点或子结点。编程人员通过这些方法就可以读取整个XML文档的内容、或添加、修改、删除XML文档的内容.
 *
 * 缺点：
 * 一次性的完全加载整个xml文件，需要消耗大量的内存。
 */
public class DomParser {
	private String filesPath = "data/data/com.example.lockscreendemo/files/";
	private Context mContext;
	public DomParser(Context context) {
		mContext = context;
	}

	public NTLockScreenManager parserXml(Document document) {
		NTLockScreenManager lockscreen = NTLockScreenManager.getInstance();
		Element lockscreenElement = document.getDocumentElement();
		NodeList nodeList = lockscreenElement.getChildNodes();
		if(nodeList == null || nodeList.getLength() == 0) {
			return null;
		}
		for(int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				lockscreenElement(lockscreen, (Element)node);
			}
		}
		return lockscreen;
	}
	
	public void lockscreenElement(NTLockScreenManager lockscreen, Element element) {
		String name = element.getNodeName();
		if(name.equalsIgnoreCase("Wallpaper")) {
			retriveWallpaper(lockscreen, element);
		}else if(name.equalsIgnoreCase("Image")) {
			retriveImage(lockscreen, element);
		}else if(name.equalsIgnoreCase("Time")) {
			retriveTime(lockscreen, element);
		}else if(name.equalsIgnoreCase("Group")) {
			retriveGroup(lockscreen, element);
		}else if(name.equalsIgnoreCase("Unlocker")) {
			retriveUnlocker(lockscreen, element);
		}else if(name.equalsIgnoreCase("DateTime")) {
		    retriveDate(lockscreen, element);
		}
	}
	
	public void retriveUnlocker(NTLockScreenManager lockscreen, Element unLockerElement) {
		// TODO Auto-generated method stub
		NodeList nodeList = unLockerElement.getChildNodes();
		if(nodeList != null && nodeList.getLength() != 0) {
			for(int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					unlockerElement(lockscreen, (Element)node);
				}
			}
		}
	}

	public void unlockerElement(NTLockScreenManager lockscreen, Element element) {
		String name = element.getNodeName();
		if(name.equalsIgnoreCase("StartPoint")) {
			Rect startRect = getStartPoint(element);
			//TODO lockscreen.setStartPoint(startRect);
			lockscreen.curLockPath.set_startRect(startRect);
			String normalSound = filesPath + getNormalSound(element);
			String pressedSound = filesPath + getPressedSound(element);
			if(normalSound != null && pressedSound != null) {
				lockscreen.curLockPath.setAudio(normalSound, pressedSound);
			}
			NodeList nodeList = element.getChildNodes();
			if(nodeList != null && nodeList.getLength() != 0) {
				for(int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					if(node.getNodeType() == Node.ELEMENT_NODE) {
						startPointElement(lockscreen, (Element)node);
					}
				}
			}
		}else if(name.equalsIgnoreCase("EndPoint")) {
			Rect endRect = getEndPoint(element);
			//TODO lockscreen.setStartPoint(startRect);
			lockscreen.curLockPath.set_endRect(endRect);
			NodeList nodeList = element.getChildNodes();
			if(nodeList != null && nodeList.getLength() != 0) {
				for(int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					if(node.getNodeType() == Node.ELEMENT_NODE) {
						endPointElement(lockscreen, (Element)node);
					}
				}
			}
		}
	}
	
	public void endPointElement(NTLockScreenManager lockscreen, Element element) {
		String name = element.getNodeName();
		if(name.equalsIgnoreCase("Path")) {
			retrivePath(lockscreen, element);
		}
	}
	
	public void retrivePath(NTLockScreenManager lockscreen, Element pathElement) {
		int [][] path = getPath(pathElement);
		//TODO lockscreen.setPath(path[0][0], path[0][1], path[1][0], path[1][1]);
		lockscreen.curLockPath.set_tolerance( 300 );
		try {
			lockscreen.curLockPath.setPath(path[0][0], path[0][1], path[1][0], path[1][1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int[][] getPath(Element pathElement) {
		int [][] path = new int [2][2];
		NodeList positionNode = pathElement.getElementsByTagName("Position");
		if (positionNode == null || positionNode.getLength() == 0) {
			return null;
		}
		Element positionElement0 = (Element)positionNode.item(0);
		Element positionElement1 = (Element)positionNode.item(1);
		path[0][0] = Integer.parseInt(positionElement0.getAttribute("x"));
		path[0][1] = Integer.parseInt(positionElement0.getAttribute("y"));
		path[1][0] = Integer.parseInt(positionElement1.getAttribute("x"));
		path[1][1] = Integer.parseInt(positionElement1.getAttribute("y"));
		return path;
	}
	
	public void startPointElement(NTLockScreenManager lockscreen, Element element) {
		String name = element.getNodeName();
		if(name.equalsIgnoreCase("NormalState")) {
			retriveNormalState(lockscreen, element);
		}else if(name.equalsIgnoreCase("Image")) {
			retriveLockerImage(lockscreen, element);
		}else if(name.equalsIgnoreCase("PressedState")) {
			retrivePressedState(lockscreen, element);
		}
	}
	
	public void retriveLockerImage(NTLockScreenManager lockscreen,
			Element imageElement) {
		//NTGroupManager objNormal = new NTGroupManager();
		NTImage image = getImage(imageElement);
		//objNormal.addObject(image);
		lockscreen.curLockPath.addNormalObjs(image);
	}
	
	public void retrivePressedState(NTLockScreenManager lockscreen,
			Element pressedElement) {
		NTGroupManager objPressed = getPressedState(pressedElement);
		//TODO lockscreen.setPressedObjs(objPressed);
		lockscreen.curLockPath.setPressedObjs(objPressed);
	}
	
	public NTGroupManager getPressedState(Element pressedElement) {
		NTGroupManager objPressed = new NTGroupManager();
		NodeList nodeList = pressedElement.getElementsByTagName("Image");
		if(nodeList != null && nodeList.getLength() != 0) {
			for(int i = 0; i < nodeList.getLength(); i++) {
				Element imageElement = (Element)nodeList.item(i);
				NTImage image = getImage(imageElement);
				if(image != null) {
					objPressed.addObject(image);
				}
			}
		}
		return objPressed;
	}
	public void retriveNormalState(NTLockScreenManager lockscreen,
			Element normalElement) {
		NTGroupManager objNormal = getNromalState(normalElement);
		//TODO lockscreen.setNormalObjs(objNormal);
		lockscreen.curLockPath.setNormalObjs(objNormal);
	}
	
	public NTGroupManager getNromalState(Element normalElement) {
		NTGroupManager objNormal = new NTGroupManager();
		NodeList nodeList = normalElement.getElementsByTagName("Image");
		if(nodeList != null && nodeList.getLength() != 0) {
			for(int i = 0; i < nodeList.getLength(); i++) {
				Element imageElement = (Element)nodeList.item(i);
				NTImage image = getImage(imageElement);
				if(image != null) {
					objNormal.addObject(image);
				}
			}
		}
		return objNormal;
	}
	
	public String getPressedSound(Element startPointElement) {
		return startPointElement.getAttribute("pressedSound");
	}
	
	public String getNormalSound(Element startPointElement) {
		return startPointElement.getAttribute("normalSound");
	}
	
	public Rect getEndPoint(Element endPointElement) {
		int x = Integer.parseInt(endPointElement.getAttribute("x"));
		int y = Integer.parseInt(endPointElement.getAttribute("y"));
		int w = Integer.parseInt(endPointElement.getAttribute("w"));
		int h = Integer.parseInt(endPointElement.getAttribute("h"));
		return new Rect(x, y, x+w, y+h);
	}

	public Rect getStartPoint(Element startPointElement) {
		int x = Integer.parseInt(startPointElement.getAttribute("x"));
		int y = Integer.parseInt(startPointElement.getAttribute("y"));
		int w = Integer.parseInt(startPointElement.getAttribute("w"));
		int h = Integer.parseInt(startPointElement.getAttribute("h"));
		return new Rect(x, y, x+w, y+h);
	}

	public void retriveGroup(NTLockScreenManager lockscreen, Element groupElement) {
		String x = groupElement.getAttribute("x");
		String y = groupElement.getAttribute("y");
		NodeList nodeList = groupElement.getChildNodes();
		if(nodeList != null && nodeList.getLength() != 0) {
			for(int i = 0 ; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					String name = node.getNodeName();
					if(name.equalsIgnoreCase("Image")) {
						NTImage image = getImage((Element)node);
//						image.addOffset(x, y);
						lockscreen.addForegroundObj(image);
					} else if(name.equalsIgnoreCase("DateTime")) {
						NTDate dt = getDate((Element)node);
//						dt.addOffset(x, y);
						lockscreen.addForegroundObj(dt);
					}
				}
			}
		}
	}
	
	public void retriveTime(NTLockScreenManager lockscreen, Element timeElement) {
		NTTime time = getTime(timeElement);
		lockscreen.addForegroundObj(time);
	}

	public NTTime getTime(Element timeElement) {
		String src = timeElement.getAttribute("src");
		android.util.Log.i("slong.liang", "getTime src = " + src);
		String imageX = timeElement.getAttribute("x");
		String imageY = timeElement.getAttribute("y");
		String align = timeElement.getAttribute("align");
		NTTime time = new NTTime(src, imageX, imageY);
		if(align != null) {
			time.setVAlignment(align);
		}
		return time;
	}
	
    private void retriveDate(NTLockScreenManager lockscreen, Element DateElement) {
        NTDate date = getDate(DateElement);
        lockscreen.addBackgroundObj(date);
    }

    private NTDate getDate(Element DTElement) {
        String dateX = DTElement.getAttribute("x");
        String dateY = DTElement.getAttribute("y");
        String colorStr = DTElement.getAttribute("color");
        String sizeStr = DTElement.getAttribute("size");
        NTDate nTDate = new NTDate(dateX, dateY);
        nTDate.setColor(colorStr);
        nTDate.setSize(sizeStr);
        return nTDate;
    }

	public void retriveImage(NTLockScreenManager lockscreen, Element imageElement) {
		NTImage image = getImage(imageElement);
		lockscreen.addBackgroundObj(image);
	}

	public NTImage getImage(Element imageElement) {
        String src = imageElement.getAttribute("src");
        android.util.Log.i("slong.liang", "getImage src = " + src);
        /*
        if(src == null || src.equals("")) {
        	return null;
        }
        Bitmap bitmap = BitmapFactory.decodeFile(filesPath + src);
        if(bitmap == null) {
        	return null;
        }
        NTImage image = new NTImage(bitmap);
        */
        NTImage image = new NTImage(src);
        /*
		float imageX = Float.parseFloat(imageElement.getAttribute("x"));
		float imageY = Float.parseFloat(imageElement.getAttribute("y"));
		image.setPosition(new PointF(imageX, imageY));*/
        String imageX = imageElement.getAttribute("x");
        String imageY = imageElement.getAttribute("y");
        String centerX = imageElement.getAttribute("centerX");
        String centerY = imageElement.getAttribute("centerY");
        String align = imageElement.getAttribute("align");
        String angle = imageElement.getAttribute("angle");
        image.setPos(imageX, imageY);
        if(align != null && !align.equals("")) {
            image.setHAlignment(align);
        }
        if(angle != null && !angle.equals("")) {
        	image.setAngle(angle);
        }
        if(centerX != null && !centerX.equals("") && centerY != null && !centerY.equals("")) {
        	image.setCenter(centerX, centerY);
        }
		NodeList nodeList = imageElement.getChildNodes();
		if(nodeList != null) {
			for(int i = 0; i < nodeList.getLength(); i++) {
				if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element)nodeList.item(i);
					String name = element.getNodeName();
	                if(name.equalsIgnoreCase("PositionAnimation")) {
	                	NodeList positionNode = element.getElementsByTagName("Position");
						if (positionNode != null && positionNode.getLength() != 0) {
							NTAnimationPositionLinear animPos = new NTAnimationPositionLinear(positionNode.getLength());
							for(int k = 0; k < positionNode.getLength(); k++) {
								Element positionElement = (Element) positionNode.item(k);
								String x = positionElement.getAttribute("x");
								String y = positionElement.getAttribute("y");
								String time = positionElement.getAttribute("time");
								if(x != null && y!= null && time != null) {
									animPos.addPosTime(Float.parseFloat(x), Float.parseFloat(y), Long.parseLong(time));
								}
							}
							image.setAnimPos(animPos);
						}
	                }else if(name.equalsIgnoreCase("RotationAnimation")) {
						NodeList rotationNode = element.getElementsByTagName("Rotation");
						if (rotationNode != null && rotationNode.getLength() != 0) {
							NTAnimationRotationLinear animRotate = new NTAnimationRotationLinear(rotationNode.getLength());
							//centerX and centerY may be should be added in NTImage--start
							//String centerX = imageElement.getAttribute("centerX");
							//String centerY = imageElement.getAttribute("centerY");
							if(centerX != null && centerX != "" && centerY != null && centerY != "") {
								animRotate.set_center(new PointF(Float.parseFloat(centerX),Float.parseFloat(centerY)));
							}
							//centerX and centerY may be should be added in NTImage--end
							for(int k = 0; k < rotationNode.getLength(); k++) {
								Element rotationElement = (Element) rotationNode.item(k);
								String roAngle = rotationElement.getAttribute("angle");
								String time = rotationElement.getAttribute("time");
								if(angle != null && time != null) {
									animRotate.addRotationTime(Integer.parseInt(roAngle), Long.parseLong(time));
								}
							}
							image.setAnimRotate(animRotate);
						}
	                }else if(name.equalsIgnoreCase("AlphaAnimation")) {
	                	NodeList alphaNode = element.getElementsByTagName("Alpha");
						if (alphaNode != null && alphaNode.getLength() != 0) {
							NTAnimationAlphaLinear animAlpha = new NTAnimationAlphaLinear(alphaNode.getLength());
							for(int k = 0; k < alphaNode.getLength(); k++) {
								Element alphaElement = (Element) alphaNode.item(k);
								String a = alphaElement.getAttribute("a");
								String time = alphaElement.getAttribute("time");
								if(a != null && time != null) {
									animAlpha.addAlphaTime(Integer.parseInt(a), Long.parseLong(time));
								}
							}
							image.setAnimAlpha(animAlpha);
						}
	                }
				}
			}
		}
		return image;
	}

	public void retriveWallpaper(NTLockScreenManager lockscreen,
			Element wallpaperElement) {
		String wallpaper = getWallPaper(wallpaperElement);
		if(wallpaper != null) {
			/*
			int w = wallpaper.getWidth();
			int h = wallpaper.getHeight();
			WindowManager winm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
			Display display = winm.getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			
			NTLockScreenGlobal.fScaleX = size.x *1.0f/ 720;
			NTLockScreenGlobal.fScaleY = size.y *1.0f/ 1280;
			
			NTLockScreenGlobal.fWallScaleX = size.x*1.0f / w;
			NTLockScreenGlobal.fWallScaleY = size.y*1.0f / h;*/
			setScale(wallpaper);
			lockscreen.setWallPaper(wallpaper);
		}
	}
    @Deprecated
	private void setScale(String wp) {
		// TODO Auto-generated method stub
		Bitmap wallpaper = BitmapFactory.decodeFile(filesPath + wp);
		if(wallpaper != null) {
			int w = wallpaper.getWidth();
			int h = wallpaper.getHeight();
			WindowManager winm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
			Display display = winm.getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			
			NTLockScreenGlobal.fScaleX = size.x *1.0f/ 720;
			NTLockScreenGlobal.fScaleY = size.y *1.0f/ 1280;
			
			NTLockScreenGlobal.fWallScaleX = size.x*1.0f / w;
			NTLockScreenGlobal.fWallScaleY = size.y*1.0f / h;
		}
	}

	public String getWallPaper(Element wallpaperElement) {
		return wallpaperElement.getAttribute("src");
	}
}
