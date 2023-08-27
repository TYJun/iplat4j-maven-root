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
    <title>污水站系统运行图</title>
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
        margin-bottom: 17%;
    }

    .wsText1,
    .wsText2,
    .wsText3,
    .wsText4,
    .wsText5,
    .wsText6,
    .wsText7,
    .wsText8,
    .wsText9 {
        position: absolute;
        color: #3366FF;
        font-size: 18px;
    }

    .wsText1 {
        left: 18%;
        top: 22%;
    }

    .wsText11 {
        position: absolute;
        color: #3366FF;
        font-size: 13px;
        left: 29.8%;
        top: 21%;
    }

    .wsText2 {
        left: 24%;
        top: 27%;
    }

    .wsText3 {
        left: 34%;
        top: 27%;
    }

    .wsText4 {
        left: 51%;
        top: 26%;
    }

    .wsText5 {
        left: 66%;
        top: 27%;
    }

    .wsText6 {
        left: 34.8%;
        top: 48%;
    }

    .wsText7 {
        left: 62%;
        top: 57%;
    }

    .wsText8 {
        left: 57.5%;
        top: 14%;
    }

    .wsText9 {
        left: 53%;
        top: 47%;
        text-align: right;
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
        <img src="${ctx}/img/ws.png"/>
        <span class="wsText1"></span>
        <span class="wsText2">格栅井</span>
        <span class="wsText3">调节池</span>
        <span class="wsText4">缺氧池</span>
        <span class="wsText5">好氧池</span>
        <span class="wsText11"></span>
        <span class="wsText6">市政污水</span>
        <span class="wsText7">沉淀池</span>
        <div class="wsText8">
            <p></p>
            <p></p>
        </div>
        <div class="wsText9">
            <!-- <p>分析仪：3</p> -->
            <p></p>
            <p></p>
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
    </div>
    </body>
    <script>
        function show() {
            $.ajax({
                url: 'http://iplat-sxry.yyhq365.cn/iplat_sxry/sym/flowchart',
                post: 'get',
                success: function (data) {
                    const noticeFiter = data.filter(item => item.tagClassify === '卫安系统');
                    if (noticeFiter == "" || noticeFiter == "null" || noticeFiter == null) {
                        return;
                    } else {
                        // console.log(noticeFiter);
                        if (noticeFiter[6].tagRValue == "null" || noticeFiter[6].tagRValue == null) {
                            $(".wsText1").text("液位：无数据");
                            $(".showBox3 div:nth-child(1) span:nth-child(1)").text("液位：无数据");
                        } else {
                            $(".wsText1").text("液位：" + noticeFiter[6].tagRValue);
                            $(".showBox3 div:nth-child(1) span:nth-child(1)").text("液位：" + noticeFiter[6].tagRValue);
                        }
                        if (noticeFiter[1].tagRValue == "null" || noticeFiter[1].tagRValue == null) {
                            $(".wsText11").text("流量：无数据");
                            $(".showBox3 div:nth-child(1) span:nth-child(2)").text("流量：无数据");
                        } else {
                            $(".wsText11").text("流量：" + noticeFiter[1].tagRValue);
                            $(".showBox3 div:nth-child(1) span:nth-child(2)").text("流量：" + noticeFiter[1].tagRValue);
                        }
                        if (noticeFiter[3].tagRValue == "null" || noticeFiter[3].tagRValue == null) {
                            $(".wsText8 p:nth-child(1)").text("溶解氧：无数据");
                            $(".showBox3 div:nth-child(1) span:nth-child(3)").text("溶解氧：无数据");
                        } else {
                            $(".wsText8 p:nth-child(1)").text("溶解氧：" + noticeFiter[3].tagRValue);
                            $(".showBox3 div:nth-child(1) span:nth-child(3)").text("溶解氧：" + noticeFiter[3].tagRValue);
                        }
                        if (noticeFiter[4].tagRValue == "null" || noticeFiter[4].tagRValue == null) {
                            $(".wsText8 p:nth-child(2)").text("溶解氧：无数据");
                        } else {
                            $(".wsText8 p:nth-child(2)").text("溶解氧：" + noticeFiter[4].tagRValue);
                        }
                        if (noticeFiter[0].tagRValue == "null" || noticeFiter[0].tagRValue == null) {
                            $(".wsText9 p:nth-child(1)").text("PH3：无数据");
                            $(".showBox3 div:nth-child(2) span:nth-child(1)").text("PH3：无数据");
                        } else {
                            $(".wsText9 p:nth-child(1)").text("PH3：" + noticeFiter[0].tagRValue);
                            $(".showBox3 div:nth-child(2) span:nth-child(1)").text("PH3：" + noticeFiter[0].tagRValue);
                        }
                        if (noticeFiter[5].tagRValue == "null" || noticeFiter[5].tagRValue == null) {
                            $(".wsText9 p:nth-child(2)").text("COD3：无数据");
                            $(".showBox3 div:nth-child(2) span:nth-child(2)").text("COD3：无数据");
                        } else {
                            $(".wsText9 p:nth-child(2)").text("COD3：" + noticeFiter[5].tagRValue);
                            $(".showBox3 div:nth-child(2) span:nth-child(2)").text("COD3：" + noticeFiter[5].tagRValue);
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
