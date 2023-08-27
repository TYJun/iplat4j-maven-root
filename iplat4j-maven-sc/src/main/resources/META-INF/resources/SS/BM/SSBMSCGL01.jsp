<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- 菜品信息 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 引用/smp_meal/WebContent/WEB-INF/fragments/MEAL-head.jsp -->
<EF:EFPage prefix="MEAL">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-deptName" cname="科室名称" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="inqu_status-0-workName" cname="送餐人员姓名" colWidth="4" ratio="3:8"/>
			<%-- <EF:EFInput ename="inqu_status-0-deptType" cname="送餐人员类型" colWidth="4" ratio="3:8"/> --%>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="no" rowNo="false" readonly="false" checkMode="row">
		    <!-- 隐藏列 -->
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="deptNum" cname="科室编码"  width="100" hidden="true"/>
			<!-- 展示列 -->
			<EF:EFColumn ename="workNo" cname="工号" width="100" align="center" readonly="true"/>
			<EF:EFColumn ename="workName" cname="送餐人员姓名" width="150" align="center"  readonly="true" />
			<EF:EFColumn ename="deptName" cname="科室名称" width="100" align="center" readonly="true"/>
			
		</EF:EFGrid>
	</EF:EFRegion>
	
	<!-- 弹窗 -->
	<EF:EFWindow id="shEdit" width="550px" height="400px" modal="true" url="" title="录入" style="display:none;">
		
		<EF:EFRegion id="edit" title="送餐人员选择" showClear="true">
			<div class="row">
				<EF:EFInput ename="workNo" cname="工号" colWidth="10" required="true" maxLength="50" />
			</div>
			<div class="row" >
				<EF:EFInput ename="workName" cname="姓名" colWidth="10" required="true" maxLength="200"/>
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="deptChoice" title="病区选择" showClear="true"  >

			<EF:EFGrid blockId="result2" autoDraw="no" rowNo="false" autoBind="false" showCount="false" readonly="true">
			    <EF:EFColumn ename="id" cname="id" width="100" hidden="true" />   
			    <EF:EFColumn ename="deptCode" cname="病区编码"  width="100" align="center" readonly="true" required="true"/>
			    <EF:EFColumn ename="deptName" cname="病区名称" width="100" align="center" readonly="true" required="true"/>
			</EF:EFGrid>
		</EF:EFRegion>
	  <EF:EFButton ename="submit" cname="确认"  class="k-button window-btn" style="float:left;"/>
	  <EF:EFButton ename="cancel2" cname="取消"  class="k-button window-btn" style="float:right;"/>
	</EF:EFWindow>

		<!-- 弹窗 -->
	<EF:EFWindow id="ksEdit" width="550px" height="400px" modal="true" url="" title="录入" style="display:none;">
		<EF:EFRegion id="dept" title="科室" showClear="true">
			<div class="row">
				<EF:EFInput ename="inqu_status-0-bingqu" cname="科室" colWidth="10" maxLength="50" />
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="deptChoice" title="病区选择" showClear="true" >
			<EF:EFGrid blockId="result3" autoDraw="no" rowNo="false" autoBind="false" 
			        serviceName="SSBMSCGL01" queryMethod="query2">
			    <EF:EFColumn ename="id" cname="id" width="100" hidden="true" />   
			    <EF:EFColumn ename="deptCode" cname="病区编码"  width="100" align="center" readonly="true"/>
			    <EF:EFColumn ename="deptName" cname="病区名称" width="100" align="center" readonly="true"/>
			</EF:EFGrid>
		</EF:EFRegion>
	  <EF:EFButton ename="confirm" cname="确认"  class="k-button window-btn" style="float:left;"/>
	  <EF:EFButton ename="cancel" cname="取消"  class="k-button window-btn" style="float:right;"/>
	</EF:EFWindow>
</EF:EFPage>
<script type="text/javascript">
var editValidator = null;
IPLATUI.EFWindow = {
        "ksEdit": {
            close: function (e) {
                //清空result3Grid
                result3Grid.unCheckAllRows();
               
            }
        },
        
        "shEdit": {
            close: function (e) {
                //清空result2Grid
            	$("#edit-trash").click();
            	result2Grid.removeRows(result2Grid.getCheckedRows());
            }
        }
    }
	IPLATUI.EFGrid = {
        "result2": {
        	//不显示翻页栏
        	pageable: false,
        	beforeEdit: function (e) {
        		e.preventDefault();
       	    }
        }
	}

    
$(function() {
	
    //录入
    $("#ADD").on("click", function(e) {
        submitType = "insert";
        shEditWindow.setOptions({"title":"送餐人员添加"});
        //打开弹窗
        shEditWindow.open().center();
    });
    
    
    $("#ADD2").on("click", function(e) {
        submitType = "insert";
        ksEditWindow.setOptions({"title":"送餐人员病区添加"});
        //打开弹窗
        ksEditWindow.open().center();
        $("#QUERY1").click();
    });
    
    
    $("#DELETE2").on("click", function(e) {
    	result2Grid.removeRows(result2Grid.getCheckedRows());
    });
    
    
    //提交
    $("#confirm").on("click", function(e) {
    	result2Grid.addRows(result3Grid.getCheckedRows());
    	$("#cancel").click();
    });
    $("#cancel").on("click", function(e) {
        window['ksEditWindow'].close();
    });
    
    
    //提交
    $("#submit").on("click", function(e) {
    	
		if (!editValidator.validate()) {
			//校验不通过
			return ;
		}
    	
        var eiInfo = new EiInfo();
        //获取编辑的值
        var value = {
        		workName : IPLAT.EFInput.value($("#workName")),
        		workNo : IPLAT.EFInput.value($("#workNo"))
        };
        var selectRow = resultGrid.getCheckedRows()[0];
        if(!selectRow){
            selectRow = {};
        }
        //复制数据
        $.extend(selectRow, value);
        
        var rows = [];rows[0] = selectRow;
        eiInfo.set("submit", rows);
        //病区数据
        eiInfo.set("result2", result2Grid.getDataItems());
    	
        //提交数据
        EiCommunicator.send("SSBMSCGL01", "insert", eiInfo, {
            onSuccess : function(ei) {
                if(ei.status <= 0) {
                    NotificationUtil(ei.getMsg(), "error");
                }else {
                    NotificationUtil(ei.getMsg(), "success");
                    //刷新grid
                    refreshResultGrid();
                    //关闭弹窗
                    $("#cancel").click();
    	            $("#cancel2").click();
                    
                }
            }
        });
    });
    
    
    
    $("#cancel2").on("click", function(e) {
        window['shEditWindow'].close();
    });
        
    
    $("#QUERY").on("click", function(e) {
        refreshResultGrid();
        //result3Grid.dataSource.page(1);
    });
    //页面加载时查询一次
    $("#QUERY").click();
    
    $("#QUERY1").on("click", function(e) {
    	result3Grid.dataSource.page(1);
    });
    
    editValidator = IPLAT.Validator({ id: "edit" });
    //页面加载时查询一次
    //$("#QUERY1").click();
});	
</script>