(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-0f8e2eac"],{"3c44":function(t,e,n){"use strict";n("c5f6")},c5f6:function(t,e,n){},cdf6:function(t,e,n){"use strict";n.r(e);var r=n("1da1"),i=(n("96cf"),n("d3b7"),n("365c")),o={name:"energy-consumption-circles",components:{},props:{},render:function(){return(0,arguments[0])("div",{class:"energy-consumption-circles",attrs:{id:"pie-1"}})},data:function(){return{echart:null,pie_nhgk:[],nh_total:""}},methods:{get_current_reportPie:function(){var o=this;return Object(r.a)(regeneratorRuntime.mark(function t(){var e,n,r;return regeneratorRuntime.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return e=o,t.next=3,Object(i.m)();case 3:n=t.sent,r=n.data.data.pieList.reduce(function(t,e,n){return t+e.value},0),e.pie_nhgk=n.data.data.pieList,e.nh_total=r,console.log(e.nh_total,"total"),n.success&&(null===(r=o.echart)||void 0===r||r.setOption({series:[{data:n.data.data.pieList,label:{show:!1,position:"center",normal:{show:!0,position:"center",color:"#4c4a4a",formatter:"{nh_total_title|总能耗}\n\r{nh_total|"+e.nh_total+"}\n\r{nh_unit|tce}",rich:{nh_total_title:{fontFamily:"微软雅黑",fontSize:16,color:"#1b1b1b",lineHeight:30},nh_total:{fontSize:25,fontFamily:"微软雅黑",color:"#1b1b1b",fontWeight:600,lineHeight:40},nh_unit:{fontFamily:"微软雅黑",fontSize:16,color:"#1b1b1b",lineHeight:30}},emphasis:{label:{show:!1,fontSize:"40",fontWeight:"bold"}}}}}]}));case 9:case"end":return t.stop()}},t)}))()},init_echarts:function(){var e=this;return Object(r.a)(regeneratorRuntime.mark(function t(){return regeneratorRuntime.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,e.$utils.delay(100);case 2:e.echart||function(){var t=document.getElementById("pie-1");e.echart=echarts.init(t),e.nh_total=e.pie_nhgk.reduce(function(t,e,n){return t+e.value},0);t={tooltip:{trigger:"item"},title:{show:!0,text:"能耗概况",x:"center",y:"85%",textAlign:"center",textStyle:{fontSize:18,fontStyle:"normal",fontWeight:"normal",color:"#1b1b1b"}},legend:{orient:"vertical",right:"3%",top:"25%",itemGap:10},series:[{name:"能耗概况",type:"pie",radius:["50%","70%"],center:["40%","45%"],avoidLabelOverlap:!1,label:{},labelLine:{show:!1},data:[]}]};e.echart.setOption(t),e.get_current_reportPie(),e.$once("hook:beforeDestroy",function(){e.echart&&e.echart.dispose(),e.echart=null})}();case 3:case"end":return t.stop()}},t)}))()}},created:function(){this.init_echarts()}},n=(n("3c44"),n("2877")),o=Object(n.a)(o,void 0,void 0,!1,null,"62cf70f7",null);e.default=o.exports}}]);