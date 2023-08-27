package com.baosight.wilp.ms.common.web;

import com.baosight.wilp.ms.common.service.IGatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 采集数据上行接口控制层（运转图接口）
 *
 * @author: panlingfeng
 * @createDate: 2022/3/10 6:19 下午
 */
@RestController
@RequestMapping("sym")
public class GatherController {

    @Autowired
    private IGatherService iGatherService;
    /**
     * 运转图接口
     *
     * @return
     * @author panlingfeng
     * @date 2022/3/10 6:20 下午
     */
    @GetMapping("flowchart")
    @CrossOrigin
    public Object flowchart() {
        return iGatherService.findFlowchart();
    }
}
