package com.baosight.wilp.di.lj.impl;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.di.lj.patrolLogicService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PatrolLogicServiceImpl implements patrolLogicService {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");
    @Override
    public void sceneDeal(Map<String,Object>dealDate){
         dao.update("DILJ01.sceneDeal",dealDate);
    }

    @Override
    public void reportDeal(Map<String,Object>reportDeal){
        dao.update("DILJ01.reportDeal",reportDeal);
    }
}
