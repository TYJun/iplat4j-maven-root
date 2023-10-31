package com.baosight.wilp.si.lj.service.impl;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.si.lj.service.SiStorageService;
import org.springframework.stereotype.Service;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 库存Service实现
 * @ClassName: SiStorageServiceImpl
 * @Package com.baosight.wilp.si.lj.service.impl
 * @date: 2023年10月19日 11:18
 */
@Service("siStorageService")
public class SiStorageServiceImpl implements SiStorageService {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");
}
