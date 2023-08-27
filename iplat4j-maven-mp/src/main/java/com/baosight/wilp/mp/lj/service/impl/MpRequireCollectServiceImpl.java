package com.baosight.wilp.mp.lj.service.impl;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.mp.lj.domain.MpRequireCollect;
import com.baosight.wilp.mp.lj.domain.MpRequireCollectDetail;
import com.baosight.wilp.mp.lj.domain.MpRequireRelation;
import com.baosight.wilp.mp.lj.service.MpRequireCollectService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 需求计划汇总逻辑处理Service实现
 * @ClassName: MpRequireCollectServiceImpl
 * @Package com.baosight.wilp.mp.lj.service.impl
 * @date: 2022年10月18日 11:44
 *
 * 1.根据ID获取需求汇总
 * 2.新增需求汇总
 * 3.更新需求汇总
 * 4.删除需求汇总
 * 5.获取指定需求汇总明细
 * 6.批量新增需求汇总明细
 * 7.批量新增需求汇总关联信息
 * 8.删除需求汇总明细
 * 9.根据汇总ID获取需求计划ID集合
 * 10.根据汇总ID获取需求计划明细ID集合
 */
@Service
public class MpRequireCollectServiceImpl implements MpRequireCollectService {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    /**
     * 根据ID获取需求汇总
     * @Title: queryRequireCollect
     * @param id id : 需求汇总ID
     * @return com.baosight.wilp.mp.lj.domain.MpRequireCollect
     * @throws
     **/
    @Override
    public MpRequireCollect queryRequireCollect(String id) {
        List<MpRequireCollect> list = dao.query("MPLJ06.query", new HashMap(2) {{
            put("id", id);
        }});
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 新增需求汇总
     * @Title: insert
     * @param collect collect : 需求汇总对象
     * @return void
     * @throws
     **/
    @Override
    public void insert(MpRequireCollect collect) {
        dao.insert("MPLJ06.insert", collect);
    }

    /**
     * 更新需求汇总
     * @Title: update
     * @param collect collect : 需求汇总对象
     * @return int
     * @throws
     **/
    @Override
    public int update(MpRequireCollect collect) {
        return dao.update("MPLJ06.update", collect);
    }

    /**
     * 删除需求汇总
     * @Title: delete
     * @param id id : 需求汇总ID
     * @return int
     * @throws
     **/
    @Override
    public int delete(String id) {
        return dao.delete("MPLJ06.delete", id);
    }

    /**
     * 获取指定需求汇总明细
     * @Title: queryDetailList
     * @param collectId collectId : 需求汇总ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpRequireCollectDetail>
     * @throws
     **/
    @Override
    public List<MpRequireCollectDetail> queryDetailList(String collectId) {
        return dao.query("MPLJ06.queryDetail", new HashMap(2) {{
            put("collectId", collectId);
        }});
    }

    /**
     * 批量新增需求汇总明细
     * @Title: insertDetail
     * @param list list : 需求汇总明细集合
     * @return void
     * @throws
     **/
    @Override
    public void insertDetail(List<MpRequireCollectDetail> list) {
        dao.insert("MPLJ06.insertDetail", list);
    }

    /**
     * 删除需求汇总明细
     * @Title: deleteDetail
     * @param collectId collectId : 需求汇总ID
     * @return int
     * @throws
     **/
    @Override
    public int deleteDetail(String collectId) {
        return dao.update("MPLJ06.deleteDetail", collectId);
    }

    /**
     * 批量新增需求汇总关联信息
     * @Title: insertRelation
     * @param list list : 需求汇总关联对象集合
     * @return void
     * @throws
     **/
    @Override
    public void insertRelation(List<MpRequireRelation> list) {
        dao.insert("MPLJ06.insertRelation", list);
    }

    /**
     * 删除需求汇总关联信息
     * @Title: deleteRelation
     * @param collectId collectId : 需求汇总ID
     * @return int
     * @throws
     **/
    @Override
    public int deleteRelation(String collectId) {
        return dao.delete("MPLJ06.deleteRelation", collectId);
    }

    /**
     * 根据汇总ID获取需求计划ID集合
     * @Title: queryRequireIds
     * @param id id : 需求汇总ID
     * @return java.util.List<java.lang.String>
     * @throws
     **/
    @Override
    public List<String> queryRequireIds(String id) {
        return dao.query("MPLJ06.queryRequireIds", id);
    }

    /**
     * 根据汇总ID获取需求计划明细ID集合
     * @Title: queryRequireDetailIds
     * @param id id
     * @return java.util.List<java.lang.String>
     * @throws
     **/
    @Override
    public List<String> queryRequireDetailIds(String id) {
        return dao.query("MPLJ06.queryRequireDetailIds", id);
    }
}
