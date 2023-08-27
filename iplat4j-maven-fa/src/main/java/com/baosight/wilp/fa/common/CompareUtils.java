package com.baosight.wilp.fa.common;

import com.baosight.iplat4j.core.util.DateUtils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author zhaowei
 * @version 5.0.0
 * @date 2022年12月07日 20:43
 */
public class CompareUtils {
	private final static String DATE_FORMATTR_SECOND = "yyyy-MM-dd HH:mm:ss";
	private final static String DATE_FORMATTR = "yyyy-MM-dd";

	/**
	 * 比较两个实体属性值，返回一有差异的属性
	 *
	 * @param obj1 进行属性比较的对象1
	 * @param obj2 进行属性比较的对象2
	 * @return
	 */
	public static List<ComparisonResult> compareFields(Object obj1, Object obj2, Class clazz) throws Exception {
		try {
			List<String> ignoreList = getCompareFields(clazz);
			Map<String, String> descsMap = getFieldSwaggerValue(clazz);
			List<ComparisonResult> list = new ArrayList<>();

			// 只有两个对象都是同一类型的才有可比性
			if (obj1.getClass() == obj2.getClass()) {
				Class claz = obj1.getClass();
				// 获取object的属性描述
				PropertyDescriptor[] pds = Introspector.getBeanInfo(claz, Object.class).getPropertyDescriptors();
				for (PropertyDescriptor pd : pds) {
					String name = pd.getName();
					String label = descsMap.get(name);
					if (ignoreList != null && !ignoreList.contains(name)) {
						continue;
					}
					Method readMethod = pd.getReadMethod();
					Object o1 = readMethod.invoke(obj1);
					Object o2 = readMethod.invoke(obj2);
					String r1 = null, r2 = null;
					if (Objects.isNull(o1) && Objects.isNull(o2)) {
						continue;
					}
					if (null == o1 || null == o2) {
						setResult(o1, o2, name, label, list);
						continue;
					}
					if (compareField(o1, o2)) {
						setResult(o1, o2, name, label, list);
					}
				}
			} else {
				throw new Exception("对象类型不一致");
			}
			return list;
		} catch (Exception e) {
			throw new Exception("对象比较失败", e);
		}
	}

	public static List<ComparisonResult> compareFields(Object obj1, Object obj2, Class clazz, String id) throws Exception {
		try {
			List<String> ignoreList = getCompareFields(clazz);
			Map<String, String> descsMap = getFieldSwaggerValue(clazz);
			List<ComparisonResult> list = new ArrayList<>();

			// 只有两个对象都是同一类型的才有可比性
			if (obj1.getClass() == obj2.getClass()) {
				Class claz = obj1.getClass();
				// 获取object的属性描述
				PropertyDescriptor[] pds = Introspector.getBeanInfo(claz, Object.class).getPropertyDescriptors();
				for (PropertyDescriptor pd : pds) {
					String name = pd.getName();
					String label = descsMap.get(name);
					if (ignoreList != null && !ignoreList.contains(name)) {
						continue;
					}
					Method readMethod = pd.getReadMethod();
					Object o1 = readMethod.invoke(obj1);
					Object o2 = readMethod.invoke(obj2);
					String r1 = null, r2 = null;
					if (Objects.isNull(o1) && Objects.isNull(o2)) {
						continue;
					}
					if (null == o1 || null == o2) {
						setResult(o1, o2, name, label, list, id);
						continue;
					}
					if (compareField(o1, o2)) {
						setResult(o1, o2, name, label, list, id);
					}
				}
			} else {
				throw new Exception("对象类型不一致");
			}
			return list;
		} catch (Exception e) {
			throw new Exception("对象比较失败", e);
		}
	}

	public static List<ComparisonResult> compareFields(Object obj1, Object obj2, Class clazz, String id, String batchId) throws Exception {
		try {
			List<String> ignoreList = getCompareFields(clazz);
			Map<String, String> descsMap = getFieldSwaggerValue(clazz);
			List<ComparisonResult> list = new ArrayList<>();

			// 只有两个对象都是同一类型的才有可比性
			if (obj1.getClass() == obj2.getClass()) {
				Class claz = obj1.getClass();
				// 获取object的属性描述
				PropertyDescriptor[] pds = Introspector.getBeanInfo(claz, Object.class).getPropertyDescriptors();
				for (PropertyDescriptor pd : pds) {
					String name = pd.getName();
					String label = descsMap.get(name);
					if (ignoreList != null && !ignoreList.contains(name)) {
						continue;
					}
					Method readMethod = pd.getReadMethod();
					Object o1 = readMethod.invoke(obj1);
					Object o2 = readMethod.invoke(obj2);
					String r1 = null, r2 = null;
					if (Objects.isNull(o1) && Objects.isNull(o2)) {
						continue;
					}
					if (null == o1 || null == o2) {
						setResult(o1, o2, name, label, list, id, batchId);
						continue;
					}
					if (compareField(o1, o2)) {
						setResult(o1, o2, name, label, list, id, batchId);
					}
				}
			} else {
				throw new Exception("对象类型不一致");
			}
			return list;
		} catch (Exception e) {
			throw new Exception("对象比较失败", e);
		}
	}

	/**
	 * 获取比较的类中字段
	 *
	 * @param objectClass
	 * @return
	 */
	private static List<String> getCompareFields(Class objectClass) {
		Field[] fields = objectClass.getDeclaredFields();
		List<String> map = new ArrayList<>();
		for (Field f : fields) {
			boolean annotationPresent2 = f.isAnnotationPresent(AnjiDescription.class);
			if (annotationPresent2) {
				map.add(f.getName());
			}
		}
		return map;
	}

	/**
	 * 获取实体类字段的description
	 *
	 * @param clazz
	 * @return
	 */
	private static Map<String, String> getFieldSwaggerValue(Class clazz) {
		Field[] fields = clazz.getDeclaredFields();
		Map<String, String> map = new HashMap<>();
		for (Field f : fields) {
			boolean annotationPresent2 = f.isAnnotationPresent(AnjiDescription.class);
			if (annotationPresent2) {
				AnjiDescription name = f.getAnnotation(AnjiDescription.class);
				String nameStr = name.value();
				map.put(f.getName(), nameStr);
			}
		}
		return map;
	}

	private static void setResult(Object o1, Object o2, String name, String label, List<ComparisonResult> list, String id) {
		Object ot = null;
		String r1 = null, r2 = null;
		if (!Objects.isNull(o1)) {
			ot = o1;
		} else if (!Objects.isNull(o2)) {
			ot = o2;
		}
		if (ot instanceof String) {
			r1 = objectToString(o1);
			r2 = objectToString(o2);
		} else if (ot instanceof BigDecimal) {
			System.out.println("BigDecimal");
			r1 = objectToString(o1);
			r2 = objectToString(o2);
		} else if (ot instanceof Integer) {
			System.out.println("Integer");
			r1 = objectToString(o1);
			r2 = objectToString(o2);
		} else if (ot instanceof Boolean) {
			System.out.println("Boolean");
			r1 = booleanToString(o1);
			r2 = booleanToString(o2);
		} else if (ot instanceof Long) {
			System.out.println("Long");
			r1 = objectToString(o1);
			r2 = objectToString(o2);
		} else if (ot instanceof LocalDateTime) {
			System.out.println("LocalDateTime");
			r1 = localDateTimeToString(o1);
			r2 = localDateTimeToString(o2);
		} else if (ot instanceof Date) {
			System.out.println("Date");
			r1 = DateToString(o1);
			r2 = DateToString(o2);
		}
		ComparisonResult result = new ComparisonResult();
		result.setId(UUID.randomUUID().toString());
		result.setFaInfoId(id);
		result.setKey(name);
		result.setLabel(label);
		result.setPrevious(r1);
		result.setLater(r2);
		result.setTime(DateTimeFormatter.ofPattern(DATE_FORMATTR).format(LocalDateTime.now()));
		list.add(result);
	}

	private static void setResult(Object o1, Object o2, String name, String label, List<ComparisonResult> list, String id, String batchId) {
		Object ot = null;
		String r1 = null, r2 = null;
		if (!Objects.isNull(o1)) {
			ot = o1;
		} else if (!Objects.isNull(o2)) {
			ot = o2;
		}
		if (ot instanceof String) {
			r1 = objectToString(o1);
			r2 = objectToString(o2);
		} else if (ot instanceof BigDecimal) {
			System.out.println("BigDecimal");
			r1 = objectToString(o1);
			r2 = objectToString(o2);
		} else if (ot instanceof Integer) {
			System.out.println("Integer");
			r1 = objectToString(o1);
			r2 = objectToString(o2);
		} else if (ot instanceof Boolean) {
			System.out.println("Boolean");
			r1 = booleanToString(o1);
			r2 = booleanToString(o2);
		} else if (ot instanceof Long) {
			System.out.println("Long");
			r1 = objectToString(o1);
			r2 = objectToString(o2);
		} else if (ot instanceof LocalDateTime) {
			System.out.println("LocalDateTime");
			r1 = localDateTimeToString(o1);
			r2 = localDateTimeToString(o2);
		} else if (ot instanceof Date) {
			System.out.println("Date");
			r1 = DateToString(o1);
			r2 = DateToString(o2);
		}
		ComparisonResult result = new ComparisonResult();
		result.setId(UUID.randomUUID().toString());
		result.setBatchId(batchId);
		result.setFaInfoId(id);
		result.setKey(name);
		result.setLabel(label);
		result.setPrevious(r1);
		result.setLater(r2);
		result.setTime(DateTimeFormatter.ofPattern(DATE_FORMATTR_SECOND).format(LocalDateTime.now()));
		list.add(result);
	}

	private static void setResult(Object o1, Object o2, String name, String label, List<ComparisonResult> list) {
		Object ot = null;
		String r1 = null, r2 = null;
		if (!Objects.isNull(o1)) {
			ot = o1;
		} else if (!Objects.isNull(o2)) {
			ot = o2;
		}
		if (ot instanceof String) {
			r1 = objectToString(o1);
			r2 = objectToString(o2);
		} else if (ot instanceof BigDecimal) {
			System.out.println("BigDecimal");
			r1 = objectToString(o1);
			r2 = objectToString(o2);
		} else if (ot instanceof Integer) {
			System.out.println("Integer");
			r1 = objectToString(o1);
			r2 = objectToString(o2);
		} else if (ot instanceof Boolean) {
			System.out.println("Boolean");
			r1 = booleanToString(o1);
			r2 = booleanToString(o2);
		} else if (ot instanceof Long) {
			System.out.println("Long");
			r1 = objectToString(o1);
			r2 = objectToString(o2);
		} else if (ot instanceof LocalDateTime) {
			System.out.println("LocalDateTime");
			r1 = localDateTimeToString(o1);
			r2 = localDateTimeToString(o2);
		}
		ComparisonResult result = new ComparisonResult();
		result.setId(UUID.randomUUID().toString());
		result.setFaInfoId("");
		result.setKey(name);
		result.setLabel(label);
		result.setPrevious(r1);
		result.setLater(r2);
		result.setTime(DateTimeFormatter.ofPattern(DATE_FORMATTR_SECOND).format(LocalDateTime.now()));
		list.add(result);
	}

	private static boolean compareField(Object o1, Object o2) {
		Boolean sign = false;
		Object ot = null;
		if (!Objects.isNull(o1)) {
			ot = o1;
		} else if (!Objects.isNull(o2)) {
			ot = o2;
		}
		if (ot instanceof String) {
			sign = !o1.equals(o2);
		} else if (ot instanceof BigDecimal) {
			System.out.println("BigDecimal");
			sign = ((BigDecimal) o1).compareTo((BigDecimal) o2) != 0 ? true : false;
		} else if (ot instanceof Integer) {
			System.out.println("Integer");
			sign = ((Integer) o1).compareTo((Integer) o2) != 0 ? true : false;
		} else if (ot instanceof Boolean) {
			System.out.println("Boolean");
			sign = ((Boolean) o1).compareTo((Boolean) o2) != 0 ? true : false;
		} else if (ot instanceof Long) {
			System.out.println("Long");
			sign = ((Long) o1).compareTo((Long) o2) != 0 ? true : false;
		} else if (ot instanceof LocalDateTime) {
			System.out.println("LocalDateTime");
			sign = ((LocalDateTime) o1).compareTo((LocalDateTime) o2) != 0 ? true : false;
		} else if (ot instanceof Date) {
			System.out.println("Date");
			sign = ((Date) o1).compareTo((Date) o2) != 0 ? true : false;
		}

		return sign;
	}

	private static String objectToString(Object obj) {
		if (null == obj) {
			return "";
		} else {
			return obj.toString();
		}
	}

	private static String booleanToString(Object obj) {
		if (null == obj) {
			return null;
		} else {
			return (Boolean) obj ? "是" : "否";
		}
	}

	private static String localDateTimeToString(Object obj) {
		if (null == obj) {
			return null;
		} else {
			DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_FORMATTR_SECOND);
			LocalDateTime ld = LocalDateTime.now();
			return df.format(ld);
		}
	}

	private static String DateToString(Object obj) {
		if (null == obj) {
			return null;
		} else {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMATTR);
			return simpleDateFormat.format(obj);
		}
	}
}
