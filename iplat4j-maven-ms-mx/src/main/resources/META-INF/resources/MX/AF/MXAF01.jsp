<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
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
    <title>液氧站系统运行图</title>
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
        margin-bottom: 24%;
    }

    .fcText1,
    .fcText2,
    .fcText3,
    .fcText4,
    .fcText5,
    .fcText6 {
        position: absolute;
        color: #3366FF;
        font-size: 18px;
    }

    .fcText1 {
        left: 32.6%;
        bottom: 57%;
    }

    .fcText1 p {
        margin: 0;
    }

    .fcText2 {
        left: 42.6%;
        bottom: 57%;
    }

    .fcText2 p {
        margin: 0;
    }

    .fcText3 {
        left: 53.6%;
        bottom: 57%;
    }

    .fcText3 p {
        margin: 0;
    }

    .fcText4 {
        left: 64%;
        bottom: 57%;
    }

    .fcText4 p {
        margin: 0;
    }

    .fcText5 {
        left: 69%;
        bottom: 49%;
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
    <img src="${ctx}/img/fc.png" />
    <div class="fcText1">
        <p></p>
        <p></p>
        <!-- <p>流量:3m²/H</p> -->
    </div>
    <div class="fcText2">
        <p></p>
        <p></p>
        <!-- <p>流量:3m²/H</p> -->
    </div>
    <div class="fcText3">
        <p></p>
        <p></p>
        <!-- <p>流量:3m²/H</p> -->
    </div>
    <div class="fcText4">
        <p></p>
        <p></p>
        <!-- <p>流量:3m²/H</p> -->
    </div>
    <div class="box3">
        <span>参数展示区</span>
        <div class="showBox3">
            <div>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
            </div>
            <div>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
            </div>
        </div>
    </div>
    <!-- <span class="fcText5">压力：0.419MP</span> -->
</div>
</body>
<script>
    function show() {
        $.ajax({
            url: 'http://iplat-sxry.yyhq365.cn/iplat_sxry/sym/flowchart',
            post: 'get',
            success: function (data) {
                const noticeFiter = data.filter(item => item.tagClassify === '汇流排');
                if (noticeFiter == "" || noticeFiter == "null" || noticeFiter == null) {
                    return;
                } else {
                    if (noticeFiter[3].tagRValue == "null" || noticeFiter[3].tagRValue == null) {
                        $(".fcText1 p:nth-child(2)").text("压力：MPa");
                        $(".showBox3 div:nth-child(1) span:nth-child(1)").text("压力：MPa");
                    } else {
                        $(".fcText1 p:nth-child(2)").text("压力：" + noticeFiter[3].tagRValue + "MPa");
                        $(".showBox3 div:nth-child(1) span:nth-child(1)").text("压力：" + noticeFiter[3].tagRValue + "MPa");
                    }
                    if (noticeFiter[1].tagRValue == "null" || noticeFiter[1].tagRValue == null) {
                        $(".fcText1 p:nth-child(1)").text("液位：M");
                        $(".showBox3 div:nth-child(1) span:nth-child(2)").text("液位：M");
                    } else {
                        $(".fcText1 p:nth-child(1)").text("液位：" + noticeFiter[1].tagRValue + "M");
                        $(".showBox3 div:nth-child(1) span:nth-child(2)").text("液位：" + noticeFiter[1].tagRValue + "M");
                    }
                    if (noticeFiter[2].tagRValue == "null" || noticeFiter[2].tagRValue == null) {
                        $(".fcText2 p:nth-child(2)").text("压力：MPa");
                        $(".showBox3 div:nth-child(1) span:nth-child(3)").text("压力：MPa");
                    } else {
                        $(".fcText2 p:nth-child(2)").text("压力：" + noticeFiter[2].tagRValue + "MPa");
                        $(".showBox3 div:nth-child(1) span:nth-child(3)").text("压力：" + noticeFiter[2].tagRValue + "MPa");
                    }
                    if (noticeFiter[4].tagRValue == "null" || noticeFiter[4].tagRValue == null) {
                        $(".fcText2 p:nth-child(1)").text("液位：M");
                        $(".showBox3 div:nth-child(1) span:nth-child(4)").text("液位：M");
                    } else {
                        $(".fcText2 p:nth-child(1)").text("液位：" + noticeFiter[4].tagRValue + "M");
                        $(".showBox3 div:nth-child(1) span:nth-child(4)").text("液位：" + noticeFiter[4].tagRValue + "M");
                    }
                    if (noticeFiter[7].tagRValue == "null" || noticeFiter[7].tagRValue == null) {
                        $(".fcText3 p:nth-child(2)").text("压力：MPa");
                        $(".showBox3 div:nth-child(2) span:nth-child(1)").text("压力：MPa");
                    } else {
                        $(".fcText3 p:nth-child(2)").text("压力：" + noticeFiter[7].tagRValue + "MPa");
                        $(".showBox3 div:nth-child(2) span:nth-child(1)").text("压力：" + noticeFiter[7].tagRValue + "MPa");
                    }
                    if (noticeFiter[5].tagRValue == "null" || noticeFiter[5].tagRValue == null) {
                        $(".fcText3 p:nth-child(1)").text("液位：M");
                        $(".showBox3 div:nth-child(2) span:nth-child(2)").text("液位：M");
                    } else {
                        $(".fcText3 p:nth-child(1)").text("液位：" + noticeFiter[5].tagRValue + "M");
                        $(".showBox3 div:nth-child(2) span:nth-child(2)").text("液位：" + noticeFiter[5].tagRValue + "M");
                    }
                    if (noticeFiter[8].tagRValue == "null" || noticeFiter[8].tagRValue == null) {
                        $(".fcText4 p:nth-child(2)").text("压力：MPa");
                        $(".showBox3 div:nth-child(2) span:nth-child(3)").text("压力：MPa");
                    } else {
                        $(".fcText4 p:nth-child(2)").text("压力：" + noticeFiter[8].tagRValue + "MPa");
                        $(".showBox3 div:nth-child(2) span:nth-child(3)").text("压力：" + noticeFiter[8].tagRValue + "MPa");
                    }
                    if (noticeFiter[6].tagRValue == "null" || noticeFiter[6].tagRValue == null) {
                        $(".fcText4 p:nth-child(1)").text("液位：M");
                        $(".showBox3 div:nth-child(2) span:nth-child(4)").text("液位：M");
                    } else {
                        $(".fcText4 p:nth-child(1)").text("液位：" + noticeFiter[6].tagRValue + "M");
                        $(".showBox3 div:nth-child(2) span:nth-child(4)").text("液位：" + noticeFiter[6].tagRValue + "M");
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
