(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-2ff13b11"],{2770:function(e,t,i){},b787:function(e,t,i){"use strict";i.r(t);var c={name:"integrated-query-select",props:{},render:function(){var t=this,e=arguments[0];return e("div",{class:"integrated-query-select"},[e("global-van-cell-group",[e("global-van-field",{class:"global-van-field-xz",attrs:{readonly:!0,clickable:!0,name:"picker",value:this.select_site_office.text||"请选择筛选条件",label:"请选择筛选条件:",placeholder:this.select_site_office.text||"请选择筛选条件",required:!0},on:{tap:function(){return t.integrated_query__select_site()}}}),e("global-project-state-select",{attrs:{title:"选择状态",office:this.select_site_office},on:{cancel:function(){return t.integrated_query__cancel()},confirm:function(e){return t.queck_confirm(e)},"click-overlay":function(){return t.integrated_query_click_overlay()}},model:{value:t.select_site_value,callback:function(e){t.select_site_value=e}}})])])},data:function(){return{select_site_value:!1,select_site_office:["请选择项目类型"]}},computed:{},methods:{integrated_query__select_site:function(){this.select_site_value=!0},integrated_query__cancel:function(){this.select_site_value=!1},integrated_query_click_overlay:function(){this.select_site_value=!1},queck_confirm:function(e){this.select_site_value=!1,this.select_site_office=e,this.$emit("choose-type",e)},save_tap:function(){this.$emit("save"),this.$router.push("/periodic-review")},requirement_description_input:function(e){this.requirement_description=e}}},i=(i("e61b"),i("2877")),c=Object(i.a)(c,void 0,void 0,!1,null,"7b75a06a",null);t.default=c.exports},e61b:function(e,t,i){"use strict";i("2770")}}]);