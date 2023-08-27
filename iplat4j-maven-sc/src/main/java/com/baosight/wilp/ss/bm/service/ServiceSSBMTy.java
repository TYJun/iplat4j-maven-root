package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.data.ibatis.support.SqlMapClientDaoSupport;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用service
 * */
public class ServiceSSBMTy extends ServiceBase{
	
	
	/**
	 * query:查询数据
	 * @param inInfo EiInfo
	 * paramObject(paramMap:查询条件,sqlName:查询语句)
	 * @return 返回值:outInfo(block[result:list]):
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		Map<String,Object> paramObject = (Map<String, Object>) inInfo.getAttr().get("paramObject");
		Map<String,Object> paramMap = (Map<String, Object>) paramObject.get("paramMap");
		String sqlName = paramObject.get("sqlName").toString();
		//数据查询
		List<HashMap<String,Object>> list =  dao.query(sqlName, paramMap);
		inInfo.addBlock("result").addRows(list);
		return outInfo;
	}
	
	/**
	 * 查询数据
	 * @param inInfo
	 * sql : 执行的query语句
	 * str : 查询条件
	 * @return boolean
	 * */
	public EiInfo querySqlForObjectByString(EiInfo inInfo) throws Exception{
		EiInfo outInfo = new EiInfo();
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String str =  StringUtil.toString(attr.get("str"));
		String sql = StringUtil.toString(attr.get("sql"));
		Object obj = ((SqlMapClientDaoSupport) dao).getSqlMapClient().queryForObject(sql, str);
		outInfo.set("result", obj);
		return outInfo;
	}
	
	
	/**
	 * 查询数据
	 * @param inInfo
	 * sql : 执行的query语句
	 * str : 查询条件
	 * @return boolean
	 * */
	public EiInfo querySqlForListByString(EiInfo inInfo) throws Exception{
		EiInfo outInfo = new EiInfo();
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String str =  StringUtil.toString(attr.get("str"));
		String sql = StringUtil.toString(attr.get("sql"));
		Object obj = ((SqlMapClientDaoSupport) dao).getSqlMapClient().queryForList(sql, str);
		outInfo.set("result", obj);
		return outInfo;
	}
	
	/**
	 * 查询数据
	 * @param inInfo
	 * sql : 执行的query语句
	 * map : 查询条件
	 * @return boolean
	 * */
	public EiInfo querySqlForObjectByMap(EiInfo inInfo) throws Exception{
		EiInfo outInfo = new EiInfo();
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		HashMap<String, Object> map =  (HashMap<String, Object>)attr.get("map");
		String sql = StringUtil.toString(attr.get("sql"));
		Object obj = ((SqlMapClientDaoSupport) dao).getSqlMapClient().queryForObject(sql, map);
		outInfo.set("result", obj);
		return outInfo;
	}
	
	/**
	 * 查询数据
	 * @param inInfo
	 * sql : 执行的query语句
	 * map : 查询条件
	 * @return boolean
	 * */
	public EiInfo querySqlByMap(EiInfo inInfo) throws Exception{
		EiInfo outInfo = new EiInfo();
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		HashMap<String, Object> map =  (HashMap<String, Object>)attr.get("map");
		String sql = StringUtil.toString(attr.get("sql"));
		List list = dao.query(sql, map);
		outInfo.set("result", list);
		return outInfo;
	}
	
	/**
	 * 查询数据
	 * @param inInfo
	 * sql : 执行的query语句
	 * bean : 查询条件
	 * @return boolean
	 * */
	public EiInfo querySqlByString(EiInfo inInfo) throws Exception{
		EiInfo outInfo = new EiInfo();
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String str = StringUtil.toString(attr.get("str"));
		String sql = StringUtil.toString(attr.get("sql"));
		List list = dao.query(sql, str);
		outInfo.set("result", list);
		return outInfo;
	}

	/**
	 * 查询数据
	 * @param inInfo
	 * sql : 执行的query语句
	 * bean : 查询条件
	 * @return boolean
	 * */
	public EiInfo querySqlByString1(EiInfo inInfo) throws Exception{
		EiInfo outInfo = new EiInfo();
		//JdbcTemplate jdbcTemplate= (JdbcTemplate) PlatApplicationContext.getBean("jdbcTemplate");
		//jdbcTemplate.execute("CREATE TABLE USER (user_id integer, name varchar(100))");
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String str = StringUtil.toString(attr.get("str"));
		String sql = StringUtil.toString(attr.get("sql"));
		List list = dao.query(sql, str);
		int i = 1/0;
		outInfo.set("result", list);
		return outInfo;
	}

	/**
	 * update数据
	 * @param inInfo
	 * sql : 执行的update语句
	 * bean : 要更新的实体类
	 * @return boolean
	 * */
	public EiInfo updateSqlByMap(EiInfo inInfo) throws Exception{
		boolean flag = false;
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		HashMap<String, Object> map = (HashMap<String, Object>) attr.get("map");
		String sql = StringUtil.toString(attr.get("sql"));
		EiInfo info = new EiInfo();
		info.addBlock("result").addRow(map);
		EiInfo update = super.update(info, sql);
		if(update.getStatus() > 0){
			flag = true;
		}
		update.set("success", flag);
		return update;
	}
	
	/**
	 * update数据
	 * @param inInfo
	 * sql : 执行的update语句
	 * bean : 要更新的实体类
	 * @return boolean
	 * */
	public EiInfo updateSqlByDomain(EiInfo inInfo) throws Exception{
		boolean flag = false;
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		DaoEPBase domain = (DaoEPBase) attr.get("domain");
		String sql = StringUtil.toString(attr.get("sql"));
		EiInfo info = new EiInfo();
		info.addBlock("result").addRow(domain);
		EiInfo update = super.update(info, sql);
		if(update.getStatus() > 0){
			flag = true;
		}
		update.set("success", flag);
		return update;
	}
	
	/**
	 * insert数据
	 * @param inInfo
	 *  sql : 执行的insert语句
	 *  bean : 要保存的DaoEPBase数据
	 * @return boolean
	 * */
	public EiInfo insertSqlByDomain(EiInfo inInfo) throws Exception{
		boolean flag = false;
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		DaoEPBase domain = (DaoEPBase) attr.get("domain");
		String sql = StringUtil.toString(attr.get("sql"));
		EiInfo info = new EiInfo();
		info.addBlock("result").addRow(domain);
		EiInfo insert = super.insert(info , sql);
		if(insert.getStatus() > 0){
			flag = true;
		}
		insert.set("success", flag);
		return insert;
	}
	
	/**
	 * insert数据List
	 * @param inInfo
	 * sql : 执行的insert语句
	 * list : 要保存的List<DaoEPBase>数据
	 * @return boolean
	 * */
	public EiInfo insertSqlByList(EiInfo inInfo) throws Exception{
		boolean flag = false;
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		List<DaoEPBase> list = (List<DaoEPBase>) attr.get("list");
		String sql = StringUtil.toString(attr.get("sql"));
		EiInfo info = new EiInfo();
		info.addBlock("result").addRows(list);
		EiInfo insert = super.insert(info, sql);
		if(insert.getStatus() > 0){
			flag = true;
		}
		insert.set("success", flag);
		return insert;
	}


	/**
	 * 删除数据
	 * @param inInfo
	 * sql : 执行的delete语句
	 * str : 执行条件
	 * @return boolean
	 * */
	public EiInfo deleteSqlByString(EiInfo inInfo) throws Exception{
		EiInfo outInfo = new EiInfo();
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		String str = StringUtil.toString(attr.get("str"));
		String sql = StringUtil.toString(attr.get("sql"));
		int i = dao.delete(sql, str);
		outInfo.set("result", i);
		return outInfo;
	}
}
