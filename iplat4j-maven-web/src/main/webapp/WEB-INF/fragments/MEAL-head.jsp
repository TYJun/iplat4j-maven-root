<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%-- 引入项目定制的公共 CSS --%>
<link href="${ctx}/MEAL/common/css/common.css" rel="stylesheet">
<%-- 引入项目公共的 JS --%>
<script src="${ctx}/MEAL/common/js/common.js"></script>
<%-- 引入第三方的 JS 库 --%>
<script src="${ctx}/MEAL/common/echarts/echarts4.9.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="currentTime" class="java.util.Date" />
<fmt:formatDate type="time" value="${currentTime}"
	pattern="yyyy-MM dd HH:mm:ss" var="formatDate" />

<%-- 引用方法 <EF:EFPage prefix="MEAL"> MEAL是文件名的前缀--%>
<%-- 少量的样式，可以在 head.jsp 中直接定义 --%>
<style>
/* 弹窗按钮样式 */
.window-btn {
	width: 90px !important;
	height: 30px !important;
	margin-right: 15px;
}
.k-grid-toolbar{
    height : 38px !important;
}
.k-grid-toolbar .grid_toolbar{
    height : 36px !important;
}
.xplat-float-right{
    height : 25px !important;
}

.xplat-float-right button{
    height : 100% !important;
}

.xplat-float-right button .fa{
    font-size: 14px;
}


/* 超链接下划线 */
a{
	text-decoration:underline
}

/* ADD按钮样式 */
.kendo-xplat-ADD {
	float: left;
}

/* 删除展示图片 */
.deleteBtn {
	position: absolute;
	width: 18px;
	height: 18px;
	float: right;
	z-index: 100;
}

.deleteBtn button {
	border: 0px;
	width: 13px;
	height: 16px;
}

.deleteBtn button i {
	background-position: -126px -252px;
}
/* 文件上传控件 */
#uploaddiv {
	border: 1px solid #C0DCFF;
}

#uploaddiv .block-content {
	padding: 0px;
	height: 200px;
}

.upload-file1 .k-header {
	border: 0px;
}
/* 图片展示的样式 */
.showImg {
	width: 245px;
	float: left;
	margin-right: 10px;
}

.imgDiv {
	display: inline-block;
}
/* 在grid中展示的图片 */
.gridImg {
	height: 50px;
	width: 50px;
}

.gridImgDiv {
	height: 50px;
	width: 60px;
}
/* 菜品组成grid */
#ef_grid_description {
	height: 170px;
	overflow-y: scroll;
}
</style>

<%-- 覆盖默认的 IPLATUI.Config 配置 --%>
<script type="text/javascript">
	$.extend(true, IPLATUI.Config, {
	    EFGrid : {
	        pageable : {
	            pageSize : 10, // DataSource 设置会覆盖此处设置
	            pageSizes : [10, 20, 50, 100 ]
	        }
	    }
	});
	/** 根据key获取initLoad中的指定Block*/
	function getInitLoadBlocks(key) {
		if (key) {
			return _getEi().blocks[key];
		} else {
			return _getEi().blocks;
		}
	}
	/** 根据key获取initLoad中指定Block的map值*/
	function getBlocksMappedRows(key) {
		return getInitLoadBlocks(key).getMappedRows();
	}

	/** 刷新resultGrid*/
	function refreshResultGrid(i) {
		resultGrid.dataSource.page(i ? i : 1);
	}

	/* img转换base64,带回调 */
	function imgChange(file, callback) {
		//获取文件域中选中的图片
		var image = file;
		//实例化文件读取对象
		var reader = new FileReader();
		//将文件读取为 DataURL,也就是base64编码
		reader.readAsDataURL(image);
		//文件读取成功完成时触发
		reader.onload = function(ev) {
			//获得文件读取成功后的DataURL,也就是base64编码
			var dataURL = ev.target.result;
			//将DataURL码赋值给img标签
			//document.querySelector("img").src = dataURL;
			callback(dataURL);
		}
	}
	
	/* img压缩，转换base64,带回调,encoderOptions:图片质量 */
	function imgCompress(file,encoderOptions, callback ) {
		//实例化文件读取对象
		var reader = new FileReader();
		//文件读取成功完成时触发
		reader.onload = function(e) {
			var image = $('<img/>');
			image.load(function(){
				//创建画布
				var canvas = document.createElement('canvas');
				ctx = canvas.getContext("2d");
				imageWidth = this.width,
				imageHeight = this.height,
				offsetX = 0,
				offsetY = 0;
				
				canvas.width = imageWidth;
				canvas.height = imageHeight;
				//清理画布
				ctx.clearRect(0,0,imageWidth,imageHeight);
				//绘制图像
				ctx.drawImage(this,offsetX,offsetY,imageWidth,imageHeight);
				//设置图片质量，质量越低压缩率越高
				encoderOptions = encoderOptions ? encoderOptions : 0.3;
				var img64 = canvas.toDataURL("image/jpeg",encoderOptions);
				callback(img64);
			});
			//填充图片
			image.attr('src',e.target.result);
		};
		//将文件读取为 DataURL,也就是base64编码
        reader.readAsDataURL(file);
	}
	
	/**自定义文件上传*/
	$(function() {
		/* <EF:EFUpload ename="fileForm" docTag="hk_file11" path="MEAL"/> */
	    IPLAT.FileUploader = function(options) {
	        options = {
	            id : "fileForm",
	            ename : "fileForm",
	            serviceName : "SSAEBT01",
	            methodName : "uploadFile"
	        };
	        var element = $("#" + options.id);
	        // 文件导入后会跳转到这里指定到 jsp，可增加自定义参数
	        var saveUrl = IPLATUI.CONTEXT_PATH + "/SS/BM/SSBMFILE.jsp?ename="
	                    + options.ename + "&serviceName=" + options.serviceName
	                    + "&methodName=" + options.methodName;
	        $.extend(options, {
	            async : {
	                saveUrl : saveUrl
	            }
	        });
	        return element.kendoUpload(options).data("kendoUpload");
	    };
	});
</script>