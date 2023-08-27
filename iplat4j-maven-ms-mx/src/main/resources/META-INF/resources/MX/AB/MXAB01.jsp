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
    <title>气体中心系统运行图</title>
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
        width: 60%;
        margin-bottom: 12%;
    }

    .qtText1,
    .qtText2,
    .qtText3,
    .qtText4,
    .qtText5,
    .qtText6,
    .qtText7,
    .qtText8,
    .qtText9,
    .qtText10,
    .qtText11,
    .qtText12 {
        position: absolute;
        color: #3366FF;
        font-size: 15px;
    }

    .qtText1 {
        top: 25%;
        left: 26.5%;
    }

    .qtText2 {
        top: 25%;
        left: 32.5%;
    }

    .qtText3 {
        top: 25%;
        left: 38.5%;
    }

    .qtText4 {
        top: 25%;
        left: 44.8%;
    }

    .qtText5 {
        top: 25%;
        left: 50.8%;
    }

    .qtText6 {
        top: 25%;
        left: 56.8%;
    }

    .qtText7 {
        top: 25%;
        left: 62.8%;
    }

    .qtText8 {
        top: 25%;
        left: 68.8%;
    }

    .qtText9 {
        top: 63%;
        left: 18%;
        transform: rotate(270deg);
    }

    .qtText10 {
        top: 56%;
        left: 31.5%;
    }

    .qtText11 {
        top: 38%;
        left: 69%;
    }

    .qtText12 {
        top: 47%;
        left: 69%;
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
        <img src="${ctx}/img/qt.png"/>
        <!-- <span class="qtText1">温度：51°C</span>
        <span class="qtText2">温度：51°C</span>
        <span class="qtText3">温度：51°C</span>
        <span class="qtText4">温度：51°C</span>
        <span class="qtText5">温度：51°C</span>
        <span class="qtText6">温度：51°C</span>
        <span class="qtText7">温度：51°C</span>
        <span class="qtText8">温度：51°C</span>-->
        <span class="qtText9"></span>
        <span class="qtText10"></span>
        <span class="qtText11"></span>
        <span class="qtText12"></span>
        <div class="box3">
            <span>参数展示区</span>
            <div class="showBox3">
                <div>
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
                    const noticeFiter = data.filter(item => item.tagClassify === '空压机组');
                    if (noticeFiter == "" || noticeFiter == "null" || noticeFiter == null) {
                        return;
                    } else {
                        if (noticeFiter[1].tagRValue == "null" || noticeFiter[1].tagRValue == null) {
                            $(".qtText11").text("机组状态：无数据");
                            $(".showBox3 span:nth-child(1)").text("机组状态：无数据");
                        } else if (noticeFiter[1].tagRValue == 0) {
                            $(".qtText11").text("机组状态：关闭");
                            $(".showBox3 span:nth-child(1)").text("机组状态：关闭");
                        } else {
                            $(".qtText11").text("机组状态：正常");
                            $(".showBox3 span:nth-child(1)").text("机组状态：正常");
                        }
                        if (noticeFiter[0].tagRValue == "null" || noticeFiter[0].tagRValue == null) {
                            $(".qtText12").text("机组状态：无数据");
                            $(".showBox3 span:nth-child(2)").text("机组状态：无数据");
                        } else if (noticeFiter[0].tagRValue == 0) {
                            $(".qtText12").text("机组状态：关闭");
                            $(".showBox3 span:nth-child(2)").text("机组状态：关闭");
                        } else {
                            $(".qtText12").text("机组状态：正常");
                            $(".showBox3 span:nth-child(2)").text("机组状态：正常");
                        }
                    }

                    const noticeFiter1 = data.filter(item => item.tagClassify === '空压系统汇流排');
                    if (noticeFiter1 == "" || noticeFiter1 == "null" || noticeFiter1 == null) {
                        return;
                    } else {
                        if (noticeFiter1[0].tagRValue == "null" || noticeFiter1[0].tagRValue == null) {
                            $(".qtText9").text("压力：无数据");
                        } else {
                            $(".qtText9").text("压力：" + noticeFiter1[0].tagRValue + "MPa");
                        }
                    }

                    const noticeFiter2 = data.filter(item => item.tagClassify === '负压系统汇流排');
                    if (noticeFiter2 == "" || noticeFiter2 == "null" || noticeFiter2 == null) {
                        return;
                    } else {
                        if (noticeFiter2[0].tagRValue == "null" || noticeFiter2[0].tagRValue == null) {
                            $(".qtText10").text("压力：无数据");
                        } else {
                            $(".qtText10").text("压力：" + noticeFiter2[0].tagRValue + "MPa");
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
