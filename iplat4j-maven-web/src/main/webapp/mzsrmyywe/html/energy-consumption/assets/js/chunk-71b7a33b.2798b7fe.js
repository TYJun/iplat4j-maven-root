(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-71b7a33b"],{2474:function(t,e,a){"use strict";a.r(e),a("e7e5");var o=a("d399"),n=a("1da1"),i=(a("96cf"),a("b0c0"),a("a15b"),a("365c")),c={name:"data-upload-bottom",props:{condition:{type:Object,default:function(){return{}}},file_list:{type:Object||Array}},render:function(){var t=this,e=arguments[0];return e("div",{class:"data-upload-bottom"},[e("global-tap",{class:"data-upload-bottom-button-sumbit",on:{tap:function(){t.data_upload_bottom_sumbit_tap()}}},[e("global-van-button",{attrs:{disabled:this.disabled,type:"primary"}},["申请"])])])},data:function(){return{disabled:!0,projectId:this.$route.query.id,workNo:localStorage.getItem("workNo"),name:localStorage.getItem("name"),deptCode:localStorage.getItem("deptCode"),deptName:localStorage.getItem("deptName")}},computed:{},watch:{file_list:{handler:function(t,e){this.change_button_type()},deep:!0,immediate:!0}},methods:{change_button_type:function(){1<=this.file_list.sign.length&&1<=this.file_list.risk.length&&1<=this.file_list.security.length?this.disabled=!1:this.disabled=!0},data_upload_bottom_sumbit_tap:function(){var e=this;return Object(n.a)(regeneratorRuntime.mark(function t(){return regeneratorRuntime.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return e.$toast.loading({message:"申请中...",forbidClick:!0}),t.next=3,Object(i.a)({workNo:e.workNo,name:e.name,projectId:e.projectId,applyDeptNum:e.deptCode,applyDeptName:e.deptName,projectType:"01",projectTypeName:e.condition.classify,projectName:e.condition.project_name,principalNum:"",companyName:e.condition.company_name,principalName:e.condition.principal,contactTel:e.condition.phone,beginDate:e.condition.start_time,fileIds:e.condition.file_ids.join(",")});case 3:e.$toast.clear(),e.$router.push("/hazardous-operation"),o.a.success("提交成功");case 6:case"end":return t.stop()}},t)}))()}}},a=(a("b089"),a("2877")),c=Object(a.a)(c,void 0,void 0,!1,null,"a80f19f6",null);e.default=c.exports},"2e6c":function(t,e,a){},b089:function(t,e,a){"use strict";a("2e6c")}}]);