package com.baosight.wilp.mp.lj.service.impl;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.mp.lj.domain.MpContPay;
import com.baosight.wilp.mp.lj.service.MpContPayService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 合同付款业务service实现
 * @ClassName: MpContPayServiceImpl
 * @Package com.baosight.wilp.mp.lj.service.impl
 * @date: 2022年10月28日 9:34
 *
 * 1.根据ID查询付款信息
 * 2.新增付款信息
 * 3.更新付款信息
 * 4.删除付款信息
 */
@Service
public class MpContPayServiceImpl implements MpContPayService {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    /**
     * 根据ID查询付款信息
     * @Title: queryContPay
     * @param id id : 付款ID
     * @return com.baosight.wilp.mp.lj.domain.MpContPay
     * @throws
     **/
    @Override
    public MpContPay queryContPay(String id) {
        List<MpContPay> list = dao.query("MPLJ07.query", new HashMap(2) {{put("id", id);}});
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 新增付款信息
     * @Title: insert
     * @param pay pay : 付款信息对象
     * @return void
     * @throws
     **/
    @Override
    public void insert(MpContPay pay) {
        dao.insert("MPLJ07.insert", pay);
    }

    /**
     * 更新付款信息
     * @Title: update
     * @param pay pay : 付款信息对象
     * @return int
     * @throws
     **/
    @Override
    public int update(MpContPay pay) {
        return dao.update("MPLJ07.update", pay);
    }

    /**
     * 删除付款信息
     * @Title: delete
     * @param id id : 付款ID
     * @return int
     * @throws
     **/
    @Override
    public int delete(String id) {
        return dao.delete("MPLJ07.delete", id);
    }
}
