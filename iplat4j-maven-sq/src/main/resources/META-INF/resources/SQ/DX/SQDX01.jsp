<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style type="text/css">
    /* CSS Document */
    input{
        background-color: transparent;
        border:none;
        border-bottom: 1px solid #000;
    }
    .tabtop13 {
        margin-top: 13px;
    }
    .tabtop13 td{
        background-color:#ffffff;
        height:25px;
        line-height:200%;
    }
    .font-center{ text-align:center}
    .btbg{background:#e9faff !important;}
    .btbg1{background: #00bfff !important;}
    .btbg2{background:#f3f3f3 !important;}

    .biaoti{
        font-family: 微软雅黑;
        font-size: 26px;
        font-weight: bold;
        border-bottom:1px dashed #CCCCCC;
        color: #255e95;
    }
    .geshi{
        font-family: 微软雅黑;
        font-size: 16px;
        font-weight: bold;
        color: red;
    }
    .yaoqiu{
        font-family: 微软雅黑;
        font-size: 16px;
        font-weight: bold;
    }
    .peizhi{
        font-family: 微软雅黑;
        font-size: 16px;
        font-weight: bold;
        color: blue;
    }
    .titfont {
        font-family: 微软雅黑;
        font-size: 16px;
        /*font-weight: bold;*/
        /*color: #255e95;*/
        color: black;
        background-color:#e9faff;
    }
    .titlefont{
        font-family: 微软雅黑;
        font-size: 20px;
        font-weight: bold;
        color: black;
    }
    .tEmpty input[type=checkbox] {
        margin: 0 3px 0 10px;
        line-height: normal;
    }
    .tEmpty input[type="text"] {
        font-family: 微软雅黑;
        width: 33px;
        margin: 5px 2px;
        font-size: 15px;
        text-align:center;
        color: coral;
    }
    .smsTemp input[type="text"] {
        font-family: 微软雅黑;
        width: 2000px;
        margin: 0 3px 0 10px;
        font-size: 13px;
        color: #000000;
    }
    #SAVE,#REFRESH{
        margin-top: 8px;
        border: 1px solid #3088F4;
        border-radius: 3px;
        background-color: #3088F4;
        min-width: 80px;
        min-height: 24px;
        margin-right:8px;
        color: #FFFFFF;
        line-height: 100%;
        font-size: 14px;
    }
</style>
<EF:EFPage>
    <EF:EFRegion id="config" head="hidden" title="短信配置" showClear="false">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
            <tr>
                <td align="left" class="geshi" height="25">
                    短信内容配置格式要求：
                </td>
                <td align="left" height="25" class="yaoqiu">
                    问卷：<span class="peizhi">$standard_name$</span>
                </td>
                <td align="left" class="yaoqiu">
                    批次号：<span class="peizhi">$batch_no$</span>
                </td>
                <td align="left" class="yaoqiu">
                    问卷截止天数：<span class="peizhi">$day$</span>
                </td>
            </tr>
            <tr>
                <td align="center" class="biaoti" colspan="4"></td>
            </tr>
        </table>
    </EF:EFRegion>
    <EF:EFRegion id="result" head="hidden" title="短信配置" showClear="false">
        <table width="100%" border="0" cellspacing="1" cellpadding="4" bgcolor="#cccccc" class="tabtop13" align="center">
            <tr class="font-left titlefont">
                <td colspan="4">问卷截止提醒</td>
            </tr>
            <tr>
                <td class="btbg font-left titfont tEmpty" colspan="4">
                    <input type="hidden" id="inqu_status-0-id" value="1"/>
                    <input type="hidden" id="inqu_status-0-configType" value="sq1"/>
                    <input type="hidden" id="inqu_status-0-configTypeName" value="问卷截止提醒"/>
                    <input type="hidden" id="inqu_status-0-statusCode" value="1"/>
                    <input type="checkbox" id="inqu_status-0-isRunning" name="isRunning"/>
                    距离问卷截止还有<input type="text" name="remainingDays" class="input" id="inqu_status-0-lateDays" />天发送短信
                    ，当天<input type="text" name="remainingDays" class="input" id="inqu_status-0-time" />点
                    发送短信提醒。
                </td>
            </tr>
            <tr>
                <td class=" font-left titfont smsTemp" colspan="4">
                    &emsp;&emsp;
                    短信内容&emsp;<input size="140" maxlength="100" value="您好,批次号为$batch_no$,问卷名称为$standard_name$的问卷距截止时间还剩$day$天"
                                     class="text" id="inqu_status-0-smsTemp" />
                </td>
            </tr>
            <tr>
                <td class=" font-left titfont">
                    &emsp;&emsp;
                    接收人员&emsp;
                    <input type="checkbox" class="person0" value="SQFZ" />问卷负责人&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                    <input type="checkbox" class="person0" value="SQRY_ALL" />【全体】答卷人&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                    <input type="checkbox" class="person0" value="SQRY_PART" />【未答题】答卷人
                </td>
            </tr>
        </table>

        <table width="100%" border="0" cellspacing="1" cellpadding="4" bgcolor="#cccccc" class="tabtop13" align="center">
            <tr class="font-left titlefont">
                <td colspan="4">问卷周期生成提醒</td>
            </tr>
            <tr>
                <td class="btbg font-left titfont tEmpty" colspan="4">
                    <input type="hidden" id="inqu_status-1-id" value="2"/>
                    <input type="hidden" id="inqu_status-1-configType" value="sq2"/>
                    <input type="hidden" id="inqu_status-1-configTypeName" value="问卷周期生成提醒"/>
                    <input type="hidden" id="inqu_status-1-statusCode" value="2"/>
                    <input type="checkbox" id="inqu_status-1-isRunning" name="isRunning"/>
                    距离问卷自动生成前<input type="text" name="remainingDays" class="input" id="inqu_status-1-lateDays" />天发送短信
                    ，当天<input type="text" name="remainingDays" class="input" id="inqu_status-1-time" />点
                    发送短信提醒。
                </td>
            </tr>
            <tr>
                <td class=" font-left titfont smsTemp" colspan="4">
                    &emsp;&emsp;
                    短信内容&emsp;<input size="240" maxlength="200" value="您好,问卷名称为$standard_name$的问卷,离自动生成还剩$day$天"
                                     class="text" id="inqu_status-1-smsTemp" />
                </td>
            </tr>
            <tr>
                <td class=" font-left titfont">
                    &emsp;&emsp;
                    接收人员&emsp;
                    <input type="checkbox" class="person1" value="SQFZ" />问卷负责人&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                </td>
            </tr>
            <tr>
                <td colspan="4" style="text-align:right">
                    <input type="button" id="SAVE" value="&nbsp保&nbsp存&nbsp"  style="cursor:pointer"/>
                    <input type="button" id="REFRESH" value="&nbsp刷&nbsp新&nbsp"  style="cursor:pointer;"/>
                </td>
            </tr>
        </table>
    </EF:EFRegion>
</EF:EFPage>


