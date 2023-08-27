<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style type="text/css">
	.A{
		float:left;
		width: 920px;
		margin-left:150px;
	}
	
	.table1 {
		border:1px solid #6495ed;
		border-collapse:collapse;
		margin: 0px;
	}

	.table1 td {
		line-height: 35px;
		border:1px solid #6495ed;
		width: 150px;
	}
	
	#T1 td{
		background-color: #d2e8ff;
		font-weight:bold;
		font-size: 18px;
		color: #0000FF;
		width: 100%;
		text-align: center;
	}

	.thclass th{
		line-height: 35px;
		font-weight:bold;
		font-size: 18px;
		color: #0000FF;
		text-align: center;
		border:1px solid #6495ed;
	}

	.list-td td{
		text-align: center;
		font-size: 16px;
	}
	
	.choose {
    	font-size: 16px;
    	color: #000000;
    	font-family:Microsoft YaHei;
	}
	
	.text {
	    font-size: 16px;
    	color: #000000;
    	font-family:Microsoft YaHei;
	} 

	#saveCheckList{
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
<title>宿舍检查清单</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" head="hidden" title="宿舍检查清单" showClear="false">
		<EF:EFInput ename="id" cname="id" colWidth="5" type="hidden"/>
   		<div class="A">
		<table class="table1">
			<tr id="T1">
				<td colspan="6">检查清单</td>
			</tr>
			<tr class="thclass">
				<th>序号</th>
				<th>项目</th>
				<th>
					<input type="checkbox" id="allYes" name="allYes">有/
					<input type="checkbox" id="allNo" name="allNo">否
				</th>
				<th width="170px">
					<input type="checkbox" id="allIntact" name="allIntact">完好/
					<input type="checkbox" id="allNoIntact" name="allNoIntact">损坏
				</th>
				<th>其他费用(元)</th>
				<th>备注</th>
			</tr>
			<tr class="list-td">
				<td><span id="item1_num">1</span></td>
				<td>
					<input type="hidden" id="itemCode1" value="item1"/>
					<input type="hidden" id="item1_id" />
					<span id="item1_name">空调</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item1_exits" required="required" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item1_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item1_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item1_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item1_text" /></td>
				<td><input type="text" class="text" id="item1_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item2_num">2</span></td>
				<td>
					<input type="hidden" id="itemCode2" value="item2"/>
					<input type="hidden" id="item2_id" />
					<span id="item2_name">热水器</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item2_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item2_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item2_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item2_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item2_text" /></td>
				<td><input type="text" class="text" id="item2_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item3_num">3</span></td>
				<td>
					<input type="hidden" id="itemCode3" value="item3"/>
					<input type="hidden" id="item3_id" />
					<span id="item3_name">冰箱</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item3_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item3_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item3_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item3_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item3_text" /></td>
				<td><input type="text" class="text" id="item3_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item4_num">4</span></td>
				<td>
					<input type="hidden" id="itemCode4" value="item4"/>
					<input type="hidden" id="item4_id" />
					<span id="item4_name">洗衣机</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item4_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item4_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item4_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item4_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item4_text" /></td>
				<td><input type="text" class="text" id="item4_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item5_num">5</span></td>
				<td>
					<input type="hidden" id="itemCode5" value="item5"/>
					<input type="hidden" id="item5_id" />
					<span id="item5_name">电视机</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item5_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item5_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item5_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item5_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item5_text" /></td>
				<td><input type="text" class="text" id="item5_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item6_num">6</span></td>
				<td>
					<input type="hidden" id="itemCode6" value="item6"/>
					<input type="hidden" id="item6_id" />
					<span id="item6_name">电视柜</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item6_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item6_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item6_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item6_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item6_text" /></td>
				<td><input type="text" class="text" id="item6_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item7_num">7</span></td>
				<td>
					<input type="hidden" id="itemCode7" value="item7"/>
					<input type="hidden" id="item7_id" />
					<span id="item7_name">微波炉</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item7_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item7_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item7_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item7_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item7_text" /></td>
				<td><input type="text" class="text" id="item7_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item8_num">8</span></td>
				<td>
					<input type="hidden" id="itemCode8" value="item8"/>
					<input type="hidden" id="item8_id" />
					<span id="item8_name">床</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item8_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item8_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item8_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item8_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item8_text" /></td>
				<td><input type="text" class="text" id="item8_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item9_num">9</span></td>
				<td>
					<input type="hidden" id="itemCode9" value="item9"/>
					<input type="hidden" id="item9_id" />
					<span id="item9_name">床头柜</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item9_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item9_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item9_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item9_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item9_text" /></td>
				<td><input type="text" class="text" id="item9_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item10_num">10</span></td>
				<td>
					<input type="hidden" id="itemCode10" value="item10"/>
					<input type="hidden" id="item10_id" />
					<span id="item10_name">床垫</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item10_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item10_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item10_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item10_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item10_text" /></td>
				<td><input type="text" class="text" id="item10_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item11_num">11</span></td>
				<td>
					<input type="hidden" id="itemCode11" value="item11"/>
					<input type="hidden" id="item11_id" />
					<span id="item11_name">沙发</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item11_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item11_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item11_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item11_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item11_text" /></td>
				<td><input type="text" class="text" id="item11_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item12_num">12</span></td>
				<td>
					<input type="hidden" id="itemCode12" value="item12"/>
					<input type="hidden" id="item12_id" />
					<span id="item12_name">办公桌</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item12_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item12_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item12_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item12_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item12_text" /></td>
				<td><input type="text" class="text" id="item12_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item13_num">13</span></td>
				<td>
					<input type="hidden" id="itemCode13" value="item13"/>
					<input type="hidden" id="item13_id" />
					<span id="item13_name">办公椅</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item13_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item13_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item13_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item13_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item13_text" /></td>
				<td><input type="text" class="text" id="item13_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item14_num">14</span></td>
				<td>
					<input type="hidden" id="itemCode14" value="item14"/>
					<input type="hidden" id="item14_id" />
					<span id="item14_name">衣柜</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item14_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item14_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item14_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item14_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item14_text" /></td>
				<td><input type="text" class="text" id="item14_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item15_num">15</span></td>
				<td>
					<input type="hidden" id="itemCode15" value="item15"/>
					<input type="hidden" id="item15_id" />
					<span id="item15_name">燃气炉</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item15_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item15_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item15_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item15_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item15_text" /></td>
				<td><input type="text" class="text" id="item15_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item16_num">16</span></td>
				<td>
					<input type="hidden" id="itemCode16" value="item16"/>
					<input type="hidden" id="item16_id" />
					<span id="item16_name">消毒碗柜</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item16_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item16_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item16_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item16_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item16_text" /></td>
				<td><input type="text" class="text" id="item16_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item17_num">17</span></td>
				<td>
					<input type="hidden" id="itemCode17" value="item17"/>
					<input type="hidden" id="item17_id" />
					<span id="item17_name">抽油烟机</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item17_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item17_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item17_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item17_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item17_text" /></td>
				<td><input type="text" class="text" id="item17_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item18_num">18</span></td>
				<td>
					<input type="hidden" id="itemCode18" value="item18"/>
					<input type="hidden" id="item18_id" />
					<span id="item18_name">窗帘</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item18_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item18_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item18_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item18_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item18_text" /></td>
				<td><input type="text" class="text" id="item18_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item19_num">19</span></td>
				<td>
					<input type="hidden" id="itemCode19" value="item19"/>
					<input type="hidden" id="item19_id" />
					<span id="item19_name">茶几</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item19_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item19_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item19_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item19_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item19_text" /></td>
				<td><input type="text" class="text" id="item19_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item20_num">20</span></td>
				<td>
					<input type="hidden" id="itemCode20" value="item20"/>
					<input type="hidden" id="item20_id" />
					<span id="item20_name">饭桌</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item20_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item20_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item20_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item20_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item20_text" /></td>
				<td><input type="text" class="text" id="item20_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item21_num">21</span></td>
				<td>
					<input type="hidden" id="itemCode21" value="item21"/>
					<input type="hidden" id="item21_id" />
					<span id="item21_name">毛巾架</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item21_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item21_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item21_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item21_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item21_text" /></td>
				<td><input type="text" class="text" id="item21_note" /></td>
			</tr>

			<tr class="list-td">
				<td><span id="item22_num">22</span></td>
				<td>
					<input type="hidden" id="itemCode22" value="item22"/>
					<input type="hidden" id="item22_id" />
					<span id="item22_name">其他</span>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item22_exits" />是
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item22_exits" />否
					</div>
				</td>
				<td>
					<div class="choose">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="Y" name="item22_intact" />完好
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="N" name="item22_intact" />损坏
					</div>
				</td>
				<td><input type="text" class="text" id="item22_text" /></td>
				<td><input type="text" class="text" id="item22_note" /></td>
			</tr>

			<tr>
				<td colspan="6" style="text-align:right">
					<input type="button" id="saveCheckList" value="&nbsp保&nbsp存&nbsp" style="cursor:pointer"/>
				</td>
			</tr>
		</table>
	</div>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript">
	//全选
	var allYes = document.getElementById("allYes");
	var allNo = document.getElementById("allNo");
	var allIntact = document.getElementById("allIntact");
	var allNoIntact = document.getElementById("allNoIntact");
	allYes.onclick = function () {
		if (allYes.checked == true) {
			$("input[name='allNo']").prop("checked",false);
			for (let i = 1; i < 23; i++) {
				var selector = $("#itemCode"+ i).val();
				$("input[name='"+selector+"_exits']").eq(0).prop("checked",true);
			}
		}else {
			for (let i = 1; i < 23; i++) {
				var selector = $("#itemCode"+ i).val();
				$("input[name='"+selector+"_exits']").eq(0).prop("checked",false);
			}
		}
	}
	allNo.onclick = function () {
		if (allNo.checked == true) {
			$("input[name='allYes']").prop("checked",false);
			for (let i = 1; i < 23; i++) {
				var selector = $("#itemCode"+ i).val();
				$("input[name='"+selector+"_exits']").eq(1).prop("checked",true);
			}
		}else {
			for (let i = 1; i < 23; i++) {
				var selector = $("#itemCode"+ i).val();
				$("input[name='"+selector+"_exits']").eq(1).prop("checked",false);
			}
		}
	}

	allIntact.onclick = function () {
		if (allIntact.checked == true) {
			$("input[name='allNoIntact']").prop("checked",false);
			for (let i = 1; i < 23; i++) {
				var selector = $("#itemCode"+ i).val();
				$("input[name='"+selector+"_intact']").eq(0).prop("checked",true);
			}
		}else {
			for (let i = 1; i < 23; i++) {
				var selector = $("#itemCode"+ i).val();
				$("input[name='"+selector+"_intact']").eq(0).prop("checked",false);
			}
		}
	}

	allNoIntact.onclick = function () {
		if (allNoIntact.checked == true) {
			$("input[name='allIntact']").prop("checked",false);
			for (let i = 1; i < 23; i++) {
				var selector = $("#itemCode"+ i).val();
				$("input[name='"+selector+"_intact']").eq(1).prop("checked",true);
			}
		}else {
			for (let i = 1; i < 23; i++) {
				var selector = $("#itemCode"+ i).val();
				$("input[name='"+selector+"_intact']").eq(1).prop("checked",false);
			}
		}
	}
</script>