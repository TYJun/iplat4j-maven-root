package com.baosight.wilp.fa.zj.service;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.data.ibatis.dao.SqlMapDao;
import com.baosight.iplat4j.core.data.ibatis.dao.SqlMapDaoLogProxy;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.log.LogUtils;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.fa.da.domain.FaInfoDO;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import com.baosight.wilp.fa.zj.domain.FaDepreciation;
import com.baosight.wilp.fa.zj.domain.FaDepreciationDTO;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 固定资产折旧管理接口API.
 * 按月度进行折旧.
 * 判断是否存在固定资产卡片信息.
 * 判断固定资产使用月份.
 * 开始折旧月份 <= 折旧月份 < 最后一次折旧月份.
 * 折旧月份 >= 最后一次折旧月份.
 * 不存在折旧记录，计算第一次折旧，部分折旧.
 * 发生资产变更，重新计算月折旧.
 * 不发生资产变更，复制之前的月折旧.
 * 不存在折旧记录，全部折旧.
 * 发生资产变更，重新计算月折旧，计算最后一次折旧.
 * 不发生资产变更，复制之前的月折旧，计算最后一次折旧.
 * 判断资产是否进行了变更，折旧表中的 资产净值 + 累计折旧 = 当前固定资产表中的 资产原值或暂估资产.
 * 暂估资产与资产原值,存在资产原值则以原值为主 都为0也折旧，保存折旧记录.
 * 整月折旧 = 资产原值/使用年限/12.
 * 非整月折旧 = 月折旧 * 天数 / 当前月天数.
 * 非整月折旧 = 月折旧 * 天数 / 当前月天数
 * 计算固定资产使用月份
 * 计算折旧月份通过月份
 * 月份相减 = 购入日期与当前日期相差几个月
 * 存在固定资产折旧信息
 * 获取符合要求的资产折旧信息
 * 计算固定资产第一个月折旧的年月
 * 计算固定资产最后折旧的年月
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2022年09月21日 21:04
 */
public class ServiceFAZJ01 extends ServiceBase {
	// 引入日志类
	private static final Logger LOG = LoggerFactory.getLogger(LogUtils.class);
	// 定义时间转化格式
	private static final SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyy-MM");
	private static final SimpleDateFormat yyyyMM1 = new SimpleDateFormat("yyyyMM");
	private static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
	private static final int THREADS = Runtime.getRuntime().availableProcessors();

	/*
	 * 按月度进行折旧
	 * 0.通过数据库先获取固定资产卡片信息和固定资产折旧信息
	 * 1.不存在固定资产卡片信息，直接跳过
	 * 2.存在固定资产卡片信息
	 * 2.1.折旧月份 < 开始折旧月份，提示使用时间为负error，直接跳过
	 * 2.2.开始折旧月份 <= 折旧月份 < 最后一次折旧月份
	 * 2.2.1.不存在折旧记录，计算第一次折旧，部分折旧
	 * 2.2.2.存在折旧记录
	 * 2.2.2.1.存在符合要求的记录
	 * 2.2.2.1.1.发生资产变更，重新计算月折旧
	 * 2.2.2.1.2.不发生资产变更，复制之前的月折旧
	 * 2.3.折旧月份 >= 最后一次折旧月份
	 * 2.3.1.不存在折旧记录，全部折旧
	 * 2.3.2.存在折旧记录
	 * 2.3.2.1.存在符合要求的记录
	 * 2.3.2.1.1.发生资产变更，重新计算月折旧，计算最后一次折旧
	 * 2.3.2.1.2.不发生资产变更，复制之前的月折旧，计算最后一次折旧
	 * 2.4.使用时间 > 最后一次折旧时间
	 * 2.4.1.不存在折旧记录，全部折旧
	 * 2.4.2.存在折旧记录
	 * 2.4.2.1.不存在符合要求的记录，全部折旧
	 * 2.4.2.2.存在符合要求的记录
	 * 2.4.2.2.1.发生资产变更，重新计算月折旧，计算最后一次折旧
	 * 2.4.2.2.2.不发生资产变更，复制之前的月折旧，计算最后一次折旧
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/9/21 20:21
	 * @version 5.0.0
	 */
	public EiInfo monthlyDEPOld(EiInfo info) {
		/*
		 * 0.通过数据库先获取固定资产卡片信息和固定资产折旧信息
		 */
		List<FaInfoDO> queryFaInfoList = dao.query("FAZJ01.queryFaInfo", new HashMap<>());
		List<FaDepreciation> queryFaDepreciationList = dao.query("FAZJ01.queryFaDepreciation", new HashMap<>());
		// 判断是否存在固定资产卡片信息
		if (CollectionUtils.isNotEmpty(queryFaInfoList)) {
			// 2.存在固定资产卡片信息
			existFaInfo(queryFaInfoList, queryFaDepreciationList);
		}
		// 1.不存在固定资产卡片信息，直接跳过
		return info;
	}

	/**
	 * 2.进行固定资产卡片折旧
	 *
	 * @param queryFaInfoList
	 * @param queryFaDepreciationList
	 * @author zhaowei
	 * @date 2022/10/12 13:58
	 * @version v5.0.0
	 */
	public void existFaInfo(List<FaInfoDO> queryFaInfoList, List<FaDepreciation> queryFaDepreciationList) {
		// 遍历固定资产信息，存在则判断固定资产使用月份
		for (int i = 0; i < queryFaInfoList.size(); i++) {
			FaInfoDO faInfo = queryFaInfoList.get(i);
			faUsedMonth(faInfo, queryFaDepreciationList);
		}
	}

	/**
	 * 判断固定资产使用月份
	 *
	 * @param faInfo
	 * @param queryFaDepreciationList
	 * @author zhaowei
	 * @date 2022/10/12 14:03
	 */
	public void faUsedMonth(FaInfoDO faInfo, List<FaDepreciation> queryFaDepreciationList) {
		Date buyDate = faInfo.getBuyDate();
		if (buyDate == null) {
			return;
		}
		Integer useYears = faInfo.getUseYears();
		if (useYears == null) {
			return;
		}
		Integer nowDate = Integer.valueOf(yyyyMM1.format(new Date()));
		Integer firstDEPDate = Integer.valueOf(countFirstDEPDate(buyDate));
		Integer lastDEPDate = Integer.valueOf(countLastDEPDate(buyDate, useYears));
		// 2.1.折旧月份 < 开始折旧月份
		// 2.2.开始折旧月份 <= 折旧月份 < 最后一次折旧月份
		// 2.3.折旧月份 >= 最后一次折旧月份
		if (nowDate < firstDEPDate) {
			LOG.info("使用时间为负数！");
		} else if (firstDEPDate <= nowDate && nowDate < lastDEPDate) {
			betweenDEP(faInfo, queryFaDepreciationList);
		} else {
			afterDEP(faInfo, queryFaDepreciationList);
		}
	}

	/**
	 * 2.2.开始折旧月份 <= 折旧月份 < 最后一次折旧月份
	 *
	 * @param faInfo
	 * @param queryFaDepreciationList
	 * @author zhaowei
	 * @date 2022/10/12 14:03
	 */
	public void betweenDEP(FaInfoDO faInfo, List<FaDepreciation> queryFaDepreciationList) {
		String faInfoId = faInfo.getId();
		FaDepreciation faDepreciation = getFaDepreciation(faInfoId, queryFaDepreciationList);
		if (faDepreciation == null) {
			// 2.2.1.不存在折旧记录，计算第一次折旧，部分折旧
			partDEP(faInfo);
		} else {
			BigDecimal netAssetValue = faDepreciation.getNetAssetValue();
			if (netAssetValue.compareTo(BigDecimal.ZERO) == 0) {
				return;
			}
			// 2.2.2.1.存在符合要求的记录
			Boolean whetherToChange = whetherToChange(faInfo, faDepreciation);
			if (whetherToChange) {
				// 2.2.2.1.1.发生资产变更，重新计算月折旧
				partEDPexistRecordChange(faInfo, faDepreciation);
			} else {
				// 2.2.2.1.2.不发生资产变更，复制之前的月折旧
				partEDPexistRecordCopy(faInfo, faDepreciation);
			}
		}
	}

	/**
	 * 2.3.折旧月份 >= 最后一次折旧月份
	 *
	 * @param faInfo
	 * @param queryFaDepreciationList
	 * @author zhaowei
	 * @date 2022/10/12 14:03
	 */
	public void afterDEP(FaInfoDO faInfo, List<FaDepreciation> queryFaDepreciationList) {
		String faInfoId = faInfo.getId();
		FaDepreciation faDepreciation = getFaDepreciation(faInfoId, queryFaDepreciationList);
		if (faDepreciation == null) {
			// 2.3.1.不存在折旧记录，全部折旧
			completeDEP(faInfo);
		} else {
			BigDecimal netAssetValue = faDepreciation.getNetAssetValue();
			if (netAssetValue.compareTo(BigDecimal.ZERO) == 0) {
				return;
			}
			// 2.3.2.1.存在符合要求的记录
			Boolean whetherToChange = whetherToChange(faInfo, faDepreciation);
			if (whetherToChange) {
				// 2.3.2.1.1.发生资产变更，重新计算月折旧，计算最后一次折旧
				commonEDPexistRecordChange(faInfo, faDepreciation);
			} else {
				// 2.3.2.1.2.不发生资产变更，复制之前的月折旧，计算最后一次折旧
				commonEDPexistRecordCopy(faInfo, faDepreciation);
			}
		}
	}

	/**
	 * 2.2.1.不存在折旧记录，计算第一次折旧，部分折旧
	 *
	 * @param faInfo
	 * @author zhaowei
	 * @date 2022/10/12 14:03
	 */
	public void partDEP(FaInfoDO faInfo) {
		Date buyDate = faInfo.getBuyDate();
		String firstDepreciationMonth = getDepreciationMonthByMonth(buyDate, 1);
		String faInfoId = faInfo.getId();
		Integer usedMonth = Integer.valueOf((int) monthSubtract(faInfo)) - 1;
		BigDecimal assetValue = getAssetValue(faInfo);
		BigDecimal depreciationValue = completeMonthDEP(faInfo);
		BigDecimal firstDepreciationValue = noCompleteMonthDEP(depreciationValue, faInfo);
		List<FaDepreciation> faDepreciations = new ArrayList<>();
		// 第一次折旧
		FaDepreciation firstFaDepreciation = new FaDepreciation();
		firstFaDepreciation.setId(UUID.randomUUID().toString().replace("-", ""));
		firstFaDepreciation.setGoodId(faInfoId);
		firstFaDepreciation.setDepreciationMonth(firstDepreciationMonth);
		firstFaDepreciation.setUsedMonth(1);
		firstFaDepreciation.setDepreciationValue(firstDepreciationValue);
		firstFaDepreciation.setTotalDepreciation(firstDepreciationValue);
		firstFaDepreciation.setNetAssetValue(assetValue.subtract(firstDepreciationValue));
		faDepreciations.add(firstFaDepreciation);
		// 普通折旧
		for (int j = 0; j < usedMonth; j++) {
			FaDepreciation newFaDepreciation = new FaDepreciation();
			String depreciationMonth = getDepreciationMonthByMonth(buyDate, j + 2);
			newFaDepreciation.setId(UUID.randomUUID().toString().replace("-", ""));
			newFaDepreciation.setGoodId(faInfoId);
			newFaDepreciation.setDepreciationMonth(depreciationMonth);
			newFaDepreciation.setUsedMonth(j + 2);
			newFaDepreciation.setDepreciationValue(depreciationValue);
			newFaDepreciation.setTotalDepreciation(firstDepreciationValue.add(depreciationValue.multiply(new BigDecimal(j + 1))));
			newFaDepreciation.setNetAssetValue(assetValue.subtract(firstDepreciationValue).subtract(depreciationValue.multiply(new BigDecimal(j + 1))));
			newFaDepreciation.setMonthDepreciation(depreciationValue);
			faDepreciations.add(newFaDepreciation);
		}
		dao.insert("FAZJ01.completeDEP", faDepreciations);
		dao.update("FAZJ01.updateDEP", faDepreciations.get(faDepreciations.size() - 1));
	}

	/**
	 * 2.2.2.1.1.发生资产变更，重新计算月折旧
	 *
	 * @param faInfo
	 * @param faDepreciation
	 * @author zhaowei
	 * @date 2022/10/12 14:03
	 */
	public void partEDPexistRecordChange(FaInfoDO faInfo, FaDepreciation faDepreciation) {
		List<FaDepreciation> faDepreciations = new ArrayList<>();
		String faInfoId = faInfo.getId();
		String depreciationMonth = faDepreciation.getDepreciationMonth();
		// 现在的资产价值
		BigDecimal assetValue = getAssetValue(faInfo);
		// 现在的资产价值 - 之前的累计折旧 = 现在的资产净值
		BigDecimal totalDepreciation = faDepreciation.getTotalDepreciation();
		BigDecimal nowNetAssetValue = assetValue.subtract(totalDepreciation);
		// 现在的资产净值 / 剩余月份 = 月折旧
		Integer alreadyUsedMonth = faDepreciation.getUsedMonth();
		Integer useYears = faInfo.getUseYears();
		Integer remainMonth = useYears * 12 - alreadyUsedMonth;
		Integer usedMonth = Integer.valueOf((int) monthSubtract(faInfo));
		Integer toBeUsedMonth = usedMonth - alreadyUsedMonth;
		BigDecimal depreciationValue = nowNetAssetValue.divide(new BigDecimal(remainMonth), BigDecimal.ROUND_CEILING).setScale(1, BigDecimal.ROUND_DOWN);
		// 剔除最后一个月，和已经折旧的月份
		// 构建新的集合
		for (int j = 0; j < toBeUsedMonth; j++) {
			FaDepreciation newFaDepreciation = new FaDepreciation();
			String nowDepreciationMonth = getDepreciationMonthByMonth(depreciationMonth, j + 1);
			newFaDepreciation.setId(UUID.randomUUID().toString().replace("-", ""));
			newFaDepreciation.setGoodId(faInfoId);
			newFaDepreciation.setDepreciationMonth(nowDepreciationMonth);
			newFaDepreciation.setUsedMonth(alreadyUsedMonth + j + 1);
			newFaDepreciation.setDepreciationValue(depreciationValue);
			newFaDepreciation.setTotalDepreciation(totalDepreciation.add(depreciationValue.multiply(new BigDecimal(j + 1))));
			newFaDepreciation.setNetAssetValue(assetValue.subtract(depreciationValue.multiply(new BigDecimal(j + 1))).subtract(totalDepreciation));
			newFaDepreciation.setMonthDepreciation(depreciationValue);
			faDepreciations.add(newFaDepreciation);
		}
		dao.insert("FAZJ01.completeDEP", faDepreciations);
		dao.update("FAZJ01.updateDEP", faDepreciations.get(faDepreciations.size() - 1));
	}

	/**
	 * 2.2.2.1.2.不发生资产变更，复制之前的月折旧
	 *
	 * @param faInfo
	 * @param faDepreciation
	 * @author zhaowei
	 * @date 2022/10/12 14:03
	 */
	public void partEDPexistRecordCopy(FaInfoDO faInfo, FaDepreciation faDepreciation) {
		List<FaDepreciation> faDepreciations = new ArrayList<>();
		String faInfoId = faInfo.getId();
		BigDecimal depreciationValue = completeMonthDEP(faInfo);
		String depreciationMonth = faDepreciation.getDepreciationMonth();
		BigDecimal totalDepreciation = faDepreciation.getTotalDepreciation();
		BigDecimal netAssetValue = faDepreciation.getNetAssetValue();
		Integer usedMonth = Integer.valueOf((int) monthSubtract(faInfo));
		Integer alreadyUsedMonth = faDepreciation.getUsedMonth();
		Integer toBeUsedMonth = usedMonth - alreadyUsedMonth;
		for (int j = 0; j < toBeUsedMonth; j++) {
			FaDepreciation newFaDepreciation = new FaDepreciation();
			String nowDepreciationMonth = getDepreciationMonthByMonth(depreciationMonth, j + 1);
			newFaDepreciation.setId(UUID.randomUUID().toString().replace("-", ""));
			newFaDepreciation.setGoodId(faInfoId);
			newFaDepreciation.setDepreciationMonth(nowDepreciationMonth);
			newFaDepreciation.setUsedMonth(alreadyUsedMonth + j + 1);
			newFaDepreciation.setDepreciationValue(depreciationValue);
			newFaDepreciation.setTotalDepreciation(totalDepreciation.add(depreciationValue.multiply(new BigDecimal(j + 1))));
			newFaDepreciation.setNetAssetValue(netAssetValue.subtract(depreciationValue.multiply(new BigDecimal(j + 1))));
			newFaDepreciation.setMonthDepreciation(depreciationValue);
			faDepreciations.add(newFaDepreciation);
		}
		dao.insert("FAZJ01.completeDEP", faDepreciations);
		dao.update("FAZJ01.updateDEP", faDepreciations.get(faDepreciations.size() - 1));
	}

	/**
	 * 2.3.1.不存在折旧记录，全部折旧
	 *
	 * @param faInfo
	 * @author zhaowei
	 * @date 2022/10/12 14:03
	 */
	public void completeDEP(FaInfoDO faInfo) {
		Date buyDate = faInfo.getBuyDate();
		String firstDepreciationMonth = getDepreciationMonthByMonth(buyDate, 1);
		String faInfoId = faInfo.getId();
		Integer useYears = faInfo.getUseYears();
		Integer useMonths = useYears * 12;
		Integer remainMonth = useMonths - 2;
		BigDecimal assetValue = getAssetValue(faInfo);
		BigDecimal depreciationValue = completeMonthDEP(faInfo);
		BigDecimal firstDepreciationValue = noCompleteMonthDEP(depreciationValue, faInfo);
		int flag = firstDepreciationValue.compareTo(depreciationValue);
		switch (flag) {
			case -1:
				remainMonth += 1;
				useMonths += 1;
				break;
			case 0:
				break;
			case 1:
				break;
		}
		BigDecimal completeDepreciationValue = depreciationValue.multiply(new BigDecimal(remainMonth));
		BigDecimal lastDepreciationValue = assetValue.subtract(completeDepreciationValue).subtract(firstDepreciationValue);
		List<FaDepreciation> faDepreciations = new ArrayList<>();
		// 第一次折旧
		FaDepreciation firstFaDepreciation = new FaDepreciation();
		firstFaDepreciation.setId(UUID.randomUUID().toString().replace("-", ""));
		firstFaDepreciation.setGoodId(faInfoId);
		firstFaDepreciation.setDepreciationMonth(firstDepreciationMonth);
		firstFaDepreciation.setUsedMonth(1);
		firstFaDepreciation.setDepreciationValue(firstDepreciationValue);
		firstFaDepreciation.setTotalDepreciation(firstDepreciationValue);
		firstFaDepreciation.setNetAssetValue(assetValue.subtract(firstDepreciationValue));
		faDepreciations.add(firstFaDepreciation);
		// 普通折旧
		for (int j = 0; j < remainMonth; j++) {
			FaDepreciation newFaDepreciation = new FaDepreciation();
			String depreciationMonth = getDepreciationMonthByMonth(buyDate, j + 2);
			newFaDepreciation.setId(UUID.randomUUID().toString().replace("-", ""));
			newFaDepreciation.setGoodId(faInfoId);
			newFaDepreciation.setDepreciationMonth(depreciationMonth);
			newFaDepreciation.setUsedMonth(j + 2);
			newFaDepreciation.setDepreciationValue(depreciationValue);
			newFaDepreciation.setTotalDepreciation(firstDepreciationValue.add(depreciationValue.multiply(new BigDecimal(j + 1))));
			newFaDepreciation.setNetAssetValue(assetValue.subtract(firstDepreciationValue).subtract(depreciationValue.multiply(new BigDecimal(j + 1))));
			faDepreciations.add(newFaDepreciation);
		}
		// 最后一次折旧
		FaDepreciation lastFaDepreciation = new FaDepreciation();
		String lastDepreciationMonth = getDepreciationMonthByMonth(buyDate, remainMonth + 2);
		lastFaDepreciation.setId(UUID.randomUUID().toString().replace("-", ""));
		lastFaDepreciation.setGoodId(faInfoId);
		lastFaDepreciation.setDepreciationMonth(lastDepreciationMonth);
		lastFaDepreciation.setUsedMonth(useMonths);
		lastFaDepreciation.setDepreciationValue(lastDepreciationValue);
		lastFaDepreciation.setTotalDepreciation(assetValue);
		lastFaDepreciation.setNetAssetValue(new BigDecimal(0.00));
		lastFaDepreciation.setMonthDepreciation(depreciationValue);
		faDepreciations.add(lastFaDepreciation);
		dao.insert("FAZJ01.completeDEP", faDepreciations);
		dao.update("FAZJ01.updateDEP", lastFaDepreciation);
	}

	/**
	 * 2.3.2.1.1.发生资产变更，重新计算月折旧，计算最后一次折旧
	 *
	 * @param faInfo
	 * @param faDepreciation
	 * @author zhaowei
	 * @date 2022/10/12 14:04
	 */
	public void commonEDPexistRecordChange(FaInfoDO faInfo, FaDepreciation faDepreciation) {
		List<FaDepreciation> faDepreciations = new ArrayList<>();
		String faInfoId = faInfo.getId();
		String depreciationMonth = faDepreciation.getDepreciationMonth();
		// 现在的资产价值
		BigDecimal assetValue = getAssetValue(faInfo);
		// 现在的资产价值 - 之前的累计折旧 = 现在的资产净值
		BigDecimal totalDepreciation = faDepreciation.getTotalDepreciation();
		BigDecimal nowNetAssetValue = assetValue.subtract(totalDepreciation);
		// 现在的资产净值 / 剩余月份 = 月折旧
		Integer usedMonth = faDepreciation.getUsedMonth();
		Integer useYears = faInfo.getUseYears();
		Integer usedMonths = useYears * 12;
		Integer remainMonth = usedMonths - usedMonth;
		BigDecimal newDepreciationValue = nowNetAssetValue.divide(new BigDecimal(remainMonth), BigDecimal.ROUND_CEILING).setScale(1, BigDecimal.ROUND_DOWN);
		BigDecimal depreciationValue = completeMonthDEP(faInfo);
		BigDecimal firstDepreciationValue = noCompleteMonthDEP(depreciationValue, faInfo);
		// 剔除最后一个月，和已经折旧的月份
		int flag = firstDepreciationValue.compareTo(depreciationValue);
		switch (flag) {
			case -1:
				usedMonths += 1;
				break;
			case 0:
				remainMonth -= 1;
				break;
			case 1:
				break;
		}
		// 构建新的集合
		for (int j = 0; j < remainMonth; j++) {
			FaDepreciation newFaDepreciation = new FaDepreciation();
			String nowDepreciationMonth = getDepreciationMonthByMonth(depreciationMonth, j + 1);
			newFaDepreciation.setId(UUID.randomUUID().toString().replace("-", ""));
			newFaDepreciation.setGoodId(faInfoId);
			newFaDepreciation.setDepreciationMonth(nowDepreciationMonth);
			newFaDepreciation.setUsedMonth(usedMonth + j + 1);
			newFaDepreciation.setDepreciationValue(newDepreciationValue);
			newFaDepreciation.setTotalDepreciation(totalDepreciation.add(newDepreciationValue.multiply(new BigDecimal(j + 1))));
			newFaDepreciation.setNetAssetValue(assetValue.subtract(newDepreciationValue.multiply(new BigDecimal(j + 1))).subtract(totalDepreciation));
			faDepreciations.add(newFaDepreciation);
		}
		// 最后一个月的折旧
		String lastDepreciationMonth = getDepreciationMonthByMonth(depreciationMonth, remainMonth + 1);
		FaDepreciation newFaDepreciation = new FaDepreciation();
		newFaDepreciation.setId(UUID.randomUUID().toString().replace("-", ""));
		newFaDepreciation.setGoodId(faInfoId);
		newFaDepreciation.setDepreciationMonth(lastDepreciationMonth);
		newFaDepreciation.setUsedMonth(usedMonths);
		newFaDepreciation.setDepreciationValue(assetValue.subtract(newDepreciationValue.multiply(new BigDecimal(remainMonth))).subtract(totalDepreciation));
		newFaDepreciation.setTotalDepreciation(assetValue);
		newFaDepreciation.setNetAssetValue(new BigDecimal(0.00));
		newFaDepreciation.setMonthDepreciation(depreciationValue);
		faDepreciations.add(newFaDepreciation);
		dao.insert("FAZJ01.completeDEP", faDepreciations);
		dao.update("FAZJ01.updateDEP", faDepreciations.get(faDepreciations.size() - 1));
	}

	/**
	 * 2.3.2.1.2.不发生资产变更，复制之前的月折旧，计算最后一次折旧
	 *
	 * @param faInfo
	 * @param faDepreciation
	 * @author zhaowei
	 * @date 2022/10/12 14:04
	 */
	public void commonEDPexistRecordCopy(FaInfoDO faInfo, FaDepreciation faDepreciation) {
		List<FaDepreciation> faDepreciations = new ArrayList<>();
		String faInfoId = faInfo.getId();
		BigDecimal assetValue = getAssetValue(faInfo);
		BigDecimal depreciationValue = completeMonthDEP(faInfo);
		String depreciationMonth = faDepreciation.getDepreciationMonth();
		BigDecimal firstDepreciationValue = noCompleteMonthDEP(depreciationValue, faInfo);
		BigDecimal totalDepreciation = faDepreciation.getTotalDepreciation();
		BigDecimal netAssetValue = faDepreciation.getNetAssetValue();
		Integer usedMonth = faDepreciation.getUsedMonth();
		Integer useYears = faInfo.getUseYears();
		Integer usedMonths = useYears * 12;
		// 剔除已经折旧的月份
		int remainMonth = usedMonths - usedMonth;
		int flag = firstDepreciationValue.compareTo(depreciationValue);
		switch (flag) {
			case -1:
				usedMonths += 1;
				break;
			case 0:
				remainMonth -= 1;
				break;
			case 1:
				break;
		}
		for (int j = 0; j < remainMonth; j++) {
			FaDepreciation newFaDepreciation = new FaDepreciation();
			String nowDepreciationMonth = getDepreciationMonthByMonth(depreciationMonth, j + 1);
			newFaDepreciation.setId(UUID.randomUUID().toString().replace("-", ""));
			newFaDepreciation.setGoodId(faInfoId);
			newFaDepreciation.setDepreciationMonth(nowDepreciationMonth);
			newFaDepreciation.setUsedMonth(usedMonth + j + 1);
			newFaDepreciation.setDepreciationValue(depreciationValue);
			newFaDepreciation.setTotalDepreciation(totalDepreciation.add(depreciationValue.multiply(new BigDecimal(j + 1))));
			newFaDepreciation.setNetAssetValue(netAssetValue.subtract(depreciationValue.multiply(new BigDecimal(j + 1))));
			faDepreciations.add(newFaDepreciation);
		}
		// 最后一个月的折旧
		String lastDepreciationMonth = getDepreciationMonthByMonth(depreciationMonth, remainMonth + 1);
		FaDepreciation newFaDepreciation = new FaDepreciation();
		newFaDepreciation.setId(UUID.randomUUID().toString().replace("-", ""));
		newFaDepreciation.setGoodId(faInfoId);
		newFaDepreciation.setDepreciationMonth(lastDepreciationMonth);
		newFaDepreciation.setUsedMonth(usedMonths);
		newFaDepreciation.setDepreciationValue(assetValue.subtract(depreciationValue.multiply(new BigDecimal(remainMonth)).add(totalDepreciation)));
		newFaDepreciation.setTotalDepreciation(assetValue);
		newFaDepreciation.setNetAssetValue(new BigDecimal(0.00));
		newFaDepreciation.setMonthDepreciation(depreciationValue);
		faDepreciations.add(newFaDepreciation);
		dao.insert("FAZJ01.completeDEP", faDepreciations);
		dao.update("FAZJ01.updateDEP", faDepreciations.get(faDepreciations.size() - 1));
	}

	/**
	 * 判断资产是否进行了变更，折旧表中的 资产净值 + 累计折旧 = 当前固定资产表中的 资产原值或暂估资产
	 *
	 * @param faInfo
	 * @param faDepreciation
	 * @return java.lang.Boolean
	 * @author zhaowei
	 * @date 2022/10/12 14:04
	 */
	public Boolean whetherToChange(FaInfoDO faInfo, FaDepreciation faDepreciation) {
		BigDecimal totalDepreciation = faDepreciation.getTotalDepreciation();
		BigDecimal netAssetValue = faDepreciation.getNetAssetValue();
		BigDecimal assetValue = getAssetValue(faInfo);
		// 固定资产总值未变动
		if (assetValue.compareTo(totalDepreciation.add(netAssetValue)) == 0) {
			return false;
		} else {
			// 固定资产总值发生变动
			return true;
		}
	}

	/**
	 * 暂估资产与资产原值,存在资产原值则以原值为主 都为0也折旧，保存折旧记录
	 *
	 * @param faInfo
	 * @return java.math.BigDecimal
	 * @author zhaowei
	 * @date 2022/10/12 14:04
	 */
	public BigDecimal getAssetValue(FaInfoDO faInfo) {
		BigDecimal buyCost = faInfo.getBuyCost();
		BigDecimal estimateCost = faInfo.getEstimateCost();
		if (buyCost.compareTo(BigDecimal.ZERO) == 0 && estimateCost.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		} else if (buyCost.compareTo(BigDecimal.ZERO) == 0) {
			return estimateCost;
		} else {
			return buyCost;
		}
	}

	/**
	 * 整月折旧 = 资产原值/使用年限/12
	 *
	 * @param faInfo
	 * @return java.math.BigDecimal
	 * @author zhaowei
	 * @date 2022/10/12 14:04
	 */
	public BigDecimal completeMonthDEP(FaInfoDO faInfo) {
		BigDecimal assetValue = getAssetValue(faInfo);
		return assetValue.divide(new BigDecimal(faInfo.getUseYears()), BigDecimal.ROUND_CEILING).divide(new BigDecimal(12), BigDecimal.ROUND_CEILING).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 非整月折旧 = 月折旧 * 天数 / 当前月天数
	 *
	 * @param completeMonthDEP
	 * @param daySubtract
	 * @param days
	 * @return java.math.BigDecimal
	 * @author zhaowei
	 * @date 2022/10/12 14:05
	 */
	public BigDecimal noCompleteMonthDEP(BigDecimal completeMonthDEP, long daySubtract, int days) {
		BigDecimal multiply = completeMonthDEP.multiply(new BigDecimal(daySubtract));
		return multiply.divide(new BigDecimal(days), BigDecimal.ROUND_CEILING).setScale(2, BigDecimal.ROUND_UP);
	}

	/**
	 * 非整月折旧 = 月折旧 * 天数 / 当前月天数
	 *
	 * @param completeMonthDEP
	 * @param faInfo
	 * @return java.math.BigDecimal
	 * @author zhaowei
	 * @date 2022/10/12 14:05
	 */
	public BigDecimal noCompleteMonthDEP(BigDecimal completeMonthDEP, FaInfoDO faInfo) {
		LocalDate buyDate = LocalDate.parse(yyyyMMdd.format(faInfo.getBuyDate()));
		LocalDate localDate = buyDate.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
		int days = buyDate.lengthOfMonth();
		return noCompleteMonthDEP(completeMonthDEP, buyDate.until(localDate, ChronoUnit.DAYS), days);
	}

	/**
	 * 计算固定资产使用月份
	 *
	 * @param buyDate
	 * @param useYears
	 * @return java.lang.Integer
	 * @author zhaowei
	 * @date 2022/10/12 14:05
	 */
	public Integer countUsedMonth(Date buyDate, Integer useYears) {
		String buyDateStr = yyyyMMdd.format(buyDate);
		LocalDate buyDEPDate = LocalDate.parse(buyDateStr);
		LocalDate deadline = buyDEPDate.plusYears(useYears);
		LocalDate lastDEPDate = deadline.with(TemporalAdjusters.firstDayOfMonth());
		if (lastDEPDate.isEqual(deadline)) {
			return useYears * 12;
		} else {
			return useYears * 12 + 1;
		}
	}

	/**
	 * 计算折旧月份通过月份
	 *
	 * @param depreciationMonth
	 * @param amount
	 * @return java.lang.String
	 * @author zhaowei
	 * @date 2022/10/12 14:05
	 */
	public String getDepreciationMonthByMonth(String depreciationMonth, int amount) {
		Calendar instance = Calendar.getInstance();
		try {
			Date date = yyyyMM.parse(depreciationMonth);
			instance.setTime(date);
		} catch (Exception e) {
			LOG.error("报错" + e);
		}
		instance.add(Calendar.MONTH, amount);
		Date time = instance.getTime();
		return yyyyMM.format(time);
	}

	/**
	 * 计算折旧月份通过月份
	 *
	 * @param date
	 * @param amount
	 * @return java.lang.String
	 * @author zhaowei
	 * @date 2022/10/12 14:05
	 */
	public String getDepreciationMonthByMonth(Date date, int amount) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.add(Calendar.MONTH, amount);
		Date time = instance.getTime();
		return yyyyMM.format(time);
	}

	/**
	 * 月份相减 = 购入日期与当前日期相差几个月
	 *
	 * @param faInfo
	 * @return long
	 * @author zhaowei
	 * @date 2022/10/12 14:05
	 */
	public long monthSubtract(FaInfoDO faInfo) {
		String nowDate = yyyyMMdd.format(new Date());
		String buyDate = yyyyMMdd.format(faInfo.getBuyDate());
		long l = ChronoUnit.MONTHS.between(LocalDate.parse(buyDate), LocalDate.parse(nowDate));
		return l;
	}

	/**
	 * 存在固定资产折旧信息
	 *
	 * @param queryFaDepreciationList
	 * @return java.lang.Boolean
	 * @author zhaowei
	 * @date 2022/10/12 14:05
	 */
	public Boolean existFaDepreciation(List<FaDepreciation> queryFaDepreciationList) {
		if (CollectionUtils.isNotEmpty(queryFaDepreciationList)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取符合要求的资产折旧信息
	 *
	 * @param faInfoId
	 * @param queryFaDepreciationList
	 * @return com.baosight.wilp.fa.zj.domain.FaDepreciation
	 * @author zhaowei
	 * @date 2022/10/12 14:05
	 */
	public FaDepreciation getFaDepreciation(String faInfoId, List<FaDepreciation> queryFaDepreciationList) {
		if (existFaDepreciation(queryFaDepreciationList)) {
			List<FaDepreciation> faDepreciationList = queryFaDepreciationList
					.stream()
					.filter(faDepreciation -> faInfoId.equals(faDepreciation.getGoodId()))
					.collect(Collectors.toList());
			if (existFaDepreciation(queryFaDepreciationList)) {
				return faDepreciationList.get(0);
			}
		}
		return null;
	}

	/**
	 * 计算固定资产第一个月折旧的年月
	 *
	 * @param buyDate
	 * @return java.lang.String
	 * @author zhaowei
	 * @date 2022/10/12 14:05
	 */
	public String countFirstDEPDate(Date buyDate) {
		String buyDateStr = yyyyMMdd.format(buyDate);
		LocalDate buyDEPDate = LocalDate.parse(buyDateStr);
		LocalDate firstDEPDate = buyDEPDate.with(TemporalAdjusters.firstDayOfMonth());
		if (buyDEPDate.isEqual(firstDEPDate)) {
			return firstDEPDate.format(DateTimeFormatter.ofPattern("yyyyMM"));
		} else {
			return firstDEPDate.plusMonths(1).format(DateTimeFormatter.ofPattern("yyyyMM"));
		}
	}

	/**
	 * 计算固定资产最后折旧的年月
	 *
	 * @param buyDate
	 * @param useYears
	 * @return java.lang.String
	 * @author zhaowei
	 * @date 2022/10/12 14:06
	 */
	public String countLastDEPDate(Date buyDate, Integer useYears) {
		String buyDateStr = yyyyMMdd.format(buyDate);
		LocalDate buyDEPDate = LocalDate.parse(buyDateStr);
		LocalDate deadline = buyDEPDate.plusYears(useYears);
		LocalDate lastDEPDate = deadline.with(TemporalAdjusters.firstDayOfMonth());
		if (lastDEPDate.isEqual(deadline)) {
			return lastDEPDate.format(DateTimeFormatter.ofPattern("yyyyMM"));
		} else {
			return lastDEPDate.plusMonths(1).format(DateTimeFormatter.ofPattern("yyyyMM"));
		}
	}

	/**
	 * 资产月折旧
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/27 9:16
	 */
	public EiInfo monthlyDEP(EiInfo info) {
		/*
		 * 1.查询所有资产信息过滤掉资产净值为0以及已经报废、销账的资产信息
		 * 过滤出当前月是最后一批的折旧资产
		 * 通过stream流过滤出2组数据：是最后一批，非最后一批
		 * 最后一批直接进行折旧
		 */
//		Date startDate = new Date(new Date().getTime() + (long)30 * 24 * 60 * 60 * 1000);
		Date startDate = new Date();
		Map<String, String> initLoadMap = new HashMap(){{
			put("depreciationMonth",yyyyMM.format(startDate));
		}};
		int count = dao.count("FAZJ01.queryFaInfoNew", initLoadMap);
		SqlMapDao sqlMapDao = (SqlMapDao) this.dao;
		sqlMapDao.setMaxQueryCount(count);
		List<Map<String, Object>> faInfoList = sqlMapDao.query("FAZJ01.queryFaInfoNew", initLoadMap);
		List<Map<String, Object>> collect1 = faInfoList.stream().filter(map -> map.get("lastDate").equals(map.get("nowDate"))).collect(Collectors.toList());
		List<Map<String, Object>> collect2 = faInfoList.stream().filter(map -> !map.get("lastDate").equals(map.get("nowDate"))).collect(Collectors.toList());
		/*
		 * 2.非最后一批判断是否存在折旧记录
		 * 存在折旧记录的分成1组，不存在的分成1组
		 * 存在折旧记录的资产净值是否相同，相同分成1组，不同分成另外1组
		 * 不同的那组合并到不存在的那组进行重新计算
		 * 相同的那组直接复制之前的资产净值
		 * 组装数据进行保存
		 */
		List<FaDepreciation> faDepreciationList = dao.query("FAZJ01.queryFaDepreciationNew", new HashMap<>());
		List<String> collect = faDepreciationList.stream().map(faDepreciation -> faDepreciation.getGoodId()).collect(Collectors.toList());
		List<Map<String, Object>> collect3 = collect2.stream().filter(map -> collect.contains(map.get("faInfoId"))).collect(Collectors.toList());
		List<Map<String, Object>> collect4 = collect2.stream().filter(map -> !collect.contains(map.get("faInfoId"))).collect(Collectors.toList());
		List<Map<String, Object>> collect5 = new ArrayList<>();
		for (Map map : collect3) {
			for (int i = 0; i < faDepreciationList.size(); i++) {
				BigDecimal netAssetValue = (BigDecimal) map.get("netAssetValue");
				if (faDepreciationList.get(i).getDepreciationMonth().equals(map.get("nowDate"))) {
					break;
				} else {
					// 折旧记录存在并且净值相同
					if (faDepreciationList.get(i).getGoodId().equals(map.get("faInfoId")) && faDepreciationList.get(i).getNetAssetValue().compareTo(netAssetValue) == 0) {
						map.put("usedMonth", faDepreciationList.get(i).getUsedMonth());
						collect5.add(map);
						faDepreciationList.remove(i);
						i--;
						// 折旧记录存在并且净值不相同
					} else if (faDepreciationList.get(i).getGoodId().equals(map.get("faInfoId")) && faDepreciationList.get(i).getNetAssetValue().compareTo(netAssetValue) != 0) {
						map.put("usedMonth", faDepreciationList.get(i).getUsedMonth());
						collect4.add(map);
						faDepreciationList.remove(i);
						i--;
					}
				}
			}
		}

//		for (Map map : collect3) {
//			for (FaDepreciation faDepreciation : faDepreciationList) {
//				BigDecimal netAssetValue = (BigDecimal) map.get("netAssetValue");
//				// 本月已折旧
//				if (faDepreciation.getDepreciationMonth().equals(map.get("nowDate"))) {
//					break;
//				} else {
//					// 折旧记录存在并且净值相同
//					if (faDepreciation.getGoodId().equals(map.get("faInfoId")) && faDepreciation.getNetAssetValue().compareTo(netAssetValue) == 0) {
//						map.put("usedMonth", faDepreciation.getUsedMonth());
//						collect5.add(map);
//					// 折旧记录存在并且净值不相同
//					} else if (faDepreciation.getGoodId().equals(map.get("faInfoId")) && faDepreciation.getNetAssetValue().compareTo(netAssetValue) != 0) {
//						map.put("usedMonth", faDepreciation.getUsedMonth());
//						collect4.add(map);
//					}
//				}
//			}
//		}

		/*
		 * collect1 - 净值 = 0.00
		 * collect4 - 重新计算月折旧
		 * collect5 - 复制折旧表中的月折旧
		 */
		List<FaDepreciationDTO> faDepreciationDTOList = new ArrayList<>();
		List<FaDepreciationDTO> faDepreciationDTOList1 = collect1.stream().map(map -> {
			Integer useMonths = Math.toIntExact((Long) map.get("useMonths"));
			BigDecimal buyCost = (BigDecimal) map.get("buyCost");
			BigDecimal netAssetValue = (BigDecimal) map.get("netAssetValue");
			BigDecimal totalDepreciation = (BigDecimal) map.get("totalDepreciation");
			FaDepreciationDTO faDepreciationDTO = new FaDepreciationDTO();
			faDepreciationDTO.setId(UUID.randomUUID().toString());
			faDepreciationDTO.setFaInfoId((String) map.get("faInfoId"));
			faDepreciationDTO.setDeptNum((String) map.get("deptNum"));
			faDepreciationDTO.setDeptName((String) map.get("deptName"));
			faDepreciationDTO.setSurpNum((String) map.get("surpNum"));
			faDepreciationDTO.setSurpName((String) map.get("surpName"));
			faDepreciationDTO.setInvoiceNo((String) map.get("invoiceNo"));
			faDepreciationDTO.setModel((String) map.get("model"));
			faDepreciationDTO.setSpec((String) map.get("spec"));
			faDepreciationDTO.setBuyCost(buyCost);
			faDepreciationDTO.setFundingSourceNum((String) map.get("fundingSourceNum"));
			faDepreciationDTO.setFundingSourceName((String) map.get("fundingSourceName"));
			faDepreciationDTO.setGoodsTypeCode((String) map.get("goodsTypeCode"));
			faDepreciationDTO.setGoodsTypeName((String) map.get("goodsTypeName"));
			faDepreciationDTO.setGoodsNum((String) map.get("goodsNum"));
			faDepreciationDTO.setGoodsName((String) map.get("goodsName"));
			faDepreciationDTO.setGoodsCategoryCode((String) map.get("goodsCategoryCode"));
			faDepreciationDTO.setGoodsCategoryName((String) map.get("goodsCategoryName"));
			faDepreciationDTO.setGoodsClassifyCode((String) map.get("goodsClassifyCode"));
			faDepreciationDTO.setGoodsClassifyName((String) map.get("goodsClassifyName"));
			faDepreciationDTO.setVariationValue(netAssetValue);
			faDepreciationDTO.setAccountType("030");
			faDepreciationDTO.setDepreciationMonth((String) map.get("nowDate"));
			faDepreciationDTO.setUsedMonth(useMonths);
			faDepreciationDTO.setDepreciationValue(netAssetValue);
			faDepreciationDTO.setTotalDepreciation(totalDepreciation.add(netAssetValue));
			faDepreciationDTO.setNetAssetValue(new BigDecimal("0.00"));
			return faDepreciationDTO;
		}).collect(Collectors.toList());

		List<FaDepreciationDTO> faDepreciationDTOList2 = collect4.stream().map(map -> {
			String nowDate = map.get("nowDate") + "-01";
			String countDate = map.get("countDate") + "-01";
			long l = monthsBetween(nowDate, countDate);
			Integer useMonths = Math.toIntExact((Long) map.get("useMonths"));
			FaDepreciationDTO faDepreciationDTO = new FaDepreciationDTO();
			BigDecimal buyCost = (BigDecimal) map.get("buyCost");
			BigDecimal netAssetValue = (BigDecimal) map.get("netAssetValue");
			BigDecimal totalDepreciation = (BigDecimal) map.get("totalDepreciation");
			// 判断是否超期
			if (l >= useMonths){
				faDepreciationDTO.setId(UUID.randomUUID().toString());
				faDepreciationDTO.setFaInfoId((String) map.get("faInfoId"));
				faDepreciationDTO.setDeptNum((String) map.get("deptNum"));
				faDepreciationDTO.setDeptName((String) map.get("deptName"));
				faDepreciationDTO.setSurpNum((String) map.get("surpNum"));
				faDepreciationDTO.setSurpName((String) map.get("surpName"));
				faDepreciationDTO.setInvoiceNo((String) map.get("invoiceNo"));
				faDepreciationDTO.setModel((String) map.get("model"));
				faDepreciationDTO.setSpec((String) map.get("spec"));
				faDepreciationDTO.setBuyCost(buyCost);
				faDepreciationDTO.setFundingSourceNum((String) map.get("fundingSourceNum"));
				faDepreciationDTO.setFundingSourceName((String) map.get("fundingSourceName"));
				faDepreciationDTO.setGoodsCategoryCode((String) map.get("goodsCategoryCode"));
				faDepreciationDTO.setGoodsCategoryName((String) map.get("goodsCategoryName"));
				faDepreciationDTO.setGoodsTypeCode((String) map.get("goodsTypeCode"));
				faDepreciationDTO.setGoodsTypeName((String) map.get("goodsTypeName"));
				faDepreciationDTO.setGoodsClassifyCode((String) map.get("goodsClassifyCode"));
				faDepreciationDTO.setGoodsClassifyName((String) map.get("goodsClassifyName"));
				faDepreciationDTO.setGoodsNum((String) map.get("goodsNum"));
				faDepreciationDTO.setGoodsName((String) map.get("goodsName"));
				faDepreciationDTO.setVariationValue(netAssetValue);
				faDepreciationDTO.setAccountType("030");
				faDepreciationDTO.setDepreciationMonth((String) map.get("nowDate"));
				faDepreciationDTO.setUsedMonth(useMonths);
				faDepreciationDTO.setDepreciationValue(netAssetValue);
				faDepreciationDTO.setTotalDepreciation(netAssetValue.add(totalDepreciation));
				faDepreciationDTO.setNetAssetValue(netAssetValue.subtract(netAssetValue));
			} else {
				Integer usedMonths = Math.toIntExact(l) + 1;
				Integer remainMonths = Math.toIntExact(useMonths - l);
				BigDecimal monthDepreciation = netAssetValue.divide(new BigDecimal(remainMonths), BigDecimal.ROUND_CEILING).setScale(2, BigDecimal.ROUND_UP);
				faDepreciationDTO.setId(UUID.randomUUID().toString());
				faDepreciationDTO.setFaInfoId((String) map.get("faInfoId"));
				faDepreciationDTO.setDeptNum((String) map.get("deptNum"));
				faDepreciationDTO.setDeptName((String) map.get("deptName"));
				faDepreciationDTO.setSurpNum((String) map.get("surpNum"));
				faDepreciationDTO.setSurpName((String) map.get("surpName"));
				faDepreciationDTO.setInvoiceNo((String) map.get("invoiceNo"));
				faDepreciationDTO.setModel((String) map.get("model"));
				faDepreciationDTO.setSpec((String) map.get("spec"));
				faDepreciationDTO.setBuyCost(buyCost);
				faDepreciationDTO.setFundingSourceNum((String) map.get("fundingSourceNum"));
				faDepreciationDTO.setFundingSourceName((String) map.get("fundingSourceName"));
				faDepreciationDTO.setGoodsCategoryCode((String) map.get("goodsCategoryCode"));
				faDepreciationDTO.setGoodsCategoryName((String) map.get("goodsCategoryName"));
				faDepreciationDTO.setGoodsTypeCode((String) map.get("goodsTypeCode"));
				faDepreciationDTO.setGoodsTypeName((String) map.get("goodsTypeName"));
				faDepreciationDTO.setGoodsClassifyCode((String) map.get("goodsClassifyCode"));
				faDepreciationDTO.setGoodsClassifyName((String) map.get("goodsClassifyName"));
				faDepreciationDTO.setGoodsNum((String) map.get("goodsNum"));
				faDepreciationDTO.setGoodsName((String) map.get("goodsName"));
				faDepreciationDTO.setVariationValue(monthDepreciation);
				faDepreciationDTO.setAccountType("030");
				faDepreciationDTO.setDepreciationMonth((String) map.get("nowDate"));
				faDepreciationDTO.setUsedMonth(usedMonths);
				faDepreciationDTO.setDepreciationValue(monthDepreciation);
				faDepreciationDTO.setTotalDepreciation(monthDepreciation.add(totalDepreciation));
				faDepreciationDTO.setNetAssetValue(netAssetValue.subtract(monthDepreciation));
			}
			return faDepreciationDTO;
		}).collect(Collectors.toList());

		List<FaDepreciationDTO> faDepreciationDTOList3 = collect5.stream().map(map -> {
			BigDecimal buyCost = (BigDecimal) map.get("buyCost");
			BigDecimal netAssetValue = (BigDecimal) map.get("netAssetValue");
			BigDecimal totalDepreciation = (BigDecimal) map.get("totalDepreciation");
			BigDecimal monthDepreciation = (BigDecimal) map.get("monthDepreciation");
			String nowDate = map.get("nowDate") + "-01";
			String countDate = map.get("countDate") + "-01";
			long l = monthsBetween(nowDate, countDate);
			Integer usedMonths = Math.toIntExact(l) + 1;
			FaDepreciationDTO faDepreciationDTO = new FaDepreciationDTO();
			faDepreciationDTO.setId(UUID.randomUUID().toString());
			faDepreciationDTO.setFaInfoId((String) map.get("faInfoId"));
			faDepreciationDTO.setDeptNum((String) map.get("deptNum"));
			faDepreciationDTO.setDeptName((String) map.get("deptName"));
			faDepreciationDTO.setSurpNum((String) map.get("surpNum"));
			faDepreciationDTO.setSurpName((String) map.get("surpName"));
			faDepreciationDTO.setInvoiceNo((String) map.get("invoiceNo"));
			faDepreciationDTO.setModel((String) map.get("model"));
			faDepreciationDTO.setSpec((String) map.get("spec"));
			faDepreciationDTO.setBuyCost(buyCost);
			faDepreciationDTO.setFundingSourceNum((String) map.get("fundingSourceNum"));
			faDepreciationDTO.setFundingSourceName((String) map.get("fundingSourceName"));
			faDepreciationDTO.setGoodsCategoryCode((String) map.get("goodsCategoryCode"));
			faDepreciationDTO.setGoodsCategoryName((String) map.get("goodsCategoryName"));
			faDepreciationDTO.setGoodsTypeCode((String) map.get("goodsTypeCode"));
			faDepreciationDTO.setGoodsTypeName((String) map.get("goodsTypeName"));
			faDepreciationDTO.setGoodsClassifyCode((String) map.get("goodsClassifyCode"));
			faDepreciationDTO.setGoodsClassifyName((String) map.get("goodsClassifyName"));
			faDepreciationDTO.setGoodsNum((String) map.get("goodsNum"));
			faDepreciationDTO.setGoodsName((String) map.get("goodsName"));
			faDepreciationDTO.setAccountType("030");
			faDepreciationDTO.setDepreciationMonth((String) map.get("nowDate"));
			faDepreciationDTO.setUsedMonth(usedMonths);
			// 判断净值是否小于月折旧,小于月折旧取净值
			if((netAssetValue.subtract(monthDepreciation)).compareTo(new BigDecimal(0.00)) == -1){
				faDepreciationDTO.setVariationValue(netAssetValue);
				faDepreciationDTO.setDepreciationValue(netAssetValue);
				faDepreciationDTO.setTotalDepreciation(totalDepreciation.add(netAssetValue));
				faDepreciationDTO.setNetAssetValue(new BigDecimal(0.00));
			} else {
				faDepreciationDTO.setVariationValue(monthDepreciation);
				faDepreciationDTO.setDepreciationValue(monthDepreciation);
				faDepreciationDTO.setTotalDepreciation(totalDepreciation.add(monthDepreciation));
				faDepreciationDTO.setNetAssetValue(netAssetValue.subtract(monthDepreciation));
			}
			return faDepreciationDTO;
		}).collect(Collectors.toList());
		faDepreciationDTOList.addAll(faDepreciationDTOList1);
		faDepreciationDTOList.addAll(faDepreciationDTOList2);
		faDepreciationDTOList.addAll(faDepreciationDTOList3);
		// 3.保存到折旧表，保存到抛帐表，更新主表数据
		if (CollectionUtils.isNotEmpty(faDepreciationDTOList)) {
			OneSelfUtils.batchInsert("FAZJ01.completeDEPNEW", faDepreciationDTOList);
			OneSelfUtils.batchInsert("FAZJ01.throwDEPNEW", faDepreciationDTOList);
			for (FaDepreciationDTO faDepreciationDTO : faDepreciationDTOList) {
				dao.update("FAZJ01.updateDEPNEW", faDepreciationDTO);
			}
		}
		int i = calLastedTime(startDate);
		LOG.error("{==执行时间为：" + String.valueOf(i)+"s==}");
		return info;
	}

	// 计算2个月差值
	public long monthsBetween(String nowDate, String useDate) {
		long l = ChronoUnit.MONTHS.between(LocalDate.parse(useDate), LocalDate.parse(nowDate));
		return l;
	}

	// 计算使用月份
	public String countUseDate(String useDate, Integer useMonths) {
		LocalDate useLocalDate = LocalDate.parse(useDate);
		LocalDate deadline = useLocalDate.plusMonths(useMonths);
		return deadline.format(DateTimeFormatter.ofPattern("yyyyMM"));
	}

	// 计算方法使用时间
	public int calLastedTime(Date startDate){
		long a = new Date().getTime();
		long b = startDate.getTime();
		int c = (int)((a-b)/1000);
		return c;
	}
}
