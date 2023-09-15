<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.baosight.iplat4j.core.FrameworkInfo" %>
<%@ page import="com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String iPlatStaticURL = FrameworkInfo.getPlatStaticURL(request);
    String theme = org.apache.commons.lang.StringUtils.defaultIfEmpty(PlatApplicationContext.getProperty("theme"), "ant");
    String checkpasswordSwitch=PlatApplicationContext.getProperty("xservices.security.default.password.switch");
    request.setAttribute("checkpasswordSwitch", checkpasswordSwitch);
    if("on".equals(checkpasswordSwitch)){
        String passwordTip = PlatApplicationContext.getProperty("xservices.security.checkpassword.desc");
        request.setAttribute("passwordTip", passwordTip);
    }else{
        request.setAttribute("passwordTip", "密码由不超过255位的英文字母或者数字字符或下划线组成。");
    }
    String passwordRegex = PlatApplicationContext.getProperty("xservices.security.checkpassword.regex");
%>

<c:set var="iPlatStaticURL" value="<%=iPlatStaticURL%>"/>
<c:set var="theme" value="<%=theme%>" scope="session"/>
<c:set var="pwdRgx" value="<%=passwordRegex%>"/>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!--[if IE 9]>
<html class="ie9 no-focus i-theme-${theme}" xmlns="http://www.w3.org/1999/xhtml">
<![endif]-->
<!--[if gt IE 9]><!-->
<html class="no-focus i-theme-${theme}" xmlns="http://www.w3.org/1999/xhtml">
<!--<![endif]-->

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="robots" content="noindex, nofollow" />
    <meta http-equiv="Cache-Control" content="public">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
    <link rel="shortcut icon" href="${iPlatStaticURL}/iplat.ico" type="image/x-icon">
    <title>修改密码</title>
    <link rel="stylesheet" href="${iPlatStaticURL}/iplatui/assets/css/iplat.ui.bootstrap.min.css">
    <link rel="stylesheet" href="${iPlatStaticURL}/kendoui/styles/kendo.common.min.css">
    <link href="${iPlatStaticURL}/kendoui/styles/kendo.metro.min.css" rel="stylesheet" />
    <link id="themeAnchor" rel="stylesheet" href="${iPlatStaticURL}/iplatui/css/iplat.ui.ant.min.css" />
    <link href="${iPlatStaticURL}/iplatui/assets/js/plugins/oneui/plugins/magnific-popup/magnific-popup.css" />
    <script src="${iPlatStaticURL}/kendoui/js/jquery.min.js"></script>
    <script src="${iPlatStaticURL}/iplatui/js/lib/underscore.min.js"></script>
    <script src="${iPlatStaticURL}/iplatui/js/iplat.ui.config.js"></script>
    <script src="${iPlatStaticURL}/iplatui/js/iplat.ui.jquery.js"></script>
    <script src="${iPlatStaticURL}/kendoui/js/kendo.all.min.js"></script>
    <script src="${iPlatStaticURL}/kendoui/js/messages/kendo.messages.zh-CN.min.js"></script>
    <script src="${iPlatStaticURL}/kendoui/js/cultures/kendo.culture.zh-CN.min.js"></script>
    <script src="${iPlatStaticURL}/kendoui/js/jszip.min.js"></script>

    <script>
        IPLATUI.CONTEXT_PATH = "${ctx}";
    </script>

    <script src="${iPlatStaticURL}/iplatui/assets/js/iplat.ui.bootstrap.min.js"></script>
    <script src="${iPlatStaticURL}/iplatui/js/iplat.ui.min.js"></script>

    <script src="${iPlatStaticURL}/kendoui/js/kendo.virtuallist.min.js"></script>
    <script src="${iPlatStaticURL}/kendoui/js/kendo.ooxml.min.js"></script></body>

    <script id="alert-template" type="text/x-kendo-template">
        <div class="modal-dialog-container">
            <div class="kendo-modal-add-message">#=message#</div>
            <hr style="margin: 0">
            <div class="kendo-modal-form-bottom">
                <button class="i-btn-lg i-btn-wide query-sure">#: ok #</button>
            </div>
        </div>
    </script>

</head>



<body class="i-theme-${theme}">
<div id="page-container">
    <div id="main-container" class="ef-content">
        <div id='ef_form_head' class='i-page-header clearfix'>
            <div class="i-text pull-left">
                <i class="i-icon iconfont iconlocation-fill"></i>
                <span> 修改密码</span>
            </div>
        </div>
        <div>
            <div id="result" data-title="个人密码修改" class='i-region block nav-region'>
                <div class="block-header i-region-header">
                    <span class="i-title">个人密码修改</span>
                    <span id="msgTips" style="color: red"></span>
                    <ul class="block-options">
                        <li>
                            <button type="button" data-toggle="block-option" data-action="fullscreen_toggle">
                                <i class="si si-size-fullscreen i-icon i-fullscreen"></i>
                            </button>
                        </li>
                        <li>
                            <button type="button" data-toggle="block-option" data-action="content_toggle">
                                <i class="si si-arrow-up i-icon i-arrow-up"></i>
                            </button>
                        </li>
                    </ul>
                </div>
                <div class="block-content form-horizontal">
                    <div class="col-xs-12">
                        <div class="row">
                            <input id="creToken" name="creToken" value="" type="hidden"
                                   class="k-textbox">
                        </div>

                        <div class="row">
                            <div class="col-xs-2 control-label" style="text-align:right">
                                <span>原密码</span>
                            </div>
                            <div class="col-xs-3">
                                <input id="oldPassword" name="oldPassword" value="" type="password"
                                       validateGroupName="group1"
                                       class="k-textbox">
                            </div>
                            <div class="col-xs-7">
                                <span id="inqu_status-0-oldPassword-prompt">【请输入原密码】</span>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-2 control-label" style="text-align:right">
                                <span>新密码</span>
                            </div>
                            <div class="col-xs-3">
                                <input id="password" name="password" value="" type="password"
                                       data-errorPrompt="${passwordTip}"
                                       data-regex="/${pwdRgx}/"
                                       validateGroupName="group1"
                                       class="k-textbox">
                            </div>
                            <div class="col-xs-7">
                                <span id="inqu_status-0-password-prompt">【${passwordTip}】</span>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-2 control-label" style="text-align:right">
                                <span>确认密码</span>
                            </div>
                            <div class="col-xs-3">
                                <input id="rePassword" name="rePassword" value="" type="password"
                                       data-errorPrompt="${passwordTip}"
                                       data-regex="/${pwdRgx}/"
                                       validateGroupName="group1"
                                       class="k-textbox">
                            </div>
                            <div class="col-xs-7">
                                <span id="inqu_status-0-rePassword-prompt">【${passwordTip}】</span>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="button-region">
                                <button id="updatePwd" type="button" class="i-btn-lg">修改</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        $(window).load(function () {
            var reqUrl = window.location.search.substr(1);
            var reg = new RegExp("(^|&)" + "ac" + "=([^&]*)(&|$)", "i");
            var r = reqUrl.match(reg);
            var creToken = null;
            if (r != null) {
                creToken = decodeURI(r[2]);
            }
            if(creToken != null) {
                $("#creToken").val(kendo.htmlEncode(creToken));
            }

            var msgReg = new RegExp("(^|&)" + "msg" + "=([^&]*)(&|$)", "i");
            var r = reqUrl.match(msgReg);
            var msg = null;
            if (r != null) {
                msg = decodeURI(r[2]);
            }
            if(msg != null) {
                $("#msgTips").html(msg);
            }
        });


        // 新密码格式校验
        $("#password").change(function () {
            var password = $(this).val();
            password = password.trim();
            var inInfo = new EiInfo();
            inInfo.set("password", password);

            EiCommunicator.send("XS0102", "checkPassword", inInfo, {
                onSuccess: function (ei) {
                    if (-1 == ei.getStatus()) {
                        $("#inqu_status-0-password-prompt").attr("style", "color:red");
                        $("#inqu_status-0-password-prompt").html("【" + ei.getMsg() + "】");
                        $("#updatePwd").attr("disabled", true);
                    } else {
                        $("#inqu_status-0-password-prompt").attr("style", "color:blue");
                        $("#inqu_status-0-password-prompt").html("【" + ei.getMsg() + "】");
                        $("#updatePwd").attr("disabled", false);
                    }
                }, onFail: function (ei) {
                    $("#inqu_status-0-password-prompt").html("【" + ei.getMsg() + "】");
                }
            })
        });

        // 确认密码格式校验
        $("#rePassword").change(function () {
            var rePassword = $(this).val();
            rePassword = rePassword.trim();
            var inInfo = new EiInfo();
            inInfo.set("password", rePassword);

            EiCommunicator.send("XS0102", "checkPassword", inInfo, {
                onSuccess: function (ei) {
                    if (-1 == ei.getStatus()) {
                        $("#inqu_status-0-rePassword-prompt").attr("style", "color:red");
                        $("#inqu_status-0-rePassword-prompt").html("【" + ei.getMsg() + "】");
                        $("#updatePwd").attr("disabled", true);
                    } else {
                        $("#inqu_status-0-rePassword-prompt").attr("style", "color:blue");
                        $("#inqu_status-0-rePassword-prompt").html("【" + ei.getMsg() + "】");
                        $("#updatePwd").attr("disabled", false);
                    }
                }, onFail: function (ei) {
                    $("#inqu_status-0-rePassword-prompt").html("【" + ei.getMsg() + "】");
                }
            })
        });

        // 修改密码操作
        $("#updatePwd").click(function () {
            var eiInfo = new EiInfo();
            eiInfo.setByNode("result");

            var oldPassword = eiInfo.get("oldPassword");
            var password = eiInfo.get("password");
            var rePassword = eiInfo.get("rePassword");
            var creToken = eiInfo.get("creToken");

            if(!creToken){
                IPLAT.alert({
                    message: '<b>非法操作，请先登录系统。有问题请联系系统管理员！</b>',
                    okFn: function (e) {
                        window.location.href= IPLATUI.CONTEXT_PATH + "/login.jsp";
                    },
                    title: '提示信息'
                });
                return;
            }

            if (password != rePassword) {
                IPLAT.alert("新密码和确认密码不一致，请检查！");
                return false;
            }

            EiCommunicator.send("DLPE01", "updatePwd", eiInfo, {
                onSuccess: function (ei) {
                    if (-1 == ei.getStatus()) {
                        IPLAT.alert(ei.getMsg());
                        return;
                    }else {
                        IPLAT.alert({
                            message: ei.getMsg(),
                            okFn: function (e) {
                                window.location.href= IPLATUI.CONTEXT_PATH + "/login.jsp";
                            },
                            title: '提示信息'
                        });
                    }
                }, onFail: function (ei) {
                    IPLAT.alert(ei.getMsg());
                }
            });
        })
    });
</script>
</body>
</html>