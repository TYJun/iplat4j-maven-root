package com.baosight.wilp.dm.fm.common;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtils {
 
  public static HanyuPinyinOutputFormat PINYIN_FORMAT;
  static {
 
    PINYIN_FORMAT = new HanyuPinyinOutputFormat();
    /**
     * ��Сд����
     *   LOWERCASE:Сд
     *   UPPERCASE:��д
     */
    PINYIN_FORMAT.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    /**
     * �����������
     *
     * WITH_TONE_MARK:ֱ�����������VCharType��������WITH_U_UNICODE��������׳��쳣��
     * WITH_TONE_NUMBER��1-4���ֱ�ʾ����
     * WITHOUT_TONE��û������
     */
    PINYIN_FORMAT.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    /**
     * ����������������
     * WITH_U_AND_COLON:��u��ʾ(û������Ĭ����u��ʾ)
     * WITH_V:��v��ʾ
     * WITH_U_UNICODE:�è���ʾ
     */
    PINYIN_FORMAT.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
  }
 
  /**
   * 
   * TODO(获取字符串首字母简拼大写)
   *
   * @Title: toFirstPinYin 
   * @param chinese
   * @return 
   * @return: String
   */
  public static String toFirstPinYin(String chinese){
    StringBuilder result = new StringBuilder();
    char[] chars = chinese.toCharArray();
    try {
      for (char c : chars) {
        if(String.valueOf(c).matches("[\u4e00-\u9fa5]+")){
          String[] pinyinStr = PinyinHelper.toHanyuPinyinStringArray(c, PINYIN_FORMAT);
          result.append(pinyinStr[0].charAt(0));//ȡÿ�����ĵĵ�һ��ƴ����ĸ
        }else {
          result.append(c);
        }
      }
    } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
      badHanyuPinyinOutputFormatCombination.printStackTrace();
    }
    return result.toString().toUpperCase();
  }
 
  /**
   * 
   * TODO(获取字符串拼音)
   *
   * @Title: toPinYin 
   * @param chinese
   * @return 
   * @return: String
   */
  public static String toPinYin(String chinese){
    StringBuilder result = new StringBuilder();
    char[] chars = chinese.toCharArray();
    try {
      for (char c : chars) {
        if(String.valueOf(c).matches("[\u4e00-\u9fa5]+")){
          String[] pinyinStr = PinyinHelper.toHanyuPinyinStringArray(c, PINYIN_FORMAT);
          result.append(pinyinStr[0]);
        }else {
          result.append(c);
        }
      }
    } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
      badHanyuPinyinOutputFormatCombination.printStackTrace();
    }
    return result.toString();
  }
  
  /**
   * ����תƴ��ÿ���ַ����ĵ�һ����ĸ��д����Сд
   * @param chinese
   * @return
   */
  public static String toUpperStringsFirstCharPinYin(String chinese){
    StringBuilder result = new StringBuilder();
    char[] chars = chinese.toCharArray();
    try {
      for (char c : chars) {
        if(String.valueOf(c).matches("[\u4e00-\u9fa5]+")){
          String[] pinyinStr = PinyinHelper.toHanyuPinyinStringArray(c, PINYIN_FORMAT);
          String c1 = String.valueOf(pinyinStr[0]);
          result.append(c1.substring(0,1).toUpperCase()).append(c1.substring(1));
        }else {
          result.append(c);
        }
      }
    } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
      badHanyuPinyinOutputFormatCombination.printStackTrace();
    }
    return result.toString();
  }
}