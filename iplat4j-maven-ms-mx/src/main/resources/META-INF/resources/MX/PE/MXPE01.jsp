<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/MX/PE/js/echarts.js"></script>
<script src="${ctx}/MX/PE/js/jquery.min.js"></script>

<style>
	* {
		padding: 0;
		margin: 0;
	}
	body {
		padding: 10px;
	}
	.top {
		display: flex;
		justify-content: space-between;
	}
	.topCard {
		width: 24%;
		height: 180px;
		background-color: white;
		border-radius: 5px;
		position: relative;
		display: flex;
		justify-content: center;
		align-items: center;
		-moz-box-shadow: 2px 2px 5px #888888; /* 老的 Firefox */
		box-shadow: 2px 2px 5px #888888;
	}
	.topTitle {
		color: #1F65DA;
	}
	.ss {
		color: #D25059;
		font-size: 30px;
	}
	.jrjc {
		color: #69C75E;
		font-size: 30px;
	}
	.yqrd {
		color: #968EEC;
		font-size: 30px;
	}
	.wqrd {
		color: #DCC64E;
		font-size: 30px;
	}
	.title {
		margin-left: 5%;
	}
	.center {
		margin-top: 1%;
		display: flex;
		justify-content: space-between;
	}
	.bjsltj {
		width: 49%;
		height: 360px;
		border-radius: 5px;
		background-color: white;
		-moz-box-shadow: 2px 2px 5px #888888; /* 老的 Firefox */
		box-shadow: 2px 2px 5px #888888;
	}
	.bjlxzb {
		width: 48.2%;
		height: 360px;
		border-radius: 5px;
		background-color: white;
		-moz-box-shadow: 2px 2px 5px #888888; /* 老的 Firefox */
		box-shadow: 2px 2px 5px #888888;
	}
	.bjwzzb {
		width: 48.2%;
		height: 360px;
		border-radius: 5px;
		background-color: white;
		-moz-box-shadow: 2px 2px 5px #888888; /* 老的 Firefox */
		box-shadow: 2px 2px 5px #888888;
	}
	.rightBox {
		width: 49%;
		height: 360px;
		border-radius: 5px;
		display: flex;
		justify-content: space-between;
	}
	.bottom {
		margin-top: 1%;
		width: 99.7%;
		height: 350px;
		border-radius: 5px;
		background-color: white;
		-moz-box-shadow: 2px 2px 5px #888888; /* 老的 Firefox */
		box-shadow: 2px 2px 5px #888888;
	}
	#bjslChart {
		position: relative;
		height: 100%;
	}
	#bjlbEchart {
		position: relative;
		height: 100%;
	}
	#bjwzEchart {
		position: relative;
		height: 100%;
	}
	#lsbjEchart {
		position: relative;
		height: 100%;
		background-color: #368FFF;
	}
	.date-tools {
		height: 40px;
		width: 100%;
		margin-bottom: 10px;
		border-radius: 5px;
		line-height: 40px;
		display: flex;
		flex-flow: row nowrap;
		background-color: white;
	}
	.date-type {
		height: 40px;
		width: 10%;
		margin-left: 10px;
	}
	#date-type-select {
		height: 40px;
		width: 100%;
		appearance:none;
		-moz-appearance:none; /* Firefox */
		-webkit-appearance:none; /* Safari 和 Chrome */
		border: 0;
		padding-left: 20px;
	}
	#date-picker {
		height: 40px;
		width: 20%;
	}
	#date-picker input {
		height: 40px;
		border: 0;
	}
</style>
<EF:EFPage>
	<div id="box">
		<div class="date-tools">
			<div class="date-type">
				<select id="date-type-select">
					<option value="month">月</option>
					<option value="day">日</option>
				</select>
			</div>
			<div id="date-picker">
				<input type="month" value="2021-12" />
			</div>
		</div>
		<div class="top">
			<div class="topCard">
				<img src="${ctx}/MX/PE/img/1.png" alt="">
				<div class="title">
					<p class="topTitle">实时报警数量</p>
					<p class="ss"><span id="countForRealTime">7</span> 件</p>
				</div>
			</div>
			<div class="topCard">
				<img src="${ctx}/MX/PE/img/2.png" alt="">
				<div class="title">
					<p class="topTitle">解除报警数量</p>
					<p class="jrjc"><span id="countForCancel">7</span> 件</p>
				</div>
			</div>
			<div class="topCard">
				<img src="${ctx}/MX/PE/img/3.png" alt="">
				<div class="title">
					<p class="topTitle">已确认的报警数量</p>
					<p class="yqrd"><span id="countForConfirmed">7</span> 件</p>
				</div>
			</div>
			<div class="topCard">
				<img src="${ctx}/MX/PE/img/4.png" alt="">
				<div class="title">
					<p class="topTitle">未确认的报警数量</p>
					<p class="wqrd"><span id="countForUnconfirmed">7</span> 件</p>
				</div>
			</div>
		</div>
		<div class="center">
			<div class="bjsltj">
				<div id="bjslChart"></div>
			</div>
			<div class="rightBox">
				<div class="bjlxzb">
					<div id="bjlbEchart"></div>
				</div>
				<div class="bjwzzb">
					<div id="bjwzEchart"></div>
				</div>
			</div>
		</div>
		<div class="bottom">
			<div id="lsbjEchart"></div>
		</div>
	</div>
</EF:EFPage>
<script>
	$(function () {
		let $datePicker = $('#date-picker');
		$("#date-type-select").change( event => {
			if(event.target.value) {
				switch (event.target.value) {
					case 'month':
						$datePicker.html('<input type="month" value="' + initMonth() + '" />');
						break;
					case 'day':
						$datePicker.html('<input type="date" value="' + initDay() + '" />');
						break;
				}
				$("#date-picker > input").change(evt => {
					const params = {
						type: event.target.value,
						time: evt.target.value
					}
					doFind(params);
				});
			}
		});
	});
	function doFind(params) {
		$.get("${ctx}/sym/pe/query?t" + new Date().getTime(), params, res => {
			const {countForRealTime, countForCancel, countForConfirmed, countForUnconfirmed, countForAlarmType, countForClassify} = res;

			$('#countForRealTime').html(countForRealTime)
			$('#countForCancel').html(countForCancel)
			$('#countForConfirmed').html(countForConfirmed)
			$('#countForUnconfirmed').html(countForUnconfirmed)

			bjslOption.series[0].data = [countForRealTime, countForCancel, countForConfirmed, countForUnconfirmed];
			bjslEcharts.setOption(bjslOption);

			bjlxOption.series[0].data = countForAlarmType;
			bjlxEcharts.setOption(bjlxOption);

			bjwzOption.series[0].data = countForClassify;
			bjwzEcharts.setOption(bjwzOption);

			const {addForHistory, existForHistory} = res;

			if(params.type === 'month') {
				let timeArr = params.time.split('-');
				let d = new Date(timeArr[0], timeArr[1], 0)
				const xArr = [], y1Arr = [], y2Arr = [];
				for(let i = 0; i <= d.getDate(); i++) {
					xArr.push(i)
					let flag1 = true, flag2 = true;
					if(addForHistory && addForHistory.length > 0) {
						addForHistory.forEach(item => {
							let {time, value} = item
							time = time.substring(time.lastIndexOf('-') + 1)
							i == time && (y1Arr.push(value), flag1 = false)
						});
					}
					flag1 && y1Arr.push(null)

					if(existForHistory && existForHistory.length > 0) {
						existForHistory.forEach(item => {
							let {time, value} = item
							time = time.substring(time.lastIndexOf('-') + 1)
							i == time && (y2Arr.push(value), flag2 = false)
						});
					}
					flag2 && y2Arr.push(null)
				}
				lsbjOption.xAxis.data = xArr
				lsbjOption.series[0].data = y1Arr
				lsbjOption.series[1].data = y2Arr

				lsbjEcharts.setOption(lsbjOption);
			}

			if(params.type === 'day') {
				const xArr = ['00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23'];
				const y1Arr = [], y2Arr = [];
				xArr.forEach(x => {
					let flag1 = true, flag2 = true;
					if(addForHistory && addForHistory.length > 0) {
						addForHistory.forEach(item => {
							let {time, value} = item
							time = time.substring(time.lastIndexOf(' ') + 1)
							x == time && (y1Arr.push(value), flag1 = false)
						});
					}
					flag1 && y1Arr.push(null)

					if(existForHistory && existForHistory.length > 0) {
						existForHistory.forEach(item => {
							let {time, value} = item
							time = time.substring(time.lastIndexOf(' ') + 1)
							x == time && (y2Arr.push(value), flag2 = false)
						});
					}
					flag2 && y2Arr.push(null)
				})
				lsbjOption.xAxis.data = xArr
				lsbjOption.series[0].data = y1Arr
				lsbjOption.series[1].data = y2Arr

				lsbjEcharts.setOption(lsbjOption);
			}
		});
	}
	function initMonth() {
		let date = new Date();
		let time = date.getFullYear() + '-' + (date.getMonth() + 1);
		const params = {
			type: "month",
			time: time
		}
		$("#date-picker > input").change(evt => {
			const params = {
				type: "month",
				time: evt.target.value
			}
			doFind(params);
		});
		doFind(params);
		return time;
	}
	function initDay() {
		let date = new Date();
		let time = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
		const params = {
			type: "day",
			time: time
		}
		$("#date-picker > input").change(evt => {
			const params = {
				type: "day",
				time: evt.target.value
			}
			doFind(params);
		});
		doFind(params);
		return time;
	}
	initMonth();
</script>