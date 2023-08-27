package com.baosight.iplat4j.ed.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceED02 extends ServiceEPBase {
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        inInfo.addBlock(EiConstant.resultBlock);
        List<Map<String,String>> rows = new ArrayList<>();
        Map<String,String> row1 = new HashMap<>();
        row1.put("c1", "c1");
        row1.put("c2", "1");
        row1.put("c3", "2222222222222222222222222222222222222222222222222222222222222");
        row1.put("c4", "3");

        Map<String,String> row2 = new HashMap<>();
        row2.put("c1", "c1");
        row2.put("c2", "1");
        row2.put("c3", "2222222222222222222222222222222222222222222222222222222222222");
        row2.put("c4", "3");

        Map<String,String> row3 = new HashMap<>();
        row3.put("c1", "c1");
        row3.put("c2", "1");
        row3.put("c3", "2222222222222222222222222222222222222222222222222222222222222");
        row3.put("c4", "3");

        Map<String,String> row4 = new HashMap<>();
        row4.put("c1", "c1");
        row4.put("c2", "1");
        row4.put("c3", "2222222222222222222222222222222222222222222222222222222222222");
        row4.put("c4", "3");

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        inInfo.getBlock(EiConstant.resultBlock).addRows(rows);

        return inInfo;
    }
}
