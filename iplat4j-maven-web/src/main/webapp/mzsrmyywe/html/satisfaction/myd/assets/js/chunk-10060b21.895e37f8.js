(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-10060b21"],{2532:function(t,e,i){"use strict";var n=i("23e7"),a=i("e330"),s=i("5a34"),o=i("1d80"),c=i("577e"),i=i("ab13"),r=a("".indexOf);n({target:"String",proto:!0,forced:!i("includes")},{includes:function(t){return!!~r(c(o(this)),c(s(t)),1<arguments.length?arguments[1]:void 0)}})},"5a34":function(t,e,i){var n=i("da84"),a=i("44e7"),s=n.TypeError;t.exports=function(t){if(a(t))throw s("The method doesn't accept regular expressions");return t}},"81bf":function(t,e,i){"use strict";i.r(e);var n=i("1da1"),a=(i("96cf"),i("a9e3"),i("d81d"),i("99af"),i("a434"),i("caad"),i("2532"),i("d3b7"),i("b64b"),i("365c")),s={name:"questionnaire-investigation-radio",props:{page:{type:Number|String,default:0}},render:function(){function t(t,e){return a("div",{class:"questionnaire-investigation-radio-list-item-content"},[a("global-van-checkbox-group",{class:"2"==e.pointType&&"display-block",attrs:{direction:"horizontal",max:"2"},model:{value:e.check_list,callback:function(t){n.$set(e,"check_list",t)}}},[t.map(function(t){return a("global-van-checkbox",{class:("4"==e.pointType||"1"==e.pointType)&&"point-radio",attrs:{name:t.value},on:{click:function(){return n.checkbox_click(e)}}},[t.label])})])])}var e,i,n=this,a=arguments[0],s=[t,t,t,function(t,e){return a("div",{class:"questionnaire-investigation-radio-list-item-content"},[a("global-van-checkbox-group",{attrs:{direction:"horizontal",max:"2"},model:{value:(i=e).check_list,callback:function(t){n.$set(i,"check_list",t)}}},[t.map(function(t){return a("global-van-checkbox",{attrs:{name:t.value},on:{click:function(){return n.checkbox_click(i)}}},[t.label])})]),a("global-van-field",{attrs:{readonly:"-1"==i.check_list[0],rows:"3",autosize:!0,label:"",type:"textarea",placeholder:"请输入"},model:{value:i.text,callback:function(t){n.$set(i,"text",t)}}})]);var i},t];return a("div",{class:"questionnaire-investigation-radio"},[a("div",{class:"questionnaire-investigation-radio-title"},[a("global-van-cell-group",{attrs:{inset:!0}},[a("global-van-cell",{attrs:{title:this.page==this.questionnaire_list.length?this.advice:null===(e=this.questionnaire_list[this.page])||void 0===e?void 0:e.projectName,value:""}})])]),a("div",{class:"questionnaire-investigation-radio-list"},[null===(e=this.questionnaire_list[this.page])||void 0===e||null===(i=e.sqProjectInstance)||void 0===i?void 0:i.map(function(t,e){return a("div",{class:"questionnaire-investigation-radio-list-item"},[a("div",{class:"questionnaire-investigation-radio-list-item-title"},["".concat(t.orderNo," ").concat(t.instanceName)]),s[t.pointType](t.radioList,t,e)])}),a("div",{class:"questionnaire-investigation-radio-list-item"},[a("div",{class:"questionnaire-investigation-radio-list-item-title"},[this.comments_suggestions_title]),a("global-van-field",{attrs:{rows:"3",autosize:!0,label:"",type:"textarea",placeholder:"请输入"},model:{value:n.comments_suggestions,callback:function(t){n.comments_suggestions=t}}})])])])},data:function(){return{questionnaire_list:[],comments_suggestions_title:"意见或建议",comments_suggestions:"",advice:"希望对本次问卷提出意见"}},watch:{page:function(){this.$nextTick(function(){document.querySelector(".questionnaire-investigation-radio-list").scrollTop=0})}},mounted:function(){window.scrollTo(0,0)},methods:{checkbox_click:function(t){var i=this,e=this.questionnaire_list[this.page].sqProjectInstance;t.check_list[1]&&(t.check_list[0]=t.check_list[1],t.check_list.splice(1,1)),t.first_question?t.check_list.includes("-1")?e.map(function(t,e){t.text="",t.check_list[0]?i.questionnaire_list[i.page].sqProjectInstance[e].check_list=["-1"]:t.check_list.push("-1")}):e.map(function(t){return t.check_list.splice(t.check_list.indexOf("-1"),1)}):e.every(function(t,e){return!(0<e)||"-1"==t.check_list[0]})?e[0].check_list.push("-1"):(this.questionnaire_list[this.page].sqProjectInstance[0].check_list=[],t.text=""),this.$emit("complete_check",this.questionnaire_list)},get_questionnaire_list:function(){var i=this;return Object(n.a)(regeneratorRuntime.mark(function t(){var e;return regeneratorRuntime.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return e=JSON.parse(Object.keys(i.$route.query)[0]),t.next=3,Object(a.a)({prames:{billNo:e.billNo,ID:e.standardId,workNo:localStorage.getItem("workNo")}});case 3:(e=t.sent).attr.param.map(function(t){var e;t.advice="",null===(e=t.sqProjectInstance)||void 0===e||e.unshift({orderNo:"",pointType:0,instanceName:"该项目是否涉及?",first_question:!0}),t.sqProjectInstance.map(function(t){t.orderNo=t.orderNo?"".concat(t.orderNo,"."):""}),null==t||t.sqProjectInstance.map(function(t,e){t.text="",t.check_list=[],t.selectValue&&(0<e&&"0"==t.selectValue?t.check_list.push("-1"):t.check_list.push(t.selectValue)),null!==(e=t.radioList)&&void 0!==e||(t.radioList=[]),t.radioList.unshift({label:"不涉及",value:"-1"})})}),e.success&&(i.questionnaire_list=e.attr.param),i.questionnaire_list=e.attr.param,i.$emit("complete",i.questionnaire_list);case 8:case"end":return t.stop()}},t)}))()}},computed:{},created:function(){this.get_questionnaire_list()}},i=(i("a061"),i("2877")),s=Object(i.a)(s,void 0,void 0,!1,null,"8e460eb6",null);e.default=s.exports},a061:function(t,e,i){"use strict";i("eaa5")},ab13:function(t,e,i){var n=i("b622")("match");t.exports=function(e){var i=/./;try{"/./"[e](i)}catch(t){try{return i[n]=!1,"/./"[e](i)}catch(t){}}return!1}},eaa5:function(t,e,i){}}]);