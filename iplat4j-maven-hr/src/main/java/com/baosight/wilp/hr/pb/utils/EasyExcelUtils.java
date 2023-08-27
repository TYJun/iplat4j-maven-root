package com.baosight.wilp.hr.pb.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;


/**
 * (这里用一句话描述这个类的作用)
 *
 * @Title:
 * @ClassName:
 * @Package：
 * @author: xiajunqing
 * @date:
 * @version: V1.0
 * @Copyright: www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class EasyExcelUtils {

    /**
     * 读取Excel文件，解析成MapList格式
     * head:表头
     * body:表体
     * @param fileName 文件路径
     * **/
    public static Map<String,List> redExcel(String fileName){
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        List<Map<Integer, String>> listHead = new ArrayList<Map<Integer, String>>();
        List<DemoData2> listBody = new ArrayList<DemoData2>();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData2.class, new AnalysisEventListener<DemoData2>() {
            /**
             * -这里会一行行的返回头
             */
            @Override
            public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
                listHead.add(headMap);
            }

            @Override
            public void invoke(DemoData2 data, AnalysisContext context) {
                listBody.add(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) { }
        }).sheet().doRead();
        Map<String,List> mapList = new HashMap<String, List>();
        mapList.put("head", listHead);
        mapList.put("body", listBody);
        return mapList;
    }
    

    public static void main(String[] args) {
        String fileName = "d:/file/demo1"+System.currentTimeMillis()+".xlsx";
    }
    
    public static void read(){
        String fileName = "d:/file/demo1.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        List<Object> list = new ArrayList<Object>();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData2.class, new AnalysisEventListener<DemoData2>() {
            /**
             * 这里会一行行的返回头
             *
             * @param headMap
             * @param context
             */
            @Override
            public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
                list.add(headMap);
                System.out.println("解析到一条头数据:{}"+ headMap);
            }

            @Override
            public void invoke(DemoData2 data, AnalysisContext context) {
                System.out.println("解析到一条数据:{}"+ JSON.toJSONString(data));
                list.add(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) { }
        }).sheet().doRead();
        System.out.println(list);
    }
    
    
    private static List<DemoData2> data() {
        List<DemoData2> list =  new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData2 data = new DemoData2();
            data.setWorkNo("dc002");
            data.setWorkName("张sorry" + i);
            list.add(data);
        }
        return list;
    }
    
    private static List<List<String>> head() {
        List<List<String>> list = new ArrayList<>();
        List<String> head0 = new ArrayList<>();
        head0.add("字符串" + System.currentTimeMillis());
        List<String> head1 = new ArrayList<>();
        head1.add("数字" + System.currentTimeMillis());
        List<String> head2 = new ArrayList<>();
        head2.add("日期" + System.currentTimeMillis());
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }
}
