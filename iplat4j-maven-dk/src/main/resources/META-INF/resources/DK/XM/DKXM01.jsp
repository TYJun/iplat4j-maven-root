<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="保养项目管理">
	<div class="row">
		<div class="col-md-2">
			<EF:EFRegion id="tree" title="保养项目分类" fitHeight="true">
				<EF:EFTree id="menu" valueField="id" textField="classifyName" hasChildren="hasChildren" serviceName="DKXM01" methodName="queryTypeTree" style="height:660px;"/>
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
				<EF:EFWindow id="addWnd" width="40%" height="18%" title="在所选结点下添加子结点">
					<EF:EFRegion id="add_node_region" title="添加结点" style="margin-bottom: 0;">
						<div id="addNodeDiv">
							<div class="row">
								<EF:EFInput ename="classifyName" cname="分类名称" colWidth="10" required="true"/>
								<EF:EFInput ename="memo" cname="备注" colWidth="10" required="false"/>
							</div>
							
						</div>
						<div class="k-window-save k-popup-save">
							<EF:EFButton ename="addNode" cname="确定" layout="1" class="i-btn-wide"/>
						</div>
					</EF:EFRegion>
				</EF:EFWindow>
				<EF:EFWindow id="editWnd" width="40%" height="18%" title="编辑所选结点">
					<EF:EFRegion id="edit_node_region" title="编辑结点" style="margin-bottom: 0;">
						<div id="editNodeDiv">
							<div class="row">
								<EF:EFInput ename="EclassifyName" cname="分类名称" colWidth="10" required="true"/>
								<EF:EFInput ename="Ememo" cname="备注" colWidth="10" required="false"/>
							</div>
							
						</div>
						<div class="k-window-save k-popup-save">
							<EF:EFButton ename="editNode" cname="确定" layout="1" class="i-btn-wide"/>
						</div>
					</EF:EFRegion>
				</EF:EFWindow>
			</EF:EFRegion>
		</div>
		<div class="col-md-10">
			<EF:EFRegion id="inqu" title="保养项目">
				<div class="row">
					<EF:EFInput ename="typeName" cname="项目分类" />
					<EF:EFInput ename="itemName" cname="保养项目名称" />
					<EF:EFInput ename="typeId" cname="保养项目id" type="hidden"/>
				</div>
				
			</EF:EFRegion>
			<EF:EFRegion id="result" title="数据集" fitHeight="true">
				<EF:EFGrid blockId="result" autoDraw="no" serviceName="DKXM01">
					<EF:EFColumn ename="id" cname="主键" hidden="true"/>
					<EF:EFColumn ename="code" cname="项目编码" readonly="true" align="center" width="100"/>
					<EF:EFColumn ename="content" cname="项目名称" readonly="true" align="center" width="200"/>
					<EF:EFColumn ename="typeName" cname="上级分类" readonly="true" align="center" width="200"/>
					<EF:EFColumn ename="projectDesc" cname="项目描述" readonly="true" align="center" width="200"/>
					<EF:EFColumn ename="referenceValue" cname="项目参考值" readonly="true" align="center" width="70"/>
					<EF:EFColumn ename="memo" cname="备注" readonly="true" align="center" width="200"/>
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFWindow id="popData" url="" lazyload="true" width="35%" height="36%" title="保养项目新增" modal="true" />
			<EF:EFWindow id="popDataEdit" url="" lazyload="true" width="35%" height="36%" title="保养项目修改" modal="true" />
		</div>
	</div>
</EF:EFPage>