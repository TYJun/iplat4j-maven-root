(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-3fa0df16"],{"551a":function(t,n,e){"use strict";e.r(n),e("a9e3"),e("d81d"),e("b0c0");var a={name:"general-situation-tabs",props:{value:{type:String|Number,default:""},beforeChange:{type:Function,default:function(){}}},render:function(){var e=this,n=arguments[0];return n("div",{class:"general-situation-tabs"},[n("global-van-tabs",{attrs:{value:this.value,"before-change":function(t){return e.beforeChange(t)},"title-active-color":"#409eff",color:"#409eff",animated:!0},on:{change:function(t,n){return e.change(t,n)}}},[this.tabs.map(function(t){return n("global-van-tab",{attrs:{name:t.name}},[n("template",{slot:"title"},[n("div",{class:"general-situation-tabs-item"},[n("div",[n("global-van-icon",{attrs:{"class-prefix":"icon",size:"18",name:t.icon}})]),n("div",[t.text])])])])})])])},data:function(){return{tabs:[{icon:"shui",text:"水",name:0},{icon:"fl-dian",text:"电",name:1},{icon:"ranqi1",text:"燃气",name:2}]}},methods:{change:function(t,n){this.$emit("change",t,n)}}},e=(e("a73c"),e("2877")),a=Object(e.a)(a,void 0,void 0,!1,null,"433f62b2",null);n.default=a.exports},a73c:function(t,n,e){"use strict";e("ed03")},ed03:function(t,n,e){}}]);