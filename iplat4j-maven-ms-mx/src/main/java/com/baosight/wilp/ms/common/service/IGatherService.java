package com.baosight.wilp.ms.common.service;

import com.baosight.wilp.ms.common.domain.FlowchartVO;

import java.util.List;

/**
 * 采集数据上行接口业务层接口
 *
 * @author: panlingfeng
 * @createDate: 2022/3/10 6:25 下午
 */
public interface IGatherService {
    /**
     * 运转图接口
     *
     * @return
     * @author panlingfeng
     * @date 2022/3/10 6:30 下午
     */
    List<FlowchartVO> findFlowchart();
}
