 package com.baosight.wilp.hr.pb.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.IndexedColors;

import com.baosight.wilp.hr.pb.excel.style.ComplexHeadStyles;
import com.baosight.wilp.hr.pb.excel.style.HeadStyleWriteHandler;
import com.baosight.wilp.hr.pb.excel.write.builder.ExcelWriterSheetBuilder;



public class excelTest {

     
     public static void writeTest() {
         String fileName = "d:/file/dynamicHeadWrite" + System.currentTimeMillis() + ".xlsx";
         EasyExcel.write(fileName).head(head()).sheet("模板").doWrite(data());
     }
     
     public static void testWrite() {
         // 自定义表头策略
         HeadStyleWriteHandler headStyleWriteHandler=new HeadStyleWriteHandler(new ComplexHeadStyles(1,1,IndexedColors.SKY_BLUE.getIndex()));

         String fileName = "d:/file/adynamicHeadWrite" + System.currentTimeMillis() + ".xlsx";
         // 写Excel
         ExcelWriterSheetBuilder sheet = EasyExcel.write(new File(fileName)).head(head())
                 .registerWriteHandler(headStyleWriteHandler)
                 .autoCloseStream(true)
                 .sheet("模板");
         sheet.doWrite(data());
     }
     
     public static void main(String[] args) {
         testWrite();
         //writeTest();
    }
     
     
     public static List<List<String>> head() {
         List<List<String>> list = new ArrayList();
         List<String> head0 = new ArrayList();
         head0.add("字符串" + System.currentTimeMillis());
         List<String> head1 = new ArrayList();
         head1.add("数字" + System.currentTimeMillis());
         List<String> head2 = new ArrayList();
         head2.add("日期" + System.currentTimeMillis());
         list.add(head0);
         list.add(head1);
         list.add(head2);
         return list;
     }


     public static List<DemoData2> data() {
         List<DemoData2> list = new ArrayList();
         for (int i = 0; i < 10; i++) {
             DemoData2 data = new DemoData2();
             data.setWorkNo("dc002");
             data.setWorkName("张sorry" + i);
             list.add(data);
         }
         return list;
     }

}
