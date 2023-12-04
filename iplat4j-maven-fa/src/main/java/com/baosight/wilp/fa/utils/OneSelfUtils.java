package com.baosight.wilp.fa.utils;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.data.ibatis.dao.SqlMapDao;
import com.baosight.iplat4j.core.data.ibatis.dao.SqlMapDaoLogProxy;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.fa.lb.domain.FaTypeDO;
import com.baosight.wilp.fa.lb.domain.FaTypeDTO;
import com.baosight.wilp.fa.lb.domain.FaTypeTreeVo;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 固定资产自用工具类.
 * 固定资产创建编码公共方法.
 * iplat4j本地方法调用.
 * 判断固定资产是否被使用中.
 * 判断固定资产是否存在子节点.
 * 根据当前登录人信息查询固定资产角色.
 * 根据当前登录人信息查询固定资产角色(需要传入工号).
 *
 * @author zhaowei
 * @version v5.0.0
 * @date 2022年05月19日 16:52
 */
public class OneSelfUtils {
	private static String defaultBlankValue = "";
	// 注入bean
	private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");

	/**
	 * 固定资产创建编码公共方法
	 * 1.通过传入的编码查询不同规格的编号
	 * 1.1.固定资产类别
	 * 1.2.固定资产档案
	 * 1.3.固定资产变更
	 * 1.4.固定资产调拨
	 * 1.5.固定资产闲置
	 * 1.6.固定资产报损
	 * 1.7.固定资产报废
	 * 1.8.固定资产盘点
	 * 1.9.固定资产盘点子项
	 *
	 * @param Code
	 * @return java.lang.String
	 * @author zhaowei
	 * @date 2022/9/21 13:51
	 * @version V5.0.0
	 */
	public synchronized static String publicCreateCode(String Code) {
		// 获取当前时间并进行格式化
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
		String format = dateFormat.format(new Date());
		// 初始化编号创建
		String CreateCode = Code + format + "001";
		List<Map<String, Object>> maxCode;
		/*
		 * 1.通过传入的编码查询不同规格的编号
		 */
		switch (Code) {
			/*
			 * 1.1.固定资产类别
			 * 查询当天固定资产的编号
			 * 不存在则进行当天编号的初始化
			 * 存在则进行当天编号的累加
			 */
			case "LB":
				maxCode = dao.query("FALB01.queryMaxCode", format);
				if (CollectionUtils.isEmpty(maxCode)) {
					CreateCode = "GZLB" + format + "0001";
				} else {
					String typeCode = (String) maxCode.get(0).get("typeCode");
					int length = typeCode.length();
					if (length == 16) {
						String substring = typeCode.substring(12);
						CreateCode = "GZLB" + format + String.format("%04d", Integer.valueOf(substring) + 1);
					} else {
						CreateCode = "GZLB" + format + "0001";
					}
				}
				break;
			/*
			 * 1.2.固定资产档案
			 * 查询当天固定资产的编号
			 * 不存在则进行当天编号的初始化
			 * 存在则进行当天编号的累加
			 */
			case "DA":
				maxCode = dao.query("FADA01.queryMaxCode", format);
				if (CollectionUtils.isEmpty(maxCode)) {
					CreateCode = "GZDA" + format + "0001";
				} else {
					String typeCode = (String) maxCode.get(0).get("typeCode");
					int length = typeCode.length();
					if (length == 16) {
						String substring = typeCode.substring(12);
						CreateCode = "GZDA" + format + String.format("%04d", Integer.valueOf(substring) + 1);
					} else {
						CreateCode = "GZDA" + format + "0001";
					}
				}
				break;
			/*
			 * 1.3.固定资产变更
			 * 查询当天固定资产的编号
			 * 不存在则进行当天编号的初始化
			 * 存在则进行当天编号的累加
			 */
			case "BG":
				maxCode = dao.query("FABG01.queryMaxCode", format);
				if (CollectionUtils.isEmpty(maxCode)) {
					CreateCode = "GZBG" + format + "0001";
				} else {
					String typeCode = (String) maxCode.get(0).get("typeCode");
					int length = typeCode.length();
					if (length == 16) {
						String substring = typeCode.substring(12);
						CreateCode = "GZBG" + format + String.format("%04d", Integer.valueOf(substring) + 1);
					} else {
						CreateCode = "GZBG" + format + "0001";
					}
				}
				break;
			/*
			 * 1.4.固定资产调拨
			 * 查询当天固定资产的编号
			 * 不存在则进行当天编号的初始化
			 * 存在则进行当天编号的累加
			 */
			case "DB":
				maxCode = dao.query("FADB01.queryMaxCode", format);
				if (CollectionUtils.isEmpty(maxCode)) {
					CreateCode = "GZDB" + format + "0001";
				} else {
					String typeCode = (String) maxCode.get(0).get("typeCode");
					int length = typeCode.length();
					if (length == 16) {
						String substring = typeCode.substring(12);
						CreateCode = "GZDB" + format + String.format("%04d", Integer.valueOf(substring) + 1);
					} else {
						CreateCode = "GZDB" + format + "0001";
					}
				}
				break;
			case "CF":
				maxCode = dao.query("FACF01.queryMaxCode", format);
				if (CollectionUtils.isEmpty(maxCode)) {
					CreateCode = "GZCF" + format + "0001";
				} else {
					String typeCode = (String) maxCode.get(0).get("typeCode");
					int length = typeCode.length();
					if (length == 16) {
						String substring = typeCode.substring(12);
						CreateCode = "GZCF" + format + String.format("%04d", Integer.valueOf(substring) + 1);
					} else {
						CreateCode = "GZCF" + format + "0001";
					}
				}
				break;
			/*
			 * 1.5.固定资产闲置
			 * 查询当天固定资产的编号
			 * 不存在则进行当天编号的初始化
			 * 存在则进行当天编号的累加
			 */
			case "XZ":
				maxCode = dao.query("FAXZ01.queryMaxCode", format);
				if (CollectionUtils.isEmpty(maxCode)) {
					CreateCode = "GZXZ" + format + "0001";
				} else {
					String typeCode = (String) maxCode.get(0).get("typeCode");
					int length = typeCode.length();
					if (length == 16) {
						String substring = typeCode.substring(12);
						CreateCode = "GZXZ" + format + String.format("%04d", Integer.valueOf(substring) + 1);
					} else {
						CreateCode = "GZXZ" + format + "0001";
					}
				}
				break;
			/*
			 * 1.6.固定资产报损
			 * 查询当天固定资产的编号
			 * 不存在则进行当天编号的初始化
			 * 存在则进行当天编号的累加
			 */
			case "BS":
				maxCode = dao.query("FABS01.queryMaxCode", format);
				if (CollectionUtils.isEmpty(maxCode)) {
					CreateCode = "GZBS" + format + "0001";
				} else {
					String typeCode = (String) maxCode.get(0).get("typeCode");
					int length = typeCode.length();
					if (length == 16) {
						String substring = typeCode.substring(12);
						CreateCode = "GZBS" + format + String.format("%04d", Integer.valueOf(substring) + 1);
					} else {
						CreateCode = "GZBS" + format + "0001";
					}
				}
				break;
			/*
			 * 1.7.固定资产报废
			 * 查询当天固定资产的编号
			 * 不存在则进行当天编号的初始化
			 * 存在则进行当天编号的累加
			 */
			case "BF":
				maxCode = dao.query("FABF01.queryMaxCode", format);
				if (CollectionUtils.isEmpty(maxCode)) {
					CreateCode = "GZBF" + format + "0001";
				} else {
					String typeCode = (String) maxCode.get(0).get("typeCode");
					int length = typeCode.length();
					if (length == 16) {
						String substring = typeCode.substring(12);
						CreateCode = "GZBF" + format + String.format("%04d", Integer.valueOf(substring) + 1);
					} else {
						CreateCode = "GZBF" + format + "0001";
					}
				}
				break;
			/*
			 * 1.8.固定资产盘点
			 * 查询当天固定资产的编号
			 * 不存在则进行当天编号的初始化
			 * 存在则进行当天编号的累加
			 */
			case "PD":
				maxCode = dao.query("FAPD01.queryMaxCode", format);
				if (CollectionUtils.isEmpty(maxCode)) {
					CreateCode = "GZPD" + format + "0001";
				} else {
					String typeCode = (String) maxCode.get(0).get("typeCode");
					int length = typeCode.length();
					if (length == 16) {
						String substring = typeCode.substring(12);
						CreateCode = "GZPD" + format + String.format("%04d", Integer.valueOf(substring) + 1);
					} else {
						CreateCode = "GZPD" + format + "0001";
					}
				}
				break;
			/*
			 * 1.9.固定资产盘点子项
			 * 查询当天固定资产的编号
			 * 不存在则进行当天编号的初始化
			 * 存在则进行当天编号的累加
			 */
			case "PDZX":
				maxCode = dao.query("FAPD01.queryMaxSubNo", format);
				if (CollectionUtils.isEmpty(maxCode)) {
					CreateCode = "PDZX" + format + "0001";
				} else {
					String typeCode = (String) maxCode.get(0).get("typeCode");
					int length = typeCode.length();
					if (length == 16) {
						String substring = typeCode.substring(12);
						CreateCode = "PDZX" + format + String.format("%04d", Integer.valueOf(substring) + 1);
					} else {
						CreateCode = "PDZX" + format + "0001";
					}
				}
				break;
			case "SH":
				maxCode = dao.query("FASH01.queryMaxSubNo", format);
				if (CollectionUtils.isEmpty(maxCode)) {
					CreateCode = "ZCSH" + format + "0001";
				} else {
					String typeCode = (String) maxCode.get(0).get("typeCode");
					int length = typeCode.length();
					if (length == 16) {
						String substring = typeCode.substring(12);
						CreateCode = "ZCSH" + format + String.format("%04d", Integer.valueOf(substring) + 1);
					} else {
						CreateCode = "ZCSH" + format + "0001";
					}
				}
				break;
		}
		return CreateCode;
	}

	/**
	 * 创建资产分类编码
	 *
	 * @return java.lang.String
	 * @author zhaowei
	 * @date 2023/2/23 9:56
	 */
	public synchronized static String privateCreateTypeCode(String codeRule, String parentId) {
		String typeCode = "";
		// 1.如果不存在编码规则则调用自动生成编码GZLB
		if (StringUtils.isBlank(codeRule)) {
			typeCode = publicCreateCode("LB");
		} else {
			// 1.查出当前节点的编码规则
			int count = dao.count("FAAP01.queryFaTypeInfo", new HashMap(4));
			SqlMapDao sqlMapDao = (SqlMapDao) dao;
			sqlMapDao.setMaxQueryCount(count);
			List<FaTypeDTO> faTypeInfo = sqlMapDao.query("FAAP01.queryFaTypeInfo", new HashMap(4));
			List<FaTypeDTO> collect = faTypeInfo.stream().filter(faTypeDTO -> faTypeDTO.getParentId().equals(parentId)).collect(Collectors.toList());
			List<FaTypeDTO> existCodeRule = collect.stream().filter(faTypeDTO -> faTypeDTO.getCodeRule().equals(codeRule)).collect(Collectors.toList());
			// 3.如果存在编码则查询匹配的编码
			if (CollectionUtils.isNotEmpty(existCodeRule)) {
				// 4.如果新编码重复，返回提示信息
				return "当前编码规则已经重复";
			} else {
				// 5.如果新编码不重复，则返回编码
				String codeRule1 = collect.get(0).getCodeRule();
				String typeCode1 = collect.get(0).getTypeCode();
				String substring = typeCode1.substring(0, typeCode1.length() - codeRule1.length());
				typeCode = substring + codeRule;
			}
		}
		return typeCode;
	}

	/**
	 * 创建资产编码
	 *
	 * @param typeCode
	 * @return java.lang.String
	 * @author zhaowei
	 * @date 2023/2/23 9:55
	 */
	public synchronized static String privateCreateCode(String typeCode) {
		String goodNum = "";
		int count = dao.count("FAAP01.queryFaTypeInfo", new HashMap(4));
		SqlMapDao sqlMapDao = (SqlMapDao) dao;
		sqlMapDao.setMaxQueryCount(count);
		List<FaTypeDTO> faTypeInfo = sqlMapDao.query("FAAP01.queryFaTypeInfo", new HashMap(4));
		List<FaTypeDTO> collect = faTypeInfo.stream().filter(faTypeDTO -> faTypeDTO.getTypeCode().equals(typeCode)).collect(Collectors.toList());
		if (CollectionUtils.isNotEmpty(collect)) {
			List<FaTypeDTO> faTypeTreeVos = FaTypeTreeVo.getParentObject(collect.get(0), faTypeInfo, new ArrayList<FaTypeDTO>());
			Collections.reverse(faTypeTreeVos);
			String name = faTypeTreeVos.stream().map(FaTypeDTO::getCodeRule).collect(Collectors.joining());
			if("B".equals(name.substring(0,1))){
				if (3 > name.length()){
					name = name + String.format("%0" + (3 - name.length()) + "d", 0);
				}
			} else {
				if (5 > name.length()){
					name = name + String.format("%0" + (5 - name.length()) + "d", 0);
				}
			}
			List<String> goodNumList = dao.query("FADA01.queryMaxGoodNum", name);
			if (CollectionUtils.isNotEmpty(goodNumList)) {
				String nowGoodNum = goodNumList.get(0);
				goodNum = name + String.format("%05d", Integer.valueOf(nowGoodNum.substring(name.length())) + 1);
			} else {
				goodNum = name + "00001";
			}
			return goodNum;
		} else {
			return "";
		}
	}

	/*
	 * iplat4j本地方法调用
	 * 1.iplat4j本地方法调用
	 * @author zhaowei
	 * @date 2022/8/25 19:05
	 * @param inInfo
	 * @param serviceName
	 * @param methodName
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version v5.0.0
	 */
	public static EiInfo invoke(EiInfo inInfo, String serviceName, String methodName) {
		// 1.iplat4j本地方法调用
		inInfo.set(EiConstant.serviceName, serviceName);
		inInfo.set(EiConstant.methodName, methodName);
		EiInfo outInfo = XLocalManager.call(inInfo);
		return outInfo;
	}

	/*
	 * 判断固定资产是否被使用中
	 * @author zhaowei
	 * @date 2022/8/25 19:05
	 * @param map
	 * @return java.lang.Boolean
	 * @version v5.0.0
	 */
	public static Boolean existsLock(Map<String, Object> map) {
		List<Map<String, Object>> inventoryList = dao.query("FAPD01.queryInventory", map);
		return CollectionUtils.isEmpty(inventoryList) ? false : true;
	}

	/*
	 * 判断固定资产是否存在子节点
	 * @author zhaowei
	 * @date 2022/8/25 19:07
	 * @param map
	 * @return java.lang.Boolean
	 * @version v5.0.0
	 */
	public static Boolean existsExWife(Map<String, Object> map) {
		List<Map<String, Object>> inventoryList = dao.query("FAPD01.queryInventoryList", null);
		List<Map<String, Object>> transferDetailList = dao.query("FAPD01.queryExWife", map);
		if (CollectionUtils.isEmpty(inventoryList)) {
			return false;
		}
		return transferDetailList.containsAll(inventoryList);
	}

	/**
	 * PC端根据当前登录人信息查询固定资产角色
	 *
	 * @return java.lang.String
	 * @author zhaowei
	 * @date 2022/10/9 13:24
	 * @version v5.0.0
	 */
	public static String specifyDept() {
		// 获取当前登录人的用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 查询固定资产角色，根据权限返回所属科室进行数据隔离
		List<String> rolesList = dao.query("FAAP01.queryRoles", staffByUserId);
		if (rolesList.contains(FixedAssetsEum.leader.getAcronym()) || rolesList.contains(FixedAssetsEum.member.getAcronym())) {
			return null;
		} else {
			return (String) staffByUserId.get("deptName");
		}
	}

	/**
	 * 根据当前登录人信息查询固定资产角色(需要传入工号)
	 *
	 * @param workNo
	 * @return java.lang.String
	 * @author zhaowei
	 * @date 2022/10/12 16:17
	 */
	public static List<String> specifyDept(String workNo) {
		if (StringUtils.isNotBlank(workNo)) {
			// 获取当前登录人的用户信息
			Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(workNo);
			// 查询固定资产人员权限
			List<String> rolesList = dao.query("FAAP01.queryRoles", staffByUserId);
			if (rolesList.contains(FixedAssetsEum.leader.getAcronym()) || rolesList.contains(FixedAssetsEum.member.getAcronym())) {
				return null;
			} else {
				List<String> list = queryDeptByWorkNo(workNo);
				if (CollectionUtils.isNotEmpty(list)){
					list.add((String) staffByUserId.get("deptName"));
					list = list.stream().distinct().collect(Collectors.toList());
					return list;
				} else {
					list.add(0, (String) staffByUserId.get("deptName"));
					return list;
				}

			}
		}
		return null;
	}

	/**
	 * 单次插入的最大行数
	 */
	private final static Integer SINGLE_INSERT_SIZE = 500;
	private final static SqlMapDaoLogProxy DAO = (SqlMapDaoLogProxy) PlatApplicationContext.getBean("dao");

	/**
	 * 批量插入
	 *
	 * @param name sqlmap
	 * @param list 参数
	 */
	public static void batchInsert(String name, List<? extends Object> list) {
		int offset = 0;
		while (list.size() > offset) {
			DAO.insert(name, list.subList(offset, Math.min(list.size(), offset + SINGLE_INSERT_SIZE)));
			offset += SINGLE_INSERT_SIZE;
		}
	}

	/**
	 * 初始化页面时间查询条件
	 *
	 * @param info            info : 参数EiInfo
	 * @param beginTimeColumn beginTimeColumn : 开始时间字段名
	 * @param endTimeColumn   endTimeColumn : 结束时间字段名
	 * @return void
	 * @throws
	 * @Title: initQueryTime
	 **/
	public static void initQueryTime(EiInfo info, String beginTimeColumn, String endTimeColumn) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		info.setCell(EiConstant.queryBlock, 0, beginTimeColumn, DateUtils.toDateStr(cal.getTime()));
		info.setCell(EiConstant.queryBlock, 0, endTimeColumn, DateUtils.curDateStr());
	}

	/**
	 * 执行服务调用
	 *
	 * @throws
	 * @Title: invoke
	 * @Description:
	 * @param: @param inInfo ： 参数对象
	 * @param: @param serviceId ： 服务ID
	 * @param: @return
	 * @return: EiInfo  : 返回对象
	 */
	public static EiInfo invoke(EiInfo inInfo, String serviceId) {
		try {
			inInfo.set(EiConstant.serviceId, serviceId);
			EiInfo outInfo = XServiceManager.call(inInfo);
			if (outInfo.getStatus() < 0) {
				throw new PlatException(outInfo.getMsg());
			}
			return outInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String defaultIfEmpty(Object str, String defaultString) {
		if (str == null) {
			return defaultString;
		} else {
			return str.toString().trim().length() == 0 ? "" : str.toString().trim();
		}
	}

	public static String defaultIfNull(Object str, String defaultString) {
		if (str == null) {
			return defaultString;
		} else {
			return str.toString().trim().length() == 0 ? null : str.toString().trim();
		}
	}

	public static Date defaultDate(Object obj, String type) {
		if (obj == null) {
			return null;
		} else {
			return DateUtils.toDate(defaultIfEmpty(obj, null), StringUtils.isNotEmpty(type) ? type : DateUtils.DATETIME19_PATTERN);
		}
	}

	public static Date defaultDate10(Object obj, String type) {
		if (obj == null) {
			return null;
		} else {
			return DateUtils.toDate(defaultIfEmpty(obj, null), StringUtils.isNotEmpty(type) ? type : DateUtils.DATE10_PATTERN);
		}
	}

	public static Timestamp defaultTimestamp(Object obj) {
		if (obj == null) {
			return null;
		} else {
			return DateUtils.toTimestamp(defaultIfEmpty(obj, null));
		}
	}

	// 处理返回前端参数如果null变为“”
	public static List<Map<String, Object>> transitionResult(List<Map<String, Object>> result) {
		for (Map map : result) {
			Set<String> set = map.keySet();
			for (String str : set) {
				if (map.get(str) == null) {
					map.put(str, "");
				}
			}
		}
		return result;
	}

	/**
	 * 固定资产调拨管理保存图片
	 *
	 * @param
	 * @author zhaowei
	 * @date 2022/6/6 14:00
	 */
	public static void savePicInfo(String fileCode, String num, String type, Map<String, Object> data, String sortNo) {
		Map<String, Object> map = new HashMap<>();
//		String property = System.getProperty("catalina.home");
		String dir = System.getProperty("user.dir");
//		String docRootDir = PlatApplicationContext.getProperty("docRootDir");
		String destPath = dir + File.separator + "upload" + File.separator + "fa" + File.separator + "img" + File.separator;
		map.put("type", type);
		map.put("relateId", num);
		map.put("fileId", fileCode);
		map.put("fileName", fileCode + ".png");
		map.put("filePath", destPath);
		map.put("sortNo", sortNo);
		String path = destPath + File.separator + fileCode + ".png";
		String signatureImg = "data:image/png;base64," + data.get("signatureImg");
		CommonUtils.base64StrToImage(signatureImg, path);
		dao.insert("FADB01.savePicInfo", map);
	}

	/**
	 * 通过用户工号查询用户的所在护理单元
	 *
	 * @param workNo
	 * @author zhaowei
	 * @date 2023/4/18 9:43
	 */
	public static List<String> queryDeptByWorkNo(String workNo) {
		List<String> deptList = new ArrayList<>();
		List<Map<String,Object>> list = dao.query("FAAP01.queryDeptByWorkNo", new HashMap<String, Object>() {{
			put("workNo", workNo);
		}});
		if (CollectionUtils.isNotEmpty(list)){
			for (int i = 0; i < list.size(); i++) {
				String deptName = (String) list.get(i).get("deptName");
				deptList.add(deptName);
			}
		}
		return deptList;
	}

	/**
	 * 空处理
	 *
	 * @throws
	 * @Title: isEmpty
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param object ： 参数
	 * @param: @param defultValue ： 默认值
	 * @param: @return
	 * @return: String  ： 参数字符串
	 */
	public static String isEmpty(Object object, String defaultValue) {
		if (StringUtils.isBlank(defaultValue)) {
			defaultValue = defaultBlankValue;
		}
		if (object == null) {
			return defaultValue;
		}
		if (StringUtils.isBlank(object.toString())) {
			return defaultValue;
		}
		return object.toString();
	}

	/**
	 * 判断当前成员是否是资产管理员或护长
	 * 是返回true
	 * 不是返回false
	 */
	public static Boolean isManagerOrNurses(String workNo){
		if (StringUtils.isNotBlank(workNo)) {
			// 获取当前登录人的用户信息
			Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(workNo);
			// 查询固定资产人员权限
			List<String> rolesList = dao.query("FAAP01.queryRoles", staffByUserId);
			// 是护长或者是管理员
			if (rolesList.contains(FixedAssetsEum.guard.getAcronym()) || rolesList.contains(FixedAssetsEum.common.getAcronym())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}


	/**
	 * 判断当前成员是否是科室负责人
	 * 存在返回true
	 * 不存在返回false
	 */
	public static Boolean isDeptHead(String workNo){
		if (StringUtils.isNotBlank(workNo)) {
			// 获取当前登录人的用户信息
			Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(workNo);
			// 查询固定资产人员权限
			List<String> rolesList = dao.query("FAAP01.queryRoles", staffByUserId);
			// 是护长或者是管理员
			if (rolesList.contains(FixedAssetsEum.user.getAcronym())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 企业微信发送通知
	 * 申请科室-applyDeptNo，接收科室-confirmDeptNo，鉴定科室-identifyDeptNo,归口科室-functionDeptNo
	 */
	public static Boolean pushWxMsgOfFa(Map<String,String> deptMap){
		//获取app编码
		String appCode = "AP00002";
		List<String> workNoList = new ArrayList<>();
		List<String> paramList = new ArrayList<>();
		String smsTemp = "";
		// 消息模板
		switch (deptMap.get("type")){
			case "调拨申请":
				// 通过接收科室查找对应的科室管理员
				smsTemp = "【资产调拨申请】您有一条来自于"+ deptMap.get("applyDeptName") + "，单号为" + deptMap.get("billNo") + "的资产调拨申请，请到后勤一站式系统中进行确认。";
				break;
			case "调拨申请驳回":
				smsTemp = "";
				break;
			case "调拨审批":
				// 发送给张老师、谢老师、廖老师
				smsTemp = "【资产调拨审批】您有一条"+ deptMap.get("applyDeptName") + "调拨至" + deptMap.get("confirmDeptName") + "，单号为" + deptMap.get("billNo") + "的资产调拨需要审批，请到后勤一站式系统中进行审批。" ;
				break;
			case "调拨审批驳回":
				smsTemp = "";
				break;
			case "报废申请":
				// 发送给科室的负责人
				smsTemp = "【资产报废申请】您的科室"+ deptMap.get("applyDeptName") + "有"+ deptMap.get("count") +"条资产需要进行报废，请到后勤一站式系统中进行审批。";
				break;
			case "报废申请驳回":
				smsTemp = "";
				break;
			case "报废分配":
				// 发送给对应科室的盘点员
				smsTemp = "【分配鉴定科室】您负责的科室"+ deptMap.get("identifyDeptName") +"有"+ deptMap.get("count") + "条资产需要进行鉴定科室分配，请到后勤一站式系统中进行分配。";
				break;
			case "报废鉴定":
				// 发送给对应鉴定科室的成员
				smsTemp = "【报废资产技术鉴定】您有"+ deptMap.get("count") + "条" + deptMap.get("applyDeptName") + "的资产需要进行鉴定，鉴定结果请到后勤一站式系统中进行填报。";
				break;
			case "报废鉴定驳回":
				smsTemp = "";
				break;
			case "报废归口":
				// 发送给对应归口科室的成员
				smsTemp = "【报废资产鉴定确认】您有"+ deptMap.get("count") + "条" + deptMap.get("applyDeptName") + "已经完成鉴定的资产需要确认，请到后勤一站式系统中进行确认。";
				break;
			case "报废归口驳回":
				smsTemp = "";
				break;
			case "报废审批":
				// 发送给郑科
				smsTemp = "【报废资产资产科审批】您有"+ deptMap.get("count") + "条" + deptMap.get("applyDeptName") + "已经完成报废流程的资产需要审批，请到后勤一站式系统中进行审批。";
				break;
			case "报废审批驳回":
				smsTemp = "";
				break;
			default:
				smsTemp = "";
		}
		// 获取需要发送企业消息的对象
		List<Map<String,String>> list = dao.query("CPDJ01.queryManByRole", new HashMap<String, String>() {{
			put("deptNo", deptMap.get("deptNo"));
			put("deptName", deptMap.get("deptName"));
		}});
		for (Map map : list) {
			workNoList.add((String) map.get("workNo"));
		}
		paramList.add(smsTemp);
		if (workNoList.isEmpty()){
			return false;
		} else {
			//发送企业微信
//			return BaseDockingUtils.pushWxMsg(workNoList, paramList,"TP00001", appCode);
			return true;
		}
	}
}