package com.baosight.wilp.ms.common.service;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.ms.common.domain.FlowchartVO;
import com.baosight.wilp.ms.common.domain.RtDTO;
import com.baosight.wilp.ms.common.web.GatherServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 采集数据上行接口业务层实现
 *
 * @author: panlingfeng
 * @createDate: 2022/3/10 6:29 下午
 */
@Service
public class GatherServiceImpl implements IGatherService {

    @Autowired
    private InfluxDBService influxDBService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 运转图接口
     *
     * @return
     * @author panlingfeng
     * @date 2022/3/10 6:30 下午
     * 1.查询基础数据
     * 2.查询时序数据
     */
    @Override
    public List<FlowchartVO> findFlowchart() {
        //查询基础数据
        Dao dao = (Dao) PlatApplicationContext.getBean("dao");
        List<FlowchartVO> flowchartVOList = dao.query("MSPL01.queryFlowchart", null);

        //查询所有的流程图最新警告信息
        List<FlowchartVO> flowchartVOListChild = dao.query("MSPL01.queryLastFlowchartChild", new HashMap<>());
        Map<String, FlowchartVO> flowchartVOChildMap =
                flowchartVOListChild.stream().collect(Collectors.toMap(child -> child.getTag(), child -> child));

        if (flowchartVOList != null && flowchartVOList.size() > 0) {
            //查询对应的时序数据
            if (GatherServer.currentRtDTOList != null && GatherServer.currentRtDTOList.size() > 0) {
                flowchartVOList.forEach(flowchartVO -> {
                    List<RtDTO> collect = GatherServer.currentRtDTOList
                            .stream()
                            .filter(rtDTO -> rtDTO.getTnm().equals(flowchartVO.getTag())).collect(Collectors.toList());
                    if (collect.size() > 0) {
                        RtDTO rtDTO = collect.get(0);
                        flowchartVO.setTagRValue(rtDTO.getVal().toString());
                        Date date = new Date(Long.parseLong(rtDTO.getTss() + rtDTO.getTsm()));
                        flowchartVO.setTagTransferTime(sdf.format(date));
                        flowchartVO.setTagVQ("0");
                    }
                    // 关联告警信息
                    FlowchartVO flowchartVOChild = flowchartVOChildMap.get(flowchartVO.getTag());
                    if (flowchartVOChild != null) {
                        flowchartVO.setAlarmValue(flowchartVOChild.getAlarmValue());
                        flowchartVO.setAlarmGrade(flowchartVOChild.getAlarmGrade());
                        flowchartVO.setAlarmTime(flowchartVOChild.getAlarmTime());
                    }
                });
            }
        }
        return flowchartVOList;
    }
}
