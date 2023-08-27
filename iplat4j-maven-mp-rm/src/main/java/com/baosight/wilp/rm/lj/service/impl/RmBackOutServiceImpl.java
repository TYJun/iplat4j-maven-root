package com.baosight.wilp.rm.lj.service.impl;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.rm.lj.domain.RmBackOut;
import com.baosight.wilp.rm.lj.domain.RmBackOutDetail;
import com.baosight.wilp.rm.lj.service.RmBackOutService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资退库逻辑Service实现
 * @ClassName: RmBackOutServiceImpl
 * @Package com.baosight.wilp.rm.lj.service.impl.impl
 * @date: 2022年08月31日 17:29
 * <p>
 * 1.获取指定的退库申请
 * 2.新增退库申请
 * 3.更新退库申请
 * 4.删除退库申请
 * 5.根据退库申请ID查询明细
 * 6.批量新增退库申请明细
 * 7.更新退库申请明细
 * 8.删除退库申请明细
 * 9.退库单是否已完成退库
 */
@Service
public class RmBackOutServiceImpl implements RmBackOutService {

    /**
     * 注入dao
     **/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    /**
     * 获取指定的退库申请
     *
     * @param id id : 退库申请ID
     * @return com.baosight.wilp.rm.lj.domain.RmBackOut
     * @throws
     * @Title: queryBackOut
     **/
    @Override
    public RmBackOut queryBackOut(String id) {
        List<RmBackOut> list = dao.query("RMLJ03.query", new HashMap(2) {{
            put("id", id);
        }});
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 新增退库申请
     *
     * @param backOut backOut : 退库申请对象
     * @return void
     * @throws
     * @Title: insert
     **/
    @Override
    public void insert(RmBackOut backOut) {
        dao.insert("RMLJ03.insert", backOut);
    }

    /**
     * 更新退库申请
     *
     * @param backOut backOut : 退库申请对象
     * @return int
     * @throws
     * @Title: update
     **/
    @Override
    public int update(RmBackOut backOut) {
        return dao.update("RMLJ03.update", backOut);
    }

    /**
     * 删除退库申请
     *
     * @param id id : 退库申请ID
     * @return int
     * @throws
     * @Title: delete
     **/
    @Override
    public int delete(String id) {
        return dao.delete("RMLJ03.delete", id);
    }

    /**
     * 根据退库申请ID查询明细
     *
     * @param backOutId backOutId : 退库申请ID
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmBackOutDetail>
     * @throws
     * @Title: queryBackOutDetailList
     **/
    @Override
    public List<RmBackOutDetail> queryBackOutDetailList(String backOutId) {
        return dao.query("RMLJ03.queryDetail", new HashMap(2) {{
            put("backOutId", backOutId);
        }});
    }

    /**
     * 批量新增退库申请明细
     *
     * @param list list : 退库申请明细对象集合
     * @return void
     * @throws
     * @Title: insertDetail
     **/
    @Override
    public void insertDetail(List<RmBackOutDetail> list) {
        dao.insert("RMLJ03.insertDetail", list);
    }

    /**
     * 更新退库申请明细
     *
     * @param detail detail : 退库申请明细对象
     * @return int
     * @throws
     * @Title: updateDetail
     **/
    @Override
    public int updateDetail(RmBackOutDetail detail) {
        return dao.update("RMLJ03.updateDetail", detail);
    }

    /**
     * 删除退库申请明细
     *
     * @param backOutId backOutId : 退库申请ID
     * @return int
     * @throws
     * @Title: deleteDetail
     **/
    @Override
    public int deleteDetail(String backOutId) {
        return dao.delete("RMLJ03.deleteDetail", backOutId);
    }

    /**
     * 退库单是否已完成退库
     *
     * @param id id
     * @return void
     * @throws
     * @Title: hasAllBackOut
     **/
    @Override
    public boolean hasAllBackOut(String id) {
        int count = dao.count("RMLJ03.hasAllBackOut", id);
        return count == 0;
    }

    /**
     * 根据领用单号获取对应退库明细数量
     *
     * @param claimNo claimNo : 领用单号
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @throws
     * @Title: queryBackOutDetailListByClaimNo
     **/
    @Override
    public List<Map<String, Object>> queryBackOutDetailListByClaimNo(String claimNo) {
        return dao.query("RMLJ03.queryBackOutDetailListByClaimNo", claimNo);
    }
}
