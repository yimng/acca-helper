package com.thinkgem.jeesite.freetek.util;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.dom4j.DocumentException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.AXmlResourceParser;
import android.util.TypedValue;



public class ApkUtils {
	
	public static void main(String[] args) throws ZipException, IOException, DocumentException, XmlPullParserException{
		String filePath="D:\\test.apk";
		String[] str=parser(filePath);
		System.out.println(str[0]+"  "+str[1]+"  "+ str[2]+"  "+str[3]);
	} 
	
	public static String[] parser(String filePath) throws ZipException, IOException, XmlPullParserException{
		String[] result = new String[4];
		File file = new File(filePath);
		result[3]=String.valueOf(file.length());
		ZipFile zipFile = new ZipFile(file);
		Enumeration enu= zipFile.entries();
		ZipEntry zipEntry = null ; 
		while(enu.hasMoreElements()){
			zipEntry = (ZipEntry) enu.nextElement();  
			if(!zipEntry.isDirectory()){
				//System.out.println(zipEntry.getName());
				if("AndroidManifest.xml".equals(zipEntry.getName())){
					AXmlResourceParser parser=new AXmlResourceParser();
					parser.open(zipFile.getInputStream(zipEntry));
					while (true) {
                        int type=parser.next();
                        if (type==XmlPullParser.END_DOCUMENT) {
                            break;
                        }
                        switch (type) {
                            case XmlPullParser.START_TAG:
                            {
                                for (int i=0;i!=parser.getAttributeCount();++i) {
                                    if("versionName".equals(parser.getAttributeName(i))){
                                        System.out.println("versionName:"+getAttributeValue(parser,i));
                                        result[0]=getAttributeValue(parser,i);
                                    }else if("package".equals(parser.getAttributeName(i))){
                                        System.out.println("package:"+getAttributeValue(parser,i));
                                        result[1]=getAttributeValue(parser,i);
                                    }
                                    else if("versionCode".equals(parser.getAttributeName(i))){
                                        System.out.println("versionCode:"+getAttributeValue(parser,i));
                                        result[2]=getAttributeValue(parser,i);
                                    }
                                }
                            }
                            default:
                        }
                    }
				}
			}
		}
		return result;
	}
	
	private static String getAttributeValue(AXmlResourceParser parser,int index) {
        int type=parser.getAttributeValueType(index);
        int data=parser.getAttributeValueData(index);
        if (type==TypedValue.TYPE_STRING) {
            return parser.getAttributeValue(index);
        }
        if (type==TypedValue.TYPE_ATTRIBUTE) {
            return String.format("?%s%08X",getPackage(data),data);
        }
        if (type==TypedValue.TYPE_REFERENCE) {
            return String.format("@%s%08X",getPackage(data),data);
        }
        if (type==TypedValue.TYPE_FLOAT) {
            return String.valueOf(Float.intBitsToFloat(data));
        }
        if (type==TypedValue.TYPE_INT_HEX) {
            return String.format("0x%08X",data);
        }
        if (type==TypedValue.TYPE_INT_BOOLEAN) {
            return data!=0?"true":"false";
        }
        if (type==TypedValue.TYPE_DIMENSION) {
            return Float.toString(complexToFloat(data))+
                DIMENSION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
        }
        if (type==TypedValue.TYPE_FRACTION) {
            return Float.toString(complexToFloat(data))+
                FRACTION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
        }
        if (type>=TypedValue.TYPE_FIRST_COLOR_INT && type<=TypedValue.TYPE_LAST_COLOR_INT) {
            return String.format("#%08X",data);
        }
        if (type>=TypedValue.TYPE_FIRST_INT && type<=TypedValue.TYPE_LAST_INT) {
            return String.valueOf(data);
        }
        return String.format("<0x%X, type 0x%02X>",data,type);
    }
    
   private static String getPackage(int id) {
        if (id>>>24==1) {
            return "android:";
        }
        return "";
   }
   
   public static float complexToFloat(int complex) {
       return (float)(complex & 0xFFFFFF00)*RADIX_MULTS[(complex>>4) & 3];
   }
    
   private static final float RADIX_MULTS[]={
       0.00390625F,3.051758E-005F,1.192093E-007F,4.656613E-010F
   };
   private static final String DIMENSION_UNITS[]={
       "px","dip","sp","pt","in","mm","",""
   };
   private static final String FRACTION_UNITS[]={
       "%","%p","","","","","",""
   };

}
