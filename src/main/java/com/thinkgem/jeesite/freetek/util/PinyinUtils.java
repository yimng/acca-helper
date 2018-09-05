package com.thinkgem.jeesite.freetek.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


/**
 * 拼音操作类，包含功能
 * 给出汉字的首字母拼音，全部拼音，以及中间带空格的拼音等
 * @author Administrator
 *
 */
public class PinyinUtils {

	/**
	 * 汉字转换位第一个汉语拼音首字母，英文字符不变
	 * 
	 * @param chines
	 *            汉字
	 * @return 拼音  首字母
	 */
	public static String converterToNameFirstSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0].charAt(0);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName.substring(0, 1);
	}

	/**
	 * 汉字转换位汉语拼音首字母，英文字符不变
	 * 
	 * @param chines
	 *            汉字
	 * @return 拼音  首字母缩写
	 */
	public static String converterToFirstSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0].charAt(0);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	/**
	 * 汉字转换位汉语拼音，英文字符不变
	 * 
	 * @param chines
	 *            汉字
	 * @return 小写拼音
	 */
	public static String converterToSpell(String chines) {
		StringBuffer pinyinName = new StringBuffer();
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName.append(PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0]);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName.append( nameChar[i]);
			}
		}
		return pinyinName.toString();
	}
	
	
	/**
	 * 汉字转换位汉语拼音，英文字符不变，两个汉字之间增加一个空格
	 * @param chines 汉字
	 * @return 小写拼音
	 */
	public static String converterToSpellAddSpace(String chines) {
		StringBuffer pinyinName = new StringBuffer();
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName.append(PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0]).append(" ");
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName.append( nameChar[i]);
			}
		}
		return pinyinName.toString();
	}

	public static void main(String[] args) {
//		System.out.println(converterToSpellAddSpace("欢 迎来到最棒的Java中文社区"));
//		System.out.println(converterToSpellAddSpace("JIANG WEI"));
		
		String name = "单冬";
		String firstWord =PinyinUtils.converterToNameFirstSpell(name);
		String lowerName = PinyinUtils.converterToSpell(name);
		String lowerSpaceName = PinyinUtils.converterToSpellAddSpace(name).trim();
		String upperName = lowerName.toUpperCase();
		String upperSpaceName = lowerSpaceName.toUpperCase();
	
		System.out.println("firstWord="+firstWord);
		System.out.println("name="+name);
		System.out.println("lowerName="+lowerName);
		System.out.println("lowerSpaceName="+lowerSpaceName);
		System.out.println("upperName="+upperName);
		System.out.println("upperSpaceName="+upperSpaceName);
		System.out.println("aaa="+converterToFirstSpell(name));
	}
}
