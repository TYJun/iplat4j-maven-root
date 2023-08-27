<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
    let baseUrl = "${ctx}"
</script>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>威派格供水系统正常图</title>
    <script src="${ctx}/js/jquery-2.1.4.js"></script>
</head>
<style>
    .content {
        position: absolute;
        width: 99%;
        height: 98%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    img {
        position: relative;
        width: 65%;
        margin-bottom: 20%;
    }

    .wgpText1,
    .wgpText2,
    .wgpText3,
    .wgpText4,
    .wgpText5,
    .wgpText6 {
        position: absolute;
        font-size: 18px;
        color: #3366FF;
    }

    .wgpText1 {
        left: 30.5%;
        top: 31%;
    }

    .wgpText2 {
        left: 48.5%;
        top: 40%;
    }

    .wgpText3 {
        left: 47.2%;
        top: 51%;
    }

    .wgpText4 {
        left: 64.3%;
        top: 21%;
    }

    .wgpText5 {
        left: 65.5%;
        top: 32.5%;
    }

    .wgpText6 {
        left: 67%;
        top: 44%;
    }

    .box3 {
        position: fixed;
        bottom: 0;
        width: 100%;
        margin: 0.5% 0% 0% 0%;
        height: 140px;
        border: 1px solid #AFAFAF;
        display: flex;
        justify-content: space-between;
        align-items: center;
        text-align: center;
        background-color: #0A235B;
        color: #fff;
    }

    .box3 span {
        width: 25%;
        text-align: left;
        margin-left: 5%;
    }

    .showBox3 {
        width: 80%;
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: space-around;
    }

    .showBox3 div:nth-child(1) {
        display: flex;
        justify-content: flex-start;
        text-align: left;
    }

    .showBox3 div:nth-child(2) {
        display: flex;
        justify-items: flex-start;
        text-align: left;
    }
</style>
<EF:EFPage>
    <body>
    <div class="content">
        <img src="${ctx}/img/wpg.png"/>
        <span class="wgpText1"></span>
        <span class="wgpText2"></span>
        <span class="wgpText3"></span>
        <span class="wgpText4"></span>
        <span class="wgpText5"></span>
        <span class="wgpText6"></span>
        <div class="box3">
            <span>参数展示区</span>
            <div class="showBox3">
                <div>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
                <div>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </div>
        </div>
    </div>
    </body>
    <script>
        function show() {
            $.ajax({
                url: 'http://iplat-sxry.yyhq365.cn/iplat_sxry/sym/flowchart',
                post: 'get',
                success: function (data) {
                    const noticeFiter = data.filter(item => item.tagClassify === '低区智慧水务');
                    if (noticeFiter == "" || noticeFiter == "null" || noticeFiter == null) {
                        return;
                    } else {
                        if (noticeFiter[4].tagRValue == "null" || noticeFiter[4].tagRValue == null) {
                            $(".wgpText2").text("液位：ml");
                            $(".showBox3 div:nth-child(1) span:nth-child(1)").text("液位：ml");
                        } else {
                            $(".wgpText2").text("液位：" + noticeFiter[4].tagRValue + "ml");
                            $(".showBox3 div:nth-child(1) span:nth-child(1)").text("液位：" + noticeFiter[4].tagRValue + "ml");
                        }
                        if (noticeFiter[3].tagRValue == "null" || noticeFiter[3].tagRValue == null) {
                            $(".wgpText3").text("水流量：m³/H");
                            $(".showBox3 div:nth-child(1) span:nth-child(2)").text("水流量：m³/H");
                        } else {
                            $(".wgpText3").text("水流量：" + noticeFiter[3].tagRValue + "m³/H");
                            $(".showBox3 div:nth-child(1) span:nth-child(2)").text("水流量：" + noticeFiter[3].tagRValue + "m³/H");
                        }
                        if (noticeFiter[0].tagRValue == "null" || noticeFiter[0].tagRValue == null) {
                            $(".wgpText4").text("水泵状态：无数据");
                            $(".showBox3 div:nth-child(1) span:nth-child(3)").text("水泵状态：无数据");
                        } else if (noticeFiter[0].tagRValue == 0) {
                            $(".wgpText4").text("水泵状态：关闭");
                            $(".showBox3 div:nth-child(1) span:nth-child(3)").text("水泵状态：关闭");
                        }
                            // else {
                            //     $(".wgpText4").text("水泵状态：" + noticeFiter[0].tagRValue);
                            //     $(".showBox3 div:nth-child(1) span:nth-child(3)").text("水泵状态：" + noticeFiter[0].tagRValue);
                        // }
                        else {
                            $(".wgpText4").text("水泵状态：正常");
                            $(".showBox3 div:nth-child(1) span:nth-child(3)").text("水泵状态：正常");
                        }
                        if (noticeFiter[1].tagRValue == "null" || noticeFiter[1].tagRValue == null) {
                            $(".wgpText5").text("水泵状态：无数据");
                            $(".showBox3 div:nth-child(2) span:nth-child(1)").text("水泵状态：无数据");
                        } else if (noticeFiter[1].tagRValue == 0) {
                            $(".wgpText5").text("水泵状态：关闭");
                            $(".showBox3 div:nth-child(2) span:nth-child(1)").text("水泵状态：关闭");
                        } else {
                            $(".wgpText5").text("水泵状态：正常");
                            $(".showBox3 div:nth-child(2) span:nth-child(1)").text("水泵状态：正常");
                        }
                        if (noticeFiter[2].tagRValue == "null" || noticeFiter[2].tagRValue == null) {
                            $(".wgpText6").text("水泵状态：无数据");
                            $(".showBox3 div:nth-child(2) span:nth-child(2)").text("水泵状态：无数据");
                        } else if (noticeFiter[2].tagRValue == 0) {
                            $(".wgpText6").text("水泵状态：关闭");
                            $(".showBox3 div:nth-child(2) span:nth-child(2)").text("水泵状态：关闭");
                        } else {
                            $(".wgpText6").text("水泵状态：正常");
                            $(".showBox3 div:nth-child(2) span:nth-child(2)").text("水泵状态：正常");
                        }
                    }
                }
            })
        }

        show();
        setInterval("show()", 3000);
    </script>

    </html>
</EF:EFPage>
