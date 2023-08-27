package com.baosight.wilp.ss.bm.utils;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.data.ibatis.support.SqlMapClientDaoSupport;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.log.Logger;
import com.baosight.iplat4j.core.log.LoggerFactory;
import com.baosight.iplat4j.core.resource.I18nMessages;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.ExceptionUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用service
 * */
public class SqlUtils extends ServiceBase{
	private static final Logger logger = LoggerFactory.getLogger(SqlUtils.class);
	public static Dao dao;

	static{
		//建立baseDao作为数据库操作对象
		setDao("base");
	}

	//重写父类的setDao方法
	public static void setDao(String serviceName) {
		ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) PlatApplicationContext.getApplicationContext();
		long bf = System.currentTimeMillis();
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)applicationContext.getBeanFactory();
		if (dao == null) {
			//创建一个baseDao
			String id = serviceName + "Dao";
			BeanDefinitionBuilder builder = BeanDefinitionBuilder.childBeanDefinition("dao");
			builder.addPropertyValue("autoLimit", "true");
			builder.addPropertyValue("autoCount", "true");
			beanFactory.registerBeanDefinition(id, builder.getBeanDefinition());
			SqlUtils.dao = (Dao)beanFactory.getBean(id);
		}
	}


	/**
	 * query:查询数据
	 * @param sqlName String
	 * @param paramMap HashMap
	 * paramObject(paramMap:查询条件,sqlName:查询语句)
	 * @return 返回值:outInfo(block[result:list]):
	 * */
	public static List<HashMap<String,Object>> queryForListMap(String sqlName,Map<String,Object> paramMap) {
		//数据查询
		return dao.query(sqlName, paramMap);
	}
	
	/**
	 * 查询数据
	 * @param sqlName
	 * sql : 执行的query语句
	 * str : 查询条件
	 * @return boolean
	 * */
	public static Object queryForObjectByString(String sqlName,String str) throws Exception{
		Object obj = ((SqlMapClientDaoSupport) dao).getSqlMapClient().queryForObject(sqlName, str);
		return obj;
	}
	
	
	/**
	 * 查询数据
	 * @param sqlName
	 * sqlName : 执行的query语句
	 * str : 查询条件
	 * @return boolean
	 * */
	public static List queryForListByString(String sqlName,String str) throws Exception{
		return ((SqlMapClientDaoSupport) dao).getSqlMapClient().queryForList(sqlName, str);
	}
	
	/**
	 * 查询数据
	 * @param sqlName
	 * sqlName : 执行的query语句
	 * map : 查询条件
	 * @return boolean
	 * */
	public static Object queryForObjectByMap(String sqlName,Map<String, Object> map) throws Exception{
		return ((SqlMapClientDaoSupport) dao).getSqlMapClient().queryForObject(sqlName, map);
	}
	
	/**
	 * 统计数据数据
	 * @param sqlName
	 * sqlName : 执行的query语句
	 * map : 查询条件
	 * @return boolean
	 * */
	public static int countByMap(String sqlName,Map<String, Object> map) throws Exception{
	    return dao.count(sqlName, map);
	}
	
	/**
	 * 查询数据
	 * @param sqlName
	 * sqlName : 执行的query语句
	 * map : 查询条件
	 * @return boolean
	 * */
	public static List querySqlByMap(String sqlName,Map<String, Object> map) throws Exception{
		return dao.query(sqlName, map);
	}
	
	/**
	 * 查询数据
	 * @param sqlName
	 * sqlName : 执行的query语句
	 * bean : 查询条件
	 * @return boolean
	 * */
	public static List querySqlByString(String sqlName,String str) throws Exception{
		return dao.query(sqlName, str);
	}

	public static EiInfo updateCommen(EiInfo inInfo, String sqlName, DaoEPBase bean, boolean copyProperty, String inDataBlock){
		if (inDataBlock == null || "".equals(inDataBlock)) {
			inDataBlock = EiConstant.resultBlock;
		}
		int i= 0;
		try {
			for(i = 0; i < inInfo.getBlock(inDataBlock).getRowCount(); ++i) {
				if (copyProperty && bean != null) {
					bean.fromMap(inInfo.getBlock(inDataBlock).getRow(i));
					dao.update(sqlName, bean);
				} else {
					dao.update(sqlName, inInfo.getBlock(inDataBlock).getRow(i));
				}
			}
			inInfo.setMsgByKey("ep.1000", new String[]{String.valueOf(i), I18nMessages.getText("label.update", "修改")});
		} catch (Exception var8) {
			handleUpdateException(inInfo, i, var8);
		}

		return inInfo;
	}

	/**
	 * update数据
	 * @param sqlName
	 * sql : 执行的update语句
	 * bean : 要更新的实体类
	 * @return boolean
	 * */
	public static EiInfo updateSqlByMap(String sqlName,HashMap<String, Object> map) throws Exception{
		boolean flag = false;
		EiInfo info = new EiInfo();
		info.addBlock("result").addRow(map);
		EiInfo update = updateCommen(info, sqlName,null,false,"");
		if(update.getStatus() > 0){
			flag = true;
		}
		update.set("success", flag);
		return update;
	}
	
	/**
	 * update数据
	 * @param sqlName
	 * sql : 执行的update语句
	 * bean : 要更新的实体类
	 * @return boolean
	 * */
	public static EiInfo updateSqlByDomain(String sqlName,DaoEPBase domain) throws Exception{
		boolean flag = false;
		EiInfo info = new EiInfo();
		info.addBlock("result").addRow(domain);
		EiInfo update = updateCommen(info, sqlName,null,false,"");
		if(update.getStatus() > 0){
			flag = true;
		}
		update.set("success", flag);
		return update;
	}

	/**
	 * update数据List
	 * @param sqlName
	 * sql : 执行的update语句
	 * list : 要更新的List<DaoEPBase>数据
	 * @return boolean
	 * */
	public static EiInfo updateSqlByList(String sqlName,List list) throws Exception{
		boolean flag = false;
		EiInfo info = new EiInfo();
		info.addBlock("result").addRows(list);
		EiInfo insert = updateCommen(info, sqlName, null, false, null);
		if(insert.getStatus() > 0){
			flag = true;
		}
		insert.set("success", flag);
		return insert;
	}


	public static EiInfo insertCommen(EiInfo inInfo, String sqlName, DaoEPBase bean, boolean copyProperty, String inDataBlock) {
		if (inDataBlock == null || "".equals(inDataBlock)) {
			inDataBlock = EiConstant.resultBlock;
		}
		int i = 0;
		try {
			for(i = 0; i < inInfo.getBlock(inDataBlock).getRowCount(); ++i) {
				if (copyProperty && bean != null) {
					bean.fromMap(inInfo.getBlock(inDataBlock).getRow(i));
					dao.insert(sqlName, bean);
				} else {
					dao.insert(sqlName, inInfo.getBlock(inDataBlock).getRow(i));
				}
			}
			inInfo.setMsgByKey("ep.1000", new String[]{String.valueOf(i), I18nMessages.getText("label.insert", "新增")});
		} catch (Exception var8) {
			handleInsertException(inInfo, i, var8);
		}
		return inInfo;
	}

	/**
	 * insert数据
	 * @param sqlName
	 *  sqlName : 执行的insert语句
	 *  bean : 要保存的DaoEPBase数据
	 * @return boolean
	 * */
	public static EiInfo insertSqlByDomain(String sqlName,DaoEPBase domain) throws Exception{
		boolean flag = false;
		EiInfo info = new EiInfo();
		info.addBlock("result").addRow(domain);
		EiInfo insert = insertCommen(info, sqlName,null,false,"");
		if(insert.getStatus() > 0){
			flag = true;
		}
		insert.set("success", flag);
		return insert;
	}
	
	/**
	 * insert数据List
	 * @param sqlName
	 * sql : 执行的insert语句
	 * list : 要保存的List<DaoEPBase>数据
	 * @return boolean
	 * */
	public static EiInfo insertSqlByList(String sqlName,List list) throws Exception{
		boolean flag = false;
		EiInfo info = new EiInfo();
		info.addBlock("result").addRows(list);
		EiInfo insert = insertCommen(info, sqlName, null, false, null);
		if(insert.getStatus() > 0){
			flag = true;
		}
		insert.set("success", flag);
		return insert;
	}

	private static void handleUpdateException(EiInfo inInfo, int i, Exception ex) {
		logger.error(ex);
		inInfo.setMsgByKey("ep.0002", new String[]{String.valueOf(i + 1), I18nMessages.getText("label.update", "修改"), ExceptionUtil.getRootCauseMessage(ex)});
		inInfo.setDetailMsg(ex.toString());
		throw new PlatException("E_Plat_UpdateError", ex, inInfo);
	}

	private static void handleInsertException(EiInfo inInfo, int i, Exception ex) {
		logger.error(ex);
		inInfo.setMsgByKey("ep.0002", new String[]{String.valueOf(i + 1), I18nMessages.getText("label.insert", "新增"), ExceptionUtil.getRootCauseMessage(ex)});
		inInfo.setDetailMsg(ex.toString());
		throw new PlatException("E_Plat_InsertError", ex, inInfo);
	}
}
