(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-07767380"],{"0bd6":function(e,t,a){"use strict";a("1a0a")},"1a0a":function(e,t,a){},"91c1":function(e,t,a){"use strict";a.r(t);var r=a("1da1"),n=(a("96cf"),a("d81d"),a("365c")),l={name:"periodic-review-list",props:{select:{type:String,default:""}},render:function(){var t=arguments[0];return t("div",{class:"periodic-review-list"},[this.table_data.map(function(e){return t("div",{attrs:{width:"0%"},class:"periodic-review-list-global-tap"},[t("global-van-cell-group",{attrs:{inset:!0}},[t("global-van-cell",{attrs:{title:"项目ID",value:e.xmid}}),t("global-van-cell",{attrs:{title:"项目名称",value:e.xm}}),t("global-van-cell",{attrs:{title:"巡查人",value:e.fzr}}),t("global-van-cell",{attrs:{title:"创建时间",value:e.time}}),t("global-van-cell",{attrs:{title:"巡查描述",value:e.ks}})])])})])},data:function(){return{table_data:[]}},watch:{select:{handler:function(){this.opened()},deep:!0,immediate:!0}},methods:{opened:function(){var a=this;return Object(r.a)(regeneratorRuntime.mark(function e(){var t;return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(n.f)({projectName:a.select.text,page:1,size:30});case 2:t=e.sent,console.log(a.select.text),console.log(t.data),a.table_data=t.data.map(function(e,t){return{xm:e.projectName,xmid:e.projectId,time:e.recCreateTime,gsmc:e.companyName,fzr:e.recCreatorName,ks:e.inspectDesc,zt:e.statusName,id:e.projectId}});case 5:case"end":return e.stop()}},e)}))()}}},a=(a("0bd6"),a("2877")),l=Object(a.a)(l,void 0,void 0,!1,null,"70803366",null);t.default=l.exports}}]);