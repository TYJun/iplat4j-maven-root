(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-76179b56"],{"239e":function(t,e,a){},a76b:function(t,e,a){"use strict";a("239e")},aa42:function(t,e,a){"use strict";a.r(e);var i=a("5530"),n=(a("b0c0"),a("4e82"),a("2f62")),n={name:"global-container",props:{query:{type:Object,default:function(){return{}}},backUrl:{type:String,default:""},backName:{type:String,default:""},left:{type:Boolean,default:!1},right:{type:Boolean,default:!1},title:{type:String,default:""}},computed:Object(i.a)({},Object(n.b)(["other_state_sort","other_state_animate"])),render:function(){var t,e,a,i,n=arguments[0];return n("transition",{attrs:{mode:"out-in",name:null!==(t=this.animate[this.$route.name])&&void 0!==t?t:this.other_state_animate[this.$route.name]}},[n("div",{class:"global-container"},[n("global-van-nav-bar",{attrs:{title:this.title||(null===(t=this.$route)||void 0===t||null===(e=t.meta)||void 0===e?void 0:e.title),sort:null===(e=this.$route)||void 0===e||null===(a=e.meta)||void 0===a?void 0:a.sort,"back-url":this.backUrl,"back-name":this.backName,query:this.query,right:this.right},on:{"click-left":function(){}}},[n("template",{slot:"left"},[this.$slots.left]),n("template",{slot:"right"},[this.$slots.right])]),this.$slots.default,0==(null===(a=this.$route)||void 0===a||null===(i=a.meta)||void 0===i?void 0:i.sort[2])?n("global-tabbar"):null])])},data:function(){return{animate:{"download-resource":"left","hospital-list":"van-fade","user-info":null,"home-page":null}}},methods:{},mounted:function(){},activated:function(){}},a=(a("a76b"),a("2877")),n=Object(a.a)(n,void 0,void 0,!1,null,"3b16adc8",null);e.default=n.exports}}]);