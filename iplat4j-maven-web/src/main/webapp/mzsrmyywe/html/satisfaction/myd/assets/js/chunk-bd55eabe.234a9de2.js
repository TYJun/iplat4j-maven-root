(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-bd55eabe"],{"7eaf":function(t,e,a){},aa42:function(t,e,a){"use strict";a.r(e);var o=a("5530"),i=(a("b0c0"),a("4e82"),a("2f62")),i={name:"global-container",props:{backUrl:{type:String,default:""},backName:{type:String,default:""},left:{type:Boolean,default:!1},right:{type:Boolean,default:!1},title:{type:String,default:""}},computed:Object(o.a)({},Object(i.b)(["other_state_sort","other_state_animate"])),render:function(){var t,e,a,o,i=arguments[0];return i("transition",{attrs:{mode:"out-in",name:null!==(t=this.animate[this.$route.name])&&void 0!==t?t:this.other_state_animate[this.$route.name]}},[i("div",{class:"global-container"},[i("global-van-nav-bar",{attrs:{title:this.title||(null===(t=this.$route)||void 0===t||null===(e=t.meta)||void 0===e?void 0:e.title),sort:null===(e=this.$route)||void 0===e||null===(a=e.meta)||void 0===a?void 0:a.sort,"back-url":this.backUrl,"back-name":this.backName,right:this.right},on:{"click-left":function(){}}},[i("template",{slot:"left"},[this.$slots.left]),i("template",{slot:"right"},[this.$slots.right])]),this.$slots.default,0==(null===(a=this.$route)||void 0===a||null===(o=a.meta)||void 0===o?void 0:o.sort[2])?i("global-tabbar"):null])])},data:function(){return{animate:{"download-resource":"left","hospital-list":"van-fade","user-info":null,"home-page":null}}},methods:{},mounted:function(){},activated:function(){}},a=(a("fe31"),a("2877")),i=Object(a.a)(i,void 0,void 0,!1,null,"adb66e4c",null);e.default=i.exports},fe31:function(t,e,a){"use strict";a("7eaf")}}]);