<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="宿舍信息管理">
			<EF:EFRegion id="inqu" title="查询区" showClear="true">
				<div class="row">

                 <%--   <EF:EFSelect cname="楼"  ename="buildingCode">
						<EF:EFOption label="全部" value=""/>
                   <EF:EFCodeOption codeName="wilp.dm.rm.buildingCode"/>--%>
                   <%-- </EF:EFSelect>
                    <EF:EFSelect cname="层"  ename="floorCode">
						<EF:EFOption label="全部" value=""/>
                        <EF:EFCodeOption codeName="wilp.dm.rm.floorCode"/>
                    </EF:EFSelect>--%>

					<EF:EFInput ename="buildingCode" cname="楼" />
					<EF:EFInput ename="floorCode" cname="层" />
					<EF:EFInput ename="roomNo" cname="房间号" />
					<EF:EFSelect ename="typeCode" cname="宿舍类型" >
						<EF:EFOption label="请先择房间类型" value=""/>    
					    <EF:EFOption label="男生宿舍" value="男"/>     
					    <EF:EFOption label="女生宿舍" value="女"/>
    				</EF:EFSelect>

					<div class="col-md-4" style="text-align: right" id="inqu"></div>
					<div class="col-md-4" style="text-align: right" id="reset"></div>
					<%-- <EF:EFInput ename="queryDept" cname="状态" /> --%>
				</div>
				
			</EF:EFRegion>
			<EF:EFRegion id="result" title="房间信息" fitHeight="true">
				<EF:EFGrid blockId="result" autoDraw="no" serviceName="DMRM01">
					<EF:EFColumn ename="id" cname="id" readonly="true" hidden="true"/>
					<EF:EFColumn ename="buildingCode" cname="楼" readonly="true" width="70"  />
					<EF:EFColumn ename="floorCode" cname="层" readonly="true" width="70"  />
					<EF:EFColumn ename="roomNo" cname="房间号" readonly="true" width="70" />
					<%-- <EF:EFColumn ename="roomStatus" cname="状态" readonly="true"/> --%>
					<EF:EFColumn ename="bedNum" cname="宿舍类型" readonly="true" width="60"  />
					<EF:EFColumn ename="remainBed" cname="剩余床位数" readonly="true" width="60"  />
					<%-- <EF:EFColumn ename="dormsBedOpen" cname="被封锁床位" readonly="true"/> --%>
					<EF:EFColumn ename="typeCode" cname="男/女生宿舍" readonly="true" width="70"  />
					<%-- <EF:EFColumn ename="alreadyNum" cname="已住人数" readonly="true"/> --%>
					<EF:EFColumn ename="dormsPosition" cname="宿舍位置" readonly="true" width="60"  />
<%--					<EF:EFColumn ename="dormsIfwc" cname="独立卫生间" readonly="true" width="70"  />--%>
					<EF:EFColumn ename="dormsAreas" cname="房屋面积" readonly="true" width="60"  />
					<EF:EFColumn ename="rent" cname="房租" readonly="true" width="60"  />
					<EF:EFColumn ename="hospitalManageFee" cname="医院管理费" readonly="true" width="60"  />
					<EF:EFColumn ename="propertyManageFee" cname="物业管理费" reado物业管理费nly="true" width="60"  />
					<%-- <EF:EFColumn ename="manageFee" cname="管理费" readonly="true" width="60"  /> --%>
					<%-- <EF:EFColumn ename="isBatch" cname="是否批量" readonly="true"/> --%>
					<EF:EFColumn ename="note" cname="备注信息" readonly="true"/>
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFWindow id="popData" url="" lazyload="true" width="80%" height="70%" title="新增" modal="true" />
			<EF:EFWindow id="popDataModify" url="" lazyload="true" width="60%" height="90%" title="修改" modal="true" />
		</div>
</EF:EFPage>
