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
    <title>锅炉房系统运行图</title>
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
        margin-top: -9%;
    }

    .text1,
    .text2,
    .text3,
    .text4,
    .text5,
    .text6,
    .text7 {
        position: absolute;
        font-size: 26px;
        color: #3366FF;
    }

    .text1 {
        left: 24%;
        top: 9%;
    }

    .text2 {
        left: 49%;
        top: 9%;
    }

    .text3 {
        left: 17%;
        top: 66%;
    }

    .text4 {
        left: 25.5%;
        top: 50%;
    }

    .text5 {
        left: 41.5%;
        top: 72%;
    }

    .text5 p {
        margin: 0;
    }

    .text6 {
        left: 51%;
        top: 72%;
    }

    .text6 p {
        margin: 0;
    }

    .text7 {
        left: 60.5%;
        top: 72%;
    }

    .text7 p {
        margin: 0;
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
        <img src="${ctx}/img/gl.png"/>
        <span class="text1"></span>
        <span class="text2"></span>
        <!-- <span class="text3">压力：<span id="pressure">3MPa</span></span> -->
        <span class="text4"></span>
        <!-- <div class="text5">
            <p>水位：<span id="sw1">1M</span></p>
            <p>流量：<span id="flow1">1㎡/H</span></p>
            <p>状态：<span id="state1">正常</span></p>
        </div>
        <div class="text6">
            <p>水位：<span id="sw2">1M</span></p>
            <p>流量：<span id="flow2">1㎡/H</span></p>
            <p>状态：<span id="state2">正常</span></p>
        </div>
        <div class="text7">
            <p>水位：<span id="sw3">1M</span></p>
            <p>流量：<span id="flow3">1㎡/H</span></p>
            <p>状态：<span id="state3">正常</span></p>
        </div> -->
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
    </div>
    </body>
    <script>
        function show() {
            $.ajax({
                url: 'http://iplat-sxry.yyhq365.cn/iplat_sxry/sym/flowchart',
                post: 'get',
                success: function (data) {
                    const noticeFiter = data.filter(item => item.tagClassify === '锅炉房');
                    if (noticeFiter == "" || noticeFiter == "null" || noticeFiter == null) {
                        return;
                    } else {
                        if (noticeFiter[6].tagRValue == "null" || noticeFiter[6].tagRValue == null) {
                            $(".text1").text("溢水口：无数据");
                            $(".showBox3 span:nth-child(1)").text("溢水口：无数据");
                        } else {
                            $(".text1").text("溢水口：" + noticeFiter[6].tagRValue);
                            $(".showBox3 span:nth-child(1)").text("溢水口：" + noticeFiter[6].tagRValue);
                        }
                        if (noticeFiter[7].tagRValue == "null" || noticeFiter[7].tagRValue == null) {
                            $(".text2").text("溢水口：无数据");
                            $(".showBox3 span:nth-child(2)").text("溢水口：无数据");
                        } else {
                            $(".text2").text("溢水口：" + noticeFiter[7].tagRValue);
                            $(".showBox3 span:nth-child(2)").text("溢水口：" + noticeFiter[7].tagRValue);
                        }
                    }

                    const noticeFiter1 = data.filter(item => item.tagClassify === '洗衣房蒸汽流量计');
                    if (noticeFiter1 == "" || noticeFiter1 == "null" || noticeFiter1 == null) {
                        return;
                    } else {
                        if (noticeFiter1[4].tagRValue == "null" || noticeFiter1[4].tagRValue == null) {
                            $(".text4").text("流量：无数据");
                            $(".showBox3 span:nth-child(3)").text("流量：无数据");
                        } else {
                            $(".text4").text("流量：" + noticeFiter1[4].tagRValue + "㎡/H");
                            $(".showBox3 span:nth-child(3)").text("流量：" + noticeFiter1[4].tagRValue + "㎡/H");
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
