package com.baosight.wilp.ci.fy.service;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.ci.common.XSSExcelUtils;
import com.baosight.wilp.ci.fy.domain.CiCanteenCost;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * TODO(这里用一句话描述这个方法的作用)
 *
 * @Title: ServiceCIFY01
 * @ClassName: ServiceCIFY01
 * @Package：com.baosight.wilp.ci.fy.service
 * @author: liu
 * @date: 2022-06-10 15:27
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History: // 历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version> // 版本
 * <desc>   // 描述修改内容
 */
public class ServiceCIFY01 extends ServiceBase {

    @Override
    public EiInfo query(EiInfo inInfo) {
        return super.query(inInfo, "CIFY01.query", new CiCanteenCost());
    }

    /**
     * 导入excel的最大列数
     */
    private static final int COLUMN_COUNT_LIXU = 8;

    public EiInfo uploadFile(EiInfo inInfo){

        Map attr = inInfo.getAttr();

        List<Map<String, Object>> okList = new ArrayList<Map<String, Object>>();

        /**
         * 1.获取上传的excel文件
         */
        String docUrl = StringUtils.toString(attr.get("docUrl"));
        String fileType = StringUtils.toString(attr.get("fileType"));
        File file = new File(docUrl);
        InputStream in = null;

        if (file != null) {

            try {
                in = new FileInputStream(docUrl);
                /**
                 * 2.将excel文件通过框架API工具类XSExcelUtils.getDataByInputStream解析成二维数组
                 */
                String[][] data = null;
                if(".xls".equals(fileType)){
                    data = XSSExcelUtils.getXlsDataByInputStream(in, 0);
                }else if(".xlsx".equals(fileType)){
                    data = XSSExcelUtils.getXlsxDataByInputStream(in, 0);
                }
                if (data[0].length < COLUMN_COUNT_LIXU) {
                    String msg = "导入失败!当前导入文件的列数为:[" + (data[0].length) + "]列,模板中列数为[" + COLUMN_COUNT_LIXU + "]列,请重新选择正确的导入文件.";
                    inInfo.setMsg(msg);
                    inInfo.setStatus(-1);
                    return inInfo;
                }

                /**4.将excel文件转化为map*/
                for (int i = 1; i < data.length; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();

                    map.put("id", UUID.randomUUID().toString());
                    map.put("canteenName", data[i][0]);
                    map.put("canteenType", data[i][1]);
                    map.put("date", data[i][2]);
                    map.put("water", data[i][3]);
                    map.put("electricity", data[i][4]);
                    map.put("gas", data[i][5]);
                    map.put("labour", data[i][6]);
                    map.put("additionalCosts", data[i][7]);
                    map.put("memo", "");
                    map.put("recCreateTime", DateUtils.curDateTimeStr19());

                    okList.add(map);
                }

                dao.insert("CIFY01.insertByExcel",okList);

            } catch (Exception e) {
                e.printStackTrace();
                inInfo.setStatus(-1);
                inInfo.setMsg("导入失败!");
                return inInfo;
            }

        }
        inInfo.setStatus(1);
        return inInfo;
    }

}
