(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-e78553ca"],{"0556":function(t,e,a){},"441e":function(t,e,a){"use strict";a("0556")},6600:function(t,e,a){"use strict";a.r(e);var l=a("5530"),o=a("2f62"),o={name:"global-text-dialog",props:{value:{type:Boolean,default:!1},topText:{type:String,default:"提示"},cancelText:{type:String,default:"取消"},contentText:{type:String,default:"是否确认退出?"},confirmText:{type:String,default:"确定"}},computed:Object(l.a)({},Object(o.b)(["other_state_sort"])),render:function(){var t=this,e=arguments[0];return e("global-van-dialog",{attrs:{value:this.value}},[e("template",{slot:"title"},[e("div",{class:"global-text-dialog-top"},[this.topText])]),e("template",{slot:"default"},[e("div",{class:"global-text-dialog-content"},[this.contentText]),e("div",{class:"global-text-dialog-bottom"},[e("global-tap",{class:"global-tap",on:{tap:function(){return t.$emit("cancel")}}},[e("global-van-button",{attrs:{plain:!0,hairline:!0,block:!0}},[this.cancelText])]),e("global-tap",{class:"global-tap",on:{tap:function(){return t.$emit("confirm")}}},[e("global-van-button",{attrs:{block:!0,type:"info"}},[this.confirmText])])])])])},data:function(){return{}},methods:{},mounted:function(){}},a=(a("441e"),a("2877")),o=Object(a.a)(o,void 0,void 0,!1,null,"5fb0e9f0",null);e.default=o.exports}}]);