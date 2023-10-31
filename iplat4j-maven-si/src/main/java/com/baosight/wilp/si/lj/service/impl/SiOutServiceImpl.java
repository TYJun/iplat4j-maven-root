package com.baosight.wilp.si.lj.service.impl;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.si.ck.domain.SiOut;
import com.baosight.wilp.si.ck.domain.SiOutDetail;
import com.baosight.wilp.si.lj.service.SiOutService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 出库单Service实现
 * @ClassName: SiOutServiceImpl
 * @Package com.baosight.wilp.si.lj.service.impl
 * @date: 2023年10月19日 11:15
 */
@Service("siOutService")
public class SiOutServiceImpl implements SiOutService {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    /**
     * 新增出库主单据
     * @Title: insert
     * @param out out 出库单
     * @return void
     * @throws
     **/
    @Override
    public void insert(SiOut out) {
        dao.insert("SICK01.insert", out);
    }

    /**
     * 新增出库明细
     * @Title: insertDetail
     * @param detailList detailList 出库明细集合
     * @return void
     * @throws
     **/
    @Override
    public void insertDetail(List<SiOutDetail> detailList) {
        dao.insert("SICK0101.insert", detailList);
    }

}
