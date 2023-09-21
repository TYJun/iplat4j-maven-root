<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage>
    <EF:EFRegion id="result" title="新增物资" fitHeight="true">

        <div class="row">
            <EF:EFInput ename="matCode" cname="物资编码(不填自动生成)" colWidth="4" maxLength="255"/>

            <EF:EFInput ename="matName" cname="物资名称" colWidth="4" required="true" maxLength="100"/>

            <EF:EFTreeInput ename="matClassId" cname="物资分类" bindId="tree01" readonly="true"
                            serviceName="ACGM01" methodName="queryTree"
                            textField="matClassName" valueField="label" hasChildren="leaf"
                            root="{label: 'root',matClassName: '根节点'}" popupTitile="上级物资分类" clear="true"
                            colWidth="4" required="true">
            </EF:EFTreeInput>
            <EF:EFInput ename="matSpec" cname="物资规格" colWidth="4" maxLength="100"/>
        </div>
        <div class="row">
            <EF:EFInput ename="matModel" cname="物资型号" colWidth="4" maxLength="100"/>
<%--            <EF:EFSelect ename="unit" cname="计量单位">--%>
<%--                <EF:EFCodeOption codeName="wilp.ac.gm.unit" textField="label" valueField="value"/>--%>
<%--            </EF:EFSelect>--%>
<%--            <EF:EFSelect ename="unit" cname="计量单位" colWidth="3" ratio="4:8"--%>
<%--                              serviceName="ACGM01" queryMethod="queryUnit" filter="contains">--%>
<%--                <EF:EFOptions blockId="unit" textField="itemCname" valueField="unit"/>--%>
<%--            </EF:EFSelect>--%>
            <EF:EFSelect ename="unit" cname="计量单位" resultId="unit"
                         serviceName="ACGM01" methodName="queryUnit" textField="itemCname" valueField="itemCode" filter="contains">
                <EF:EFOption label="全部" value="" />
            </EF:EFSelect>

            <EF:EFInput ename="price" cname="订购单价(元)" colWidth="4" maxLength="100" data-rules="non_negative_number"/>


        </div>
        <div class="row">
                <%--			<EF:EFPopupInput ename="supplier" cname="供应商"--%>
                <%--							 popupTitle="供应商选择"  readOnly="true"--%>
                <%--							 popupType="ServiceGrid" serviceName="ACSU01" methodName="querySupplier" resultId="result"--%>
                <%--							 valueField="supplierCode" textField="supplierName"--%>
                <%--							 columnEnames="supplierCode,supplierName" columnCnames="供应商编码,供应商名称" />--%>

            <EF:EFInput ename="manufacturer" cname="制造商" colWidth="4" maxLength="500"/>
            <EF:EFSelect ename="matTypeCode" cname="物资大类">
                <EF:EFCodeOption codeName="wilp.ac.gm.type" textField="label" valueField="value"/>
            </EF:EFSelect>
            <EF:EFInput ename="packfactor" cname="包装系数" colWidth="4" maxLength="100" />
        </div>

        <div class="row">
            <EF:EFInput ename="remark" cname="备注" colWidth="4" type="textarea" maxLength="500"/>
            <EF:EFInput ename="pictureUri" cname="图片地址" colWidth="4"  type="hidden"/>
        </div>

        <div style=" margin-top: 15px;">
            <li style="margin-left:40%;list-style-type:none"><span style="color: red;">* </span>上传图片只支持<span
                    id="format"></span>格式，图片大小最大为<span id="maxSize"></span>kb
            </li>
            <br/>
            <form id="pictureForm" enctype="multipart/form-data" method="post">
                <input style="margin-left:40%;" id="pictureFile" type="file" name="file">
                <button type="button" class="i-btn-lg" style="float: right;" id="uploadButton">
                    上传
                </button>
            </form>
            <img id="imageShow" width="190px" height="100px"
                 style="position: relative;left: 40%;margin-bottom: 20px;margin-top: 20px;">
        </div>
    </EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">

    $("#matClassId").val(__ei.matClassId);
    $("#maxSize").text(__ei.maxSize);
    $("#format").text(__ei.format);
    $("#matClassId_textField").val(__ei.matClassName);
    var validator = IPLAT.Validator({
        id: "result"
    });

    $("#SUBMIT").on("click", function (e) {
        var pictureUri = $("#pictureUri").val();
        var matCode = IPLAT.EFInput.value($("#matCode")).replace(" ","");
        // //参数校验
        // if (!validate(pictureUri)) {
        //     return;
        // }
        //
        // //参数校验
        // function validate(pictureUri) {
        //
        //
        //     if (isEmpty(pictureUri)) {
        //         IPLAT.alert("上传图片不能为空");
        //         return false;
        //     }
        //
        //
        //     return true;
        // }

        function isEmpty(parameter) {
            if (parameter == undefined || parameter == null) {
                return true;
            } else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == "") {
                return true;
            } else {
                return false;
            }
        }


        //参数校验
        if (!validator.validate()) {
            return;
        }
        // 如果价格是 空 则 设置为默认值 0
        var price = IPLAT.EFInput.value($("#price"));
        if (!price) {
            IPLAT.EFInput.value($("#price"), '0.00');
        }
        // var picCode=pictureUri.substring(pictureUri.indexOf("/")+1,pictureUri.lastIndexOf("."))
        // if(matCode!=""&&pictureUri!=''&&picCode!=matCode){
        //     IPLAT.alert("必须先输入自定义的物资编码，再上传图片");
        //     return;
        // }


        var eiInfo = new EiInfo();
        eiInfo.setByNode("result");

        EiCommunicator.send("ACGM0103", "insert", eiInfo, {
            onSuccess: function (ei) {
                if(ei.getStatus()=='-2'){
                    NotificationUtil(ei.getMsg(), "error");
                    return;
                }
                window.parent['addMatPopDataWindow'].close();
                NotificationUtil(ei.getMsg(), "success");
                window.parent.resultGrid.dataSource.page(1);
            }
        });

    });

    var uploading = false;
    $("#uploadButton").on("click", function () {
        var matCode = IPLAT.EFInput.value($("#matCode")).replace(/\s*/g,"");
        // var thisFile = $('#pictureFile')[0].files[0];
        //
        // var fileType = thisFile.type.substring(thisFile.type.length - 3, thisFile.type.length);
        // if (fileType != "png") {
        // 	IPLAT.alert("上传图片不支持该格式");
        // 	return;
        // }

        // var fd = new FormData($('#pictureForm')[0]);
        var fd = new FormData();
        fd.append("file", $('#pictureFile')[0].files[0]);
        if (""!== matCode) {
            fd.append("matCode", matCode);
        } else {
            fd.append("matCode", "");
        }
        //需要校验重复
        fd.append("type","0");

        // fd.append("type","1");
        // console.log(fd);

        if ($('#pictureFile')[0].files[0] == null) {
            IPLAT.alert("请选择上传图片");
            return;
        } else {
            $("#uploadButton").attr("disabled", "disabled");
        }

        if (uploading) {
            alert("图片正在上传中，请稍候");
            return;
        }

        $.ajax({
            //url: IPLATUI.CONTEXT_PATH + "/app/materialsPicUpload",
            url: IPLATUI.CONTEXT_PATH + "/frame/servlet/uploadImage",
            // url: IPLATUI.CONTEXT_PATH + "/" + $("#pictureUri").val(),
            data: fd,
            type: "post",
            cache: false,
            processData: false,
            contentType: false,
            beforeSend: function () {
                uploading = true;
            },
            success: function (data) {
                uploading = false;
                // console.log(data);
                // console.log(data.status);
                // console.log(data.msg);
                if (data != undefined && data != null) {
                    if (data.status ==-1) {
                        IPLAT.alert(data.msg);
                        $("#uploadButton").removeAttr("disabled");
                        return;
                    }
                    if (data.status ==-2) {
                        IPLAT.alert(data.msg);
                        $("#uploadButton").removeAttr("disabled");
                        return;
                    }


                    var obj = document.getElementById('pictureFile');
                    obj.outerHTML = obj.outerHTML;

                    $('#uploadButton').attr("disabled", false);
                    //$('#uploadButtonOne').removeAttr("disabled");
                    //$('#uploadButtonOne').attr("disabled","");
                    //$('#pictureUri').val(data.msg);
                    $('#pictureUri').val(data);

                    //var accessPath = IPLATUI.CONTEXT_PATH + "/" + data.msg + "?nowTime=" + new Date().getTime();
                    // console.log(accessPath);
                    var accessPath =IPLATUI.CONTEXT_PATH +"/" + data;
                    $("#imageShow").attr("src", accessPath);
                    setTimeout(function(){$("#imageShow")[0].src="";$("#imageShow")[0].src=accessPath;},5000);
                    setTimeout(function(){IPLAT.alert("图片上传成功");},2000);

                } else {
                    NotificationUtil("图片上传失败", "error");
                }

            },
            error: function (data) {
                alert("上传出错")
            }
        });
    });
</script>
