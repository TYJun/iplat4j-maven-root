(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-533ba38a"],{2782:function(t,e,r){"use strict";r.r(e);var o=r("1da1"),a=(r("96cf"),r("d81d"),r("b0c0"),r("99af"),r("365c")),n={name:"report-statistics-forms-cell",props:{},render:function(){var e=this,r=arguments[0];return r("div",{class:"report-statistics-forms-cell"},[r("global-van-cell-group",[this.report_forms.map(function(t){return r("global-tap",{on:{tap:function(){return e.load(t)}}},[r("global-van-cell",{attrs:{title:t.name,"icon-prefix":"icon",icon:"excel-full"}},[r("template",{slot:"label"},["".concat(t.data," ").concat(t.size)])])])})])])},data:function(){return{type:"",report_forms:[],url:""}},watch:{computed_type:{handler:function(t,e){this.get_current_uploadReport(t)},deep:!0}},computed:{computed_type:function(){return{energyType:this.$route.query.type}}},methods:{load:function(t){location.href=t.loadUrl},get_current_uploadReport:function(){var n=this;return Object(o.a)(regeneratorRuntime.mark(function t(){var e,r;return regeneratorRuntime.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return e=n,t.next=3,Object(a.n)(n.computed_type);case 3:r=t.sent,e.report_forms=r.data.data.report_forms,console.log(r.data.data.report_forms);case 6:case"end":return t.stop()}},t)}))()}},mounted:function(){this.get_current_uploadReport()}},r=(r("5ee9"),r("2877")),n=Object(r.a)(n,void 0,void 0,!1,null,"de8db79a",null);e.default=n.exports},"396e":function(t,e,r){},"5ee9":function(t,e,r){"use strict";r("396e")}}]);