<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>

	<div class="col-md-9">
		<EF:EFRegion id="result" title="服务项目分类列表">
			<EF:EFGrid blockId="result" autoDraw="no" >
                <EF:EFColumn ename="DC_id" cname="DC_id" width="100" hidden="true" />
                <EF:EFColumn ename="code" cname="视频源" width="100" />
                <EF:EFColumn ename="name" cname="视频地址" width="100" />
                <%--                <EF:EFColumn ename="group_id" cname="院区标识" width="100"  />--%>
<%--                <EF:EFColumn ename="type" cname="设备大类" width="100" />--%>
                <EF:EFColumn ename="t_area_classify_id" cname="显示通道" width="100" />
                <EF:EFColumn ename="group_id" cname="描述" width="100"  />
                <EF:EFColumn ename="weight" cname="权重" width="100" />
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
</EF:EFPage>
<script type="text/javascript">
	$(function(){
		//新增按钮
		$("#ADDDEPT").on("click", function(e) {
            popData("","adddept",$("#inqu_status-0-parentId").val());
          //  popData($("#inqu_status-0-parentId").val());
		});
		//编辑按钮
		$("#EDIT").on("click", function(e) {
			if (datagrid == null) {
				IPLAT.alert("请先选择一条记录进行修改");
			} else {
				popData(datagrid.id, "edit");
				datagrid = null;
			}
		});
		//移动按钮
		$("#MOVE").on("click", function(e) {
			if (datagrid == null) {
				IPLAT.alert("请先选择一条记录进行移动");
			} else {
                popData2(datagrid.id);
        //        datagrid = null;
			}
		});

        //查看按钮
        $("#VIEW").on("click", function(e) {
            if (datagrid == null) {
                IPLAT.alert("请先选择一条记录进行修改");
            } else {
                popData3(datagrid.id, "edit");
                datagrid = null;
            }
        });
	
		//查询
		$("#QUERY").on("click", function(e) {
			resultGrid.dataSource.query(1);
		});

		//重置按钮
		$("#REQUERY").on("click", function(e) {
	//		document.getElementById("inqu-trash").click();
			resultGrid.dataSource.query($("#inqu_status-0-parentId").val());
		});
		//新增区域
        $("#INCREASE").on("click", function(e) {
            resultGrid.dataSource.query(1);
        });
	})

</script>