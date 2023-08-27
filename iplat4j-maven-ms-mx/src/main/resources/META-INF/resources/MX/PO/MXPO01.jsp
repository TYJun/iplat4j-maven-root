<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
    #MXPO01 {
        margin: 0;
        padding: 0;
        background-color: #eeeeee;
    }
    .PO01 > div {
        width: 100vw;
    }
    .up {
        height: 80vh;
        display: flex;
        justify-content: center;
        align-items: center;
        position: relative;
    }
    .down {
        height: 15.7vh;
        background-color: #0a235b;
        display: flex;
        justify-content: center;
        align-items: center;
        color: white;
        font-size: 16px;
    }
    .down .left {
        width: 15vw;
        position: relative;
        left: 1rem;
    }
    .down .right {
        display: flex;
        justify-content: center;
        width: 85vw;
    }
    .right > div {
        margin-left: 1rem;
    }
    .right > div > span {
        font-size: 14px;
        margin-left: .5rem;
        text-align: center;
    }
    .right .value {
        display: inline-block;
        width: 2.5rem;
        background-color: white;
        color: #5396ae;
    }
    .up > up-box {
        width: 100%;
        height: 90%;
    }
    .up-box > span {
        position: absolute;
		color: white;
    }
    .s1 {
        left: 67.6vw;
        top: 19vh;
    }
	.s2 {
		left: 61.8vw;
		top: 39.6vh;
	}
	.s3 {
		left: 64.9vw;
		top: 54.7vh;
	}
	.s4 {
		left: 41.7vw;
		top: 60.9vh;
	}
</style>
<script>

</script>
<EF:EFPage>
    <div class="PO01">
        <div class="up">
            <div class="up-box">
                <img src="${ctx}/MX/PO/img/po.png">
                <span id="s1" class="s1">0.85</span>
                <span id="s2" class="s2">0.14</span>
                <span id="s3" class="s3">1.43</span>
                <span id="s4" class="s4">1.56</span>
            </div>
        </div>
        <div class="down">
            <div class="left">数据显示区</div>
            <div class="right">
                <div>
                    <span>负一层水渠房生活供水管道压力</span><span id="s5" class="value" >0.14</span><span>Mpa</span>
                </div>
                <div>
                    <span>负一层水渠房生活供水管道压力</span><span id="s6" class="value">0.14</span><span>Mpa</span>
                </div>
                <div>
                    <span>负一层水渠房生活供水管道</span><span id="s7" class="value">0.14</span><span>Mpa</span>
                </div>
                <div>
                    <span>负一层水渠房生活</span><span id="s8" class="value">0.14</span><span>Mpa</span>
                </div>
            </div>
        </div>
    </div>
</EF:EFPage>
<script>
	setInterval(function (){
		$('#s1').html(Math.random().toFixed(2))
		$('#s2').html(Math.random().toFixed(2))
	}, 500)
	setInterval(function (){
		$('#s5').html(Math.random().toFixed(2))
		$('#s6').html(Math.random().toFixed(2))
	}, 1000)
	setInterval(function (){
		$('#s3').html(Math.random().toFixed(2))
		$('#s4').html(Math.random().toFixed(2))
		$('#s7').html(Math.random().toFixed(2))
		$('#s8').html(Math.random().toFixed(2))
	}, 1200)
</script>
