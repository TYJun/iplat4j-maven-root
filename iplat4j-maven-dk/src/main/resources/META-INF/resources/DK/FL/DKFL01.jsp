<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="保养系统分类">
	<div class="row">
		<div class="col-md-2">
			<EF:EFRegion id="tree" title="保养分类菜单树" fitHeight="true">
				<EF:EFTree id="menu" valueField="id" textField="classifyName" hasChildren="hasChildren" 
				serviceName="DKFL01" methodName="queryTypeTree" style="height:560px;"/>
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
				<EF:EFWindow id="addWnd" width="60%" height="30%" title="新增分类">
					<EF:EFRegion id="add_node_region" title="添加分类" style="margin-bottom: 0;">
						<div id="addNodeDiv">
							<div class="row">
								<EF:EFInput ename="classifyName" cname="分类名称" colWidth="10" required="true"/>
							</div>
							<div class="row">
								<EF:EFInput ename="parentId" cname="上级分类" colWidth="10" type="hidden"/>
								<EF:EFInput ename="parentName" cname="上级名称" colWidth="10" required="true" readonly="true"/>
							</div>
							<div class="row">
								<EF:EFInput ename="memo" cname="备注" colWidth="10" />
							</div>
						</div>
						<div class="k-window-save k-popup-save">
							<EF:EFButton ename="addNode" cname="确定" layout="1" class="i-btn-wide"/>
						</div>
					</EF:EFRegion>
				</EF:EFWindow>
				<EF:EFWindow id="editWnd" width="60%" height="30%" title="编辑分类">
					<EF:EFRegion id="edit_node_region" title="编辑分类" style="margin-bottom: 0;">
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
				
				<EF:EFWindow id="showWnd" width="60%" height="30%" title="查看分类">
					<EF:EFRegion id="show_node_region" title="查看分类" style="margin-bottom: 0;">
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
			
					
			<EF:EFRegion id="inqu" title="查询区">
					<EF:EFInput ename="classifyNameS" cname="保养分类名称" />
					<EF:EFInput ename="classifyNameSS" cname="保养分类名称" type="hidden"/>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="数据集" fitHeight="true">
				<EF:EFGrid blockId="result" autoDraw="no" serviceName="DKFL01" >
					<EF:EFColumn ename="id" cname="主键"  hidden="true"/>
					<EF:EFColumn ename="classifyCode" cname="保养分类编码" />
					<EF:EFColumn ename="classifyName" cname="保养分类名称" />
					<EF:EFColumn ename="memo" cname="保养分类备注" />
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFWindow id="popData" url="" lazyload="true" width="40%" height="40%" title="设备参数" modal="true" />
		</div>
	</div>
</EF:EFPage>
<script type="text/javascript">
	
</script>