<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
    <div class="col-md-12">
        <EF:EFRegion id="inqu" title="近期汇总" showClear="true" >
            <EF:EFSelect ename="splitDate" cname="时间节点">
                <EF:EFOption label="全部" value="" />
                <EF:EFOption label="近一年" value="12" />
                <EF:EFOption label="近六个月" value="6" />
                <EF:EFOption label="近三个月" value="3" />
                <EF:EFOption label="近一个月" value="1" />
            </EF:EFSelect>
        </EF:EFRegion>
        <EF:EFRegion id="result" title="合同综合预览" autoDraw="no" autoBind="true">
            <EF:EFGrid blockId="result" autoDraw="no" serviceName="CMZH00" methodName="querySplitDate">
                <EF:EFColumn ename="total" cname="合同总数" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="overdue" cname="超期" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="threeMonth" cname="临期三个月" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="twoMonth" cname="临期两个月" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="oneMonth" cname="临期一个月" width="100" enable="false" align="center"/>
                <EF:EFColumn ename="normal" cname="正常" width="100" enable="false" align="center"/>
            </EF:EFGrid>
            <div id="mainA" class="col-md-7" style="width: 1000px;height:500px;"></div>
            <div id="mainB" class="col-md-4" style="width: 420px;height:500px;"></div>
        </EF:EFRegion>
    </div>
</EF:EFPage>
<script src="${ctx}/CONTRACT/common/echarts/echarts.js"></script>