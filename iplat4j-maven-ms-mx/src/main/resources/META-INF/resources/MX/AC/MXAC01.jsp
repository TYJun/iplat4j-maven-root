<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>生活水系统运行图</title>
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
        width: 50%;
        margin-top: -6%;
    }

    .kyText1,
    .kyText2,
    .kyText3,
    .kyText4,
    .kyText5,
    .kyText6,
    .kyText7,
    .kyText8 {
        position: absolute;
        font-size: 18px;
        color: #3366FF;
    }

    .kyText1 {
        left: 32%;
        bottom: 27.5%;
    }

    .kyText1 p {
        margin: 0;
    }

    .kyText2 {
        left: 45%;
        top: 22%;
    }

    .kyText2 p {
        margin: 0;
    }

    .kyText3 {
        left: 44%;
        top: 40%;
    }

    .kyText3 p {
        margin: 0;
    }

    .kyText4 {
        left: 44%;
        top: 53%;
    }

    .kyText4 p {
        margin: 0;
    }

    .kyText5 {
        left: 60%;
        top: 26%;
    }

    .kyText5 p {
        margin: 10px;
    }

    .kyText6 {
        left: 60%;
        top: 40.8%;
    }

    .kyText6 p {
        margin: 10px;
    }

    .kyText7 {
        left: 60.5%;
        top: 56%;
    }

    .kyText7 p {
        margin: 10px;
    }

    .kyText8 {
        left: 72%;
        top: 17%;
    }

    .kyText7 p {
        margin: 10px;
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
        <img src="${ctx}/img/ky.png"/>
        <div class="kyText1">
            <p></p>
            <p></p>
        </div>
        <div class="kyText2">
            <p></p>
            <p></p>
        </div>
        <div class="kyText3">
            <p></p>
            <p></p>
        </div>
        <div class="kyText4">
            <p></p>
            <p></p>
        </div>
        <div class="kyText5">
            <p></p>
            <p></p>
            <p></p>
        </div>
        <div class="kyText6">
            <p></p>
            <p></p>
            <p></p>
        </div>
        <div class="kyText7">
            <p></p>
            <p></p>
            <p></p>
        </div>
        <div class="kyText8">
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
                    const noticeFiter0 = data.filter(item => item.tagClassify === '水箱供水');
                    if (noticeFiter0 == "" || noticeFiter0 == "null" || noticeFiter0 == null) {
                        return;
                    } else {
                        if (noticeFiter0[0].tagRValue == "null" || noticeFiter0[0].tagRValue == null) {
                            $(".kyText1 p:nth-child(1)").text("液位：无数据");
                            $(".showBox3 div:nth-child(1) span:nth-child(1)").text("液位：无数据");
                        } else {
                            $(".kyText1 p:nth-child(1)").text("液位：" + noticeFiter0[0].tagRValue + "ml");
                            $(".showBox3 div:nth-child(1) span:nth-child(1)").text("液位：" + noticeFiter0[0]
                                .tagRValue + "ml");
                        }
                    }

                    const noticeFiter1 = data.filter(item => item.tagClassify === '高区电磁流量计');
                    if (noticeFiter1 == "" || noticeFiter1 == "null" || noticeFiter1 == null) {
                        return;
                    } else {
                        if (noticeFiter1[1].tagRValue == "null" || noticeFiter1[1].tagRValue == null) {
                            $(".kyText2 p:nth-child(1)").text("高流量：㎡/H");
                            $(".showBox3 div:nth-child(1) span:nth-child(2)").text("高流量：㎡/H");
                        } else {
                            $(".kyText2 p:nth-child(1)").text("高流量：" + noticeFiter1[1].tagRValue + "㎡/H");
                            $(".showBox3 div:nth-child(1) span:nth-child(2)").text("高流量：" + noticeFiter1[1]
                                .tagRValue + "㎡/H");
                        }
                        if (noticeFiter1[4].tagRValue == "null" || noticeFiter1[4].tagRValue == null) {
                            $(".kyText2 p:nth-child(2)").text("高压力：MPa");
                            $(".showBox3 div:nth-child(2) span:nth-child(1)").text("高压力：MPa");
                        } else {
                            $(".kyText2 p:nth-child(2)").text("高压力：" + noticeFiter1[4].tagRValue + "MPa");
                            $(".showBox3 div:nth-child(2) span:nth-child(1)").text("高压力：" + noticeFiter1[4]
                                .tagRValue + "MPa");
                        }
                    }

                    const noticeFiter2 = data.filter(item => item.tagClassify === '中区电磁流量计');
                    if (noticeFiter2 == "" || noticeFiter2 == "null" || noticeFiter2 == null) {
                        return;
                    } else {
                        if (noticeFiter2[2].tagRValue == "null" || noticeFiter2[2].tagRValue == null) {
                            $(".kyText3 p:nth-child(1)").text("中流量：㎡/H");
                            $(".showBox3 div:nth-child(1) span:nth-child(3)").text("中流量：㎡/H");
                        } else {
                            $(".kyText3 p:nth-child(1)").text("中流量：" + noticeFiter2[2].tagRValue + "㎡/H");
                            $(".showBox3 div:nth-child(1) span:nth-child(3)").text("中流量：" + noticeFiter2[2]
                                .tagRValue + "㎡/H");
                        }
                        if (noticeFiter2[0].tagRValue == "null" || noticeFiter2[0].tagRValue == null) {
                            $(".kyText3 p:nth-child(2)").text("中压力：MPa");
                            $(".showBox3 div:nth-child(2) span:nth-child(2)").text("中压力：MPa");
                        } else {
                            $(".kyText3 p:nth-child(2)").text("中压力：" + noticeFiter2[0].tagRValue + "Mpa");
                            $(".showBox3 div:nth-child(2) span:nth-child(2)").text("中压力：" + noticeFiter2[0]
                                .tagRValue + "Mpa");
                        }
                    }

                    const noticeFiter3 = data.filter(item => item.tagClassify === '低区电磁流量计');
                    if (noticeFiter3 == "" || noticeFiter3 == "null" || noticeFiter3 == null) {
                        return;
                    } else {
                        if (noticeFiter3[0].tagRValue == "null" || noticeFiter3[0].tagRValue == null) {
                            $(".kyText4 p:nth-child(1)").text("低流量：㎡/H");
                            $(".showBox3 div:nth-child(1) span:nth-child(4)").text("低流量：㎡/H");
                        } else {
                            $(".kyText4 p:nth-child(1)").text("低流量：" + noticeFiter3[0].tagRValue + "㎡/H");
                            $(".showBox3 div:nth-child(1) span:nth-child(4)").text("低流量：" + noticeFiter3[0]
                                .tagRValue+"㎡/H");
                        }
                        if (noticeFiter3[3].tagRValue == "null" || noticeFiter3[3].tagRValue == null) {
                            $(".kyText4 p:nth-child(2)").text("低压力：MPa");
                            $(".showBox3 div:nth-child(2) span:nth-child(3)").text("低压力：MPa");
                        } else {
                            $(".kyText4 p:nth-child(2)").text("低压力：" + noticeFiter3[3].tagRValue + "Mpa");
                            $(".showBox3 div:nth-child(2) span:nth-child(3)").text("低压力：" + noticeFiter3[3]
                                .tagRValue + "Mpa");
                        }
                    }


                    const noticeFiter4 = data.filter(item => item.tagClassify === '-2层供水机房1#污水泵');
                    if (noticeFiter4 == "" || noticeFiter4 == "null" || noticeFiter4 == null) {
                        return;
                    } else {
                        if (noticeFiter4[0].tagRValue == "null" || noticeFiter4[0].tagRValue == null) {
                            $(".kyText8 p:nth-child(1)").text("污水泵1：无数据");
                        } else if (noticeFiter4[0].tagRValue == 0) {
                            $(".kyText8 p:nth-child(1)").text("污水泵1：关闭");
                        } else {
                            $(".kyText8 p:nth-child(1)").text("污水泵1：正常");
                        }
                    }

                    const noticeFiter5 = data.filter(item => item.tagClassify === '-2层供水机房2#污水泵');
                    if (noticeFiter5 == "" || noticeFiter5 == "null" || noticeFiter5 == null) {
                        return;
                    } else {
                        if (noticeFiter5[0].tagRValue == "null" || noticeFiter5[0].tagRValue == null) {
                            $(".kyText8 p:nth-child(2)").text("污水泵2：无数据");
                        } else if (noticeFiter5[0].tagRValue == 0) {
                            $(".kyText8 p:nth-child(2)").text("污水泵2：关闭");
                        } else {
                            $(".kyText8 p:nth-child(2)").text("污水泵2：运行");
                        }


                        const noticeFiterG3 = data.filter(item => item.tagClassify === '高区3#水泵');
                        //2
                        if (noticeFiterG3[2].tagRValue == "null" || noticeFiterG3[2].tagRValue == null) {
                            $(".kyText5 p:nth-child(1)").text("水泵1：无数据");
                        } else {
                            if (noticeFiterG3[2].tagRValue == 8) {
                                $(".kyText5 p:nth-child(1)").text("水泵1：停止");
                            } else if (noticeFiterG3[2].tagRValue == 9 || noticeFiterG3[2].tagRValue == 10) {
                                $(".kyText5 p:nth-child(1)").text("水泵1：就绪");
                            } else {
                                $(".kyText5 p:nth-child(1)").text("水泵1：运行");
                            }
                        }

                        const noticeFiterG2 = data.filter(item => item.tagClassify === '高区2#水泵');
                        //0
                        if (noticeFiterG2[0].tagRValue == "null" || noticeFiterG2[0].tagRValue == null) {
                            $(".kyText5 p:nth-child(2)").text("水泵2：无数据");
                        } else {
                            if (noticeFiterG2[0].tagRValue == 8) {
                                $(".kyText5 p:nth-child(2)").text("水泵2：停止");
                            } else if (noticeFiterG2[0].tagRValue == 9 || noticeFiterG2[0].tagRValue == 10) {
                                $(".kyText5 p:nth-child(2)").text("水泵2：就绪");
                            } else {
                                $(".kyText5 p:nth-child(2)").text("水泵2：运行");
                            }
                        }

                        const noticeFiterG1 = data.filter(item => item.tagClassify === '高区1#水泵');
                        //0
                        if (noticeFiterG1[0].tagRValue == "null" || noticeFiterG1[0].tagRValue == null) {
                            $(".kyText5 p:nth-child(3)").text("水泵3：无数据");
                        } else {
                            if (noticeFiterG1[0].tagRValue == 8) {
                                $(".kyText5 p:nth-child(3)").text("水泵3：停止");
                            } else if (noticeFiterG1[0].tagRValue == 9 || noticeFiterG1[0].tagRValue == 10) {
                                $(".kyText5 p:nth-child(3)").text("水泵3：就绪");
                            } else {
                                $(".kyText5 p:nth-child(3)").text("水泵3：运行");
                            }
                        }

                        const noticeFiterZ3 = data.filter(item => item.tagClassify === '中区3#水泵');
                        //0
                        if (noticeFiterZ3[0].tagRValue == "null" || noticeFiterZ3[0].tagRValue == null) {
                            $(".kyText6 p:nth-child(1)").text("水泵4：");
                        } else {
                            if (noticeFiterZ3[0].tagRValue == 8) {
                                $(".kyText6 p:nth-child(1)").text("水泵4：停止");
                            } else if (noticeFiterZ3[0].tagRValue == 9 || noticeFiterZ3[0].tagRValue == 10) {
                                $(".kyText6 p:nth-child(1)").text("水泵4：就绪");
                            } else {
                                $(".kyText6 p:nth-child(1)").text("水泵4：运行");
                            }
                        }

                        const noticeFiterZ2 = data.filter(item => item.tagClassify === '中区2#水泵');
                        //0
                        if (noticeFiterZ2[0].tagRValue == "null" || noticeFiterZ2[0].tagRValue == null) {
                            $(".kyText6 p:nth-child(2)").text("水泵5：");
                        } else {
                            if (noticeFiterZ2[0].tagRValue == 8) {
                                $(".kyText6 p:nth-child(2)").text("水泵5：停止");
                            } else if (noticeFiterZ2[0].tagRValue == 9 || noticeFiterZ2[0].tagRValue == 10) {
                                $(".kyText6 p:nth-child(2)").text("水泵5：就绪");
                            } else {
                                $(".kyText6 p:nth-child(2)").text("水泵5：运行");
                            }
                        }

                        const noticeFiterZ1 = data.filter(item => item.tagClassify === '中区1#水泵');
                        //0
                        if (noticeFiterZ1[0].tagRValue == "null" || noticeFiterZ1[0].tagRValue == null) {
                            $(".kyText6 p:nth-child(3)").text("水泵6：");
                        } else {
                            if (noticeFiterZ1[0].tagRValue == 8) {
                                $(".kyText6 p:nth-child(3)").text("水泵6：停止");
                            } else if (noticeFiterZ1[0].tagRValue == 9 || noticeFiterZ1[0].tagRValue == 10) {
                                $(".kyText6 p:nth-child(3)").text("水泵6：就绪");
                            } else {
                                $(".kyText6 p:nth-child(3)").text("水泵6：运行");
                            }
                        }

                        const noticeFiterD3 = data.filter(item => item.tagClassify === '低区3#水泵');
                        //0
                        if (noticeFiterD3[0].tagRValue == "null" || noticeFiterD3[0].tagRValue == null) {
                            $(".kyText7 p:nth-child(1)").text("水泵7：");
                        } else {
                            if (noticeFiterD3[0].tagRValue == 8) {
                                $(".kyText7 p:nth-child(1)").text("水泵7：停止");
                            } else if (noticeFiterD3[0].tagRValue == 9 || noticeFiterD3[0].tagRValue == 10) {
                                $(".kyText7 p:nth-child(1)").text("水泵7：就绪");
                            } else {
                                $(".kyText7 p:nth-child(1)").text("水泵7：运行");
                            }
                        }
                        const noticeFiterD2 = data.filter(item => item.tagClassify === '低区2#水泵');
                        //0
                        if (noticeFiterD2[0].tagRValue == "null" || noticeFiterD2[0].tagRValue == null) {
                            $(".kyText7 p:nth-child(2)").text("水泵8：");
                        } else {
                            if (noticeFiterD2[0].tagRValue == 8) {
                                $(".kyText7 p:nth-child(2)").text("水泵8：停止");
                            } else if (noticeFiterD2[0].tagRValue == 9 || noticeFiterD2[0].tagRValue == 10) {
                                $(".kyText7 p:nth-child(2)").text("水泵8：就绪");
                            } else {
                                $(".kyText7 p:nth-child(2)").text("水泵8：运行");
                            }
                        }

                        const noticeFiterD1 = data.filter(item => item.tagClassify === '低区1#水泵');
                        //0
                        if (noticeFiterD1[0].tagRValue == "null" || noticeFiterD1[0].tagRValue == null) {
                            $(".kyText7 p:nth-child(3)").text("水泵9：");
                        } else {
                            if (noticeFiterD1[0].tagRValue == 8) {
                                $(".kyText7 p:nth-child(3)").text("水泵9：停止");
                            } else if (noticeFiterD1[0].tagRValue == 9 || noticeFiterD1[0].tagRValue == 10) {
                                $(".kyText7 p:nth-child(3)").text("水泵9：就绪");
                            } else {
                                $(".kyText7 p:nth-child(3)").text("水泵9：运行");
                            }
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
