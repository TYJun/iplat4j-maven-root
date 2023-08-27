<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>独立任务评价</title>
<EF:EFPage>
    <EF:EFRegion id="info" head="hidden">
        <div class="row">
            <EF:EFInput ename="type" cname="操作类型" colWidth="4" ratio="4:6" readonly="true" type="hidden"/>
            <EF:EFInput ename="itTaskId" cname="itTaskId" colWidth="4" ratio="4:6" readonly="true" type="hidden"/>
            <EF:EFInput ename="archiveFlag" cname="归档标记" colWidth="4" ratio="4:6" readonly="true" type="hidden"/>
        </div>
        <div class="row">
            <div class="col-md-5">
                <div class="form-group">
                    <label data-for="impFlag" class="col-xs-4 control-label">
                        <span>是否紧急</span>
                    </label>
                    <div class="col-md-8">
                        <EF:EFInput ename="impFlag" cname="是" value="Y" type="radio" inline="true"/>
                        <EF:EFInput ename="impFlag" cname="否" value="N" type="radio" inline="true"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-5">
                <div class="form-group">
                    <label for="reqStaffId" class="col-xs-4 control-label">需求人工号</label>
                    <div class="col-xs-8">
                        <input id="reqStaffId" name="reqStaffId"
                               ondblclick="showAll('reqStaffId')" onblur="echoOne('reqStaffId','workNo')">
                    </div>
                </div>
            </div>
            <div class="col-md-5">
                <div class="form-group">
                    <label for="reqStaffName" class="col-xs-4 control-label">需求人姓名</label>
                    <div class="col-xs-8">
                        <input id="reqStaffName" name="reqStaffName" ondblclick="showAll('reqStaffName')">
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-5">
                <div class="form-group">
                    <label for="reqTelNum" class="col-xs-4 control-label">需求电话</label>
                    <div class="col-xs-8">
                        <input id="reqTelNum" name="reqTelNum" ondblclick="showAll('reqTelNum')" onblur="echoOne('reqTelNum','telNum')">
                    </div>
                </div>
            </div>
            <div class="col-md-5">
                <div class="form-group">
                    <label for="reqDeptName" class="col-xs-4 control-label"><span style="color:red">*</span>需求科室</label>
                    <div class="col-xs-8">
                        <input id="reqDeptNum" name="reqDeptNum" placeholder="需求科室" type="hidden">
                        <input id="reqDeptName" name="reqDeptName" ondblclick="showAll('reqDeptName')">
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <EF:EFTreeInput ename="parentId" cname="任务分类" serviceName="ITFL01" methodName="queryItClassifyTree"
                            valueField="id" textField="classifyName" hasChildren="isLeaf" readonly="true" required="true"
                            root="{id: 'root', classifyName: '根节点'}" colWidth="5" ratio="4:8">
            </EF:EFTreeInput>
            <div class="col-md-5">
                <div class="form-group">
                    <label for="serveDeptName" class="col-xs-4 control-label"><span style="color:red">*</span>服务科室</label>
                    <div class="col-xs-8">
                        <input id="serveDeptNum" name="serveDeptNum" placeholder="服务科室" type="hidden">
                        <input id="serveDeptName" name="serveDeptName" ondblclick="showAll('serveDeptName')">
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-10">
                <div class="form-group">
                    <label class="col-md-2 control-label">
                        <span style="color:red">*</span>服务地点
                    </label>
                    <div class="col-md-2" >
                        <input id="building" name="building" placeholder="楼" ondblclick="showAll('building')">
                    </div>
                    <div class="col-md-2" style="padding-left:0px;margin-left:0px;" >
                        <input id="floor" name="floor" placeholder="层" ondblclick="showAll('floor')">
                    </div>
                </div>
            </div>

        </div>

        <div class="row">
            <div class="col-md-10">
                <div class="form-group">
                    <label class="col-md-2 control-label"></label>
                    <div class="col-md-4">
                        <input id="reqSpotNum" name="reqSpotNum" placeholder="地点" type="hidden">
                        <input id="reqSpotName" name="reqSpotName" placeholder="地点" ondblclick="showAll('reqSpotName')">
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <EF:EFInput ename="serveContent" cname="服务内容" colWidth="5" rows="3" type="textarea" placeholder="不能超过200字"/>
            <EF:EFInput ename="remark" cname="备注" colWidth="5" rows="3" type="textarea" placeholder="不能超过200字"/>
        </div>

        <div class="row">
            <EF:EFInput ename="evalAdvice" cname="评价意见" colWidth="5" rows="3" type="textarea" placeholder="不能超过200字"/>
            <div class="col-md-5">
                <div class="form-group">
                    <label data-for="evalPoint" class="col-xs-4 control-label">
                        <span style="color:red">*</span>服务评价
                    </label>
                    <div class="col-md-8">
                        <EF:EFInput ename="evalPoint" cname="很差" value="1" type="radio" inline="true"/>
                        <EF:EFInput ename="evalPoint" cname="差" value="2" type="radio" inline="true"/>
                        <EF:EFInput ename="evalPoint" cname="一般" value="3" type="radio" inline="true"/>
                        <EF:EFInput ename="evalPoint" cname="满意" value="4" type="radio" inline="true"/>
                        <EF:EFInput ename="evalPoint" cname="很满意" value="5" type="radio" inline="true"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="确认" ename="SAVE" layout="3"></EF:EFButton>
            <EF:EFButton cname="关闭" ename="CLOSE" layout="3"></EF:EFButton>
        </div>
    </EF:EFRegion>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/IT/js/mt_detail_utils.js"></script>