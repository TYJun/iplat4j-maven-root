package com.baosight.wilp.hr.pb.excel.style;

import com.alibaba.excel.metadata.Head;
import com.baosight.wilp.hr.pb.excel.util.StyleUtil;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;
import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 自定义样式拦截器-复杂表头样式的使用
 *
 * @Author: nxf
 * @Date: 2021/1/17 14:31
 */
public class HeadStyleWriteHandler extends AbstractCellStyleStrategy {

    
    private WriteCellStyle headWriteCellStyle;
    private List<WriteCellStyle> contentWriteCellStyleList;

    private CellStyle headCellStyle;
    private List<CellStyle> contentCellStyleList;

    public HeadStyleWriteHandler(WriteCellStyle headWriteCellStyle,
        List<WriteCellStyle> contentWriteCellStyleList) {
        this.headWriteCellStyle = headWriteCellStyle;
        this.contentWriteCellStyleList = contentWriteCellStyleList;
    }

    public HeadStyleWriteHandler(WriteCellStyle headWriteCellStyle, WriteCellStyle contentWriteCellStyle) {
        this.headWriteCellStyle = headWriteCellStyle;
        contentWriteCellStyleList = new ArrayList<WriteCellStyle>();
        contentWriteCellStyleList.add(contentWriteCellStyle);
    }
    
    /**
     * 复杂表头自定义样式队列，先进先出，方便存储
     */
    private ArrayBlockingQueue<ComplexHeadStyles> headStylesQueue;
    /**
     * WorkBoot
     */
    private Workbook workbook;

    /**
     * 构造方法，创建对象时传入需要定制的表头信息队列
     *
     */
    public HeadStyleWriteHandler(ArrayBlockingQueue<ComplexHeadStyles> headStylesQueue) {
        this.headStylesQueue = headStylesQueue;
    }
    
    /**
     * 构造方法，创建对象时传入需要定制的表头信息
     *
     */
    public HeadStyleWriteHandler(ComplexHeadStyles headStyles) {
        this.headStylesQueue = new ArrayBlockingQueue<>(1);
        this.headStylesQueue.add(headStyles);
    }

    @Override
    protected void initCellStyle(Workbook workbook) {
        this.workbook = workbook;
        if (headWriteCellStyle != null) {
            headCellStyle = StyleUtil.buildHeadCellStyle(workbook, headWriteCellStyle);
        }
        if (contentWriteCellStyleList != null && !contentWriteCellStyleList.isEmpty()) {
            contentCellStyleList = new ArrayList<CellStyle>();
            for (WriteCellStyle writeCellStyle : contentWriteCellStyleList) {
                contentCellStyleList.add(StyleUtil.buildContentCellStyle(workbook, writeCellStyle));
            }
        }
    }

    @Override
    protected void setHeadCellStyle(Cell cell, Head head, Integer relativeRowIndex) {
        
        WriteCellStyle writeCellStyle = new WriteCellStyle();

        if (headStylesQueue != null && !headStylesQueue.isEmpty()) {

            ComplexHeadStyles complexHeadStyle = headStylesQueue.peek();
            // 取出队列中的自定义表头信息，与当前坐标比较，判断是否相符
            if (cell.getColumnIndex() == complexHeadStyle.getY() && relativeRowIndex.equals(complexHeadStyle.getX())) {
                // 设置自定义的表头样式
                writeCellStyle.setFillForegroundColor(complexHeadStyle.getIndexColor());
                // 样式出队
                headStylesQueue.poll();
            }
        }

        // WriteCellStyle转换为CellStyle
        CellStyle headCellStyle = StyleUtil.buildHeadCellStyle(workbook, writeCellStyle);
        // 设置表头样式
        cell.setCellStyle(headCellStyle);

    }

    @Override
    protected void setContentCellStyle(Cell cell, Head head, Integer relativeRowIndex) {
        if (contentCellStyleList == null || contentCellStyleList.isEmpty()) {
            return;
        }
        cell.setCellStyle(contentCellStyleList.get(relativeRowIndex % contentCellStyleList.size()));
    }

}
