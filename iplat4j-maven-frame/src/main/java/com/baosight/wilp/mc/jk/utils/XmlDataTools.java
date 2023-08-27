package com.baosight.wilp.mc.jk.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * XML工具类型
 * 
 * @author 严家俊
 *
 */
public class XmlDataTools 
{
	/**
	 * 将XML文本的内容，转换为org.dom4j的DOCUMENT结构化对象
	 * @param tContent  文本内容
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Document parseXmlString(String tContent) throws IOException, DocumentException
	{
		//处理文本内容，确定文件转换的有效的字节编码方式
		String    tEncoding="utf-8";
		int       iBeginPos=tContent.indexOf("<?xml ");
		int       iFirstLinePos=tContent.indexOf("\r");
		tContent=tContent.trim();
		if(iBeginPos>=0&&iFirstLinePos>iBeginPos)
		{
			int iPosEncoding=tContent.indexOf("encoding");
			int iPosEnd=tContent.indexOf("?>");
			if(iPosEncoding<iPosEnd)
			{
				StringBuffer pBuffer=null;
				for(int iCyc=iPosEncoding;iCyc<iPosEncoding;iCyc++)
				{
					if(tContent.charAt(iCyc)=='\"')
					{
						if(pBuffer==null)
						{
							pBuffer=new StringBuffer();
						}
						else
						{
							break;
						}
					}
					if(pBuffer==null)
					{
						continue;
					}
					pBuffer.append(tContent.charAt(iCyc));
				}
				if(pBuffer!=null)
				{
					tEncoding=pBuffer.toString().trim();
				}
				if(tEncoding==null||tEncoding.trim().equals(""))
				{
					tEncoding="utf-8";
				}
			}
		}
		else
		{
			//tContent="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"+tContent;
		}
		//内存字节流
		ByteArrayInputStream pStream=new ByteArrayInputStream(tContent.getBytes(tEncoding));
		Document             pReturnDoc=null;
		try
		{
			SAXReader pReader=new SAXReader();
			pReturnDoc=pReader.read(pStream);
		}
		finally
		{
			pStream.close();
			pStream=null;
		}
		return pReturnDoc;
	}
	
	/**
	 * 将XML转换为JSON对象。xml的tagName转换为JSON对象中的属性key，txt转换为内容
	 * @param pDoc           XML文档
	 * @param bRootIsMember  是否在XML的根节点做为json的成员属性。
	 *                       对于如下的XML：
	 *                       <root><abc>111</abc><def>222</dev></root>
	 *                       如果选择true，那么生成的json类似于 {root:{abc:111,def:222}}。
	 *                       如果选择false，那么生成的json类似于{abc:111,def:222}。
	 * @return 格式化好的JSON对象
	 */
	public static JSONObject xmlDocToJson(Document pDoc,boolean bRootIsMember)
	{
		JSONObject pReturnObject=new JSONObject();
		Element    pRootElement=pDoc.getRootElement();
		if(bRootIsMember)
		{
			JSONObject pRootObject=new JSONObject();
			pReturnObject.put(pRootElement.getName(),pRootObject);
			xmlElementWriteToJson(pRootElement,pRootObject);
		}
		else
		{
			xmlElementWriteToJson(pRootElement,pReturnObject);
		}
		return pReturnObject;
	}
	
	/**
	 * 将Element的内容，转换写入到JSON。xml的tagName转换为JSON对象中的属性key，txt转换为内容
	 * @param pBootElement  XML的节点
	 * @param pWriteJson    写入的JSON对象
	 */
	public static void xmlElementWriteToJson(Element pBootElement,JSONObject pWriteJson)
	{
		//第一步，按Element的TagName对Element进行分组
		//第二步，对于其中分组数量>1的element,按TagName生成JSONArray处理，对于为1的按普通属性或JSONObject处理
		//      如果没有子节点，按普通属性处理。如果有子节点，按JSONObject处理
		//其中，对于Element。如果即没有Text内容，也没有子节点的，按NULL处理
		//--No.1 Element过滤分组
		Map<String,List<Element>> pElementMap=new HashMap<String,List<Element>>();
		List 					  pElementList=pBootElement.elements();
		List<String>              pArrayList=new ArrayList<String>();
		for(int iCyc=0;iCyc<pElementList.size();iCyc++)
		{
			Element pElement=(Element) pElementList.get(iCyc);
			String tTagName=pElement.getName();
			if(pElementMap.containsKey(tTagName))
			{
				if(!pArrayList.contains(tTagName))
				{
					pArrayList.add(tTagName);
				}
				pElementMap.get(tTagName).add(pElement);
			}
			else
			{
				List<Element> pList=new ArrayList<Element>();
				pList.add(pElement);
				pElementMap.put(tTagName, pList);
			}
		}
		//--No.2.1 处理JSONArray的情况
		for(int iA=0;iA<pArrayList.size();iA++)
		{
			JSONArray pResultArray=new JSONArray();
			List<Element> pProcessList=pElementMap.get(pArrayList.get(iA));
			for(int iB=0;iB<pProcessList.size();iB++)
			{
				Element pElement=pProcessList.get(iB);
				//--写入正常值
				if(pElement.elements()==null||pElement.elements().size()==0)
				{
					String tContent=pElement.getTextTrim();
					pResultArray.add(tContent==null?"":tContent);
					continue;
				}
				//--递归写入
				JSONObject pObject=new JSONObject();
				pResultArray.add(pObject);
				xmlElementWriteToJson(pElement,pObject);
			}
			pWriteJson.put(pArrayList.get(iA), pResultArray);
			pElementMap.remove(pArrayList.get(iA));
		}
		//--No.2.2 处理其它属性
		Iterator<String> pIterator=pElementMap.keySet().iterator();
		while(pIterator.hasNext())
		{
			String tKey=pIterator.next();
			Element pElement=pElementMap.get(tKey).get(0);
			//--写入正常值
			if(pElement.elements()==null||pElement.elements().size()==0)
			{
				String tContent=pElement.getTextTrim();
				pWriteJson.put(tKey,tContent==null?"":tContent);
				continue;
			}
			//--递归写入
			JSONObject pObject=new JSONObject();
			pWriteJson.put(tKey, pObject);
			xmlElementWriteToJson(pElement,pObject);
		}
	}
	
	
}
