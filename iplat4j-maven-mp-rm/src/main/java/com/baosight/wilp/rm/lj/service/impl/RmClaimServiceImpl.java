package com.baosight.wilp.rm.lj.service.impl;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.rm.lj.domain.RmClaim;
import com.baosight.wilp.rm.lj.domain.RmClaimDetail;
import com.baosight.wilp.rm.lj.service.RmClaimService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资领用逻辑service实现
 * @ClassName: RmClaimServiceImpl
 * @Package com.baosight.wilp.rm.lj.service.impl
 * @date: 2022年08月31日 17:20
 * <p>
 * 1.查询领用单列表
 * 2.根据ID查询指定领用单
 * 3.根据领用单号查询指定领用单
 * 4.新增领用单
 * 5.编辑领用单
 * 6.删除领用单
 * 7.查询领用单明细列表
 * 8.批量新增领用单明细
 * 9.编辑领用单明细
 * 10.删除领用单明细
 * 11.获取物资的预约量集合
 * 12.获取指定物资的预约量
 */
@Service
public class RmClaimServiceImpl implements RmClaimService {

    /**
     * 注入dao
     **/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    /**
     * 查询领用单列表
     *
     * @param claim  claim : 领用单对象
     * @param offset offset : 分页参数,指定条数(偏移量)
     * @param limit  limit : 分页参数，每页数量
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmClaim>
     * @throws
     * @Title: queryClaimList
     **/
    @Override
    public List<RmClaim> queryClaimList(RmClaim claim, Integer offset, Integer limit) {
        Map map = claim == null ? new HashMap<>(0) : claim.toMap();
        if (offset == null || limit == null) {
            return dao.query("RMLJ02.query", map);
        }
        return dao.query("RMLJ02.query", map, offset, limit);
    }

    /**
     * 查询指定领用单
     *
     * @param id id : 领用单ID
     * @return com.baosight.wilp.rm.lj.domain.RmClaim
     * @throws
     * @Title: queryClaimById
     **/
    @Override
    public RmClaim queryClaimById(String id) {
        List<RmClaim> list = dao.query("RMLJ02.query", new HashMap(2) {{
            put("id", id);
        }});
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 根据领用单号查询指定领用单
     *
     * @param claimNo claimNo
     * @return com.baosight.wilp.rm.lj.domain.RmClaim
     * @throws
     * @Title: queryClaimByClaimNo
     **/
    @Override
    public RmClaim queryClaimByClaimNo(String claimNo) {
        List<RmClaim> list = dao.query("RMLJ02.query", new HashMap(2) {{
            put("claimNo", claimNo);
        }});
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else if (list.size() > 1) {
            return list.stream().filter(claim -> claim.getClaimNo().equals(claimNo)).findFirst().orElse(null);
        } else {
            return list.get(0);
        }
    }

    /**
     * 查询领用单状态
     *
     * @param claimId claimId
     * @return java.lang.String
     * @throws
     * @Title: queryClaimStatusCode
     **/
    @Override
    public String queryClaimStatusCode(String claimId) {
        List<String> list = dao.query("RMLJ02.queryClaimStatusCode", claimId);
        return CollectionUtils.isNotEmpty(list) ? list.get(0) : "";
    }

    /**
     * 新增领用单
     *
     * @param claim claim : 领用单对象
     * @return void
     * @throws
     * @Title: insert
     **/
    @Override
    public void insert(RmClaim claim) {
        claim.setRecCreateTime(new Date());
        dao.insert("RMLJ02.insert", claim);
    }

    /**
     * 编辑领用单
     *
     * @param claim claim : 领用单对象
     * @return void
     * @throws
     * @Title: update
     **/
    @Override
    public void update(RmClaim claim) {
        claim.setRecReviseTime(new Date());
        dao.update("RMLJ02.update", claim);
    }

    /**
     * 删除领用单
     *
     * @param id id : 领用单ID
     * @return void
     * @throws
     * @Title: delete
     **/
    @Override
    public void delete(String id) {
        dao.delete("RMLJ02.delete", id);
    }

    /**
     * 查询领用单明细列表
     *
     * @param claimId claimId : 领用单ID
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmClaimDetail>
     * @throws
     * @Title: queryClaimDetailList
     **/
    @Override
    public List<RmClaimDetail> queryClaimDetailList(String claimId) {
        List<RmClaimDetail> list = dao.query("RMLJ02.queryDetail", new HashMap(2) {{
            put("claimId", claimId);
        }});
        return list;
    }

    /**
     * 批量新增领用单明细
     *
     * @param detailList detailList : 领用单明细集合
     * @return void
     * @throws
     * @Title: insertDetail
     **/
    @Override
    public void insertDetail(List<RmClaimDetail> detailList) {
        dao.insert("RMLJ02.insertDetail", detailList);
    }

    /**
     * 编辑领用单明细
     *
     * @param detail detail : 领用单明细对象
     * @return void
     * @throws
     * @Title: updateDetail
     **/
    @Override
    public void updateDetail(RmClaimDetail detail) {
        dao.update("RMLJ02.updateDetail", detail);
    }

    /**
     * 删除领用单明细
     *
     * @param claimId claimId : 领用单ID
     * @return void
     * @throws
     * @Title: deleteDetail
     **/
    @Override
    public void deleteDetail(String claimId) {
        dao.delete("RMLJ02.deleteDetail", claimId);
    }

    /**
     * 获取物资的预约量集合
     *
     * @param matNumList matNumList: 物资编码集合
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @throws
     * @Title: queryReserveNums
     **/
    @Override
    public List<Map<String, Object>> queryReserveNums(List<String> matNumList) {
        return dao.query("RMLJ02.queryReserveNums", matNumList);
    }

    /**
     * 获取指定物资的预约量
     *
     * @param matNum matNum: 物资编码
     * @return java.lang.Double
     * @throws
     * @Title: queryReserveNum
     **/
    @Override
    public Double queryReserveNum(String matNum) {
        List<Map<String, Object>> list = queryReserveNums(Arrays.asList(matNum));
        if (CollectionUtils.isNotEmpty(list)) {
            return NumberUtils.toDouble(list.get(0).get("totalNum"), 0d);
        }
        return 0d;
    }

    /**
     * 获取领用单流程
     *
     * @param claimNo claimNo: 领用单号
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.String>>
     * @throws
     * @Title: queryClaimFlow
     **/
    @Override
    public List<Map<String, String>> queryClaimFlow(String claimNo) {

        return null;
    }
}
