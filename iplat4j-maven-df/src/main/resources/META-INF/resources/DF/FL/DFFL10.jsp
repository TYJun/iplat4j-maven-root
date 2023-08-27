<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="设备系统分类">
	<div class="row">
		<div class="col-md-2">
			<EF:EFRegion id="tree" title="设备系统分类" fitHeight="true">
				<EF:EFTree id="menu" valueField="id" textField="classifyName" hasChildren="isLeaf" 
				serviceName="DFFL10" methodName="queryDFFLTree" style="height:560px;"/>
				<ul id="handleMenu" style="display: none">
					<li data-type="add">
						<span class="fa fa-plus"></span>添加
					</li>
					<li data-type="edit">
						<span class="fa fa-pencil"></span>编辑
					</li>
					<li data-type="delete">
						<span class="fa fa-trash"></span>删除
					</li>
				</ul>
				<EF:EFWindow id="addWnd" width="60%" height="30%" title="新增">
					<EF:EFRegion id="add_node_region" title="" style="margin-bottom: 0;">
						<div id="addNodeDiv">
							<div class="row">
								<EF:EFInput ename="classifyName" cname="分类名称" colWidth="10" required="true"/>
							</div>
							<div class="row">
								<EF:EFInput ename="parentId" cname="上级分类" colWidth="10" type="hidden"/>
								<%-- <EF:EFInput ename="parentName" cname="上级名称" colWidth="10" required="true"/> --%>
								<EF:EFTreeInput ename="parentName" cname="上级名称" 
									serviceName="DFFL10" methodName="queryDFFLTree" textField="classifyName"
									valueField="id" hasChildren="isLeaf" popupTitle=""
									root="{id: 'root',classifyName: '分类信息'}" clear="true" readonly="true"
									colWidth="10" required="true">
								</EF:EFTreeInput>
							</div>
							<div class="row">
								<EF:EFInput ename="memo" cname="备注" colWidth="10" type="textarea"/>
							</div>
						</div>
						<div class="k-window-save k-popup-save">
							<EF:EFButton ename="addNode" cname="确定" layout="1" class="i-btn-wide"/>
						</div>
					</EF:EFRegion>
				</EF:EFWindow>
				
				<EF:EFWindow id="editWnd" width="60%" height="30%" title="编辑所选结点">
					<EF:EFRegion id="edit_node_region" title="编辑结点" style="margin-bottom: 0;">
						<div id="editNodeDiv">
							<div class="row">
								<EF:EFInput ename="editClassifyName" cname="分类名称" colWidth="10" required="true"/>
							</div>
							
							<div class="row">
								<EF:EFInput ename="editMemo" cname="备注" colWidth="10" />
							</div>
						</div>
						<div class="k-window-save k-popup-save">
							<EF:EFButton ename="editNode" cname="确定" layout="1" class="i-btn-wide"/>
						</div>
					</EF:EFRegion>
				</EF:EFWindow>
				
				<EF:EFWindow id="showWnd" width="60%" height="30%" title="查看所选结点">
					<EF:EFRegion id="show_node_region" title="查看结点" style="margin-bottom: 0;">
						<div id="showNodeDiv">
							<div class="row">
								<EF:EFInput ename="showClassifyName" cname="分类名称" colWidth="10" readOnly="readOnly" required="true"/>
							</div>
							
							<div class="row">
								<EF:EFInput ename="showMemo" cname="备注" colWidth="10" readOnly="readOnly" />
							</div>
						</div>
						<div class="k-window-save k-popup-save">
							<EF:EFButton ename="showNode" cname="确定" layout="1" class="i-btn-wide"/>
						</div>
					</EF:EFRegion>
				</EF:EFWindow>
			</EF:EFRegion>
		</div>
		<div class="col-md-10">
			<EF:EFRegion id="inqu" title="查询区" showClear="true">
					<EF:EFInput ename="queryModuleId" cname="ID" type="hidden" />
					<EF:EFInput ename="queryClassifyName" cname="设备分类名称" />
					<EF:EFInput ename="queryClassifyMemo" cname="设备分类备注" />
			</EF:EFRegion>
			<EF:EFRegion id="result" title="数据集" fitHeight="true">
				<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true"
				checkMode="single,row" readonly="false" rowNo="true" isFloat="true" serviceName="DFFL10">
					<EF:EFColumn ename="id" cname="主键"  hidden="true"/>
					<EF:EFColumn ename="classifyCode" cname="设备分类编码" enable="false" align="center"/>
					<EF:EFColumn ename="classifyName" cname="设备分类名称" enable="false" align="center"/>
					<EF:EFColumn ename="memo" cname="设备分类备注" enable="false" align="center"/>
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFWindow id="popData" url="" lazyload="true" width="40%" height="40%" title="设备参数" modal="true" />
		</div>
	</div>
</EF:EFPage>