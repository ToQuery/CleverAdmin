package com.cleverweb.common.util;

/** 
 * 说明：
 * 创建人：FH Q313596790
 * 修改时间：2015年11月24日
 * @version
 */
public class Constants {
	
	public static String PICTURE_VISIT_FILE_PATH = "";//图片访问的路径
	public static String PICTURE_SAVE_FILE_PATH = "";//图片存放的路径
	public static String getPICTURE_VISIT_FILE_PATH() {
		return PICTURE_VISIT_FILE_PATH;
	}

	public static void setPICTURE_VISIT_FILE_PATH(String pICTURE_VISIT_FILE_PATH) {
		PICTURE_VISIT_FILE_PATH = pICTURE_VISIT_FILE_PATH;
	}

	public static String getPICTURE_SAVE_FILE_PATH() {
		return PICTURE_SAVE_FILE_PATH;
	}

	public static void setPICTURE_SAVE_FILE_PATH(String pICTURE_SAVE_FILE_PATH) {
		PICTURE_SAVE_FILE_PATH = pICTURE_SAVE_FILE_PATH;
	}

	public static void main(String[] args) {
		Constants.setPICTURE_SAVE_FILE_PATH("D:/Tomcat 6.0/webapps/FH/topic/");
		Constants.setPICTURE_VISIT_FILE_PATH("http://192.168.1.225:8888/FH/topic/");
	}
	
}
