(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-d7b4c698"],{"69fa":function(t,e,n){n("a29f")},c6aa:function(t,e,n){"use strict";n.r(e),n("68ef"),n("a9e3"),n("99af"),n("4de4"),n("d3b7");var a=n("d282"),r=n("9884"),s=n("b1d2"),i=Object(a.a)("collapse"),a=i[0],o=i[1],c=a({mixins:[Object(r.b)("vanCollapse")],props:{accordion:Boolean,value:[String,Number,Array],border:{type:Boolean,default:!0}},methods:{switch:function(e,t){this.accordion||(e=t?this.value.concat(e):this.value.filter(function(t){return t!==e})),this.$emit("change",e),this.$emit("input",e)}},render:function(){var t;return(0,arguments[0])("div",{class:[o(),((t={})[s.e]=this.border,t)]},[this.slots()])}}),r=n("2638"),u=n.n(r),l=n("5530"),r=(n("69fa"),{name:"global-van-collapse",props:{},render:function(){return(0,arguments[0])(c,u()([{},{attrs:Object(l.a)({},this.attrs)},{},{on:this.$listeners}]),[this.$slots.default])},computed:{attrs:function(){return Object(l.a)(Object(l.a)({},this.$attrs),this.$props)}},inheritAttrs:!1,data:function(){return{}}}),n=n("2877"),r=Object(n.a)(r,void 0,void 0,!1,null,"7628d3f7",null);e.default=r.exports}}]);