$(function () {
		$("#SUBMIT").click(function(){
			var cycle=IPLAT.EFInput.value($("#cycle")); 
			var regu = /^[1-9]\d*$/;
			if(!regu.test(cycle)){
				NotificationUtil("时间间隔为正整数,请重输", "error");
				return;
			} 
			var unit = $('input[name="unit"]:checked').val(); 
			var holiday = $('input[name="holiday"]:checked').val(); 
			var weekend = $('input[name="weekend"]:checked').val(); 
			var startTime=kendo.toString($("#startTime").data("kendoDateTimePicker").value(), "yyyy-MM-dd HH:mm:ss");
			
			//校验
			if (startTime == null || startTime == "") {
				NotificationUtil("请输入开始时间", "error");
				return;
			}
			
			var count=window.parent.cycleGrid.getDataItems().length;
			/*window.parent.cycleGrid.addRow();
			var model=window.parent.cycleGrid.getDataItems()[0];*/
			var model=createModel(0);
			model['cycle']=cycle;
			model['unit']=unit;
			model['holiday']=holiday;
			model['weekend']=weekend;
			model['startTime']=startTime;
			
			//存储中文
			switch(unit) {
				case "h":
					model['unitName'] = "时";
					break;
				case "d":
					model['unitName'] = "日";
					break;
				case "m":
					model['unitName'] = "月";
					break;
			}
			switch(holiday) {
				case "Y":
					model['holidayName'] = "是";
					break;
				case "N":
					model['holidayName'] = "否";
					break;
			}
			switch(weekend) {
			case "Y":
				model['weekendName'] = "是";
				break;
			case "N":
				model['weekendName'] = "否";
				break;
			}
			
		    window.parent.cycleGrid.addRows(model);
//		    window.parent.cycleGrid.removeRows(window.parent.cycleGrid.getDataItems()[0]);
		    window.parent['popDataCycleWindow'].close();
		});
		
		//创建kendo.data.Model
		function createModel(id){
			var gridRow = kendo.data.Model.define({
			    id: "uploadId", 
			    fields: {
			        "cycle": {type: "string"},
			        "unit": {type: "string"},
			        "holiday": {type: "string"},
			        "weekend": {type: "string"},
			        "startTime": {type: "string"},
			        "unitName": {type: "string"},
			        "holidayName": {type: "string"},
			        "weekendName": {type: "string"}
			    }
			});
			var model = new gridRow({uploadId:id});
			return model;
		}
	});
